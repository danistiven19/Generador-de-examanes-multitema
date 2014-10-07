/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.preguntaTemaDAO;
import DTO.Opcion;
import DTO.Pregunta;
import DTO.Pregunta_Tema;
import DTO.Tema;
import java.util.Collection;

/**
 *
 * @author Daniel
 */
public class CtrlPregunta {
    private preguntaTemaDAO   ptDAO = new preguntaTemaDAO();
    private DAO.opcionesDAO op1 =new  DAO.opcionesDAO();
  private DAO.preguntaDAO p1 =new  DAO.preguntaDAO();
    
    public Collection<Pregunta> listarcodigos(int codigoEnunciado){
             return p1.listarCodigosPreguntasdeEnunciado(codigoEnunciado);
    }
    
    public int obtenerCodigo(Pregunta preg){
        return p1.obtenerUltimoCodigoPreguntaDeEnunciado(preg);
        
    }
    
    public int crearPregunta(Pregunta p){
         return p1.crearPregunta(p);
    }
    
    public int modificarPregunta(Pregunta p){
        return p1.modificarPregunta(p);
    }
    
    public Pregunta cargarInfPreg(Pregunta p){
         p1.cargarPregunta(p);
         return p;
    }
    
     public Collection<Opcion> listarOpcionesDePRegunta(Pregunta preg){
              return p1.cargarOpcionesdePregunta(preg);
    }
     
     public int obtenerUltimoCodigoOpcionPregunta(Opcion o){
         return op1.obtenerUltimoCodigoOpcionPregunta(o);
     }
}
