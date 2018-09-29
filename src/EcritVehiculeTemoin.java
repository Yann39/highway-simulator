/*
 * EcritDansFichier.java
 *
 * Created on 30 november 2006, 10:28
 *
 * @author Yann <rockyracer@mailfence.com>
 */

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;

class EcritVehiculeTemoin {

    // <editor-fold defaultstate="collapsed" desc="Déclarations des variables">
    private long vitesse_moyenne = 0;
    private String couleur_voiture = "";
    private int nb = 0;
    // </editor-fold>

    private double round(double what, int howmuch) {
        return (double) ((int) (what * Math.pow(10, howmuch) + .5)) / Math.pow(10, howmuch);
    }

    void Ecrire(String nomFichier) {
        try {
            FileWriter fichier = new FileWriter(nomFichier);
            fichier.write("########################################" + "\r\n");
            fichier.write("##### STATISTIQUES VEHICULE TEMOIN ######" + "\r\n");
            fichier.write("########################################" + "\r\n");
            for (int voie = 0; voie < Main.autoroute.nbVoies(); voie++)
                for (int i = 0; i < Main.autoroute.longueur(); i++)
                    if (Main.autoroute.existeVehicule(voie, i) != null) {
                        if (Main.autoroute.existeVehicule(voie, i).temoin) {
                            nb++;
                            vitesse_moyenne += Main.autoroute.existeVehicule(voie, i).vitesse_courante * 222;
                            if (Main.autoroute.existeVehicule(voie, i).couleur == Color.red)
                                couleur_voiture = "rouge";
                            if (Main.autoroute.existeVehicule(voie, i).couleur == Color.green)
                                couleur_voiture = "vert";
                            if (Main.autoroute.existeVehicule(voie, i).couleur == Color.blue)
                                couleur_voiture = "bleu";
                            if (Main.autoroute.existeVehicule(voie, i).couleur == Color.yellow)
                                couleur_voiture = "jaune";
                            if (Main.autoroute.existeVehicule(voie, i).couleur == Color.magenta)
                                couleur_voiture = "mauve";
                            fichier.write("\r\n     - couleur voiture : " + couleur_voiture);
                            fichier.write("\r\n     - acceleration voiture : " + Main.autoroute.existeVehicule(voie, i).acceleration * 1000 + " m/s²");
                            fichier.write("\r\n     - vitesse croisiere voiture : " + Main.autoroute.existeVehicule(voie, i).vitesse_croisiere * 222 + " km/h");
                            fichier.write("\r\n     - vitesse moyenne voiture : " + vitesse_moyenne / nb + " km/h");
                            fichier.write("\r\n     - distance parcourue : " + round((Main.autoroute.existeVehicule(voie, i).position), 2) + " m");
                            fichier.write("\r\n     - nombre de dépassements : " + Main.autoroute.existeVehicule(voie, i).NbDepassements);
                        }
                    }
            fichier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
