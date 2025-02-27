/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ksiegarnia.dao.UserDAO;
import com.ksiegarnia.dao.UzytkownikHasRolaDAO;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.UzytkownikHasRola;
import jakarta.ejb.EJB;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named("logInOutBB")
@SessionScoped
@WebServlet("/LoginServlet")
public class LogInOutBB extends HttpServlet {

    private String email;
    private String password;
    private Uzytkownik loggedUser;
    private List<UzytkownikHasRola> loggedRola;
    private boolean loggedIn = false;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    UserDAO userDAO;
    @EJB
    UzytkownikHasRolaDAO uzytkownikHasRolaDAO;

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

    public Uzytkownik getLoggedUser() {
        return loggedUser;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String login() {
        Uzytkownik users = userDAO.getUser(email, password); // Pobieramy użytkownika po emailu
        List<UzytkownikHasRola> userRole = uzytkownikHasRolaDAO.getFullList(users);
        if (users != null || userRole != null) {
            loggedUser = users;
            loggedRola = userRole;
            loggedIn = true;

            // Pobranie sesji i zapisanie użytkownika
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            HttpSession session = request.getSession(true);
            session.setAttribute("user", loggedUser); // Zapisanie użytkownika w sesji
            session.setAttribute("role", loggedRola);
            return "index.xhtml?faces-redirect=true"; // Przekierowanie na stronę powitalną
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nieprawidłowe dane logowania!", ""));
            if (users == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "puste", ""));

            }

            return null;
        }
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        loggedUser = null;
        loggedIn = false;
        return "login.xhtml?faces-redirect=true";
    }

    public String loginPage() {

        return "login.xhtml?faces-redirect=true";
    }

    public String registerPage() {

        return "register.xhtml?faces-redirect=true";
    }

}
