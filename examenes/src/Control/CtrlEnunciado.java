/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.areaDAO;
import DAO.enunciadoDAO;
import DAO.preguntaDAO;
import DTO.Area;
import DTO.Autor;
import DTO.Enunciado;
import DTO.Pregunta;
import java.util.Collection;

/**
 *
 * @author Daniel
 */
public class CtrlEnunciado {
    enunciadoDAO ed=new enunciadoDAO();
    preguntaDAO p1=new preguntaDAO(); 
    areaDAO area=new areaDAO();
    
    
    public void eliminarEnunciado(int enunciadoEliminar){
        ed.borrarEnunciado(enunciadoEliminar);
    }
     public void cargarEnunciado(Enunciado en){
         ed.cargarEnunciado(en);
     }
     
     public Collection<Enunciado> listarCodigos(){
         return ed.listarCodigos();
     }
     public Collection<Autor> listarAutorres(){
         return ed.cargarAutores();
     }
     
     public int obtenerCodigo(Enunciado en){
         return ed.obtenerCodigo(en);
     }
     
     public int crearEnunciado(Enunciado en){
         return ed.crearEnunciado(en);
     }
     public int modificarEnunciado(Enunciado en){
         return ed.modificarEnunciado(en);
     }
     
      public int obtenerUltimoCodigoOpcionPregunta(Pregunta p){
         return p1.obtenerUltimoCodigoPreguntaDeEnunciado(p);
     }
       public Collection<Area> listarAreas(){
           return area.listarAreas();
       }
}
