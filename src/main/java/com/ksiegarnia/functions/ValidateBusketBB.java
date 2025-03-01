/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.PlatnosciDAO;
import com.ksiegarnia.dao.ZamowieniaDAO;
import com.ksiegarnia.dao.ZamowieniaKsiazkiDAO;
import com.ksiegarnia.entities.Ksiazki;
import com.ksiegarnia.entities.Platnosci;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.Zamowienia;
import com.ksiegarnia.entities.ZamowieniaHasKsiazki;
import com.ksiegarnia.enums.Busket;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Named
@RequestScoped
public class ValidateBusketBB {

    private static final String PAGE_BILL_SHOW = "billPage?faces-redirect=true";
    private String text; // Adres
    private Integer paymentMethod; // Metoda płatności
    private Boolean agree;
    private List<Busket> busket;
    private Zamowienia zam;
    private double totalPrice;
    private int totalBooks;
    @Inject
    ExternalContext extcontext;
    @Inject
    Flash flash;
    @EJB
    ZamowieniaDAO zamowieniaDAO;
    PlatnosciDAO platnoscDAO;
    ZamowieniaKsiazkiDAO zhkDAO;

    private static final Map<String, String> paymentMethodMap = Map.of(
            "0", "Przelew Bankowy",
            "1", "Gotowka",
            "2", "Blik"
    );

    public Boolean getAgree() {
        return agree;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Setter for totalPrice
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getter for totalBooks
    public int getTotalBooks() {
        return totalBooks;
    }

    // Setter for totalBooks
    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public void setAgree(Boolean agree) {
        this.agree = agree;
    }

    // Getter i setter dla 'text'
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Getter i setter dla 'paymentMethod'
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public String getSelectedPaymentl() {
        return paymentMethodMap.getOrDefault(paymentMethod, "Nieznana metoda");
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Metoda do przetwarzania danych po wysłaniu formularza
    public String submit() {
        if (agree != null && agree) {
            List<Object> fieldList = new ArrayList<>();
            fieldList.add(this.text);           // Add the 'text' value (address)
            fieldList.add(this.paymentMethod);  // Add the 'paymentMethod' value
            fieldList.add(this.agree);
            HttpSession session = (HttpSession) extcontext.getSession(false);
            busket = (List<Busket>) session.getAttribute("busket");
            for (Busket item : busket) {
                totalPrice += item.getKsiazka().getCena() * item.getIlosc();
                totalBooks += item.getIlosc();
            }

            flash.put("busket", busket);
            flash.put("fieldList", fieldList);
            if (busket != null) {

                try {
                    Zamowienia order = GenerateZam();
                    for (Busket item : busket) {

                        GenerateZHK(order, item.getKsiazka());
                    }

                } catch (Exception e) {
                    System.out.println("Błąd: " + e.getMessage());
                }

                removeBusket();
                // Możesz tutaj dodać logikę biznesową po zatwierdzeniu formularza
                FacesMessage msg = new FacesMessage("Formularz został wysłany");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage("Koszyk jest pusty");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } else {
            FacesMessage msg = new FacesMessage("Zaakceptuj prawa");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

        return PAGE_BILL_SHOW;
    }

    public void removeBusket() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session != null) {
            session.removeAttribute("busket");
            System.out.println("Busket attribute removed successfully!");

        } else {
            System.out.println("Session is null, cannot remove attribute.");
        }
    }

    public Zamowienia GenerateZam() {
Zamowienia zamowni = new Zamowienia();
        try {
           
            HttpSession session = (HttpSession) extcontext.getSession(false);
            Uzytkownik user = (Uzytkownik) session.getAttribute("user");
            Platnosci platnosc = new Platnosci();
            platnosc=GeneratePlat(user);
            zamowni .setUzytkownikidUzytkownik(user);
            zamowni .setDatazamowienia(new Date());
            zamowni .setPlatnosciidPlatnosci(platnosc);
            zamowni .setAdres(text);
            zamowni .setKodZamowienia(generateRandomCode());
            zamowieniaDAO.create(zamowni );
        } catch (Exception e) {
            System.out.println("blad zamownienie");
        }
        return zamowni ;
    }

    public Platnosci GeneratePlat(   Uzytkownik user ) {
       Platnosci pla = new Platnosci();
        try {
           
              pla.setKwota(totalPrice);
        pla.setDataPlatnosci(new Date());
        pla.setRodzajpłatnosci(getSelectedPaymentl());
        pla.setIdUzytkownik(user.getIdUzytkownik());
        pla.setKodPlatnosci(generateRandomCode());
        platnoscDAO.create(pla);
        } catch (Exception e) {
              System.out.println("blad platnosc");
        }

      
        return pla;
    }

    public void GenerateZHK(Zamowienia order, Ksiazki book) {
        ZamowieniaHasKsiazki ZHK = new ZamowieniaHasKsiazki();
        ZHK.setKsiazkiidKsiazki(book);
        ZHK.setZamowieniaIdzamowienia(order);
        ZHK.setIloscksiazek(totalBooks);
        zhkDAO.create(ZHK);
    }

    public static String generateRandomCode() {
        // Tworzenie losowego 16-znakowego ciągu alfanumerycznego
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

}
