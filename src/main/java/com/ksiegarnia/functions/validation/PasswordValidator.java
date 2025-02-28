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

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator<String> {
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || value.isEmpty()) {
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Hasło jest wymagane."));
    }
        if (value != null && value.length() < 10) {
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", "Hasło musi mieć co najmniej 10 znaków."));
    }
        
    }
}