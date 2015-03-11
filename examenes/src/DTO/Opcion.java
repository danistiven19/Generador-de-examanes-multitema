package DTO;

/**
 *
 * @author Daniel
 */
public class Opcion{
        private int codigo;
        private String url;
        private int orden;
        private Opcion despuesDeOpcion;
        private Pregunta pregunta;
        private String descripcionOpcion;
        
    
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
     * @return the despuesDePregunta
     */
    public Opcion getdespuesDeOpcion() {
        return despuesDeOpcion;
    }

    /**
     * @param despuesDePregunta the despuesDePregunta to set
     */
    public void setdespuesDeOpcion(Opcion despuesDeOpcion) {
        this.despuesDeOpcion = despuesDeOpcion;
    }

    /**
     * @return the pregunta
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * @param pregunta the pregunta to set
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * @return the descripcionOpcion
     */
    public String getDescripcionOpcion() {
        return descripcionOpcion;
    }

    /**
     * @param descripcionOpcion the descripcionOpcion to set
     */
    public void setDescripcionOpcion(String descripcionOpcion) {
        this.descripcionOpcion = descripcionOpcion;
    }

    }
