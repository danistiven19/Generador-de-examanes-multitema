/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.CtrlEnunciado;
import Control.CtrlPregunta;
import Control.latex;
//import DAO.areaDAO;
//import DAO.enunciadoDAO;
import DTO.Area;
import DTO.Autor;
import DTO.Enunciado;
import DTO.Pregunta;
import DTO.archivos;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author luisa.suarezz
 */
public class administrarEnunciado extends javax.swing.JFrame {

    /**
     * Creates new form VerEnunciado
     */
    private DTO.Enunciado en1;
    ArrayList codigos;
    ArrayList areas;
    ArrayList pregunta;
    private DTO.Pregunta p;
    private int seleccion;
    private int existePrimero;
    private int existeUltimo;
    private CtrlEnunciado ctrlEn = new CtrlEnunciado();
    private CtrlPregunta ctrlPreg = new CtrlPregunta();
    private int ee;

    public administrarEnunciado(DTO.Enunciado en1, int seleccion) throws ParseException {
        initComponents();
         setLocationRelativeTo(null);
        this.en1 = en1;
        Date now = new Date(System.currentTimeMillis());
        this.seleccion = seleccion;
//        fechaCreacion.setDate(now);
        //DAO.enunciadoDAO en = new DAO.enunciadoDAO();
        codigos = new ArrayList();
        codigos = (ArrayList) ctrlEn.listarCodigos();
        DefaultComboBoxModel ls1 = (DefaultComboBoxModel) cb_ListaEnunciados.getModel();
        DefaultComboBoxModel ls2 = (DefaultComboBoxModel) cbAutor.getModel();
        ls1.addElement("Ninguno");
        Iterator i = codigos.iterator();
        while (i.hasNext()) {
            String cod = i.next().toString();
            if (!cod.equals(Integer.toString(en1.getCodigo()))) {
                ls1.addElement(cod);
            }
        }
        cb_ListaEnunciados.setModel(ls1);
        areas = (ArrayList<Area>) ctrlEn.listarAreas();
        ls1 = (DefaultComboBoxModel) cb_ListaAreas.getModel();
        ls1.removeAllElements();
        i = areas.iterator();
        int select = 0;
        while (i.hasNext()) {
            Area area = (Area) i.next();
            int codigo = area.getCodigo();
            if (en1.getArea() != null) {
                if (codigo == en1.getArea().getCodigo()) {
                    select = codigo;
                }
            }
            ls1.addElement(area.getCodigo() + " - " + area.getNombre());
        }
        cb_ListaAreas.setModel(ls1);
        
        Collection<Autor> autores = ctrlEn.listarAutorres();
        ls2.removeAllElements();
        i = autores.iterator();
        int select1 = 0;
        while (i.hasNext()) {
            Autor autor = (Autor) i.next();
            int codigo = autor.getCodigo();
            if (en1.getAutor() != null) {
                if (codigo == en1.getAutor().getCodigo()) {
                    select1 = codigo;
                }
            }
            ls2.addElement(autor.getCodigo() + " - " + autor.getNombre());
        }
        
        cb_ListaAreas.setModel(ls1);
        // 1 para modificar, 2 para crear
        if (seleccion == 1) {
//                archivos arch = new archivos();
//                File ar1 = new File(en1.getUrl(),"packages.txt");
//                try {
//                    this.en1.setPaquetes(arch.leerTxt(ar1));
//                } catch (IOException ex) {
//                    Logger.getLogger(verEnunciado.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                ar1 = new File(en1.getUrl(),"enunciado.txt");
//                 try {
//                    this.en1.setEnunciado(arch.leerTxt(ar1));
//                } catch (IOException ex) {
//                    Logger.getLogger(verEnunciado.class.getName()).log(Level.SEVERE, null, ex);
//                }

            cb_OrdenEnunciados.setSelectedIndex(en1.getOrden() - 1);
            cbAutor.setSelectedIndex(en1.getAutor().getCodigo() -1);
            java.util.Date d = new SimpleDateFormat("YY-MM-dd").parse(en1.getFechaCreacion());
            //fechaCreacion.setDate(d);
            if (en1.getDespuesDe().getCodigo() == 0) {
                cb_ListaEnunciados.setSelectedItem("Ninguno");
            } else {
                cb_ListaEnunciados.setSelectedIndex(en1.getDespuesDe().getCodigo() - 1);
            }

            cb_ListaAreas.setSelectedIndex(select - 1);
            btn_AgregarPregunta.setEnabled(true);
                 //Listar preguntas
            //DAO.preguntaDAO p1 =new  DAO.preguntaDAO();
            
        } else {

            //enunciadoDAO enDAO = new enunciadoDAO();
            this.en1.setCodigo(ctrlEn.obtenerCodigo(en1));

        }
actualizarPreg();
        lbl_CodigoEnunciado.setText(Integer.toString(this.en1.getCodigo()));
        lbl_NombreEnunciado.setText("Enunciado " + Integer.toString(this.en1.getCodigo()));
    }

