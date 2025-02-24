package com.ksiegarnia.functions;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.ksiegarnia.dao.KsiazkiDAO;
import com.ksiegarnia.entities.*;

@Named
@ViewScoped
public class BookShowBB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String PAGE_PERSON_LIST = "personList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Ksiazki ksiazki = new Ksiazki();
    private Ksiazki loaded = null;

    @EJB
    KsiazkiDAO ksiazkiDAO;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    public Ksiazki getKsiazki() {
        return ksiazki;
    }

    public void onLoad() throws IOException {

        loaded = (Ksiazki) flash.get("ksiazki");

        if (loaded != null) {
            ksiazki = loaded;

        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));

        }

    }

   
}
