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

    // Metoda walidująca adres
    public void validateAddress(FacesContext context, String address) throws ValidatorException {
        if (address == null || address.trim().isEmpty()) {
            FacesMessage msg = new FacesMessage("Adres nie może być pusty");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if (address.trim().length() < 5) {
            FacesMessage msg = new FacesMessage("Adres musi mieć co najmniej 5 znaków");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    // Metoda walidująca metodę płatności
    public void validatePaymentMethod(FacesContext context, Integer paymentMethod) throws ValidatorException {
        if (paymentMethod == null || paymentMethod == 0) {
            FacesMessage msg = new FacesMessage("Wybierz metodę płatności");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    // Metoda do przetwarzania danych po wysłaniu formularza
    public void submit() {
        // Możesz tutaj dodać logikę biznesową po zatwierdzeniu formularza
        FacesMessage msg = new FacesMessage("Formularz został wysłany");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
