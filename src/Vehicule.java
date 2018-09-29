/*
 * Vehicule.java
 *
 * Created on 15 november 2006, 17:57
 *
 * @author Yann <rockyracer@mailfence.com>
 */

import java.awt.Color;

public class Vehicule {

    static int K = 120;
    int voie, NbDepassements, NbDepassementsTotal;
    private double distance_securite;
    public double vitesse_courante, vitesse_croisiere, position, acceleration;
    boolean temoin = false;
    Color couleur;
    private Autoroute autoroute;

    //Constructeur de Véhicules
    Vehicule(int VehiculeVoie, double VehiculePosition, Autoroute VehiculeRoute) {
        voie = VehiculeVoie;
        position = VehiculePosition;
        vitesse_croisiere = VitesseCroisiereAleatoire();
        vitesse_courante = 0;
        acceleration = AccelerationAleatoire();
        couleur = CouleurAleatoire();
        autoroute = VehiculeRoute;
        distance_securite = K * vitesse_courante;
        temoin = false;
        NbDepassements = 0;
        NbDepassementsTotal = 0;
        autoroute.list_veh_route[voie][(int) position] = this;
    }

    //Permet de créer un nouveau Véhicule
    Vehicule Vehicule(int SVehiculeVoie, double SVehiculePosition, Autoroute SVehiculeRoute) {
        return new Vehicule(SVehiculeVoie, SVehiculePosition, SVehiculeRoute);
    }

    //Permet de créer un véhicule aléatoire
    Vehicule VehiculeAleatoire(Autoroute VehiculeAleatoireRoute) {
        return new Vehicule((int) (Math.random() * VehiculeAleatoireRoute.nbVoies()), 0, VehiculeAleatoireRoute);
    }

    //Permet de générer aléatoirement une couleur
    private Color CouleurAleatoire() {
        Color col = Color.blue;
        double random = Math.random();
        if (random < 0.2) col = Color.green;
        if (random >= 0.2 && random < 0.4) col = Color.blue;
        if (random >= 0.4 && random < 0.6) col = Color.yellow;
        if (random >= 0.6 && random < 0.8) col = Color.magenta;
        if (random > 0.8) col = Color.red;
        return col;
    }

    //Permet de générer aléatoirement une accélération
    private double AccelerationAleatoire() {
        double acc = 0;
        acc = Math.random() / 100;
        if (acc <= 0.002) acc = 0.002;
        if (acc > 0.007) acc = 0.007;
        return acc;
    }

    //Permet de générer aléatoirement une vitesse de croisière
    private double VitesseCroisiereAleatoire() {
        double vta = 0;
        vta = Math.random();
        if (vta <= 0.45) vta = 0.45;
        if (vta > 0.8) vta = 0.8;
        return vta;
    }

    //Permet de générer aléatoirement une distance de sécurité (paramètre K)
    public double DistanceSecuriteAleatoire() {
        double dist_secu = 0;
        dist_secu = Math.random() * 100;
        if (dist_secu < 50) dist_secu = 50;
        if (dist_secu > 200) dist_secu = 200;
        return dist_secu;
    }

    //Permet de faire avancer un véhicule
    private void AvanceVehicule() {
        autoroute.list_veh_route[voie][(int) position] = null;
        position += vitesse_courante;
        distance_securite = K * vitesse_courante;
        if (position < autoroute.longueur()) autoroute.list_veh_route[voie][(int) position] = this;
    }

    //Permet de faire accélérer un véhicule
    private void AccelereVehicule() {
        if (vitesse_courante < vitesse_croisiere) {
            vitesse_courante += acceleration;
        } else {
            vitesse_courante = vitesse_croisiere;
        }
        AvanceVehicule();
    }

    //permet de savoir si il y à un véhicule dans la distance de sécurité
    private boolean existeVehiculeDevant() {
        boolean existeVehiculeDevant = false;
        for (int i = (int) position + 1; i < (int) position + distance_securite && i < autoroute.longueur(); i++)
            if (autoroute.existeVehicule(voie, i) != null)
                existeVehiculeDevant = true;
        return existeVehiculeDevant;
    }

    //permet de savoir si il on peut se rabattre après avoir doublé
    private boolean existeVehiculeADroite() {
        boolean existeVehiculeADroite = false;
        for (int i = 0; i < autoroute.longueur(); i++)
            if (autoroute.existeVehicule(voie + 1, i) != null)
                if (((int) position < autoroute.existeVehicule(voie + 1, i).position + distance_securite) && ((int) position > autoroute.existeVehicule(voie + 1, i).position - distance_securite))
                    existeVehiculeADroite = true;
        return existeVehiculeADroite;
    }

    //Permet de savoir si aucune voiture gène et si on peut doubler
    private boolean existeVehiculeAGauche() {
        boolean existeVehiculeAGauche = false;
        for (int i = 0; i < autoroute.longueur(); i++)
            if (autoroute.existeVehicule(voie - 1, i) != null) {
                if (((int) position < autoroute.existeVehicule(voie - 1, i).position + distance_securite) && ((int) position > autoroute.existeVehicule(voie - 1, i).position - distance_securite))
                    existeVehiculeAGauche = true;
            }
        return existeVehiculeAGauche;
    }

    public void RouleVehicule() {

        //Si le véhicule sort de la longueur de l'autoroute, on l'enlève
        if (this.position > Main.autoroute.longueur()) Main.autoroute.enleveVehicule(this);

        boolean existeVehiculeDevant = existeVehiculeDevant();
        boolean existeVehiculeADroite = existeVehiculeADroite();
        boolean existeVehiculeAGauche = existeVehiculeAGauche();

        //si il y a un véhicule dans la distance de sécurité
        if (existeVehiculeDevant) {
            //si on est pas sur la voie la plus à gauche, on peut essayer de doubler
            if (voie > 0) {
                if (existeVehiculeAGauche) //si il y a un véhicule à gauche, on ralenti
                    vitesse_courante = 0.94 * vitesse_courante;
                else { //si il n'y a pas de véhicule à gauche, on double
                    autoroute.enleveVehicule(this);
                    voie--;
                    position++;
                    autoroute.ajouteVehicule(this);
                }
            }
            //on est sur la voie la plus à gauche, on ne peut pas doubler donc on ralenti
            else vitesse_courante = 0.94 * vitesse_courante;
        }
        //si il n'y a pas de véhicule devant, qu'on est pas sur la voie la plus à droite, et que la voie de 
        //droite est libre, on peut se rabattre
        if ((!existeVehiculeDevant) && (voie < autoroute.nbVoies() - 1) && (!existeVehiculeADroite)) {
            autoroute.enleveVehicule(this);
            //Le dépassement est terminé, on incrémente le nombre de dépassements
            if (this.temoin) NbDepassements++;
            voie++;
            NbDepassementsTotal++;
            autoroute.ajouteVehicule(this);
        }
        AccelereVehicule();
    }
}
