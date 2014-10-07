/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.preguntaTemaDAO;
import DAO.randomDAO;
import DAO.temaDAO;
import DTO.Pregunta_Tema;
import DTO.Tema;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 *
 * @author Daniel
 */
public class CtrlTema {
    private preguntaTemaDAO ptDAO = new preguntaTemaDAO();
    private temaDAO temaDAO = new temaDAO();
    private latex lt = new latex();
     randomDAO rDAO = new randomDAO();
    public Tema cargarTema(int t){   
        Tema tt = new Tema();
        tt.setCodigo(t);
        return temaDAO.cargarTema(tt);
        
    }
    public Collection<Tema> listarTemas(){
        return temaDAO.listarTemas();
    }
    
    public void ingresarRespuesta(Tema t){
        temaDAO.ingresarRespuesta(t);
    }
    public void desplegarRespuestas(Tema maestro){
        temaDAO.desplegarRespuestas(maestro);
    }
     public void crearMaestro() throws IOException{
        Tema tema=new Tema();
           Calendar fecha = new GregorianCalendar();
        java.util.Date ju = fecha.getTime();
        java.sql.Date  date = new java.sql.Date(ju.getTime());
        tema.setFechaCreacion(date);
        tema.setCodigo(0);
        tema.setJornada("Mañana");
        temaDAO tdao = new temaDAO();
        tdao.ingresarTema(tema);
        lt.nuevoExamen(tema, "2014", "1");
    }

     public void imprimir(Tema t) throws IOException{
         lt.nuevoTema(t, "2014", "2");
     }
     
     public int randomPreguntas() throws SQLException{
         return rDAO.randompreguntas();
     }
    
}
