/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;


import jakarta.inject.Named;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.UzytkownikHasRola;

import jakarta.enterprise.context.SessionScoped;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;


@Named
@SessionScoped
public class UserData implements Serializable {
    private Uzytkownik user; // Opcjonalnie, ale nie jest pobierane z sesji automatycznie!
    private static final String PAGE_USER =  "userPage?faces-redirect=true";
    public Uzytkownik getLoggedInUser() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    HttpSession session = request.getSession(false); // Pobranie istniejącej sesji

    if (session != null) {
        return (Uzytkownik) session.getAttribute("user"); // Pobranie użytkownika z sesji
    }
    return null;
}

public List<UzytkownikHasRola> getUserRoles() {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    HttpSession session = request.getSession(false);

    if (session != null) {
        return (List<UzytkownikHasRola>) session.getAttribute("role"); // Pobranie ról użytkownika z sesji
    }
    return null;
}

    

    public Uzytkownik getUser() {
        user = getLoggedInUser();
        return user; // Żeby zawsze zwracał poprawnego użytkownika
    }
    
    
    public String userPage() {
        //1. Pass object through session
        //HttpSession session = (HttpSession) extcontext.getSession(true);
        //session.setAttribute("person", person);

     

        return PAGE_USER;
    }
   
  
}