    private void actualizarPreg() {
        cb_ListaPreguntas.removeAllItems();
        ctrlEn.cargarEnunciado(en1);
        pregunta = new ArrayList();
        pregunta = (ArrayList) ctrlPreg.listarcodigos(en1.getCodigo());
          if(pregunta.size() == 0) {
            btn_editarPregunta.setEnabled(false);
            btn_EliminarPregunta.setEnabled(false);
        }else{
            DefaultComboBoxModel ls2 = (DefaultComboBoxModel) cb_ListaPreguntas.getModel();
            int kk=1;
            while (kk < pregunta.size()+1) {
                ls2.addElement(Integer.toString(kk));
                kk++;
            }
            cb_ListaPreguntas.setModel(ls2);
            btn_editarPregunta.setEnabled(true);
            btn_EliminarPregunta.setEnabled(true);
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
        lbl_CodigoEnunciado = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_VerEnunciado = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cb_OrdenEnunciados = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cb_ListaEnunciados = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cb_ListaAreas = new javax.swing.JComboBox();
        cbAutor = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btn_editarPregunta = new javax.swing.JButton();
        btn_EliminarPregunta = new javax.swing.JButton();
        btn_AgregarPregunta = new javax.swing.JButton();
        cb_ListaPreguntas = new javax.swing.JComboBox();
        btn_cancelarEnunciado = new javax.swing.JButton();
        btn_GuardarEnunciado = new javax.swing.JButton();
        lbl_NombreEnunciado = new javax.swing.JLabel();
        lblInformacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Codigo:");

        lbl_CodigoEnunciado.setText(" ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enunciado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        jPanel1.setName(""); // NOI18N

        btn_VerEnunciado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_VerEnunciado.setText("Ver Enunciado");
        btn_VerEnunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VerEnunciadoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Despues de:");

        cb_OrdenEnunciados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_OrdenEnunciados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguno", "Primero", "Ultimo" }));
        cb_OrdenEnunciados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_OrdenEnunciadosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Orden:");

        cb_ListaEnunciados.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_ListaEnunciados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ListaEnunciadosActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Autor:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Area:");

        cb_ListaAreas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_ListaAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ListaAreasActionPerformed(evt);
            }
        });

        cbAutor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAutorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(108, 108, 108))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_VerEnunciado)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(cb_ListaEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_OrdenEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel7)))
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(cb_ListaAreas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_VerEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ListaEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_OrdenEnunciados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_ListaAreas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pregunta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        btn_editarPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_editarPregunta.setText("Editar");
        btn_editarPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarPreguntaActionPerformed(evt);
            }
        });

        btn_EliminarPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_EliminarPregunta.setText("Eliminar");
        btn_EliminarPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarPreguntaActionPerformed(evt);
            }
        });

        btn_AgregarPregunta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_AgregarPregunta.setText("Agregar Pregunta");
        btn_AgregarPregunta.setEnabled(false);
        btn_AgregarPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarPreguntaActionPerformed(evt);
            }
        });

        cb_ListaPreguntas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cb_ListaPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btn_editarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_EliminarPregunta))
                    .addComponent(btn_AgregarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 80, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_editarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_EliminarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ListaPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_AgregarPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_cancelarEnunciado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cancelarEnunciado.setText("Volver");
        btn_cancelarEnunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarEnunciadoActionPerformed(evt);
            }
        });

        btn_GuardarEnunciado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_GuardarEnunciado.setText("Guardar");
        btn_GuardarEnunciado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarEnunciadoActionPerformed(evt);
            }
        });

        lbl_NombreEnunciado.setText(" ");

        lblInformacion.setText("  ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl_NombreEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_CodigoEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancelarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(btn_GuardarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_CodigoEnunciado))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_NombreEnunciado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_GuardarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelarEnunciado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInformacion)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VerEnunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VerEnunciadoActionPerformed
        verEnunciado ver = new verEnunciado(en1, seleccion);
        ver.show();
    }//GEN-LAST:event_btn_VerEnunciadoActionPerformed

    private void cb_ListaEnunciadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ListaEnunciadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_ListaEnunciadosActionPerformed

    private void cb_ListaAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ListaAreasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_ListaAreasActionPerformed

    private void btn_editarPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarPreguntaActionPerformed
        p = new DTO.Pregunta();
        p.setCodigo((int) pregunta.get(Integer.parseInt(cb_ListaPreguntas.getSelectedItem().toString())-1));
        //DAO.preguntaDAO pDAO = new DAO.preguntaDAO();
        ctrlPreg.cargarInfPreg(p);
        Examenes ex = new Examenes();
        ex.abrirPregunta(p, 1);
        this.hide();
    }//GEN-LAST:event_btn_editarPreguntaActionPerformed

    private void btn_EliminarPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarPreguntaActionPerformed
        Pregunta preg = new Pregunta();
        preg.setCodigo((Integer.parseInt(cb_ListaPreguntas.getSelectedItem().toString())));
        latex lt = new latex();
        try {
            if (lt.borrarPregunta(preg) == 1) {
                JOptionPane.showMessageDialog(this, "Pregunta borrada correctamente!");
                actualizarPreg();
            }
        } catch (IOException ex) {
            Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EliminarPreguntaActionPerformed

    private void btn_AgregarPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarPreguntaActionPerformed
        Examenes ex = new Examenes();
        Pregunta p1 = new Pregunta();
        Enunciado enaux = new Enunciado();
        enaux.setCodigo(en1.getCodigo());
        p1.setEnunciado(enaux);

        ex.abrirPregunta(p1, 2);
        this.hide();
    }//GEN-LAST:event_btn_AgregarPreguntaActionPerformed

    private void btn_cancelarEnunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarEnunciadoActionPerformed
        Examenes ex = new Examenes();
        ex.abrirAdminEnunciados();
        this.hide();
    }//GEN-LAST:event_btn_cancelarEnunciadoActionPerformed

    private void btn_GuardarEnunciadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarEnunciadoActionPerformed
        Autor autor1 = new Autor();
        autor1.setCodigo((cbAutor.getSelectedIndex() +1));
        en1.setAutor(autor1);
        Area area = new Area();
        area.setCodigo(cb_ListaAreas.getSelectedIndex() + 1);
        en1.setArea(area);

        //String fecha = fechaCreacion.getDate().toString();
