/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jonathan
 */

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "traerDiplomados", query = "SELECT dip.nombre FROM Diplomado dip WHERE dip.id != 0 AND dip.universidad.id = 0"),
    @NamedQuery(name = "traerId", query = "SELECT dip FROM Diplomado dip WHERE dip.nombre = :nombre"),
    @NamedQuery(name = "desasociar", query = "SELECT dip.nombre FROM Diplomado dip WHERE dip.universidad.id = :id")
})
public class Diplomado implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    
    @Column
    private String nombre;
    
    @Column
    private String duracion;

    @ManyToOne
    @JoinColumn(name = "id_universidad", nullable = false)
    private Universidad universidad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diplomado")
    private List<Estudiante> listaEstudiantes;

    public Diplomado() {
    }

    public Diplomado(String nombre, String duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    
    
}
