/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Daniel
 */
public class rutaDAO {
    //C:\Users\julianesten\Documents\NetBeansProjects\Generador-de-examanes-multitema\examenes
    private String rutaBase = "D:/UDEA/UdeA 8/Proyecto Integrador II/Generador-de-examanes-multitema/examenes";

    public String getRutaBase() {
        return rutaBase;
    }

    public void setRutaBase(String rutaBase) {
        this.rutaBase = rutaBase;
    }
    private String raiz = "D:\\UDEA\\UdeA 8\\Proyecto Integrador II\\Generador-de-examanes-multitema\\examenes";
    private String raizSQL = "D:\\\\UDEA\\\\UdeA 8\\\\Proyecto Integrador II\\\\Generador-de-examanes-multitema\\\\examenes";
    private String ruta = raiz + "\\Pruebas";
    private String rutaSQL = raizSQL + "\\\\Pruebas";
    
    public String getRaiz() {
        return raiz;
    }
    
    public String getUnidad(){
        return raiz.substring(0,2);
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    public String getRaizSQL() {
        return raizSQL;
    }

    public void setRaizSQL(String raizSQL) {
        this.raizSQL = raizSQL;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the rutaSQL
     */
    public String getRutaSQL() {
        return rutaSQL;
    }

    /**
     * @param rutaSQL the rutaSQL to set
     */
    public void setRutaSQL(String rutaSQL) {
        this.rutaSQL = rutaSQL;
    }

}
