/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Enunciado;
import DTO.Opcion;
import DTO.Pregunta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author telematica
 */
public class preguntaDAO {

    private BD bd;
    private Connection con;
    private opcionesDAO opD = new opcionesDAO();
    private enunciadoDAO enDAO= new enunciadoDAO();
    public preguntaDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    public ArrayList listarCodigosPreguntasdeEnunciado(int en1) {
        ArrayList codigos = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM pregunta WHERE enunciado = '" + en1 + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                codigos.add(rs.getInt("idPregunta"));
            }
            //con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de codigo de pregunta! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return codigos;
    }

    public int modificarPregunta(DTO.Pregunta p) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "";
            Object ob = null;
            
             if (p.getDespuesDePregunta().getCodigo() != 0) {
                 ob =p.getDespuesDePregunta().getCodigo();
             }
             String des = p.getDescripcionPregunta().replace("\\", "\\\\");
                consulta = "UPDATE pregunta SET orden = '" + p.getOrden() + "', obligatoria = '" + p.getObligatorioa() + "', tipo = '" + p.getTipo() + "', despuesDePregunta =" + ob + ", descripcionP = '"+des+"' WHERE idPregunta = '" + p.getCodigo() + "'";
            
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error modificando pregunta!. Error: " + ex);
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


    /*  public Control.Pregunta cargarPreguntasDeUnEnunciado(Control.Pregunta p){
         
     try{
     String consulta = "SELECT * FROM Pregunta WHERE idPregunta = '"+p.getCodigo()+"'"; // cambiar q.getCodigo() por el combobox de la pantalla seleccionarEnunciado
     Statement sta = con.createStatement();
     ResultSet rs = sta.executeQuery(consulta);
     if(rs.next()){
     p.setUrl(rs.getString("urlPregunta"));
     p.setEnunciado(rs.getInt("enunciado"));
     p.setOrden(rs.getInt("orden"));
     p.setObligatorioa(rs.getString("obligatoria"));
     p.setDespuesDePregunta(rs.getInt("despuesDePregunta"));
     return p;
     }
            
     }catch(Exception ex){
     JOptionPane.showMessageDialog(null,"Problemas en la consulta de la pregunta! Error: "+ ex);
     }
     return null;
     }*/
    public DTO.Pregunta cargarInformacionPregunta(DTO.Pregunta p) {
        
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM pregunta WHERE idPregunta = '" + p.getCodigo() + "'"; // cambiar q.getCodigo() por el combobox de la pantalla seleccionarEnunciado
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                p.setUrl(rs.getString("urlPregunta"));
                Enunciado enaux=new Enunciado();
                enaux.setCodigo(rs.getInt("enunciado"));
                enDAO.cargarEnunciado(enaux);
                p.setEnunciado(enaux);
                p.setOrden(rs.getInt("orden"));
                p.setObligatorioa(rs.getString("obligatoria"));
                Pregunta pregaux=new Pregunta();
                if(rs.getInt("despuesDePregunta") == 3){
                   
                    pregaux.setCodigo(0);
                    p.setDespuesDePregunta(pregaux);
                }else{
                    pregaux.setCodigo(rs.getInt("despuesDePregunta"));
                    p.setDespuesDePregunta(pregaux);
                }
                p.setTipo(rs.getInt("tipo"));
                p.setDescripcionPregunta(rs.getString("descripcionP"));
                return p;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la pregunta! Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return null;
    }

    public int crearPregunta(Pregunta p) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            Object ob = null;
            if (p.getDespuesDePregunta().getCodigo() != 0) {
                ob = p.getDespuesDePregunta().getCodigo();
            }
            String des = p.getDescripcionPregunta().replace("\\", "\\\\");
                consulta = "INSERT INTO pregunta VALUES('" + p.getCodigo() + "','" + p.getUrl() + "','" + p.getEnunciado().getCodigo() + "','" + p.getOrden() + "','" + p.getObligatorioa() + "'," + ob + ",'" + p.getTipo() + "','" + des + "')";
            
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            //con.close();
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas creando la pregunta!. Error: " + ex);
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

    public int borrarPregunta(int p) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "DELETE FROM pregunta WHERE idPregunta = '" + p + "'";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error borrando la pregunta ! Error: " + ex);
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

    public int obtenerUltimoCodigoPreguntaDeEnunciado(Pregunta p) {

        try {

            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(idPregunta) FROM pregunta ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                //con.close();
                return rs.getInt("MAX(idPregunta)") + 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo código de pregunta!. Error: " + ex);
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
    /*PreguntaDAO
     Valida si ya existe una pregunta con orden primero antes de crear o modificar
     una pregunta
     */

    public int ValidaOrdenPreguntaPrimero() {
        Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Pregunta WHERE orden =0";//cero es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {

                return 1;//Ya existe enunciado con orden primero
            } else {
                return 0;//no existe aun enunciado con orden

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de pregunta!. Error: " + ex);
        }finally {  
    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
}
        return 0;
    }

    /*PreguntaDAO
     Valida si ya existe una pregunta con orden Ultimo antes de crear o modificar
     una pregunta
     */
    public int ValidaOrdenPreguntaUltimo() {
        Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Prregunta WHERE orden =1";//cero es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {

                return 1;//Ya existe enunciado con orden primero
            } else {

                return 0;//no existe aun enunciado con orden

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de pregunta. Error: " + ex);
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return 0;
    }
     public void cargarPregunta(Pregunta preg){
        try{
            this.cargarInformacionPregunta(preg);
             preg.setOpciones(opD.listarCodigosOpcionesdePregunta(preg.getCodigo()));
            
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Error obteniendo opciones de una pregunta. Error: " + ex);
    
    }finally {  
    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
}
        

            

    }
     
     public Collection<Opcion> cargarOpcionesdePregunta(Pregunta preg){
         Collection<Opcion> codigos = new ArrayList();
    
        try{
            this.cargarInformacionPregunta(preg);
            codigos= opD.listarCodigosOpcionesdePregunta(preg.getCodigo());
            return codigos;
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, "Error obteniendo opciones de una pregunta. Error: " + ex);
    
    }finally {  
    if (con != null) try { con.close(); } catch (SQLException ignore) {}  
}
      return codigos;

}
}
