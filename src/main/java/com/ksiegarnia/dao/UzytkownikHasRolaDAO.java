/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Uzytkownik;
import jakarta.ejb.Stateless;
import com.ksiegarnia.entities.UzytkownikHasRola;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class UzytkownikHasRolaDAO {
    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";
    // Dependency injection (no setter method is needed)
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(UzytkownikHasRola UHR) {
        em.persist(UHR);
    }

    public UzytkownikHasRola merge(UzytkownikHasRola UHR) {
        return em.merge(UHR);
    }

    public void remove(UzytkownikHasRola UHR) {
        em.remove(em.merge(UHR));
    }

    public UzytkownikHasRola find(Object id) {
        return em.find(UzytkownikHasRola.class, id);
    }

    public List<UzytkownikHasRola> getFullList(Uzytkownik user) {

        List<UzytkownikHasRola> list = null;
              String select = "select r ";
              String from = "from UzytkownikHasRola r ";
              String where = "where r.uzytkownikidUzytkownik = :user";

         
        
        
        // ... other parameters ... 
        // 2. Create query object
        Query query = em.createQuery(select + from + where );
     query.setParameter("user", user);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



}
