/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ValidateBusketBB {
    

    private String text; // Adres
    private Integer paymentMethod; // Metoda płatności
    private Boolean agree;

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
    public void submit() {
       if (agree != null && agree) {
        // Możesz tutaj dodać logikę biznesową po zatwierdzeniu formularza
        FacesMessage msg = new FacesMessage("Formularz został wysłany");
        FacesContext.getCurrentInstance().addMessage(null, msg);
       }
    }
}
