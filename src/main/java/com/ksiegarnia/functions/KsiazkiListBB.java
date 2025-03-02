package com.ksiegarnia.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.ksiegarnia.dao.*;
import com.ksiegarnia.entities.*;
import com.ksiegarnia.enums.Basket;
import jakarta.enterprise.context.SessionScoped;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped

public class KsiazkiListBB implements Serializable {

    private static final String PAGE_BOOK_SHOW = "bookShow?faces-redirect=true";
    private static final String PAGE_BASKET_SHOW = "basketPage?faces-redirect=true";
    private List<Ksiazki> list;
    private List<Basket> basket = new ArrayList<>();
    private int quantity = 1;
    private String tytul;
    private int currentPage = 0;
    private final int pageSize = 9;
    private int totalBooks = 0;
    private HttpSession session;

    @Inject
    ExternalContext extcontext;
    @Inject
    Flash flash;
    @EJB
    KsiazkiDAO ksiazkiDAO;
    @EJB
    KsiazkiHasAutorKsiazkiDAO khakDAO;

    public KsiazkiListBB() {
        session = (HttpSession) extcontext.getSession(false);
    }

    public List<Ksiazki> getList() {
        Map<String, Object> searchParams = new HashMap<String, Object>();
        if (tytul != null && tytul.length() > 0) {
            searchParams.put("tytul", tytul);
        }
        list = ksiazkiDAO.getList(searchParams);
        totalBooks = list.size();
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, list.size());
        return list.subList(start, end);
    }

    public String showBook(Ksiazki ksiazki) {
        flash.put("ksiazki", ksiazki);
        return PAGE_BOOK_SHOW;
    }

    public void addToBasket(Ksiazki ksiazki) {
        List<Basket> sessionBasket = (List<Basket>) session.getAttribute("basket");
        if (sessionBasket == null) {
            sessionBasket = new ArrayList<>();
        }

        boolean found = false;
        for (Basket item : sessionBasket) {
            if (item.getKsiazka().getIdKsiazki().equals(ksiazki.getIdKsiazki())) {
                item.setIlosc(item.getIlosc() + this.quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            sessionBasket.add(new Basket(ksiazki, this.quantity));
        }

        session.setAttribute("basket", sessionBasket);
        this.basket = sessionBasket;
        System.out.println("Dodano książkę: " + ksiazki.getTytul() + " w ilości: " + this.quantity);

    }

    public void usunBasket(Basket ksiazka) {
        basket.remove(ksiazka);
    }

    public void wyczyscBasket() {
        if (session != null) {
            session.removeAttribute("basket");
        }
        this.basket.clear();
    }

    public List<Basket> getBasket() {
        List<Basket> sessionBasket = (session != null) ? (List<Basket>) session.getAttribute("basket") : null;
        return (sessionBasket != null) ? sessionBasket : new ArrayList<>();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String gettytul() {
        return tytul;
    }

    public void settytul(String tytul) {
        this.tytul = tytul;
    }

    public List< Ksiazki> getFullList() {
        return ksiazkiDAO.getFullList();
    }

    public String showBasket() {
        return PAGE_BASKET_SHOW;
    }

    public void nextPage() {
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
        if (currentPage < totalPages - 1) {
            currentPage++;
        }
    }

    public void prevPage() {
        if (currentPage > 0) {
            currentPage--;
        }

    }
}
