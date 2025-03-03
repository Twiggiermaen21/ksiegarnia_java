
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Uzytkownik;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserDAO {

    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";
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

    public Uzytkownik getUser(String email, String password) {
        Uzytkownik user = null;


        String select = "select u ";
        String from = "from Uzytkownik u ";
        String where = "where u.email = :email and u.haslo = :password";

        Query query = em.createQuery(select + from + where);

        query.setParameter("email", email);
        query.setParameter("password", password);
      
        try {
            user = (Uzytkownik) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }
        return user;
    }

    public Uzytkownik findByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM Uzytkownik u WHERE u.email = :email", Uzytkownik.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
