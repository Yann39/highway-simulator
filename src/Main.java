/*
 * Main.java
 *
 * Created on 15 november 2006, 17:56
 *
 * @author Yann <rockyracer@mailfence.com>
 */

public class Main {

    public Main() {
    }

    public static Autoroute autoroute;
    private static Vehicule toto;
    static int nb_voit = 0;
    static long debut = System.currentTimeMillis();

    public static void main(String[] args) {
        autoroute = new Autoroute(3, 1000);
        Fenetre f = new Fenetre(autoroute);

        boolean ajout;

        EcritDansFichier fichier = new EcritDansFichier();
        EcritVehiculeTemoin fichier_temoin = new EcritVehiculeTemoin();

        while (true) {
            if (Math.random() < 0.01) {
                //On vérifie si il y a une voiture sur les 28 1er "mètres" (longueur d'une voiture)
                ajout = autoroute.existeVehiculeDebut();
                if (ajout) {
                    toto = new Vehicule(autoroute.nbVoies() - 1, 10, autoroute);
                    autoroute.ajouteVehicule(toto.VehiculeAleatoire(autoroute));
                    nb_voit++;
                }
            }
            for (int voie = 0; voie < autoroute.nbVoies(); voie++)
                for (int i = 0; i < autoroute.longueur(); i++)
                    if (autoroute.existeVehicule(voie, i) != null) {
                        if (Main.autoroute.existeVehicule(voie, i).temoin)
                            fichier_temoin.Ecrire("VehiculeTemoin.txt");
                        autoroute.existeVehicule(voie, i).RouleVehicule();
                    }
            fichier.Ecrire("Session.txt");

            try {
                f.repaint();
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
