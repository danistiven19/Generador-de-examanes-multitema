/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import DAO.combinacionDAO;
import DAO.preguntaDAO;
import DAO.enunciadoDAO;
import DAO.opcionesDAO;
import DAO.temaDAO;
import DTO.Enunciado;
import DTO.Opcion;
import DTO.Pregunta;
import DTO.Pregunta_Tema;
import DTO.combinacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author LIS
 */
public class Combinatoria {

    public LinkedList ordenamiento(ResultSet rs) throws SQLException{
        LinkedList lista = new LinkedList();
        LinkedList definitivo = new LinkedList();
        preguntaDAO pDAO = new preguntaDAO();
        //lista.addFirst("");
        while(rs.next()){
            Pregunta preg = new Pregunta();
            preg.setCodigo(rs.getInt("f_pregunta"));
            pDAO.cargarPregunta(preg);
            lista.add(preg);
        }
        lista = ordenar(lista);
         int com=1;
         int cont =1;
        Iterator it = lista.iterator();
        while(it.hasNext()){
            Pregunta p = (Pregunta) it.next();
            //Busca una cobinacion válida para las opciones
             com = verificarCombinacion(p);
            //Utilizar TemaDAO para crear un nuevo tema y allí crear todas las preguntaTema con la
            //"lista" y cada una de las combinaciones que resulta de la instrucción nterior
            Pregunta_Tema pt = new Pregunta_Tema();
            combinacion comb = new combinacion();
            comb.setCodigo(com);
            pt.setCombinacion(comb);
            pt.setPregunta(p);
            pt.setNroPregunta(cont);
            definitivo.add(pt);
            cont++;
        } 
        
        temaDAO tDAO = new temaDAO();
        tDAO.crearTema(definitivo);
        return definitivo;
    }
    
    public LinkedList ordenar(LinkedList lk){
        LinkedList lk2 = new LinkedList();
        Iterator it = lk.iterator();
        enunciadoDAO enDAO =new enunciadoDAO();
        while(it.hasNext()){
            Object ob = it.next();
            if( ob != null && ob != ""){
            Pregunta p = (Pregunta) ob;
            Enunciado en =new Enunciado();
            en.setCodigo(p.getEnunciado().getCodigo());
            enDAO.cargarEnunciado(en);
            /*if(en.getOrden() == 1){
                //lk2.addFirst(p);
            }else if(en.getOrden() == 2){
                //lk2.add(p);
            }else */
            if(en.getDespuesDe().getCodigo() != 0){
                lk2 =verificaCondicionEnunciado(lk2,p);
            }else{
                //Verifica que en la lista nueva no haya otra preg del mismo enunciado o que no haya 
                //en la lista nueva un enunciado que tenga como condicion su propio enunciado en despuesde
               lk2=  verificarpreguntas(lk2, p);
            }
            }
        }
        
        return lk2;
    }
    
