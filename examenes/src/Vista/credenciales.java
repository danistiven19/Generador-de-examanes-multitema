package Vista;

import Control.CtrlCredencial;
import Control.CtrlRutas;
import DAO.CredencialTemaDAO;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lis
 */
public class credenciales extends javax.swing.JFrame {

    /**
     * Creates new form credenciales
     */
    public credenciales() {
        initComponents();
         setLocationRelativeTo(null);
    }
    CtrlCredencial ctrlCredencial = new CtrlCredencial();
    CtrlRutas ctrlRutas = new CtrlRutas();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtRutaCredenciales = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSeleccionarCredenciales = new javax.swing.JButton();
        btnGuardarCredenciales = new javax.swing.JButton();
        btnVolverCredenciales = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Credenciales");

        txtRutaCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutaCredencialesActionPerformed(evt);
            }
        });

        jLabel2.setText("Ingrese ruta del archivo con las credenciales:");

        btnSeleccionarCredenciales.setText("Seleccionar archivo");
        btnSeleccionarCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarCredencialesActionPerformed(evt);
            }
        });

        btnGuardarCredenciales.setText("Guardar");
        btnGuardarCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCredencialesActionPerformed(evt);
            }
        });

        btnVolverCredenciales.setText("Volver");
        btnVolverCredenciales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverCredencialesActionPerformed(evt);
            }
        });

        btnVer.setText("Ver Tema x Credencial");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnVer)
                                    .addComponent(txtRutaCredenciales, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGuardarCredenciales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSeleccionarCredenciales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolverCredenciales)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRutaCredenciales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionarCredenciales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarCredenciales)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverCredenciales)
                    .addComponent(btnVer))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRutaCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutaCredencialesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutaCredencialesActionPerformed

    private void btnSeleccionarCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarCredencialesActionPerformed

        JFileChooser elegir = new JFileChooser();
        int opcion = elegir.showOpenDialog(btnSeleccionarCredenciales);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            String pathArchivo = elegir.getSelectedFile().getPath(); //Obtiene path del archivo
            String nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo

            System.out.println("El nombre del archivo es: " + nombre);
            System.out.println("El path del archivo es: " + pathArchivo);// TODO add your handling code here:
            txtRutaCredenciales.setText(pathArchivo);
            ctrlRutas.setRutaLeerCredenciales(pathArchivo);
        }
    }//GEN-LAST:event_btnSeleccionarCredencialesActionPerformed

    private void btnGuardarCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCredencialesActionPerformed
        btnVer.setEnabled(true);
        ArrayList credenciales = new ArrayList();
        credenciales = ctrlCredencial.readExcelFile(ctrlRutas.getRutaLeerCredenciales());

        int i = 0;

        CredencialTemaDAO ctDAO = new CredencialTemaDAO();

        //  HashMap hm = ctrlCredencial.guardarCredenciales(credenciales);
        HashMap hm = ctDAO.credencialTema(credenciales);
        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry map = (Map.Entry) it.next();
            String[] o = {map.getKey().toString(), map.getValue().toString()};
        }
        System.out.println("Credencial-Tema guardado con existo");
    }//GEN-LAST:event_btnGuardarCredencialesActionPerformed

    private void btnVolverCredencialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverCredencialesActionPerformed
        Examenes exa = new Examenes();
        exa.abrirPrincipal();
        this.hide();
    }//GEN-LAST:event_btnVolverCredencialesActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        try {
        String nombreArchivo=JOptionPane.showInputDialog("Ingrese el nombre del archivo que contendrá la relacion Tema-Credencial");
        Desktop.getDesktop().open(ctrlCredencial.writeExcelFile(nombreArchivo));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas al momento de crear el archivo Tema-Credencial! Error: " + e);
        }
    }//GEN-LAST:event_btnVerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(credenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(credenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(credenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(credenciales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new credenciales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCredenciales;
    private javax.swing.JButton btnSeleccionarCredenciales;
    private javax.swing.JButton btnVer;
    private javax.swing.JButton btnVolverCredenciales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtRutaCredenciales;
    // End of variables declaration//GEN-END:variables

}
