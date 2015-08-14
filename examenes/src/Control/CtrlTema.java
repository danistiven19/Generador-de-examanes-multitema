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
    
    private Integer year;
    private Integer semestre;
    private String jornada;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
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
     public void crearMaestro(Integer year, Integer semestre, String Jornada) throws IOException{
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
        lt.nuevoExamen(tema, this.getYear(), this.getSemestre(), this.getJornada());
    }

     public void imprimir(Tema t ) throws IOException{
         lt.nuevoTema(t, 2014, 1, "Mañana");
        // lt.nuevoTema(t, this.getYear(), this.getSemestre(), this.getJornada());
     }
     
     public int randomPreguntas() throws SQLException{
         return rDAO.randompreguntas();
     }
     
     public int reiniciarExamen(){
         temaDAO temadao = new temaDAO();
         preguntaTemaDAO ptdao = new preguntaTemaDAO();
         if(ptdao.borrarPreguntaTemas() == 1){
             temadao.borrarTemas();
             return 1;
         }else{
             return 0;
         }
     }
    
}
