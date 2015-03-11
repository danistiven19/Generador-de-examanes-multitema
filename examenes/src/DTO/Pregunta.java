    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DTO;

import java.util.Collection;

/**
 *
 * @author Daniel
 */
public class Pregunta{
      private int codigo;
        private String url;
        private Enunciado enunciado;
        private int orden;
        private String obligatorioa;
        private Pregunta despuesDePregunta;
        private int tipo;
        private String descripcionPregunta;
        private Collection<Opcion> opciones;
        
    public Collection<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(Collection<Opcion> opciones) {
        this.opciones = opciones;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the enunciado
     */
    public Enunciado getEnunciado() {
        return enunciado;
    }

    /**
     * @param enunciado the enunciado to set
     */
    public void setEnunciado(Enunciado enunciado) {
        this.enunciado = enunciado;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the obligatorioa
     */
    public String getObligatorioa() {
        return obligatorioa;
    }

    /**
     * @param obligatorioa the obligatorioa to set
     */
    public void setObligatorioa(String obligatorioa) {
        this.obligatorioa = obligatorioa;
    }

    /**
     * @return the despuesDePregunta
     */
    public Pregunta getDespuesDePregunta() {
        return despuesDePregunta;
    }

    /**
     * @param despuesDePregunta the despuesDePregunta to set
     */
    public void setDespuesDePregunta(Pregunta despuesDePregunta) {
        this.despuesDePregunta = despuesDePregunta;
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the descripcionPregunta
     */
    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    /**
     * @param descripcionPregunta the descripcionPregunta to set
     */
    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }
             

    }
