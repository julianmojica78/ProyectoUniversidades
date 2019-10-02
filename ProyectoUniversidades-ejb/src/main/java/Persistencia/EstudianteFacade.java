/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Interfaces.AbstractFacade;
import Interfaces.EstudianteFacadeLocal;
import Entity.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jonathan
 */
@Stateless
public class EstudianteFacade extends AbstractFacade<Estudiante> implements EstudianteFacadeLocal {
    @PersistenceContext(unitName = "Udec_ProyectoUniversidades-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    @Override
    public List<Estudiante> findNombre() {
        TypedQuery<Estudiante> consulta = em.createNamedQuery("traerEstudiantes", Estudiante.class);
        return consulta.getResultList();
    }

    @Override
    public Estudiante findName(String nombre) {
        TypedQuery<Estudiante> consulta = em.createNamedQuery("traerIdEst", Estudiante.class);
        consulta.setParameter("nombre", nombre);
        return consulta.getSingleResult();
    }

    @Override
    public List<Estudiante> findAsociados(int id) {
        TypedQuery<Estudiante> consulta = em.createNamedQuery("desasociarEst", Estudiante.class);
        consulta.setParameter("id", id);
        return consulta.getResultList();
    }
    
}
