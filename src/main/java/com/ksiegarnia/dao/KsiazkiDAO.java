package com.ksiegarnia.dao;

import java.util.List;
import java.util.Map;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.ksiegarnia.entities.Ksiazki;

@Stateless
public class KsiazkiDAO {

    private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(Ksiazki ksiazki) {
        em.persist(ksiazki);
    }

    public Ksiazki merge(Ksiazki ksiazki) {
        return em.merge(ksiazki);
    }

    public void remove(Ksiazki ksiazki) {
        em.remove(em.merge(ksiazki));
    }

    public Ksiazki find(Object id) {
        return em.find(Ksiazki.class, id);
    }

    public List<Ksiazki> getFullList() {
        List<Ksiazki> list = null;

        Query query = em.createQuery("select k from Ksiazki k");

        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Ksiazki> getList(Map<String, Object> searchParams) {
        List<Ksiazki> list = null;

        String select = "select k ";
        String from = "from Ksiazki k ";
        String where = "";
        String orderby = "order by k.tytul asc";

        String tytul = (String) searchParams.get("tytul");
        if (tytul != null) {
            if (where.isEmpty()) {
                where = "where ";
            } else {
                where += "and ";
            }
            where += "k.tytul like :tytul ";
        }
        Query query = em.createQuery(select + from + where + orderby);
        if (tytul != null) {
            query.setParameter("tytul", tytul + "%");
        }
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
