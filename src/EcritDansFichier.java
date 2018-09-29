/*
 * EcritDansFichier.java
 *
 * Created on 30 november 2006, 10:28
 *
 * @author Yann <rockyracer@mailfence.com>
 */

import java.io.FileWriter;
import java.io.IOException;

class EcritDansFichier {

    // <editor-fold defaultstate="collapsed" desc="Déclarations des variables">
    private double vit_plus_rapide = 0;
    private double vit_unik_plus_rapide = 0;
    private double vit_moins_rapide = 0;
    private double vit_unik_moins_rapide = 222;
    private double tot_vit_plus_rapide = 0;
    private double tot_vit_moins_rapide = 0;
    private double vitesse_moyenne = 0;
    private int nb = 0, depassements, nb_max_voit = 0;
    // </editor-fold>

    private double round(double what, int howmuch) {
        return (double) ((int) (what * Math.pow(10, howmuch) + .5)) / Math.pow(10, howmuch);
    }

    void Ecrire(String nomFichier) {
        try {
            FileWriter fichier = new FileWriter(nomFichier);
            fichier.write("########################################" + "\r\n");
            fichier.write("###### STATISTIQUES DE LA SESSION ########" + "\r\n");
            fichier.write("########################################" + "\r\n");
            fichier.write("\r\n" + "Paramètres autoroute :");
            fichier.write("\r\n     - longueur : " + Main.autoroute.longueur() + " m");
            fichier.write("\r\n     - nombre de voies : " + Main.autoroute.nbVoies() + "\r\n");
            for (int voie = 0; voie < Main.autoroute.nbVoies(); voie++) {
                for (int i = 0; i < Main.autoroute.longueur(); i++) {
                    if (Main.autoroute.existeVehicule(voie, i) != null) {
                        nb++;
                        vitesse_moyenne += Main.autoroute.existeVehicule(voie, i).vitesse_courante * 222;
                        depassements += Main.autoroute.existeVehicule(voie, i).NbDepassementsTotal;
                        vit_plus_rapide = Main.autoroute.VehiculePlusRapide().vitesse_courante;
                        tot_vit_plus_rapide += Main.autoroute.VehiculePlusRapide().vitesse_courante;
                        vit_moins_rapide = Main.autoroute.VehiculePlusLent().vitesse_courante;
                        tot_vit_moins_rapide += Main.autoroute.VehiculePlusLent().vitesse_courante;
                        if (vit_plus_rapide > vit_unik_plus_rapide) {
                            vit_unik_plus_rapide = vit_plus_rapide;
                        }
                        //On ne prend pas on compte la vitesse minimale dans les 60 1er mètres car la voiture est encore en accélération
                        if (vit_moins_rapide < vit_unik_moins_rapide && i > 60) {
                            vit_unik_moins_rapide = vit_moins_rapide;
                        }
                        if (Main.autoroute.NbVehicules() > nb_max_voit) {
                            nb_max_voit = Main.autoroute.NbVehicules();
                        }
                    }
                }
            }
            fichier.write("\r\n TOTAL SESSION :");
            fichier.write("\r\n     - durée de la session : " + round((System.currentTimeMillis() - Main.debut) / 1000, 2) + " s");
            if (nb != 0)
                fichier.write("\r\n     - vitesse moyenne tout véhicules confondus : " + round(vitesse_moyenne / nb, 2) + " km/h");
            fichier.write("\r\n     - vitesse du véhicule le plus lent après 60m : " + round(vit_unik_moins_rapide * 222, 2) + " km/h");
            fichier.write("\r\n     - vitesse du véhicule le plus rapide : " + round(vit_unik_plus_rapide * 222, 2) + " km/h");
            fichier.write("\r\n     - nombre maximum de voitures en même temps : " + round(nb_max_voit, 2));
            fichier.write("\r\n \r\n ACTUELLEMENT (ou lorsque la session s'est arrêtée) :");
            fichier.write("\r\n     - nombre de véhicules en route :" + Main.autoroute.NbVehicules());
            fichier.write("\r\n     - vitesse du véhicule actuellement le plus rapide : " + round(vit_plus_rapide * 222, 2) + " km/h");
            fichier.write("\r\n     - vitesse du véhicule actuellement le plus lent : " + round(vit_moins_rapide * 222, 2) + " km/h");
            if (nb != 0)
                fichier.write("\r\n     - vitesse moyenne du véhicule actuellement le plus lent : " + round(tot_vit_moins_rapide * 222 / nb, 2) + " km/h");
            if (nb != 0)
                fichier.write("\r\n     - vitesse moyenne du véhicule actuellement le plus rapide : " + round((tot_vit_plus_rapide * 222) / nb, 2) + " km/h");
            fichier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
