/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.CtrlEnunciado;
import Control.CtrlOpcion;
import Control.CtrlPregunta;
import Control.latex;
import DTO.Enunciado;
import DTO.Opcion;
import DTO.Pregunta;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author luisa.suarezz
 */
public class AdministrarPregunta extends javax.swing.JFrame {

    /**
     * Creates new form AdministrarPregunta
     */
    private int existePrimero;
    private int existeUltimo;
    private CtrlPregunta ctrlPreg = new CtrlPregunta();
    private DTO.Opcion op;
    private DTO.Pregunta p;
    ArrayList opciones;
    Collection<Pregunta> pregunta;
    String descripcion;
    int seleccion;
    private int maxOpcion;
    private CtrlEnunciado ctrlEn = new CtrlEnunciado();
    private CtrlOpcion ctrlop = new CtrlOpcion();

    public AdministrarPregunta(DTO.Pregunta p, int sel) {
        initComponents();
        // archivos arch = new archivos();
        this.p = p;
        this.seleccion = sel;

        /*         File ar = new File(p.getUrl(),"descripcion"+p.getCodigo()+".txt");
         try {
         descripcion = arch.leerTxt(ar);
         } catch (IOException ex) {
         Logger.getLogger(AdministrarPregunta.class.getName()).log(Level.SEVERE, null, ex);
         }
         txt_DescripcionPregunta.setText(descripcion);*/
        // DAO.preguntaDAO p1 =new  DAO.preguntaDAO();
        pregunta = new ArrayList<Pregunta>();
        Iterator j;
//        pregunta= p1.listarCodigosPreguntasdeEnunciado(p.getEnunciado());
        pregunta = ctrlPreg.listarcodigos(p.getEnunciado().getCodigo());
        DefaultComboBoxModel ls1 = (DefaultComboBoxModel) cb_ListaPreguntas.getModel();
        ls1.addElement("Ninguno");
        j = pregunta.iterator();
        while (j.hasNext()) {
            String cod1 = j.next().toString();
            if (p.getCodigo() != Integer.parseInt(cod1)) {
                ls1.addElement(cod1);
            }
        }

        cb_ListaPreguntas.setModel(ls1);
        opciones = new ArrayList();
        ctrlPreg.listarOpcionesDePRegunta(p);
        if (opciones.size() == 0) {
            btn_editarOpcion.setEnabled(false);
            btn_vaciarOpcion.setEnabled(false);
        } else {
            btn_editarOpcion.setEnabled(true);
            btn_vaciarOpcion.setEnabled(true);
        }
        if (sel == 1) {

            cb_OrdenPreguntas.setSelectedIndex(p.getOrden() - 1);
            cb_ListaPreguntas.setSelectedIndex(p.getDespuesDePregunta().getCodigo() - 1);
            //cb_ListaPreguntas.setSelectedIndex(p.getDespuesDePregunta() -1);
            lbl_NumeroPregunta.setText(Integer.toString(p.getCodigo()));
//            cb_EsObligatoria.setSelectedItem(p.getObligatorioa());
//            cb_TipoPreg.setSelectedItem(p.getTipo());
            txt_DescripcionPregunta.setText(p.getDescripcionPregunta());

            //Cargar Opciones
            //this.op=op;
            // DAO.opcionesDAO op1 =new  DAO.opcionesDAO();
            opciones = (ArrayList<Opcion>) ctrlPreg.listarOpcionesDePRegunta(p);
            // opciones= (ArrayList<Opcion>) op1.listarCodigosOpcionesdePregunta(p.getCodigo()); 
            DefaultComboBoxModel ls2 = (DefaultComboBoxModel) cb_ListaOpciones.getModel();

            j = opciones.iterator();
            while (j.hasNext()) {
                Opcion op1 = (Opcion) j.next();
                int cod1 = op1.getCodigo();
                if(cod1 > maxOpcion){
                    maxOpcion= cod1;
                }
                ls2.addElement(cod1);
                btn_editarOpcion.setEnabled(true);
                
            }
            cb_ListaOpciones.setModel(ls2);

        } else {
            this.p.setCodigo(ctrlEn.obtenerUltimoCodigoOpcionPregunta(p));
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

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbl_NumeroPregunta = new javax.swing.JLabel();
        cb_ListaPreguntas = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cb_OrdenPreguntas = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_DescripcionPregunta = new javax.swing.JTextArea();
        btn_SeleccionarImagen = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn_editarOpcion = new javax.swing.JButton();
        cb_ListaOpciones = new javax.swing.JComboBox();
        btn_vaciarOpcion = new javax.swing.JButton();
        btn_guardarOpcion = new javax.swing.JButton();
        btn_cancelarPreguntas = new javax.swing.JButton();
        btn_GuardarPreguntas = new javax.swing.JButton();
        lblInformacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pregunta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Número pregunta:");

        lbl_NumeroPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_NumeroPregunta.setText(" ");

        cb_ListaPreguntas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_ListaPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ListaPreguntasActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Orden:");

        cb_OrdenPreguntas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_OrdenPreguntas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguno", "Primero", "Ultimo" }));
        cb_OrdenPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_OrdenPreguntasActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Despues de:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Descripción:");

        txt_DescripcionPregunta.setColumns(20);
        txt_DescripcionPregunta.setRows(5);
        jScrollPane1.setViewportView(txt_DescripcionPregunta);

        btn_SeleccionarImagen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_SeleccionarImagen.setText("Seleccionar imagen");
        btn_SeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeleccionarImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_SeleccionarImagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_ListaPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(cb_OrdenPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_NumeroPregunta))
                            .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(lbl_NumeroPregunta))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(btn_SeleccionarImagen))
                        .addGap(18, 18, 18)
                        .addComponent(cb_ListaPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cb_OrdenPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 31, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btn_editarOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_editarOpcion.setText("Editar");
        btn_editarOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarOpcionActionPerformed(evt);
            }
        });

        cb_ListaOpciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_vaciarOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_vaciarOpcion.setText("Vaciar");
        btn_vaciarOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vaciarOpcionActionPerformed(evt);
            }
        });

        btn_guardarOpcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_guardarOpcion.setText("Agregar Opcion");
        btn_guardarOpcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarOpcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_guardarOpcion)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cb_ListaOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(btn_editarOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btn_vaciarOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_editarOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ListaOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_vaciarOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(btn_guardarOpcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btn_cancelarPreguntas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cancelarPreguntas.setText("Volver");
        btn_cancelarPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarPreguntasActionPerformed(evt);
            }
        });

        btn_GuardarPreguntas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_GuardarPreguntas.setText("Guardar");
        btn_GuardarPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarPreguntasActionPerformed(evt);
            }
        });

        lblInformacion.setText("    ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_cancelarPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(279, 279, 279)
                                .addComponent(btn_GuardarPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_GuardarPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cancelarPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInformacion)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editarOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarOpcionActionPerformed
        op = new DTO.Opcion();
        op.setCodigo(Integer.parseInt((String) cb_ListaOpciones.getSelectedItem().toString()));
        op.setPregunta(p);
        op= ctrlop.cargarOpcion(op);
        Examenes exa = new Examenes();
        exa.abrirOpcion(op, 1);
        this.hide();
    }//GEN-LAST:event_btn_editarOpcionActionPerformed

    private void btn_vaciarOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vaciarOpcionActionPerformed
        Opcion p = new Opcion();
        p.setCodigo((Integer.parseInt(cb_ListaOpciones.getSelectedItem().toString())));
        latex lt = new latex();
        try {
            lt.vaciarOpcion(p);
            //this.repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Opcion Limpiada !");
        }
    }//GEN-LAST:event_btn_vaciarOpcionActionPerformed

    private void btn_GuardarPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarPreguntasActionPerformed
        btn_guardarOpcion.setEnabled(true);
        if(txt_DescripcionPregunta.equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción");
            txt_DescripcionPregunta.requestFocus();
            return;
        }
        p.setDescripcionPregunta(txt_DescripcionPregunta.getText());
