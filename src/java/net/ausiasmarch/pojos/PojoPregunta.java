/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.pojos;

import java.util.ArrayList;

/**
 *
 * @author al036049
 */
public class PojoPregunta {

    private int id;
    private String descripcion;
    private int id_cuestionario;
    private ArrayList opciones;

    public PojoPregunta() {
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

    /**
     * @return the opciones
     */
    public ArrayList getOpciones() {
        return opciones;
    }

    /**
     * @param opciones the opciones to set
     */
    public void setOpciones(ArrayList opciones) {
        this.opciones = opciones;
    }

    /**
     * @return the id_pregunta
     */
    public int getId_cuestionario() {
        return id_cuestionario;
    }

    /**
     * @param id_pregunta the id_pregunta to set
     */
    public void setId_cuestionaro(int id_cuestionario) {
        this.id_cuestionario = id_cuestionario;
    }
}
