/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Interfaces.DiplomadoFacadeLocal;
import Interfaces.AbstractFacade;
import Entity.Diplomado;
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
public class DiplomadoFacade extends AbstractFacade<Diplomado> implements DiplomadoFacadeLocal {
    @PersistenceContext(unitName = "Udec_ProyectoUniversidades-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiplomadoFacade() {
        super(Diplomado.class);
    }

    @Override
    public List<Diplomado> findNombre() {
        TypedQuery<Diplomado> consulta = em.createNamedQuery("traerDiplomados", Diplomado.class);
        return consulta.getResultList();
    }

    @Override
    public Diplomado findName(String nombre) {
        TypedQuery<Diplomado> consulta = em.createNamedQuery("traerId", Diplomado.class);
        consulta.setParameter("nombre", nombre);
        return consulta.getSingleResult();
    }

    @Override
    public List<Diplomado> findAsociadas(int id) {
        TypedQuery<Diplomado> consulta = em.createNamedQuery("desasociar", Diplomado.class);
        consulta.setParameter("id", id);
        return consulta.getResultList();
    }
    
}
