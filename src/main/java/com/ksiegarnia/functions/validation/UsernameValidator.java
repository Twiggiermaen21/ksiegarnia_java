/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions.validation;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator <String>{

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        String username = value;

        // Sprawdzenie, czy nazwa użytkownika jest pusta
        if (username == null || username.trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Imie użytkownika jest wymagana."));
        }

        // Wyrażenie regularne do walidacji formatu nazwy użytkownika
        String usernamePattern = "^[a-zA-Z0-9._-]+$";  // Dozwolone litery, cyfry, kropki, podkreślenia i myślniki

        // Sprawdzenie, czy nazwa użytkownika pasuje do wzorca
        if (!username.matches(usernamePattern)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Imie użytkownika może zawierać tylko litery, cyfry, kropki, podkreślenia i myślniki."));
        }

        // Dodatkowe sprawdzenie długości (np. od 5 do 15 znaków)
        if (username.length() < 5 || username.length() > 15) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Imie użytkownika musi mieć od 5 do 15 znaków."));
        }
    }
}