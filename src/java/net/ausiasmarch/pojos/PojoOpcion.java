/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.pojos;


/**
 *
 * @author al036049
 */

public class PojoOpcion  {
    
    private int id;
    private String descripcion;
    private int id_pregunta;
    private int correcta;

     public PojoOpcion() {
        
    
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

    public int getIdPregunta() {
        return id_pregunta;
    }

    public void setIdPregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

   
    
}
