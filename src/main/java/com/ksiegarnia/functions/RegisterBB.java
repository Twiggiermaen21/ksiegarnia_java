/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.RolaDAO;
import com.ksiegarnia.dao.UserDAO;
import com.ksiegarnia.dao.UzytkownikHasRolaDAO;
import com.ksiegarnia.entities.Rola;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.UzytkownikHasRola;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class RegisterBB implements Serializable {

    private String username;
    private String surname;
    private Date dataaktualizacji;
    private int idaktualizacji;
    private String email;
    private String password;
    private Date datautworzenia;
    private Boolean aktywny;
    private Uzytkownik newUser;

    @EJB
    private UserDAO userDAO;
    @EJB
    private UzytkownikHasRolaDAO UHRDAO;
    @EJB
    private RolaDAO roleDAO;

    public RegisterBB() {
        this.idaktualizacji = 0;
        this.dataaktualizacji = new Date(0);
        this.datautworzenia = new Date();
        this.aktywny = true;
    }

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

    public String register() {
        try {
            if (username != null && surname != null && email != null && password != null
                    && userDAO.findByEmail(email) == null) {

      
                newUser = new Uzytkownik();
                newUser.setEmail(email);
                newUser.setHaslo(password);
                newUser.setAktywny(aktywny);
                newUser.setIdaktualizacji(idaktualizacji);
                newUser.setDataaktualizacji(dataaktualizacji);
                newUser.setDatautworzenia(datautworzenia);
                newUser.setImie(username);
                newUser.setNazwisko(surname);

                try {
                    userDAO.create(newUser);  
                } catch (Exception e) {
                    FacesMessage msg = new FacesMessage("Błąd przy tworzeniu użytkownika: " + e.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return null;
                }

         
                UzytkownikHasRola UHR = new UzytkownikHasRola();
                Rola role = roleDAO.getById(3);
                UHR.setRolaidRola(role);
                UHR.setDatanadania(datautworzenia);
                UHR.setUzytkownikidUzytkownik(newUser);

                try {
                    UHRDAO.create(UHR);  
                } catch (Exception e) {
                    FacesMessage msg = new FacesMessage("Błąd przy przypisywaniu roli: " + e.getMessage());
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    return null;
                }

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Rejestracja zakończona sukcesem!", null));
                return "login.xhtml?faces-redirect=true";
            } else {
                FacesMessage msg = new FacesMessage("Taki email już istnieje");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Błąd rejestracji: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return null;
    }

}
