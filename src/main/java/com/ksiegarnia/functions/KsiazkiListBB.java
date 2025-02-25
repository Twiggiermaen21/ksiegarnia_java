package com.ksiegarnia.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.ksiegarnia.dao.KsiazkiDAO;
import com.ksiegarnia.entities.*;

@Named
@RequestScoped
public class KsiazkiListBB {

    private static final String PAGE_BOOK_SHOW = "bookShow?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;
    private List<Ksiazki> list;
    private String tytul;
    private int currentPage = 0;
    private final int pageSize = 100;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    KsiazkiDAO ksiazkiDAO;

    public String gettytul() {
        return tytul;
    }

    public void settytul(String tytul) {
        this.tytul = tytul;
    }

    public List< Ksiazki> getFullList() {
        return ksiazkiDAO.getFullList();
    }

    public List<Ksiazki> getList() {

        //1. Prepare search params
        Map<String, Object> searchParams = new HashMap<String, Object>();

        if (tytul != null && tytul.length() > 0) {
            searchParams.put("tytul", tytul);
        }

        //2. Get list
        list = ksiazkiDAO.getList(searchParams);

        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, list.size());

        return list.subList(start, end);
    }

    public String showBook(Ksiazki ksiazki) {
        //1. Pass object through session
        //HttpSession session = (HttpSession) extcontext.getSession(true);
        //session.setAttribute("person", person);

        //2. Pass object through flash 
        flash.put("ksiazki", ksiazki);

        return PAGE_BOOK_SHOW;
    }

    public void nextPage() {
        currentPage++;
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
        }

    }
}
