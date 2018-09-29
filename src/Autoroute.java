/*
 * Autoroute.java
 *
 * Created on 15 november 2006, 17:57
 *
 * @author Yann <rockyracer@mailfence.com>
 */

import java.util.Vector;

class Autoroute {

    //la route qui est en fait un tableau de véhicules
    Vehicule[][] list_veh_route;

    //constructeur : créer un nouvel objet Autoroute
    Autoroute(int AutorouteNbVoies, int AutorouteLongueur) {
        list_veh_route = new Vehicule[AutorouteNbVoies][AutorouteLongueur];
    }

    //retourne la longueur de l'autoroute
    int longueur() {
        return list_veh_route[0].length;
    }

    //retourne le nombre de voies de l'autoroute
    int nbVoies() {
        return list_veh_route.length;
    }

    //retourne true si on est a l'interieur de la route
    private boolean interieur(int interieurVoie, int interieurPosition) {
        return interieurVoie >= 0 && interieurVoie < nbVoies() && interieurPosition >= 0 && interieurPosition < longueur();
    }

    //retourne le véhicule présent sur la voie existeVehiculeVoie à la position existeVehiculePosition
    Vehicule existeVehicule(int existeVehiculeVoie, int existeVehiculePosition) {
        if (!interieur(existeVehiculeVoie, existeVehiculePosition)) return null;
        return list_veh_route[existeVehiculeVoie][existeVehiculePosition];
    }

    //enleve un véhicule de l'autoroute
    void enleveVehicule(Vehicule enleveVehiculeAuto) {
        if (existeVehicule(enleveVehiculeAuto.voie, (int) enleveVehiculeAuto.position) != null)
            list_veh_route[enleveVehiculeAuto.voie][(int) enleveVehiculeAuto.position] = null;
    }

    //ajoute un véhicule sur l'autoroute
    void ajouteVehicule(Vehicule ajouteVehiculeAuto) {
        if (!interieur(ajouteVehiculeAuto.voie, (int) ajouteVehiculeAuto.position)) return;
        list_veh_route[ajouteVehiculeAuto.voie][(int) ajouteVehiculeAuto.position] = ajouteVehiculeAuto;
    }

    //retourne un vecteur contenant tout les véhicules présents sur l'autoroute
    private Vector<Vehicule> listeVehicules() {
        Vector<Vehicule> liste = new Vector<Vehicule>();
        for (int i = 0; i < nbVoies(); i++)
            for (int j = 0; j < longueur(); j++)
                if (existeVehicule(i, j) != null)
                    liste.addElement(existeVehicule(i, j));
        return liste;
    }

    //Permet de savoir si un véhicule se trouve sur les 28 premier 'mètres' de la route (longueur d'une voiture)
    boolean existeVehiculeDebut() {
        boolean existeVehiculeDebut = true;
        for (int voie = 0; voie < Main.autoroute.nbVoies(); voie++) {
            for (int i = 0; i < 28; i++) {
                if (Main.autoroute.existeVehicule(voie, i) != null)
                    existeVehiculeDebut = false;
            }
        }
        return existeVehiculeDebut;
    }

    //Retourne le véhicule le plus rapide
    Vehicule VehiculePlusRapide() {
        Vector<Vehicule> listeVehicules = listeVehicules();
        Vehicule veh1 = listeVehicules.elementAt(0);
        Vehicule vehicule = listeVehicules.elementAt(0);
        double vit_crois = 0, vit_crois_temp = 0;
        for (int i = 0; i < listeVehicules.size(); i++) {
            veh1 = listeVehicules.elementAt(i);
            vit_crois_temp = veh1.vitesse_croisiere;
            if (vit_crois_temp >= vit_crois) {
                vit_crois = vit_crois_temp;
                vehicule = veh1;
            }
        }
        return vehicule;
    }

    //Retourne le véhicule le plus lent
    Vehicule VehiculePlusLent() {
        Vector<Vehicule> listeVehicules = listeVehicules();
        Vehicule veh1 = listeVehicules.elementAt(0);
        Vehicule vehicule = listeVehicules.elementAt(0);
        double vit_crois = 222, vit_crois_temp = 0;
        for (int i = 0; i < listeVehicules.size(); i++) {
            veh1 = listeVehicules.elementAt(i);
            vit_crois_temp = veh1.vitesse_croisiere;
            if (vit_crois_temp <= vit_crois) {
                vit_crois = vit_crois_temp;
                vehicule = veh1;
            }
        }
        return vehicule;
    }

    //Retourne le nombre de véhicule
    int NbVehicules() {
        Vector<Vehicule> listeVehicules = listeVehicules();
        int nb = 0;
        for (int i = 0; i < listeVehicules.size(); i++) {
            nb++;
        }
        return nb;
    }

    //enleve tout les véhicule de l'autoroute
    void clear() {
        list_veh_route = new Vehicule[list_veh_route.length][list_veh_route[0].length];
    }

}
