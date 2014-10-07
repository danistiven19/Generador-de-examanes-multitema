/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.opcionesDAO;
import DTO.Opcion;

/**
 *
 * @author Luisa
 */
public class CtrlOpcion {
     opcionesDAO pDAO = new opcionesDAO();
     
     public int crearOpcion(Opcion o){
         o.setCodigo(pDAO.obtenerUltimoCodigoOpcionPregunta(o));
         return pDAO.crearOpcion(o);
     }
     public int modificarOpcion(Opcion o){
         return pDAO.modificarOpcion(o);
     }
     
     public Opcion cargarOpcion(Opcion op){
         return pDAO.cargarOpcion(op);
     }
}