    public int verificarCombinacion(Pregunta p){
                  // Selecciona una de las 24 opciones
            //combiacion tendrá los atributos A, B, C, D
        Vector v=llenarVec(p);
        Random r = new Random();
        combinacionDAO cDAO = new combinacionDAO();
        opcionesDAO opd=new opcionesDAO();
        combinacion com = new combinacion();
        int sel=0;
        int tam = v.size();
        sel = r.nextInt(tam+1);
        if(sel != 0){
            com = (combinacion) v.get(sel-1);
        }else{
            com = (combinacion) v.get(sel);
        }
        //com.setCodigo(sel);
      // cDAO.cargarCombinacion(com);

        for (int kk =0; kk<v.size(); kk ++){
        opcionesDAO oDAO = new opcionesDAO();
            //cargar opciones de una pregunta p falta!
             Collection<Opcion> opciones = (ArrayList<Opcion>) oDAO.listarCodigosOpcionesdePregunta(p.getCodigo());
            Iterator i = opciones.iterator();
            int temp = 0;
            while(i.hasNext()){
                Object oj = i.next();
                if(oj != null && oj != ""){
                Opcion op = (Opcion) oj;
                if(com.getA() == op.getCodigo()){
        if(op.getdespuesDeOpcion().getCodigo() != 0 || op.getOrden() == 2){
                        temp = 1;
                        continue;
                    }
                }else if(com.getD() == op.getCodigo()){
                    if(op.getOrden() == 1){
                        temp = 1;
                        continue;
                    }else if(op.getdespuesDeOpcion().getCodigo() != 0 ){
                        //buscar la opcion C y preguntar si cumple la condicion despues de de la D
                        //if(!cumple despues de)
                         if(com.getB() != op.getdespuesDeOpcion().getCodigo()){

                            temp = 1;
                            continue;
                        }
                    }
                }else{
                    if(op.getOrden() == 1 || op.getOrden() == 2 ){
                        temp = 1;
                        continue;
                    }
                    if((com.getC() == op.getCodigo() && op.getdespuesDeOpcion().getCodigo() != 0 && op.getdespuesDeOpcion().getCodigo() != com.getB()) || (com.getB() == op.getCodigo() && op.getdespuesDeOpcion().getCodigo() != 0 && op.getdespuesDeOpcion().getCodigo() != com.getA())){

                        temp =1;
                        continue;
                    }
                }
                
            }
            }
            if(temp != 0){
                    break;
            }else{
                return com.getCodigo();
            }
        }
        return 1;
    }
    
   
    
