package Vista;

import Control.CtrlOpcion;
import Control.CtrlPregunta;
import Control.latex;
//import DAO.;
//import DAO.preguntaDAO;
import DTO.Opcion;
import DTO.Pregunta;
import DTO.archivos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author julian.montoyap
 */
public class opcion extends javax.swing.JFrame {

    /**
     * Creates new form opcion
     */
    private DTO.Opcion p;
    String descripcionOpcion;
    ArrayList opcion;
    int seleccion;
    private CtrlPregunta ctrlPreg = new CtrlPregunta();
    private CtrlOpcion ctrlop = new CtrlOpcion();
    /*public opcion(Control.Opcion p, int sel) {
     initComponents();
     archivos arch = new archivos();
     this.p=p;
     this.seleccion = sel;
  	 

     DAO.opcionesDAO p1 =new  DAO.opcionesDAO();
     opcion = new ArrayList();
     opcion = p1.listarCodigosOpcionesdePregunta(p.getPregunta());
     DefaultComboBoxModel ls1 = (DefaultComboBoxModel) cb_DespuesDeOpcion.getModel();
     Iterator j = opcion.iterator();
     while(j.hasNext()){
     String cod1= j.next().toString();
     if(p.getCodigo()!= Integer.parseInt(cod1)){
     ls1.addElement(cod1);
     }
     }
     ls1.addElement("Ninguno");
     cb_DespuesDeOpcion.setModel(ls1);
     if(sel ==1){
     txt_DescripcionOpcion.setText(p.getDescripcionOpcion());
     if(p.getdespuesDeOpcion() != 0){
     cb_DespuesDeOpcion.setSelectedIndex(p.getdespuesDeOpcion()-1);
     }else{
     cb_DespuesDeOpcion.setSelectedItem("Ninguno");
     }
            
     cb_OrdenOpcion.setSelectedIndex(p.getOrden()-1);
     }else{
     this.p.setCodigo(p1.obtenerCodigoOpcion(p));
     }
     	
        
   	 
   	 
   	 
     }*/
    public opcion(DTO.Opcion p, int sel) {
        initComponents();
        archivos arch = new archivos();
        this.p = p;
        this.seleccion = sel;

        /*     	File ar = new File(p.getUrl(),"descripcion"+p.getCodigo()+".txt");
         try {
         descripcionOpcion = arch.leerTxt(ar);
         } catch (IOException ex) {
         Logger.getLogger(AdministrarPregunta.class.getName()).log(Level.SEVERE, null, ex);
         }
    	 
         txt_DescripcionOpcion.setText(descripcionOpcion);
         */
       // DAO.opcionesDAO p1 = new DAO.opcionesDAO();
       // DAO.preguntaDAO preD = new DAO.preguntaDAO();

        opcion = new ArrayList();
       // opcion = p1.listarCodigosOpcionesdePregunta(p.getPregunta());
//        Pregunta pregaux=new Pregunta();
//        pregaux.setCodigo(.getCodigo());
        ctrlPreg.listarOpcionesDePRegunta(p.getPregunta());
        opcion = (ArrayList) ctrlPreg.listarOpcionesDePRegunta(p.getPregunta());
       
        DefaultComboBoxModel ls1 = (DefaultComboBoxModel) cb_DespuesDeOpcion.getModel();
         ls1.addElement("Ninguno");
        Iterator j = opcion.iterator();
        while (j.hasNext()) {
            Opcion op = (Opcion) j.next();
            if (p.getCodigo() !=  op.getCodigo()) {
                ls1.addElement(op.getCodigo());
            }
        }
       
        cb_DespuesDeOpcion.setModel(ls1);
        if (sel == 1) {
            txt_DescripcionOpcion.setText(p.getDescripcionOpcion());
           if (p.getdespuesDeOpcion().getCodigo() != 0) {
                cb_DespuesDeOpcion.setSelectedIndex(p.getdespuesDeOpcion().getCodigo() - 1);

            } else {
                cb_DespuesDeOpcion.setSelectedItem("Ninguno");
            }

            cb_OrdenOpcion.setSelectedIndex(p.getOrden() - 1);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DescripcionOpcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        cb_OrdenOpcion = new javax.swing.JComboBox();
        btn_cancelarOpcion = new javax.swing.JButton();
        cb_DespuesDeOpcion = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btn_GuardarOpcion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Opción");

        txt_DescripcionOpcion.setColumns(20);
        txt_DescripcionOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_DescripcionOpcion.setRows(5);
        jScrollPane1.setViewportView(txt_DescripcionOpcion);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Orden");

        cb_OrdenOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_OrdenOpcion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguno", "Primero", "Ultimo" }));
        cb_OrdenOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_OrdenOpcionActionPerformed(evt);
            }
        });

        btn_cancelarOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cancelarOpcion.setText("Volver");
        btn_cancelarOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarOpcionActionPerformed(evt);
            }
        });

        cb_DespuesDeOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Despues de");

        btn_GuardarOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_GuardarOpcion.setText("Guardar");
        btn_GuardarOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarOpcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_cancelarOpcion)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_GuardarOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(336, 336, 336)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cb_OrdenOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_DespuesDeOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_OrdenOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_DespuesDeOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_GuardarOpcion)
                    .addComponent(btn_cancelarOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarOpcionActionPerformed
        
        //preguntaDAO pd = new preguntaDAO();
        
        Pregunta preg = new Pregunta();
        preg.setCodigo(p.getPregunta().getCodigo());
        ctrlPreg.cargarInfPreg(preg);
       
        Examenes ex = new Examenes();
        ex.abrirPregunta(preg, 1);
        this.hide();

    }//GEN-LAST:event_btn_cancelarOpcionActionPerformed

    private void btn_GuardarOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarOpcionActionPerformed
        p.setDescripcionOpcion(txt_DescripcionOpcion.getText());
        Opcion op2= new Opcion();
        if (cb_DespuesDeOpcion.getSelectedItem() == "Ninguno") {
            op2.setCodigo(0);
            
        } else {
             op2.setCodigo(Integer.parseInt(cb_DespuesDeOpcion.getSelectedItem().toString()));
           
        }
         p.setdespuesDeOpcion(op2);
        p.setOrden(cb_OrdenOpcion.getSelectedIndex() + 1);
        latex lt = new latex();
        try {
            if (ctrlop.crearOpcion(p) == 1) {
                Examenes exa = new Examenes();
                if (seleccion == 2) {
                    //Duplicar ls backSlashh!
                    if ( lt.crearOpcion(p) == 1) {
                        JOptionPane.showMessageDialog(this, "Opcion almacenada correctamente!");
                        exa.abrirPregunta(p.getPregunta(), 1);
                        this.hide();
                    }
                } else {
                    if (ctrlop.modificarOpcion(p) == 1) {
                        JOptionPane.showMessageDialog(this, "Opcion modificada correctamente!");
                        exa.abrirPregunta(p.getPregunta(), 1);
                        this.hide();
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:

    }//GEN-LAST:event_btn_GuardarOpcionActionPerformed

    private void cb_OrdenOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_OrdenOpcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_OrdenOpcionActionPerformed

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
            java.util.logging.Logger.getLogger(opcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(opcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(opcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(opcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new opcion(null, 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_GuardarOpcion;
    private javax.swing.JButton btn_cancelarOpcion;
    private javax.swing.JComboBox cb_DespuesDeOpcion;
    private javax.swing.JComboBox cb_OrdenOpcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_DescripcionOpcion;
    // End of variables declaration//GEN-END:variables
}
