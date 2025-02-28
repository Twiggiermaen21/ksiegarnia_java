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

@FacesValidator("emailValidator")
public class EmailValidator implements Validator<String> {
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        // Sprawdzanie, czy wartość nie jest pusta
        if (value == null || value.trim().isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Adres e-mail jest wymagany."));
        }

        // Wyrażenie regularne sprawdzające poprawny format e-maila
        // Dozwolone litery, cyfry, kropki, myślniki i podkreślenia w nazwie użytkownika
        // W domenie dozwolone litery, cyfry oraz kropki w przypadku subdomen.
        String emailPattern = "[a-zA-Z0-9._]+@[a-zA-Z0-9]+\\.[a-zA-Z]+";

        // Sprawdzanie, czy adres e-mail pasuje do wzorca
        if (!value.matches(emailPattern)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Niepoprawny format adresu e-mail."));
        }
    }
}
