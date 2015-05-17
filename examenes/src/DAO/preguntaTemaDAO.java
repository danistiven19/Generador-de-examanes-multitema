package DAO;

import DTO.Enunciado;
import DTO.Pregunta;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
import DTO.Pregunta_Tema;
import DTO.Tema;
import DTO.combinacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author luisa.suarezz
 */
public class preguntaTemaDAO {

    private BD bd;
    private Connection con;

    public preguntaTemaDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    public boolean existePreguntaTema(int p) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Pregunta_Tema2 WHERE f_tema = '" + p + "'"; // cambiar q.getCodigo() por el combobox de la pantalla seleccionarEnunciado
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la existencia de pregunta tema! Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return false;
    }

    public int ingresarPreguntaTema(Pregunta_Tema p) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            if (p.getRespuesta() != 0) {
                consulta = "INSERT INTO Pregunta_Tema VALUES('" + p.getTema().getCodigo() + "','" + p.getPregunta().getCodigo() + "','" + p.getRespuesta() + "','" + p.getNroPregunta() + "','" + p.getCombinacion().getCodigo() + "')";
            } else {
                consulta = "INSERT INTO Pregunta_Tema (f_tema, f_pregunta, nroPregunta, combinacionOpcion_idcombinacion) VALUES('" + p.getTema().getCodigo() + "','" + p.getPregunta().getCodigo() + "','" + p.getNroPregunta() + "','" + p.getCombinacion().getCodigo() + "')";
            }

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            //con.close();
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            System.out.println("Problemas creando preguntaTema!. Error: " + ex);
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

    public int ingresarPreguntaTema2(Pregunta_Tema p) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            int cont = p.getNroPregunta();
            if (p.getRespuesta() != 0) {
                consulta = "INSERT INTO Pregunta_Tema2 VALUES('" + p.getTema().getCodigo() + "','" + p.getPregunta().getCodigo() + "','" + p.getRespuesta() + "','" + cont + "','" + p.getCombinacion().getCodigo() + "')";
            } else {
                consulta = "INSERT INTO Pregunta_Tema2 (f_tema, f_pregunta, nroPregunta, combinacionOpcion_idcombinacion) VALUES('" + p.getTema().getCodigo() + "','" + p.getPregunta().getCodigo() + "','" + cont + "','" + p.getCombinacion().getCodigo() + "')";
            }

            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            ////con.close();
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas creando preguntaTema!. Error: " + ex);
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

    public DTO.Pregunta_Tema consultarPreguntaTema(Pregunta_Tema pt) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Pregunta_Tema WHERE idOpcion = '" + pt.getTema() + "' and fpregunta='" + pt.getPregunta() + "'"; // cambiar q.getCodigo() por el combobox de la pantalla seleccionarEnunciado
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                Tema t = new Tema();
                t.setCodigo(rs.getInt("f_tema"));
                pt.setTema(t); //claveprimaria
                Pregunta p = new Pregunta();
                p.setCodigo(rs.getInt("f_pregunta"));
                pt.setPregunta(p); //claveprimaria
                pt.setRespuesta(rs.getInt("respuesta"));
                combinacion c = new combinacion();
                c.setCodigo(rs.getInt("combinacionOpcion"));
                pt.setCombinacion(c);
                pt.setNroPregunta(rs.getInt("nroPregunta"));
                return pt;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de PreguntaTema! Error: " + ex);
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

    public int borrarPreguntaTemas() {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
     
            String consulta = "DELETE FROM pregunta_tema";
            Statement sta = con.createStatement();
            sta.executeUpdate(consulta);
            consulta = "DELETE FROM pregunta_tema2";
            sta = con.createStatement();
            sta.executeUpdate(consulta);
            return 1;
        } catch (Exception ex) {
            System.out.println("Problemas borrando preguntaTemas!. Error: " + ex);
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
