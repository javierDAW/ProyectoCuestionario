/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ausiasmarch.pojos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author al036049
 */
@Entity
@Table(name = "resultado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resultado.findAll", query = "SELECT r FROM Resultado r"),
    @NamedQuery(name = "Resultado.findById", query = "SELECT r FROM Resultado r WHERE r.id = :id"),
    @NamedQuery(name = "Resultado.findByIdAlumno", query = "SELECT r FROM Resultado r WHERE r.idAlumno = :idAlumno"),
    @NamedQuery(name = "Resultado.findByIdCuestionario", query = "SELECT r FROM Resultado r WHERE r.idCuestionario = :idCuestionario"),
    @NamedQuery(name = "Resultado.findByNota", query = "SELECT r FROM Resultado r WHERE r.nota = :nota")})
public class Resultado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_alumno")
    private int idAlumno;
    @Basic(optional = false)
    @Column(name = "id_cuestionario")
    private int idCuestionario;
    @Basic(optional = false)
    @Column(name = "nota")
    private int nota;

    public Resultado() {
    }

    public Resultado(Integer id) {
        this.id = id;
    }

    public Resultado(Integer id, int idAlumno, int idCuestionario, int nota) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idCuestionario = idCuestionario;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resultado)) {
            return false;
        }
        Resultado other = (Resultado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.ausiasmarch.pojos.Resultado[ id=" + id + " ]";
    }
    
}
