/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.preguntaTemaDAO;
import DAO.randomDAO;
import DAO.rutaDAO;
import DAO.temaDAO;
import DTO.Pregunta_Tema;
import DTO.Tema;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 *
 * @author Daniel
 */
public class CtrlTema {
    rutaDAO url = new rutaDAO();
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
     public void crearMaestro(String year, String semestre, String Jornada) throws IOException{
        Tema tema=new Tema();
           Calendar fecha = new GregorianCalendar();
        java.util.Date ju = fecha.getTime();
        java.sql.Date  date = new java.sql.Date(ju.getTime());
        tema.setFechaCreacion(date);
        tema.setCodigo(0);
        tema.setJornada(Jornada);
        temaDAO tdao = new temaDAO();
        this.reiniciarExamen();
        /*if(tdao.ingresarTema(tema) == 0){
            if(this.reiniciarExamen() == 1){
                this.reiniciarExamen();
                tdao.ingresarTema(tema);
            }
        }*/
        tdao.ingresarTema(tema);
        lt.nuevoExamen(tema, year, semestre);
    }

     public void imprimir(Tema t ) throws IOException{
         lt.nuevoTema(t, "2014", "2");
     }
     
     public int randomPreguntas() throws SQLException{
         return rDAO.randompreguntas();
     }
     
     public int reiniciarExamen() throws IOException{
         temaDAO temadao = new temaDAO();
         preguntaTemaDAO ptdao = new preguntaTemaDAO();
         if(ptdao.borrarPreguntaTemas() == 1){
             Collection<Tema> temas = listarTemas();
             Iterator it = temas.iterator();
             while(it.hasNext()){
                 Tema tema = (Tema) it.next();
                 String ruta = url.getRuta() + "\\examen" + tema.getCodigo();
                 File f = new File(ruta);
                 if(f.exists()){
                     Runtime.getRuntime().exec("cmd /c "+url.getUnidad()+" && cd "+url.getRuta()+" && start /b /wait \n RD /S /Q examen" + tema.getCodigo() + " \n exit");
                 }
             }
             
             temadao.borrarTemas();
             return 1;
         }else{
             return 0;
         }
     }
    
}