//        String fecha =new SimpleDateFormat("YYYY-MM-dd").format(fechaCreacion.getDate());
        Calendar c = new GregorianCalendar();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        en1.setFechaCreacion(annio + "/" + mes + "/" + dia);

        Enunciado enaux = new Enunciado();
        if (cb_ListaEnunciados.getSelectedItem().equals("Ninguno")) {
            enaux.setCodigo(0);
        } else {
            if ((cb_OrdenEnunciados.getSelectedIndex()) != 0) {
                JOptionPane.showMessageDialog(this, "Verifique sus condiciones, no pueden ingresar dos condicionamientos diferentes");
                return;
            }
            enaux.setCodigo(cb_ListaEnunciados.getSelectedIndex() + 1);
        }
        en1.setDespuesDe(enaux);

        en1.setOrden(cb_OrdenEnunciados.getSelectedIndex() + 1);
        latex lt = new latex();
        try {
            if (lt.crearEnunciado(en1) == 1) {
                // enunciadoDAO enDAO = new enunciadoDAO();

                if (seleccion == 2) {

                    //Duplicar ls backSlashh!
                    if (ctrlEn.crearEnunciado(en1) == 1) {
                        lblInformacion.setText("Enunciado "+en1.getCodigo()+ " almacenado correctamente!");
                       // JOptionPane.showMessageDialog(this, "Enunciado almacenado correctamente!");
                        btn_AgregarPregunta.setEnabled(true);
                    }
                } else {

                    if (ctrlEn.modificarEnunciado(en1) == 1) {
                        lblInformacion.setText("Enunciado "+en1.getCodigo()+ " modificado correctamente!");
                       // JOptionPane.showMessageDialog(this, "Enunciado modificado correctamente!");
                    }
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_GuardarEnunciadoActionPerformed

    private void cb_OrdenEnunciadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_OrdenEnunciadosActionPerformed
        if (this.existePrimero == 1 && cb_OrdenEnunciados.getSelectedIndex() == 0) {//cero es orden primero, 1 orden ultimo, 2 ningun orden
            JOptionPane.showMessageDialog(null, "No pueden existir dos enunciados con orden primero!");
            return;
        }
        if (this.existePrimero == 1 && cb_OrdenEnunciados.getSelectedIndex() == 1) {//cero es orden primero, 1 orden ultimo, 2 ningun orden
            JOptionPane.showMessageDialog(null, "No pueden existir dos enunciados con orden ultimo!");
            return;
        }

    }//GEN-LAST:event_cb_OrdenEnunciadosActionPerformed

    private void cbAutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAutorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAutorActionPerformed

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
            java.util.logging.Logger.getLogger(administrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(administrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(administrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(administrarEnunciado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new administrarEnunciado(null, 0).setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(administrarEnunciado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_AgregarPregunta;
    private javax.swing.JButton btn_EliminarPregunta;
    private javax.swing.JButton btn_GuardarEnunciado;
    private javax.swing.JButton btn_VerEnunciado;
    private javax.swing.JButton btn_cancelarEnunciado;
    private javax.swing.JButton btn_editarPregunta;
    private javax.swing.JComboBox cbAutor;
    private javax.swing.JComboBox cb_ListaAreas;
    private javax.swing.JComboBox cb_ListaEnunciados;
    private javax.swing.JComboBox cb_ListaPreguntas;
    private javax.swing.JComboBox cb_OrdenEnunciados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblInformacion;
    private javax.swing.JLabel lbl_CodigoEnunciado;
    private javax.swing.JLabel lbl_NombreEnunciado;
    // End of variables declaration//GEN-END:variables
}
