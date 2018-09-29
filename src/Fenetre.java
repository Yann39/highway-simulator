/*
 * Fenetre.java
 *
 * Created on 21 novembre 2006, 16:05
 */

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class Fenetre extends javax.swing.JFrame {

    Fenetre(Autoroute r) {
        setTitle("Simulation Autoroute");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //change l'ic�ne de la fen�tre
        ImageIcon icon = new ImageIcon("img/banned.gif");
        Image img_icon = icon.getImage();
        setIconImage(img_icon);
        setSize(1190, 633);
        //cr�ation du jscrollpane contenant le dessin de la route
        jScrollPane1 = new JScrollPane(new PanelRoute());
        jScrollPane1.setPreferredSize(new Dimension(900, 592));
        jScrollPane1.setWheelScrollingEnabled(true);
        //cr�ation jpanel contenant le Paneau de commandes
        jPanel1 = new JPanel();
        jPanel1.add(PanelC);
        //cr�ation jpanel contenant le jScrollPane
        jPanel2 = new JPanel();
        jPanel2.setSize(Main.autoroute.longueur() + 60, 600);
        jPanel2.add(jScrollPane1);
        //ajout des 2 panels dans la fenetre principale
        this.getContentPane().add("West", jPanel1);
        this.getContentPane().add(jPanel2);
        this.setResizable(false);
        setVisible(true);
    }


    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 568, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(0, 393, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    static JScrollPane jScrollPane1;
    private static JPanel jPanel1;
    private static JPanel jPanel2;
    static PanelCommandes PanelC = new PanelCommandes();
    // End of variables declaration//GEN-END:variables

}
