/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.dao;
import java.util.List;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.ksiegarnia.entities.Zamowienia;


@Stateless
public class ZamowieniaDAO {
     private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";

    // Dependency injection (no setter method is needed)
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Zamowienia ksiazki) {
        em.persist(ksiazki);
    }

    public Zamowienia merge(Zamowienia ksiazki) {
        return em.merge(ksiazki);
    }

    public void remove(Zamowienia ksiazki) {
        em.remove(em.merge(ksiazki));
    }

    public Zamowienia find(Object id) {
        return em.find(Zamowienia.class, id);
    }

    public List<Zamowienia> getFullList(int identyfikator) {
        List<Zamowienia> list = null;
              String select = "select z ";
        String from = "from Zamowienia z ";
        String where = "where z.uzytkownikidUzytkownik = :identyfikator";

         
        
        
        // ... other parameters ... 
        // 2. Create query object
        Query query = em.createQuery(select + from + where);
        
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