     public Vector llenarVec(Pregunta p){
         opcionesDAO oDAO = new opcionesDAO();
         combinacionDAO cDAO = new combinacionDAO();
                  Vector v1 = new Vector();
                  int consult =0;
         Vector v = new Vector();
         for (int ii=0; ii< 4; ii++){
             v.add(0);
         }
         //cargar opciones de una pregunta p falta!
       /*  for(int ob =1; ob <5; ob++){
             Opcion op = new Opcion() ;
             op.setCodigo(ob);
             op.setPregunta(p.getCodigo());
             oDAO.cargarOpcion(op);
             if(op.getOrden() == 1){
                 v.set(0, op.getCodigo());
             }else if(op.getOrden() == 2){
                 v.set(3,op.getCodigo());
                }else if(op.getdespuesDeOpcion().getCodigo() != 0){
                 for(int h=0; h< v.size(); h++){
                     Object ob1 = v.get(h);
                        if(ob1 != null){
                         Opcion aux = new Opcion();
                         aux.setCodigo((Integer) ob1);
                         aux.setPregunta(p.getCodigo());
                        oDAO.cargarOpcion(aux);
                             if(aux.getCodigo() == op.getdespuesDeOpcion().getCodigo()){
                                 v.add(h+1,op.getCodigo());
                                 break;
                             }
                        }
                 }
                 
                 if(v1.isEmpty()){
                
                 for(int k = 0; k<23; k++){
                      combinacion com = new combinacion();
                      com.setCodigo(k);
                      cDAO.cargarCombinacion(com);
  if((com.getB() == op.getCodigo() && com.getA() == op.getdespuesDeOpcion().getCodigo()) || (com.getC() == op.getCodigo() && com.getB() == op.getdespuesDeOpcion().getCodigo()) || (com.getD() == op.getCodigo() && com.getC() == op.getdespuesDeOpcion().getCodigo())){
                          consult =1;  
                          v1.add(com);
                      }
                 }
                 }else{
                     int tam = v1.size();
                     for(int k =0; k< tam; k++){
                         if(v1.get(k) != null){
                         combinacion com = new combinacion();
                         com= (combinacion) v1.get(k);
                          if(!((com.getB() == op.getCodigo() && com.getA() == op.getdespuesDeOpcion().getCodigo()) || (com.getC() == op.getCodigo() && com.getB() == op.getdespuesDeOpcion().getCodigo()) || (com.getD() == op.getCodigo() && com.getC() == op.getdespuesDeOpcion().getCodigo()))){

                            consult =1;  
                             v1.set(k,null);
                         }
                         }
                     }
                 }
                 
             }
             }*/
         
         Iterator iOpcion = p.getOpciones().iterator();
          while(iOpcion.hasNext()){
             Opcion op = (Opcion) iOpcion.next() ;
           //  op.setCodigo(ob);
            // op.setPregunta(p.getCodigo());
             //oDAO.cargarOpcion(op);
             if(op.getOrden() == 1){
                 v.set(0, op.getCodigo());
             }else if(op.getOrden() == 2){
                 v.set(3,op.getCodigo());
                }else if(op.getdespuesDeOpcion().getCodigo() != 0){
                 for(int h=0; h< v.size(); h++){
                     Object ob1 = v.get(h);
                        if(ob1 != null){
                         Opcion aux = new Opcion();
                         aux.setCodigo((Integer) ob1);
                         aux.setPregunta(p);
                        oDAO.cargarOpcion(aux);
                             if(aux.getCodigo() == op.getdespuesDeOpcion().getCodigo()){
                                 v.add(h+1,op.getCodigo());
                                 break;
                             }
                        }
                 }
                 
                 if(v1.isEmpty()){
                
                 for(int k = 0; k<23; k++){
                      combinacion com = new combinacion();
                      com.setCodigo(k);
                      cDAO.cargarCombinacion(com);
  if((com.getB() == op.getCodigo() && com.getA() == op.getdespuesDeOpcion().getCodigo()) || (com.getC() == op.getCodigo() && com.getB() == op.getdespuesDeOpcion().getCodigo()) || (com.getD() == op.getCodigo() && com.getC() == op.getdespuesDeOpcion().getCodigo())){
                          consult =1;  
                          v1.add(com);
                      }
                 }
                 }else{
                     int tam = v1.size();
                     for(int k =0; k< tam; k++){
                         if(v1.get(k) != null){
                         combinacion com = new combinacion();
                         com= (combinacion) v1.get(k);
                          if(!((com.getB() == op.getCodigo() && com.getA() == op.getdespuesDeOpcion().getCodigo()) || (com.getC() == op.getCodigo() && com.getB() == op.getdespuesDeOpcion().getCodigo()) || (com.getD() == op.getCodigo() && com.getC() == op.getdespuesDeOpcion().getCodigo()))){

                            consult =1;  
                             v1.set(k,null);
                         }
                         }
                     }
                 }
                 
             }
             }
            Vector def = new Vector();
         if(consult == 1){
             
             int tam = v1.size();
             for(int kk =0; kk<tam; kk++){
                 if(v1.get(kk) != null){
                 combinacion com = (combinacion) v1.get(kk);
                 
                 if((Integer)v.get(0) == 0 || com.getA() ==  (Integer)v.get(0)){
                     if((Integer)v.get(1) == 0 || com.getB() ==  (Integer)v.get(1)){
                         if((Integer)v.get(2) == 0 || com.getC() ==  (Integer)v.get(2)){
                             if((Integer)v.get(3) == 0 || com.getD() ==  (Integer)v.get(3)){
                                 def.add(com);
                             }
                             
                         }
                         
                     }
                 }
                 }

             }
         }else{
                
           
             for(int kk =1; kk<=23; kk++){
              
                      combinacion com = new combinacion();
                      com.setCodigo(kk);
                      cDAO.cargarCombinacion(com);
                 if((Integer)v.get(0) == 0 || com.getA() ==  (Integer)v.get(0)){
                     if((Integer)v.get(1) == 0 || com.getB() ==  (Integer)v.get(1)){
                         if((Integer)v.get(2) == 0 || com.getC() ==  (Integer)v.get(2)){
                             if((Integer)v.get(3) == 0 || com.getD() ==  (Integer)v.get(3)){
                                 def.add(com);
                             }
                             
                         }
                         
                     }
                 }
                 

             }
             
         }
               if(def.isEmpty()){
                 return v1;
             }
             return def;

          /*int señal;
         
         for(int k = 0; k<23; k++){
             combinacion com = new combinacion();
             com.setCodigo(k);
             cDAO.cargarCombinacion(com);
            señal =0;
            for(int j=0; j< v.size(); j++){
                Object ob = v.get(j);
                if(ob != null){
                 Opcion aux = new Opcion();
                 aux.setCodigo((Integer) ob);
                 aux.setPregunta(p.getCodigo());
                oDAO.cargarOpcion(aux);
                 if(aux !=null){
                     if(j == 0){
                         if( aux.getCodigo()!=com.getA()){
                             señal =1;
                             break;
                         }
                     }else if(j == 1){
                         if(aux.getCodigo() != com.getB() ){
                              señal =1;
                             break;
                         }
                     }else if(j == 2){
                         if(aux.getCodigo() != com.getC() ){
                              señal =1;
                             break;
                         }
                     }else if(j ==3 ){
                         if(aux.getCodigo() != com.getD() ){
                              señal =1;
                             break;
                         }
                     }
                 }
            }
            if (señal == 0){
                v1.add(com);
            }
            }
         }
        return v1;*/
    }
    
