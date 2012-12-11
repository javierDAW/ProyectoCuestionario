/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.pojos;


import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author al036049
 */
public class PojoCuestionario {

    private int id;
    private String descripcion;
    private ArrayList preguntas;
    private int evaluacion;
    private Date fecha;
    

    public PojoCuestionario() {
    }

    public PojoCuestionario(int id) {
        this.id = id;
    }

    public PojoCuestionario(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList getPreguntas() {
        return preguntas;
    }

    /**
     * @param preguntas the preguntas to set
     */
    public void setPreguntas(ArrayList preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * @return the evaluacion
     */
    public int getEvaluacion() {
        return evaluacion;
    }

    /**
     * @param evaluacion the evaluacion to set
     */
    public void setEvaluacion(int evaluacion) {
        this.evaluacion = evaluacion;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

   
    /**
     * @return the preguntas
     */
}
