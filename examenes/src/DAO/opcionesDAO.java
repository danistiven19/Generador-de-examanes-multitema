/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Opcion;
import DTO.Pregunta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author telematica
 */
public class opcionesDAO {

    private BD bd;
    private Connection con;

    public opcionesDAO() {
        bd = new BD();
        con = bd.conexion();
    }

   
public Collection<Opcion> listarCodigosOpcionesdePregunta(int p1) {
        Collection<Opcion> codigos = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Opcion WHERE pregunta = '" + p1 + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);

            while (rs.next()) {
                Opcion op = new Opcion();
                op.setCodigo(rs.getInt("idOpcion"));
                op.setDescripcionOpcion(rs.getString("descripcionO"));
                op.setOrden(rs.getInt("orden"));
                Pregunta preg=new Pregunta();
                preg.setCodigo(rs.getInt("pregunta"));
                op.setPregunta(preg);
                op.setUrl(rs.getString("urlOpcion"));
                Opcion opa = new Opcion();
                opa.setCodigo(rs.getInt("despuesDeOpcion"));
                op.setdespuesDeOpcion(opa);

                codigos.add(op);
            }
            // //con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de codigo de opcion de una pregunta! Error: " + e);
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




    public DTO.Opcion cargarOpcion(DTO.Opcion op) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Opcion WHERE idOpcion = '" + op.getCodigo() + "' AND pregunta = '" + op.getPregunta().getCodigo() + "'";
                Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                op.setUrl(rs.getString("urlOpcion"));
                op.setOrden(rs.getInt("orden"));
                Opcion oAux = new Opcion();
                 if(rs.getInt("despuesDeOpcion") == 3){
                    oAux.setCodigo(0);
                }else{
                    oAux.setCodigo(rs.getInt("despuesDeOpcion"));
                }
                 op.setdespuesDeOpcion(oAux);
                Pregunta preg=new Pregunta();
                preg.setCodigo(rs.getInt("pregunta"));
                op.setPregunta(preg);
                op.setDescripcionOpcion(rs.getString("descripcionO"));
                return op;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la opcion! Error: " + ex);
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

    public int crearOpcion(Opcion op) {
        String consulta;
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            Object ob = null;
            if (op.getdespuesDeOpcion().getCodigo() != 0) {
                ob = op.getdespuesDeOpcion().getCodigo();
            }
            String des = op.getDescripcionOpcion().replace("\\", "\\\\");
                consulta = "INSERT INTO Opcion VALUES(" + op.getCodigo() + ",'" + op.getUrl() + "','" + op.getOrden() + "'," + ob + ",'" + op.getPregunta().getCodigo() + "','" + des + "')";
            
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            //con.close();
            if (rs == 1 || rs == 4 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas creando la opcion!. Error: " + ex);
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

    public int obtenerUltimoCodigoOpcionPregunta(Opcion op) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(idOpcion) FROM Opcion where pregunta = '" + op.getPregunta().getCodigo() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                //con.close();
                return rs.getInt("MAX(idOpcion)") + 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo código de opcion!. Error: " + ex);
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

    public int modificarOpcion(DTO.Opcion p) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            Object ob = null;
            if (p.getdespuesDeOpcion().getCodigo() != 0) {
                ob = p.getdespuesDeOpcion().getCodigo();
            }
            String des = p.getDescripcionOpcion().replace("\\", "\\\\");
            String consulta = "UPDATE  opcion SET orden = '" + p.getOrden() + "',  despuesDeOpcion = "+ ob + ", descripcionO = '"+des+"' WHERE idOpcion = '" + p.getCodigo() + "'";  
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error modificando opcion!. Error: " + ex);
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

    public int vaciarOpcion(int p) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "DELETE FROM opcion WHERE idOpcion = '" + p + "'";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                JOptionPane.showMessageDialog(null, "Opcion limpiada !");
                return 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error limpiando la opcion ! Error: " + ex);
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
/*
    public boolean verificarOpcion(Control.Opcion op) {
        ArrayList codigos = new ArrayList();
        Iterator j;
        Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Opcion WHERE pregunta = '" + op.getPregunta() + "'"; // cambiar q.getCodigo() por el combobox de la pantalla seleccionarEnunciado
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                codigos.add(rs.getInt("idOpcion"));
            }
         
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de codigo de opcion en verficar! Error: " + e);

        }
        int contador = 0;
        j = codigos.iterator();
        while (j.hasNext()) {
            contador = contador + 1;

        }
        if (contador >= 4) {
            return false;
        }
        return true;
    }*/

}
