/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.*;
import com.ksiegarnia.entities.*;
import com.ksiegarnia.enums.Basket;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Named
@RequestScoped
public class ValidateBasketBB {

    private static final String PAGE_BILL_SHOW = "billPage?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;
    private String text; 
    private Integer paymentMethod; 
    private Boolean agree;
    private List<Basket> basket;
    private double totalPrice;
    private int totalBooks;
    @Inject
    ExternalContext extcontext;
    @Inject
    Flash flash;
    @EJB
    ZamowieniaDAO zamowieniaDAO;
    @EJB
    PlatnosciDAO platnoscDAO;
    @EJB
    ZamowieniaKsiazkiDAO zhkDAO;
    @EJB
    KsiazkiDAO ksiazkiDAO;
    private static final Map<Integer, String> paymentMethodMap = Map.of(
            0, "Przelew Bankowy",
            1, "Gotowka",
            2, "Blik"
    );

    public Boolean getAgree() {
        return agree;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    
    public int getTotalBooks() {
        return totalBooks;
    }

 
    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public void setAgree(Boolean agree) {
        this.agree = agree;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

   
    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public String getSelectedPaymentl() {
        return paymentMethodMap.getOrDefault(paymentMethod, "Nieznana metoda");
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    
    public String submit() {
        HttpSession session = (HttpSession) extcontext.getSession(false);
        basket = (List<Basket>) session.getAttribute("basket");
Zamowienia order = null;
      
        if (basket != null && !basket.isEmpty()) {
           
            if (agree != null && agree) {
               

                for (Basket item : basket) {
                    totalPrice += item.getKsiazka().getCena() * item.getIlosc();
                    totalBooks += item.getIlosc();
                }

              

                try {
                   order = GenerateZam();
                    for (Basket item : basket) {
                        GenerateZHK(order, item.getKsiazka(), item.getIlosc());
                        updateBook(item.getKsiazka(), item.getIlosc());
                    }

                } catch (Exception e) {
                    System.out.println("Błąd: " + e.getMessage());
                }

             
            
                flash.put("basket", basket);
                flash.put("fieldList", order);
             
                removeBasket();
                  FacesMessage msg = new FacesMessage("Formularz został wysłany");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
            } else {
                FacesMessage msg = new FacesMessage("Zaakceptuj prawa");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;  
            }

            
            
                
            
            return PAGE_BILL_SHOW;
        } else {
            FacesMessage msg = new FacesMessage("Koszyk jest pusty");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return PAGE_STAY_AT_THE_SAME;
        }
    }

    public void removeBasket() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        if (session != null) {
            session.removeAttribute("basket");
            System.out.println("Basket attribute removed successfully!");

        } else {
            System.out.println("Session is null, cannot remove attribute.");
        }
    }


public Zamowienia GenerateZam() {
    Zamowienia zamowni = new Zamowienia();
    try {
        HttpSession session = (HttpSession) extcontext.getSession(false);
        Uzytkownik user = (Uzytkownik) session.getAttribute("user");
        
        if (user == null) {
            throw new Exception("User session is null");
        }
        
        Platnosci platnosc = GeneratePlat(user);
        
        zamowni.setUzytkownikidUzytkownik(user);
        zamowni.setDatazamowienia(new Date());
        zamowni.setPlatnosciidPlatnosci(platnosc);
        zamowni.setAdres(text);
        zamowni.setKodZamowienia(generateRandomCode());
        
        zamowieniaDAO.create(zamowni);
  
    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Error occurred: " + e.getMessage());

    }
    return zamowni;
}

public Platnosci GeneratePlat(Uzytkownik user) {
    Platnosci pla = new Platnosci();
    try {
        pla.setKwota(totalPrice);
        pla.setDataPlatnosci(new Date());
        pla.setRodzajpłatnosci(getSelectedPaymentl());
        pla.setIdUzytkownik(user.getIdUzytkownik());
        pla.setKodPlatnosci(generateRandomCode());
        
        platnoscDAO.create(pla);

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Error occurred: " + e.getMessage());
 
    }
    return pla;
}

public void GenerateZHK(Zamowienia order, Ksiazki book, int ilosc) {
    try {
        ZamowieniaHasKsiazki ZHK = new ZamowieniaHasKsiazki();
        ZHK.setKsiazkiidKsiazki(book);
        ZHK.setZamowieniaIdzamowienia(order);
        ZHK.setIloscksiazek(ilosc);
        
        zhkDAO.create(ZHK);
   
    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Error occurred: " + e.getMessage());
   
    }
}


    
    public void updateBook(Ksiazki book, int ilosc) {
        int pom = book.getIloscsztuk() - ilosc;
        book.setIloscsztuk(pom);
        ksiazkiDAO.merge(book);
    }

    
    
    
    public static String generateRandomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

}
