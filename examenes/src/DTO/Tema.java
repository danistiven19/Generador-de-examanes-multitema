/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package DTO;


import java.util.Collection;
import java.util.Date;

/**
*
* @author Daniel
*/
public class Tema{
   	private int codigo;
   	private Date FechaCreacion;
   	private String jornada;
        private Collection<Pregunta_Tema> preguntas;

    public Collection<Pregunta_Tema> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Collection<Pregunta_Tema> preguntas) {
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
	* @return the FechaCreacion
	*/
	public Date getFechaCreacion() {
   	return FechaCreacion;
	}

	/**
	* @param FechaCreacion the FechaCreacion to set
	*/
	public void setFechaCreacion(Date FechaCreacion) {
   	this.FechaCreacion = FechaCreacion;
	}

	/**
	* @return the jornada
	*/
	public String getJornada() {
   	return jornada;
	}

	/**
	* @param jornada the jornada to set
	*/
	public void setJornada(String jornada) {
   	this.jornada = jornada;
	}
          	 
	}
