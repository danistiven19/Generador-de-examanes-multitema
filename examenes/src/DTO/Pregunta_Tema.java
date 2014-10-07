package DTO;

public class Pregunta_Tema{
    	private Tema tema;
//    	private int opcion;
    	private Pregunta pregunta;
    	private int respuesta;
    	private combinacion combinacion;
    	private int nroPregunta;
//    	private String letra;

	/**
 	* @return the tema
 	*/
	public Tema getTema() {
    	return tema;
	}

	/**
 	* @param tema the tema to set
 	*/
	public void setTema(Tema tema) {
    	this.tema = tema;
	}

	/**
 	* @return the opcion
 	*/
//	public int getOpcion() {
//    	return opcion;
//	}
//
//	/**
// 	* @param opcion the opcion to set
// 	*/
//	public void setOpcion(int opcion) {
//    	this.opcion = opcion;
//	}

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
 	* @return the respuesta
 	*/
	public int getRespuesta() {
    	return respuesta;
	}

	/**
 	* @param respuesta the respuesta to set
 	*/
	public void setRespuesta(int respuesta) {
    	this.respuesta = respuesta;
	}

	/**
 	* @return the combinacion
 	*/
	public combinacion getCombinacion() {
    	return combinacion;
	}

	/**
 	* @param combinacion the combinacion to set
 	*/
	public void setCombinacion(combinacion combinacion) {
    	this.combinacion = combinacion;
	}

	/**
 	* @return the nroPregunta
 	*/
	public int getNroPregunta() {
    	return nroPregunta;
	}

	/**
 	* @param nroPregunta the nroPregunta to set
 	*/
	public void setNroPregunta(int nroPregunta) {
    	this.nroPregunta = nroPregunta;
	}

//	/**
// 	* @return the letra
// 	*/
//	public String getLetra() {
//    	return letra;
//	}
//
//	/**
// 	* @param letra the letra to set
// 	*/
//	public void setLetra(String letra) {
//    	this.letra = letra;
//	}
   	 
	}
