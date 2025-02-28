/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.entities.Ksiazki;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ValidateBusketBB {
private static final String PAGE_BILL_SHOW = "billPage?faces-redirect=true";
    private String text; // Adres
    private Integer paymentMethod; // Metoda płatności
    private Boolean agree;
    private List<Ksiazki> busket;
    @Inject
    ExternalContext extcontext;
    @Inject
    Flash flash;

    KsiazkiListBB KLBB;
    
    public Boolean getAgree() {
        return agree;
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

            busket = (List<Ksiazki>) session.getAttribute("busket");

            
              flash.put("busket", busket);
              flash.put("fieldList", fieldList);
            removeBusket();
            // Możesz tutaj dodać logikę biznesową po zatwierdzeniu formularza
            FacesMessage msg = new FacesMessage("Formularz został wysłany");
            FacesContext.getCurrentInstance().addMessage(null, msg);
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
    
    
}
