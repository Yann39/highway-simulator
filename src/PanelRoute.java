/*
 * PanelRoute.java
 *
 * Created on 7 december 2006, 18:38
 *
 * @author Yann <rockyracer@mailfence.com>
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.event.EventListenerList;

public class PanelRoute extends javax.swing.JPanel {

    PanelRoute() {
        initComponents();
        setOpaque(true);
        texture_herbe = CreerTexture("img/herbe.jpg");
        texture_asphalte = CreerTexture("img/asphalte.jpg");
        this.setSize(Main.autoroute.longueur() + 60, 700);
        this.addInterfaceCommande(Fenetre.PanelC);
    }

    private double round(double what, int howmuch) {
        return (double) ((int) (what * Math.pow(10, howmuch) + .5)) / Math.pow(10, howmuch);
    }

    private InterfaceCommande[] getInterfaceCommande() {
        return listeners.getListeners(InterfaceCommande.class);
    }

    private void addInterfaceCommande(InterfaceCommande listener) {
        listeners.add(InterfaceCommande.class, listener);
    }

    public void removeInterfaceCommande(InterfaceCommande listener) {
        listeners.remove(InterfaceCommande.class, listener);
    }

    public Dimension getPreferredSize() {
        return new Dimension(Main.autoroute.longueur() + 70, 100);
    }

    private TexturePaint CreerTexture(String UrlImage) {
        //Création de la BufferedImage
        ImageIcon image_icon = new ImageIcon(UrlImage);
        BufferedImage buff_img = new BufferedImage(image_icon.getIconWidth(), image_icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        //initialisation des images
        Graphics2D g2d_img = buff_img.createGraphics();
        g2d_img.drawImage(image_icon.getImage(), 0, 0, this);
        //création du rectangle aux dimension de la texture de fond
        Shape img_rect = new Rectangle2D.Double(0.0, 0.0, buff_img.getWidth(), buff_img.getHeight());
        //Création des TexturePaint
        TexturePaint texture = new TexturePaint(buff_img, img_rect.getBounds2D());
        return texture;
    }

    private Graphics2D DessinerTextures(Graphics g) {
        //Création d'un graphics 2d
        Graphics2D g2d = (Graphics2D) g;
        //dessine les rectangles remplis avec les textures
        g2d.setPaint(texture_herbe);
        g2d.fillRect(0, 0, Main.autoroute.longueur() + 1000, 159);
        g2d.fillRect(0, 159 + 20 + 20 * Main.autoroute.nbVoies(), Main.autoroute.longueur() + 1000, this.getHeight() - 161 - 20 - 20 * Main.autoroute.nbVoies());
        g2d.setPaint(texture_asphalte);
        g2d.fillRect(0, 159, Main.autoroute.longueur() + 1000, 20 * Main.autoroute.nbVoies() + 20);
        //on affiche un carré d'herbe sale
        ImageIcon img_herbe_sale = new ImageIcon("img/herbe_sale.jpg");
        Image image_herbe_sale = img_herbe_sale.getImage();
        ImageObserver image_herbe_sale_obs = img_herbe_sale.getImageObserver();
        g2d.drawImage(image_herbe_sale, 10, 143 + 36 + 20 * Main.autoroute.nbVoies() + 50, image_herbe_sale_obs);
        return g2d;
    }

    private Graphics2D DessinerRoute(Graphics g) {
        //Création d'un graphics 2d
        Graphics2D g2d = (Graphics2D) g;
        //lignes orange de chaque cotés
        g2d.setColor(Color.orange);
        g2d.fillRect(0, 168, Main.autoroute.longueur() + 1000, 2); //coté gauche
        g2d.fillRect(0, 168 + 20 * Main.autoroute.nbVoies(), Main.autoroute.longueur() + 1000, 2); //coté droit
        //pointillés sur la route
        g2d.setColor(Color.white);
        for (int i = 0; i < Main.autoroute.nbVoies() - 1; i++)
            for (int j = 0; j <= Main.autoroute.longueur() + 1000; j = j + 12)
                g2d.fillRect(j + 2, 169 + 20 + i * 20, 6, 2);
        //panneaux indiquant les distances
        Font font = new Font("petit", Font.PLAIN, 9);
        g2d.setFont(font);
        int dist = 0, deb = 0;
        for (int i = 20; i <= Main.autoroute.longueur() + 20; i = i + 100) {
            g2d.setColor(Color.black);
            //On agrandi le panneau suivant la longueur à inscrire dedant
            if (dist >= 1000) {
                deb = 23;
                if (dist >= 10000) {
                    deb = 28;
                }
            } else {
                deb = 18;
            }
            g2d.drawRect(i - 1, 129, deb, 10);
            g2d.setColor(Color.lightGray);
            g2d.fillRect(i, 130, deb - 1, 9);
            g2d.setColor(Color.black);
            g2d.drawLine(8 + i, 130 + 10, 8 + i, 140 + 10 + 8);
            g2d.drawString("" + dist, i + 1, 138);
            dist = dist + 100;
        }
        return g2d;
    }

    public Graphics2D DessinerPeages(Graphics g) {
        //Création d'un graphics 2d
        Graphics2D g2d = (Graphics2D) g;
        //Péage d'entrée
        g2d.setColor(Color.gray);
        g2d.fillRect(1, 150, 16, 38 + 20 * Main.autoroute.nbVoies());
        g2d.fillRect(17, 155, 11, 28 + 20 * Main.autoroute.nbVoies());
        g2d.setColor(Color.black);
        g2d.drawRect(1, 150, 16, 38 + 20 * Main.autoroute.nbVoies());
        g2d.drawRect(17, 155, 11, 28 + 20 * Main.autoroute.nbVoies());
        g2d.drawString("P", 6, 153 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("E", 6, 163 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("A", 6, 173 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("G", 6, 183 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("E", 6, 193 + 10 * Main.autoroute.nbVoies());
        g2d.setColor(Color.green);
        int v = 0;
        for (int i = 0; i < Main.autoroute.nbVoies(); i++) {
            g2d.fillRect(19, 178 + v, 8, 2);
            g2d.fillRect(24, 177 + v, 2, 2);
            g2d.fillRect(24, 179 + v, 2, 2);
            g2d.fillRect(24, 176 + v, 1, 6);
            v = v + 20;
        }
        //Péage de sortie
        g2d.setColor(Color.gray);
        g2d.fillRect(Main.autoroute.longueur() + 8, 150, 20, 38 + 20 * Main.autoroute.nbVoies());
        g2d.fillRect(Main.autoroute.longueur() - 4, 155, 12, 28 + 20 * Main.autoroute.nbVoies());
        g2d.setColor(Color.black);
        g2d.drawRect(Main.autoroute.longueur() + 8, 150, 20, 38 + 20 * Main.autoroute.nbVoies());
        g2d.drawRect(Main.autoroute.longueur() - 4, 155, 12, 28 + 20 * Main.autoroute.nbVoies());
        g2d.drawString("P", Main.autoroute.longueur() + 15, 153 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("E", Main.autoroute.longueur() + 15, 163 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("A", Main.autoroute.longueur() + 15, 173 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("G", Main.autoroute.longueur() + 15, 183 + 10 * Main.autoroute.nbVoies());
        g2d.drawString("E", Main.autoroute.longueur() + 15, 193 + 10 * Main.autoroute.nbVoies());
        g2d.setColor(Color.red);
        v = 0;
        for (int i = 0; i < Main.autoroute.nbVoies(); i++) {
            g2d.fillRect(Main.autoroute.longueur() - 1, 178 + v, 8, 2);
            g2d.fillRect(Main.autoroute.longueur() + 4, 177 + v, 2, 2);
            g2d.fillRect(Main.autoroute.longueur() + 4, 179 + v, 2, 2);
            g2d.fillRect(Main.autoroute.longueur() + 4, 176 + v, 1, 6);
            v = v + 20;
        }
        return g2d;
    }

    private Graphics2D DessinerVehicules(Graphics g) {
        //Création d'un graphics 2d
        Graphics2D g2d = (Graphics2D) g;
        for (int voie = 0; voie < Main.autoroute.nbVoies(); voie++) {
            for (int i = 0; i < Main.autoroute.longueur(); i++) {
                if (Main.autoroute.existeVehicule(voie, i) != null) {
                    try {
                        nb++;
                        vitesse_moyenne += Main.autoroute.existeVehicule(voie, i).vitesse_courante * 222;
                        tot_vit_plus_rapide += Main.autoroute.VehiculePlusRapide().vitesse_courante;
                        tot_vit_moins_rapide += Main.autoroute.VehiculePlusLent().vitesse_courante;
                        ImageIcon img_voiture;
                        if (Main.autoroute.existeVehicule(voie, i).couleur.equals(Color.blue))
                            img_voiture = new ImageIcon("img/voiture_bleu.gif");
                        else if (Main.autoroute.existeVehicule(voie, i).couleur.equals(Color.green))
                            img_voiture = new ImageIcon("img/voiture_vert.gif");
                        else if (Main.autoroute.existeVehicule(voie, i).couleur.equals(Color.yellow))
                            img_voiture = new ImageIcon("img/voiture_jaune.gif");
                        else if (Main.autoroute.existeVehicule(voie, i).couleur.equals(Color.magenta))
                            img_voiture = new ImageIcon("img/voiture_mauve.gif");
                        else if (Main.autoroute.existeVehicule(voie, i).temoin)
                            img_voiture = new ImageIcon("img/temoin.gif");
                        else
                            img_voiture = new ImageIcon("img/voiture_rouge.gif");
                        Image image_voiture = img_voiture.getImage();
                        ImageObserver image_voiture_obs = img_voiture.getImageObserver();
                        g2d.drawImage(image_voiture, (int) Main.autoroute.existeVehicule(voie, i).position, 133 + 39 + 20 * voie, image_voiture_obs);
                        for (InterfaceCommande listener : getInterfaceCommande()) {
                            listener.NbVoituresChange(Main.autoroute.NbVehicules());
                            listener.VitesseMoyenneChange(round(vitesse_moyenne / nb, 2));
                            listener.VitessePlusLentChange(round(tot_vit_moins_rapide * 222 / nb, 2));
                            listener.VitessePlusRapideChange(round(tot_vit_plus_rapide * 222 / nb, 2));
                            //listener.NbDepassementsChange(Main.autoroute.existeVehicule(voie,i).NbDepassementsTotal); ne fonctionne pas
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        DessinerPeages(g);
        return g2d;
    }

    public void paintComponent(Graphics g) {
        if (buffer == null) {
            image = createImage(Main.autoroute.longueur() + 1000, this.getHeight());
            buffer = image.getGraphics();
        }
        DessinerTextures(buffer);
        DessinerRoute(buffer);
        DessinerVehicules(buffer);
        //Création d'un graphics 2d
        Graphics2D g2d = (Graphics2D) g;
        //Enlève l'antialiasing pour les shapes
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
        //Enlève l'antialiasing pour le texte
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        //Demande de rendu rapide
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
        //On dessine le buffer mémoire dans le buffer d'affichage
        g2d.drawImage(image, 1, 1, this);
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 549, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 424, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final EventListenerList listeners = new EventListenerList();
    static Graphics buffer; //buffer mémoire (2eme buffer)
    private Image image; //image mémoire correspondante au buffer
    private double vitesse_moyenne = 0;
    private double tot_vit_plus_rapide = 0;
    private double tot_vit_moins_rapide = 0;
    private int nb = 0;
    private TexturePaint texture_herbe, texture_asphalte;
    // End of variables declaration//GEN-END:variables

}
