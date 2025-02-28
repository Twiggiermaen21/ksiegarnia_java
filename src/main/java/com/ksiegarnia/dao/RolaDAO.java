/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Rola;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.UzytkownikHasRola;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class RolaDAO {
        private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";

        // Dependency injection (no setter method is needed)
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
    public Rola getById(int id) {
    try {
        return em.createQuery("SELECT r FROM Rola r WHERE r.idRola = :id", Rola.class)
                 .setParameter("id", id)
                 .getSingleResult();
    } catch (NoResultException e) {
        return null; // Jeśli brak roli
    }
}
}
