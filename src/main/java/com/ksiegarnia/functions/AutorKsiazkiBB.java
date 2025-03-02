package com.ksiegarnia.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;


import com.ksiegarnia.dao.AutorKsiazkiDAO;
import com.ksiegarnia.entities.AutorKsiazki;

@Named
@RequestScoped
public class AutorKsiazkiBB {

    private String nazwisko;
    @EJB
    AutorKsiazkiDAO autorKsiazkiDAO;

    public List<AutorKsiazki> getFullList() {
        return autorKsiazkiDAO.getFullList();
    }

    public List<AutorKsiazki> getList() {
        List<AutorKsiazki> list = null;

        Map<String, Object> searchParams = new HashMap<String, Object>();

        if (nazwisko != null && nazwisko.length() > 0) {
            searchParams.put("nazwisko", nazwisko);
        }

        list = autorKsiazkiDAO.getList(searchParams);

        return list;
    }

}
