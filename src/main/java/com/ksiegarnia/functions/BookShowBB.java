package com.ksiegarnia.functions;

import java.io.IOException;
import java.io.Serializable;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.ksiegarnia.entities.Ksiazki;

@Named
@ViewScoped
public class BookShowBB implements Serializable {

    private Ksiazki ksiazki = new Ksiazki();
    private Ksiazki loaded = null;

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
