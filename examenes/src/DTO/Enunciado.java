package DTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class Enunciado {
    private int codigo;
    private String url;
    private String fechaCreacion;
    private int orden;
    private Autor autor;
    private Area area;
    private Enunciado despuesDe;
    private String paquetes;
    private String enunciado;
    private Collection<Pregunta> preguntas;

    public Collection<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Collection<Pregunta> preguntas) {
        this.preguntas = preguntas;
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
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
     * @return the autor
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * @return the area
     */
    public Area getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Area area) {
        this.area = area;
    }

    /**
     * @return the despuesDe
     */
    public Enunciado getDespuesDe() {
        return despuesDe;
    }

    /**
     * @param despuesDe the despuesDe to set
     */
    public void setDespuesDe(Enunciado despuesDe) {
        this.despuesDe = despuesDe;
    }

    /**
     * @return the paquetes
     */
    public String getPaquetes() {
        return paquetes;
    }

    /**
     * @param paquetes the paquetes to set
     */
    public void setPaquetes(String paquetes) {
        this.paquetes = paquetes;
    }

    /**
     * @return the enunciado
     */
    public String getEnunciado() {
        return enunciado;
    }

    /**
     * @param enunciado the enunciado to set
     */
    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }


}

