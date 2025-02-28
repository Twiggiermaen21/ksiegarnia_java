package com.ksiegarnia.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.ksiegarnia.dao.KsiazkiDAO;
import com.ksiegarnia.entities.*;
import com.ksiegarnia.enums.Busket;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;

@Named
@SessionScoped
public class KsiazkiListBB implements Serializable {

    private static final String PAGE_BOOK_SHOW = "bookShow?faces-redirect=true";
    private static final String PAGE_BUSKET_SHOW = "busketPage?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;
    private List<Ksiazki> list;
    private List<Busket> busket = new ArrayList<>(); // Lista przedmiotów w koszyku
  
    private String tytul;
    private int currentPage = 0;
    private final int pageSize = 9;
    private int totalBooks = 0;

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
        totalBooks = list.size();
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

    public void addToBusket(Ksiazki ksiazki, int ilosc) {
    HttpSession session = (HttpSession) extcontext.getSession(true); // Tworzy sesję, jeśli nie istnieje

    // Pobranie koszyka z sesji
    List<Busket> sessionBusket = (List<Busket>) session.getAttribute("busket");

    // Jeśli koszyk nie istnieje w sesji, tworzymy nową listę
    if (sessionBusket == null) {
        sessionBusket = new ArrayList<>();
    }

    // Sprawdzamy, czy książka już jest w koszyku
        boolean found = false;
        for (Busket item : sessionBusket) {
            if (item.getKsiazka().getIdKsiazki().equals(ksiazki.getIdKsiazki())) {
                // Jeśli książka jest już w koszyku, zwiększamy ilość
                item.setIlosc(item.getIlosc() + ilosc);
                found = true;
                break;
            }
        }

        // Jeśli książka nie była w koszyku, dodajemy ją z podaną ilością
        if (!found) {
            sessionBusket.add(new Busket(ksiazki, ilosc));
        }
    
    // Aktualizacja sesji
    session.setAttribute("busket", sessionBusket);

    // Aktualizacja lokalnej listy, aby uniknąć desynchronizacji
    this.busket = sessionBusket;
}

    public void usunBusket(Ksiazki ksiazka) {
        busket.remove(ksiazka);
    }

    public void wyczyscBusket() {
    HttpSession session = (HttpSession) extcontext.getSession(false);

    if (session != null) {
        session.removeAttribute("busket"); // Usuwa koszyk z sesji
    }

    this.busket.clear(); // Czyści lokalną listę
}

    public List<Ksiazki> getBusket() {
    HttpSession session = (HttpSession) extcontext.getSession(false); // false = nie tworzy nowej sesji

    List<Ksiazki> sessionBusket = (session != null) ? (List<Ksiazki>) session.getAttribute("busket") : null;

    // Jeśli koszyk nie istnieje, zwracamy pustą listę, ale nie zapisujemy jej w sesji
    return (sessionBusket != null) ? sessionBusket : new ArrayList<>();
}

    public String showBusket() {

        return PAGE_BUSKET_SHOW;
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
