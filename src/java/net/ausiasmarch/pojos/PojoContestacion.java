/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.pojos;


/**
 *
 * @author al036049
 */

public class PojoContestacion {
  
    private int id;
    private int idAlumno;
    private int idPregunta;
    private String contestacion;

    public PojoContestacion() {
    }

    public PojoContestacion(int id) {
        this.id = id;
    }

    public PojoContestacion(int id, int idPregunta, String contestacion) {
        this.id = id;
        this.idPregunta = idPregunta;
        this.contestacion = contestacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getContestacion() {
        return contestacion;
    }

    public void setContestacion(String contestacion) {
        this.contestacion = contestacion;
    }

       
}
