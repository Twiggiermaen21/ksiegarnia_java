
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Platnosci;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PlatnosciDAO {
   
    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";

    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;
    
    
    
    
    public void create(Platnosci platnosci) {
        em.persist(platnosci);
    }

   
    


}
