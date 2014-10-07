/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Combinatoria;
import DTO.Pregunta_Tema;
import DTO.Tema;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class randomDAO {

    private BD bd;
    private Connection con;

    public randomDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    public int randompreguntas() throws SQLException {
        /* ArrayList codigos = new ArrayList();
         preguntaTemaDAO pDAO = new preguntaTemaDAO();
         Tema t = new Tema();
         t.setCodigo(1);
         Calendar fecha = new GregorianCalendar();
         int year = fecha.get(Calendar.YEAR);
         int mes = fecha.get(Calendar.MONTH);
         int dia = fecha.get(Calendar.DAY_OF_MONTH);

         String aa=Integer.toString(year);
         String mm=Integer.toString(mes+1);
         String dd=Integer.toString(dia);

         String fa=aa+"/"+mm+"/"+dd;
         t.setFechaCreacion(fa);
         t.setJornada("Mañana");
         
         tema.ingresarTema(t);*/
        preguntaTemaDAO tema = new preguntaTemaDAO();
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
             String consulta= "";
             Statement sta = con.createStatement();
             
            if(!tema.existePreguntaTema(0)){
                 consulta = "INSERT INTO Pregunta_Tema2 SELECT * FROM Pregunta_Tema WHERE f_tema = 0 ORDER BY RAND()";
                int rs = sta.executeUpdate(consulta);
            }

            consulta = "SELECT * FROM Pregunta_Tema2 WHERE f_tema = 0 ORDER BY RAND()";
            ResultSet rse = sta.executeQuery(consulta);
            Combinatoria com = new Combinatoria();
            com.ordenamiento(rse);
                      //con.close();

            /*while(rs.next()){
             Pregunta_Tema pt= new Pregunta_Tema();
             pt.setTema(t.getCodigo());
             pt.setPregunta(rs.getInt("f_pregunta"));
             pt.setRespuesta(0);
             pt.setCombinacion(1);
             pDAO.ingresarPreguntaTema(pt);
             }*/
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en el random! Error: " + e);
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return 0;
    }

}
