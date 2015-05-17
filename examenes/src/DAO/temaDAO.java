package DAO;

import Control.Combinatoria;
import DTO.Pregunta;
import DTO.Pregunta_Tema;
import DTO.Tema;
import DTO.combinacion;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author luisa.suarezz
 */
public class temaDAO {

    //ingresarTema
    private BD bd;
    private Connection con;

    public temaDAO() {
        bd = new BD();
        con = bd.conexion();
    }

    public int ingresarTema(Tema t) {
        try {
            String consulta;
            if (con.isClosed()) {
                con = bd.conexion();
            }
            consulta = "INSERT INTO tema VALUES('" + t.getCodigo() + "','" + t.getFechaCreacion() + "')";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            //con.close();
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            System.out.println("Problemas creando el tema!. Error: " + ex);
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

    public boolean existeTema(int p) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM Tema WHERE codigo = '" + p + "'"; // cambiar q.getCodigo() por el combobox de la pantalla seleccionarEnunciado
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la consulta de la combinacion! Error: " + ex);
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

    /*   public int crearTema(LinkedList lk) {
     Combinatoria cm = new Combinatoria();
     Iterator it = lk.iterator();
     Tema t = new Tema();
     int ultimo = ObtenerUltimoTema();
     t.setCodigo(ultimo + 1);

     Calendar fecha = new GregorianCalendar();
     int year = fecha.get(Calendar.YEAR);
     int mes = fecha.get(Calendar.MONTH);
     int dia = fecha.get(Calendar.DAY_OF_MONTH);

     String aa = Integer.toString(year);
     String mm = Integer.toString(mes + 1);
     String dd = Integer.toString(dia);

     String fa = aa + "/" + mm + "/" + dd;
     t.setFechaCreacion(fa);
     t.setJornada("tarde");

     if (ingresarTema(t) == 1) {
     preguntaTemaDAO ptd = new preguntaTemaDAO();
     while (it.hasNext()) {
     Object ob = it.next();
     if (ob != null && ob != "") {
     Pregunta pp = (Pregunta) ob;
     int com = cm.verificarCombinacion(pp);
     Pregunta_Tema p = new Pregunta_Tema();
     p.setCombinacion(com);
     p.setPregunta(pp.getCodigo());
     p.setTema(ultimo + 1);
     ptd.ingresarPreguntaTema2(p);
     }
     }
     }
     return 1;

     }*/
    public int crearTema(LinkedList lk) {
        Iterator it = lk.iterator();
        Tema t = new Tema();
        int ultimo = ObtenerUltimoTema();
        t.setCodigo(ultimo + 1);

        Calendar fecha = new GregorianCalendar();
        java.util.Date util = new java.util.Date();
        Date date = new Date(util.getTime());
        t.setFechaCreacion(date);
        t.setJornada("tarde");

        if (ingresarTema(t) == 1) {
            preguntaTemaDAO ptd = new preguntaTemaDAO();
            while (it.hasNext()) {
                Pregunta_Tema p = (Pregunta_Tema) it.next();
                Tema tt = new Tema();
                tt.setCodigo(ultimo + 1);
                p.setTema(tt);
                ptd.ingresarPreguntaTema2(p);
            }
        }
        return 1;
    }

    private int ObtenerUltimoTema() {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT MAX(codigo) as maximo FROM tema";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                return rs.getInt("maximo");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas en la obtencion del ultimo tema! Error: " + ex);
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

    public Tema cargarTema(Tema t) {

        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            Collection<Pregunta_Tema> pt = new ArrayList<Pregunta_Tema>();
            String consulta = "SELECT * FROM tema WHERE codigo = '" + t.getCodigo() + "'";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            if (rs.next()) {
                t.setFechaCreacion(rs.getDate("fechaCreacion"));
                consulta = "SELECT * FROM pregunta_Tema2 WHERE f_Tema = '" + t.getCodigo() + "'";
                ResultSet rs1 = sta.executeQuery(consulta);
                while (rs1.next()) {
                    Pregunta_Tema ptt = new Pregunta_Tema();
                    combinacion c = new combinacion();
                    c.setCodigo(rs1.getInt("combinacionOpcion_idCombinacion"));
                    ptt.setCombinacion(c);
                    Pregunta p = new Pregunta();
                    p.setCodigo(rs1.getInt("f_pregunta"));
                    ptt.setPregunta(p);
                    ptt.setRespuesta(rs1.getInt("respuesta"));
                    ptt.setNroPregunta(rs1.getInt("nroPregunta"));
                    pt.add(ptt);

                }
                t.setPreguntas(pt);
            }
            return t;

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

    public Collection<Tema> listarTemas() {
        Collection<Tema> temas = new ArrayList<Tema>();
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            String consulta = "SELECT * FROM tema";
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery(consulta);
            while (rs.next()) {
                Tema t = new Tema();
                t.setCodigo(rs.getInt("codigo"));
                t.setFechaCreacion(rs.getDate("fechaCreacion"));
                temas.add(t);
            }
            return temas;
        } catch (Exception ex) {
            System.out.println("Problemas listando los temas existentes");
        }
        return null;
    }

    public boolean ingresarRespuesta(Tema t) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            Collection<Pregunta_Tema> preguntas = t.getPreguntas();
            Iterator it = preguntas.iterator();
            while (it.hasNext()) {
                Pregunta_Tema pt = (Pregunta_Tema) it.next();
                pt.setTema(t);
                String consulta = "UPDATE pregunta_Tema2 SET respuesta = " + pt.getRespuesta() + " WHERE f_tema = " + pt.getTema().getCodigo() + " AND f_pregunta = " + pt.getPregunta().getCodigo();
                Statement sta = con.createStatement();
                sta.executeUpdate(consulta);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ingresando las respuestas " + ex);
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

    public void desplegarRespuestas(Tema maestro) {
        try {
            if (con.isClosed()) {
                con = bd.conexion();
            }
            Collection<Tema> temas = this.listarTemas();
            Iterator it = temas.iterator();
            while (it.hasNext()) {
                Tema tt = (Tema) it.next();
                this.cargarTema(tt);
                if (tt.getCodigo() != 0) {
                    Collection<Pregunta_Tema> preguntas = tt.getPreguntas();
                    Iterator it2 = preguntas.iterator();
                    while (it2.hasNext()) {
                        Pregunta_Tema pt = (Pregunta_Tema) it2.next();
                        Iterator it3 = maestro.getPreguntas().iterator();
                        while (it3.hasNext()) {
                            Pregunta_Tema ptMaestro = (Pregunta_Tema) it3.next();
                            if (ptMaestro.getPregunta().getCodigo() == pt.getPregunta().getCodigo()) {
                                pt.setRespuesta(ptMaestro.getRespuesta());
                                break;
                            }
                        }
                    }
                    this.ingresarRespuesta(tt);
                }

            }
        } catch (Exception ex) {
            System.out.println("Problemas desplegando las respuestas! " + ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
    }

    public int borrarTemas() {
        try {
            String consulta;
            if (con.isClosed()) {
                con = bd.conexion();
            }
            consulta = "DELETE FROM tema";
            Statement sta = con.createStatement();
            int rs = sta.executeUpdate(consulta);
            //con.close();
            if (rs == 1 || rs == 4) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas eliminando temas!. Error: " + ex);
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
