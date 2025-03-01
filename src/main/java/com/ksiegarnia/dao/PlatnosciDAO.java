/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Platnosci;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;
@Stateless
public class PlatnosciDAO {
    private Platnosci platnosci;
    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";

    // Dependency injection (no setter method is needed)
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
    
    
    
    
    public void create(Platnosci platnosci) {
        em.persist(platnosci);
    }

   
    


}
