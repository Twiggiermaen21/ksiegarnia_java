/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.dao;
import com.ksiegarnia.entities.Uzytkownik;
import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserDAO {
    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Uzytkownik user) {
		em.persist(user);
                }

	public Uzytkownik merge(Uzytkownik user) {
		return em.merge(user);
	}

	

	public Uzytkownik find(Object id) {
		return em.find(Uzytkownik.class, id);
	}

	

	public List<Uzytkownik> getList(String email, String password) {
		List<Uzytkownik> list = null;

		// 1. Build query string with parameters
		String select = "select u ";
		String from = "from Uzytkownik u ";
		String where = "";

		// search for surname
		

			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.email like :email and u.haslo like :password";
		
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where);

		// 3. Set configured parameters
		

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
        
        
        
        
          
}
