/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Area;
import DTO.Autor;
import DTO.Enunciado;
import DTO.Tema;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class enunciadoDAO {

    private BD bd;
    private Connection con;

    public enunciadoDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    public ArrayList listarCodigos() {
        ArrayList codigos = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT codigo FROM Enunciado ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                codigos.add(rs.getInt("codigo"));
            }
            //con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de codigo! Error: " + e);
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
    
    public Collection<Autor> cargarAutores(){
         Collection<Autor> autores = new ArrayList();

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM autor ";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setCodigo(rs.getInt("idAutor"));
                autor.setNombre(rs.getString("nombre"));
                autor.setTelefono(rs.getString("telefono"));
                autores.add(autor);
            }
            //con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de codigo! Error: " + e);
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return autores;
    }

    public void cargarEnunciado(DTO.Enunciado e) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT e.*, a.* FROM enunciado e, autor a WHERE codigo = '" + e.getCodigo() + "' AND e.autor = a.idAutor";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                Autor autor = new Autor();
                autor.setCodigo(rs.getInt("a.idAutor"));
                autor.setNombre(rs.getString("a.nombre"));
                e.setAutor(autor);
                e.setUrl(rs.getString("e.urlEnunciado"));
                Area are = new Area();
                are.setCodigo(rs.getInt("e.area"));
                e.setArea(are);
                e.setFechaCreacion(rs.getString("e.fechaCreacion"));
                e.setOrden(rs.getInt("e.orden"));
                Enunciado enaux = new Enunciado();

                if (rs.getInt("e.despuesDeEnunciado") == 3) {
                    enaux.setCodigo(0);
                } else {
                    enaux.setCodigo(rs.getInt("e.despuesDeEnunciado"));
                }
                e.setDespuesDe(enaux);

                e.setEnunciado(rs.getString("e.descripcionE"));
                //con.close();

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta del enunciado! Error: " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }

        return;
    }

    public int modificarEnunciado(DTO.Enunciado en) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            Object  ob = null;
            if(en.getDespuesDe().getCodigo() !=0){
                ob =en.getDespuesDe().getCodigo();
            }
            String des = en.getEnunciado().replace("\\", "\\\\");
                consulta = "UPDATE enunciado SET orden = '" + en.getOrden() + "', autor = '" + en.getAutor().getCodigo() + "', area = '" + en.getArea().getCodigo() + "', despuesDeEnunciado =" + ob+ ", descripcionE ='"+des+"' WHERE codigo = '" + en.getCodigo() + "'";
          
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                return 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error modificando enunciado!. Error: " + ex);
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

    public int crearEnunciado(Enunciado en) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta;
            String des = en.getEnunciado().replace("\\", "\\\\");
            if (en.getDespuesDe().getCodigo() != 0) {
                consulta = "INSERT INTO Enunciado VALUES('" + en.getCodigo() + "','" + en.getUrl() + "','" + en.getFechaCreacion() + "','" + en.getOrden() + "','" + en.getAutor().getCodigo() + "','" + en.getArea().getCodigo() + "','" + en.getDespuesDe().getCodigo() + "','" + en.getPaquetes() + "', '" + des + "')";
            } else {
                consulta = "INSERT INTO Enunciado (codigo, urlEnunciado, fechaCreacion, orden, autor, area, nombre, descripcionE) VALUES('" + en.getCodigo() + "','" + en.getUrl() + "','" + en.getFechaCreacion() + "','" + en.getOrden() + "','" + en.getAutor().getCodigo() + "','" + en.getArea().getCodigo() + "','" + en.getPaquetes() + "', '" + des + "')";
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
            JOptionPane.showMessageDialog(null, "Problemas creando el enunciado!. Error: " + ex);
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

    public int obtenerCodigo(Enunciado en) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(Codigo) FROM Enunciado";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                //con.close();
                return rs.getInt("MAX(Codigo)") + 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo código de enunciado!. Error: " + ex);
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

    public int borrarEnunciado(int en) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "DELETE FROM enunciado WHERE codigo = '" + en + "'";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            if (rs == 1 || rs == 4) {
                JOptionPane.showMessageDialog(null, "Enunciado borrado !");
                return 1;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error borrando el enunciado ! Error: " + ex);
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
    /*enunciadoDAO
     Valida si ya existe un enunciado con orden primero antes de crear o modificar
     enunciado
     */

    public int ValidaOrdenEnunciadoPrimero() {
        Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Enunciado WHERE orden =0";//cero es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return 1;//Ya existe enunciado con orden primero
            } else {
                return 0;//no existe aun enunciado con orden

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de enunciado!. Error: " + ex);
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
    /*enunciadoDAO
     Valida si ya existe un enunciado con orden Ultimo antes de crear o modificar
     enunciado
     */

    public int ValidaOrdenEnunciadoUltimo() {
        Connection con = bd.conexion();
        try {
            String consulta = "SELECT * FROM Enunciado WHERE orden =1";//cero es orden primero, 1 orden ultimo, 2 ningun orden
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {

                return 1;//Ya existe enunciado con orden primero
            } else {

                return 0;//no existe aun enunciado con orden

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error obteniendo orden de enunciado!. Error: " + ex);
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
