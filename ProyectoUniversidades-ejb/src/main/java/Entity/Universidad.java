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
    @NamedQuery(name = "traerUniversidades", query = "SELECT uni.nombre FROM Universidad uni WHERE uni.id != 0"),
    @NamedQuery(name = "traerIdUni", query = "SELECT uni FROM Universidad uni WHERE uni.nombre = :nombre")
})
public class Universidad implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    
    @Column
    private String nombre;
    
    @Column
    private String extension;
    
    @Column
    private String escudo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "universidad")
    private List<Diplomado> diplomados;

    public Universidad(String nombre, String extension, String escudo) {
        this.nombre = nombre;
        this.extension = extension;
        this.escudo = escudo;
    }

    public Universidad() {
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public List<Diplomado> getDiplomados() {
        return diplomados;
    }

    public void setDiplomados(List<Diplomado> diplomados) {
        this.diplomados = diplomados;
    }
    
    
    
}
