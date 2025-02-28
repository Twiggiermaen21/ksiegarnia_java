/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;



import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;


@Named
@SessionScoped
public class RegisterBB implements Serializable{

    private String username;
    private String surname;
    private String email;
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Metoda rejestracji
    public String register() {
        // Przykładowa logika rejestracji
        if (username != null && surname != null && email != null && password != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Rejestracja zakończona sukcesem!", null));
             return "login.xhtml?faces-redirect=true"; // lub inna strona, np. "home.xhtml"
        } 
      return null;
    }
}
