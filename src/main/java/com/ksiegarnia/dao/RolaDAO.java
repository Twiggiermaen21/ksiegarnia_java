/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Rola;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.UzytkownikHasRola;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author kacpe
 */
public class RolaDAO {
        private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";

        // Dependency injection (no setter method is needed)
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
    public Rola getFullList() {

        Rola list = null;
              String select = "select r ";
              String from = "from Rola r ";
              String where = "where r.idRola = 3";

         
        
        
        // ... other parameters ... 
        // 2. Create query object
        Query query = em.createQuery(select + from + where);

        try {
            list = (Rola) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
