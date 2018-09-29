/*
 * FenetreFichier.java
 *
 * Created on 11 january 2007, 13:54
 *
 * @author Yann <rockyracer@mailfence.com>
 */

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;

class FenetreFichier extends javax.swing.JFrame {

    private String monFichier = "VehiculeTemoin.txt";
    private String fichierContenu = "";

    FenetreFichier() {
        initComponents();
        setTitle("Donn�es voiture t�moin");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        //change l'ic�ne de la fen�tre
        ImageIcon icon = new ImageIcon("text.gif");
        Image img_icon = icon.getImage();
        setIconImage(img_icon);
        jPanel1.setPreferredSize(new Dimension(200, 800));
        LireFichier();
    }

    private void LireFichier() {
        try {
            RandomAccessFile raf = new RandomAccessFile(monFichier, "r");
            String ligne;
            while ((ligne = raf.readLine()) != null) {
                fichierContenu += ligne + "\r\n";
            }
            jTextArea2.setText(fichierContenu);
        } catch (IOException e) {
            System.out.println("erreur dans: " + e);
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        label1.setFont(new java.awt.Font("Dialog", 1, 12));
        label1.setText("Statistiques v\u00e9hicule t\u00e9moin :");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setDoubleBuffered(true);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jTextArea2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jTextArea2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 258, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        jScrollPane1.setViewportView(jPanel1);

        jButton1.setText("Fermer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Actualiser");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .add(132, 132, 132)
                                .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(127, Short.MAX_VALUE)
                                .add(jButton2)
                                .add(19, 19, 19)
                                .add(jButton1)
                                .add(130, 130, 130))
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[]{jButton1, jButton2}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 283, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jButton1)
                                        .add(jButton2))
                                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fichierContenu = "";
        jTextArea2.setText("");
        LireFichier();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea2;
    private java.awt.Label label1;
    // End of variables declaration//GEN-END:variables

}