    public LinkedList verificarpreguntas(LinkedList lk2, Pregunta p){
         int cont =0;
        enunciadoDAO enDAO = new enunciadoDAO(); 
        Iterator it2= lk2.iterator();
        Pregunta pr = null ;
                
                int temp =0;
                while(it2.hasNext()){
                     pr = (Pregunta) it2.next();
                    Enunciado en2 = new Enunciado();
                    en2.setCodigo(pr.getEnunciado().getCodigo());
                    enDAO.cargarEnunciado(en2);
                    if(en2.getCodigo() != p.getEnunciado().getCodigo() && temp == 1){
                        lk2.add(cont, p);
                        return lk2;
                    }
                   
                    if(en2.getDespuesDe().getCodigo()== p.getEnunciado().getCodigo()){

                         lk2.add(cont, p);
                         temp = 1;
                         break;
                    }else if( en2.getCodigo() == p.getEnunciado().getCodigo()){
                        if(p.getDespuesDePregunta().getCodigo()!= 0){
                            if(pr.getCodigo() == p.getDespuesDePregunta().getCodigo()){
                                lk2.add(cont+1, p);
                                temp=1;
                                break;
                            }else if(pr.getDespuesDePregunta().getCodigo() ==p.getCodigo()){
                                 lk2.add(cont, p);
                                 temp = 1;
                                 break;
                            }
                        }else if(p.getOrden() == 1){
                                 lk2.add(cont,p);
                                 temp = 1;
                                 break;
                        }else if(p.getOrden() == 2){
                                 lk2.add(p); 
                                 temp =1;
                                 break;
                        }
                       
                    }
                   
                    cont++;
                }
                if(temp ==0){
                    lk2.add(p);
                }
                return lk2;
                
    }
    public LinkedList verificaCondicionEnunciado(LinkedList lk,Pregunta pp){
        enunciadoDAO enDAO = new enunciadoDAO();
        Enunciado en =new Enunciado();
        en.setCodigo(pp.getEnunciado().getCodigo());
       enDAO.cargarEnunciado(en);
        int Despues = en.getDespuesDe().getCodigo();
        
        Pregunta p =(Pregunta) lk.getLast();
        if(Despues == p.getEnunciado().getCodigo()){
            lk.add(p);
        }else{
            Iterator it = lk.iterator();
            int cont = 1;
            int temp =0;
            while(it.hasNext()){
                Pregunta pg = (Pregunta) it.next();
                if (pg.getEnunciado().getCodigo() == Despues){
                    temp = cont;
                }
                cont++;
            }
            if (temp == 0){
                lk.add(pp);
            }else{
                lk.add(temp, pp);
                return lk;
            }
        }
        return lk;
    }
    
}
