
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Rola;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;


@Stateless
public class RolaDAO {
        private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
    public Rola getById(int id) {
    try {
        return em.createQuery("SELECT r FROM Rola r WHERE r.idRola = :id", Rola.class)
                 .setParameter("id", id)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}
}
