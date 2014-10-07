/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import DTO.Tema;
import Control.latex;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFrame;

/**
 *
 * @author Daniel
 */
public class Examenes {

    /**
     * @param args the command line arguments
     */
    public Examenes(){
        
    }
    
    public static void main(String[] args) {
        principal p = new principal();
        p.show();
        
    }
    
    public void abrirCredenciales(){
        credenciales cred = new credenciales();
        cred.show();
    }
    public void abrirAdminEnunciados(){
        seleccionarEnunciado admon = new seleccionarEnunciado();
        admon.show();
    }
    public void abrirEnunciado( DTO.Enunciado en, int sel) throws ParseException{
        administrarEnunciado admon = new administrarEnunciado(en,sel);
        admon.show();
    }
    public void abrirPregunta(DTO.Pregunta p , int sel){
        AdministrarPregunta admon = new AdministrarPregunta(p,sel);
        admon.show();
        
    }
    public void abrirOpcion(DTO.Opcion o, int sel) {
        opcion admon = new opcion(o, sel);
        admon.show();
    }
 
    public void abrirPrincipal(){
        principal ppal = new principal();
        ppal.show();
    }
    public void abrirRespuestas(){
        Respuestas rta = new Respuestas();
        rta.show();
    }
  
    
}
