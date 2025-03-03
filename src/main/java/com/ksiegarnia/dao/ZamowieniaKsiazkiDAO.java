
package com.ksiegarnia.dao;

import com.ksiegarnia.entities.Zamowienia;
import com.ksiegarnia.entities.ZamowieniaHasKsiazki;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Stateless
public class ZamowieniaKsiazkiDAO {
    
 private final static String UNIT_NAME = "jsfcourse-ksiegarniaPU";
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager em;

    public void create(ZamowieniaHasKsiazki ZHK) {
        em.persist(ZHK);
    }

    public ZamowieniaHasKsiazki merge(ZamowieniaHasKsiazki ZHK) {
        return em.merge(ZHK);
    }

    public void remove(ZamowieniaHasKsiazki ZHK) {
        em.remove(em.merge(ZHK));
    }

    public ZamowieniaHasKsiazki find(Object id) {
        return em.find(ZamowieniaHasKsiazki.class, id);
    }

    public List<ZamowieniaHasKsiazki> getFullList(List<Zamowienia> zam) {

        List<ZamowieniaHasKsiazki> list = null;
              String select = "select z ";
              String from = "from ZamowieniaHasKsiazki z ";
              String where = "where z.zamowieniaIdzamowienia IN :zam";

        Query query = em.createQuery(select + from + where );
     query.setParameter("zam", zam);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}