//        p.setTipo(cb_TipoPreg.getSelectedIndex() + 1);
//
//        p.setObligatorioa((String) cb_EsObligatoria.getSelectedItem());

        Pregunta pregaux = new Pregunta();
        if (cb_ListaPreguntas.getSelectedItem().equals("Ninguno")) {
            pregaux.setCodigo(0);

        } else {
            if (cb_OrdenPreguntas.getSelectedIndex()!= 0) {
               JOptionPane.showMessageDialog(this, "Verifique sus condiciones, no pueden ingresar dos condicionamientos diferentes");
                return;
            }
                pregaux.setCodigo(cb_ListaPreguntas.getSelectedIndex() + 1);
        }
        p.setDespuesDePregunta(pregaux);
        p.setOrden(cb_OrdenPreguntas.getSelectedIndex() + 1);
        latex lt = new latex();
        try {
            if (lt.crearPregunta(p) == 1) {
                //preguntaDAO pDAO = new preguntaDAO();
                if (seleccion == 2) {

                    //Duplicar ls backSlashh!
                    if (ctrlPreg.crearPregunta(p) == 1) {
                        lblInformacion.setText( "Pregunta "+p.getCodigo()+ " almacenada correctamente!");
                       // JOptionPane.showMessageDialog(this, "Pregunta almacenada correctamente!");
                    }
                } else {
                    if (ctrlPreg.modificarPregunta(p) == 1) {
                        lblInformacion.setText( "Pregunta "+p.getCodigo()+ " modificada correctamente!");
                        //JOptionPane.showMessageDialog(this, "Pregunta modificada correctamente!");
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_GuardarPreguntasActionPerformed

    private void btn_guardarOpcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarOpcionActionPerformed
        if (p.getOpciones() != null) {
            if (p.getOpciones().size() == 4) {
                JOptionPane.showMessageDialog(null, "Una pregunta solo puede tener cuatro opciones!");
                return;
            }
        }
        Examenes ex = new Examenes();
        op = new Opcion();
        op.setCodigo(maxOpcion+1);
        Pregunta preg = new Pregunta();
        preg.setCodigo(p.getCodigo());
        op.setPregunta(preg);
        ex.abrirOpcion(op, 2);
        this.hide();


    }//GEN-LAST:event_btn_guardarOpcionActionPerformed

    private void btn_cancelarPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarPreguntasActionPerformed
        try {
            Enunciado enu = new Enunciado();
            enu.setCodigo(p.getEnunciado().getCodigo());
            ctrlEn.cargarEnunciado(enu);
            Examenes ex = new Examenes();
            ex.abrirEnunciado(enu, 1);
            this.hide();
        } catch (ParseException ex1) {
            Logger.getLogger(AdministrarPregunta.class.getName()).log(Level.SEVERE, null, ex1);
        }

    }//GEN-LAST:event_btn_cancelarPreguntasActionPerformed

    private void cb_OrdenPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_OrdenPreguntasActionPerformed
        if (this.existePrimero == 1 && cb_OrdenPreguntas.getSelectedIndex() == 0) {//cero es orden primero, 1 orden ultimo, 2 ningun orden
            JOptionPane.showMessageDialog(null, "No pueden existir dos enunciados con orden primero!");
            return;
        }
        if (this.existePrimero == 1 && cb_OrdenPreguntas.getSelectedIndex() == 1) {//cero es orden primero, 1 orden ultimo, 2 ningun orden
            JOptionPane.showMessageDialog(null, "No pueden existir dos enunciados con orden ultimo!");
            return;
        }

    }//GEN-LAST:event_cb_OrdenPreguntasActionPerformed

    private void btn_SeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeleccionarImagenActionPerformed
       /**
        * Cambiamos comillas simples por comillas dobles, esto con el fin de evitar conflictos en SQL
        */
        String textoEnunciado = txt_DescripcionPregunta.getText();
        char comillaSimple = 39;
        char comillaDoble =34;
        String nuevoTexto = textoEnunciado.replace(comillaSimple,comillaDoble);
        txt_DescripcionPregunta.setText(nuevoTexto);
        
        JFileChooser elegir = new JFileChooser();
        int opcion = elegir.showOpenDialog(btn_SeleccionarImagen);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            String pathArchivo = elegir.getSelectedFile().getPath(); //Obtiene path del archivo
            String nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo
            String path = pathArchivo.replace('\\', '/');
            //txt_DescripcionPregunta.setText("\\includegraphics{"+path +"}"+ txt_DescripcionPregunta.getText());
            txt_DescripcionPregunta.insert("\\includegraphics{" + path + "}", txt_DescripcionPregunta.getCaretPosition());

        }
    }//GEN-LAST:event_btn_SeleccionarImagenActionPerformed

    private void cb_ListaPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ListaPreguntasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_ListaPreguntasActionPerformed

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
            java.util.logging.Logger.getLogger(AdministrarPregunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministrarPregunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministrarPregunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministrarPregunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministrarPregunta(null, 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_GuardarPreguntas;
    private javax.swing.JButton btn_SeleccionarImagen;
    private javax.swing.JButton btn_cancelarPreguntas;
    private javax.swing.JButton btn_editarOpcion;
    private javax.swing.JButton btn_guardarOpcion;
    private javax.swing.JButton btn_vaciarOpcion;
    private javax.swing.JComboBox cb_ListaOpciones;
    private javax.swing.JComboBox cb_ListaPreguntas;
    private javax.swing.JComboBox cb_OrdenPreguntas;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lbl_NumeroPregunta;
    private javax.swing.JTextArea txt_DescripcionPregunta;
    // End of variables declaration//GEN-END:variables
}
