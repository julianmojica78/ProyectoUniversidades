/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Jonathan
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "traerEstudiantes", query = "SELECT est.nombre FROM Estudiante est WHERE est.diplomado.id = 0"),
    @NamedQuery(name = "traerIdEst", query = "SELECT est FROM Estudiante est WHERE est.nombre = :nombre"),
    @NamedQuery(name = "desasociarEst", query = "SELECT est.nombre FROM Estudiante est WHERE est.diplomado.id = :id")
})
public class Estudiante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column
    private String nombre;

    @Column
    private String cedula;

    @ManyToOne
    @JoinColumn(name = "id_diplomado", nullable = false)
    private Diplomado diplomado;

    public Estudiante() {
    }

    public Estudiante(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Diplomado getDiplomado() {
        return diplomado;
    }

    public void setDiplomado(Diplomado diplomado) {
        this.diplomado = diplomado;
    }

}
