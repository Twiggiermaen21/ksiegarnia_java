/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.ksiegarnia.entities.AutorKsiazki;

@Stateless
public class AutorKsiazkiDAO {

    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";

    // Dependency injection (no setter method is needed)
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(AutorKsiazki AutorKsiazki) {
        em.persist(AutorKsiazki);
    }

    public AutorKsiazki merge(AutorKsiazki AutorKsiazki) {
        return em.merge(AutorKsiazki);
    }

    public void remove(AutorKsiazki AutorKsiazki) {
        em.remove(em.merge(AutorKsiazki));
    }

    public AutorKsiazki find(Object id) {
        return em.find(AutorKsiazki.class, id);
    }

 

    public List<AutorKsiazki> getFullList() {
        List<AutorKsiazki> list = null;

        Query query = em.createQuery("SELECT a FROM AutorKsiazki a");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<AutorKsiazki> getList(Map<String, Object> searchParams) {
        List<AutorKsiazki> list = null;

        // 1. Budowanie zapytania
        String select = "SELECT a ";
        String from = "FROM AutorKsiazki a ";
        String where = "";
        String orderby = " ORDER BY a.nazwisko ASC";

        // Szukanie po nazwisku
        String nazwisko = (String) searchParams.get("nazwisko");
        if (nazwisko != null) {
            if (where.isEmpty()) {
                where = "WHERE ";
            } else {
                where += "AND ";
            }
            where += "a.nazwisko LIKE :nazwisko ";
        }

        // 2. Tworzenie obiektu zapytania
        Query query = em.createQuery(select + from + where + orderby);

        // 3. Ustawianie parametrów
        if (nazwisko != null) {
            query.setParameter("nazwisko", nazwisko + "%");  // Dopasowanie nazwiska od początku
        }

        // 4. Wykonanie zapytania
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
