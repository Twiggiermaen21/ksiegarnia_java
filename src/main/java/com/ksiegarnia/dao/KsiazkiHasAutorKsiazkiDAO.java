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

import com.ksiegarnia.entities.KsiazkiHasAutorKsiazki;

@Stateless
public class KsiazkiHasAutorKsiazkiDAO {

    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";
    // Dependency injection (no setter method is needed)
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(KsiazkiHasAutorKsiazki KHAK) {
        em.persist(KHAK);
    }

    public KsiazkiHasAutorKsiazki merge(KsiazkiHasAutorKsiazki KHAK) {
        return em.merge(KHAK);
    }

    public void remove(KsiazkiHasAutorKsiazki KHAK) {
        em.remove(em.merge(KHAK));
    }

    public KsiazkiHasAutorKsiazki find(Object id) {
        return em.find(KsiazkiHasAutorKsiazki.class, id);
    }

    public List<KsiazkiHasAutorKsiazki> getFullList() {
        List<KsiazkiHasAutorKsiazki> list = null;

        Query query = em.createQuery("select k from KsiazkiHasAutorKsiazki k");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<KsiazkiHasAutorKsiazki> getList() {
        List<KsiazkiHasAutorKsiazki> list = null;

        // Poprawione zapytanie JPQL
        String select = "select k ";
        String from = "from KsiazkiHasAutorKsiazki k"; // Użycie nazwy klasy encyjnej

        Query query = em.createQuery(select + from);

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    

    
    
}
