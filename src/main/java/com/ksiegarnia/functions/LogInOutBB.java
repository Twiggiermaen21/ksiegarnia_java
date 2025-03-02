/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.ksiegarnia.dao.UserDAO;
import com.ksiegarnia.dao.UzytkownikHasRolaDAO;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.UzytkownikHasRola;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
    private FacesContext facesContext;
    private HttpServletRequest request;
    private HttpSession session;

    public LogInOutBB() {
        facesContext = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
    }

    @EJB
    UserDAO userDAO;
    @EJB
    UzytkownikHasRolaDAO uzytkownikHasRolaDAO;

    public String login() {
        Uzytkownik users = userDAO.getUser(email, password);

        List<UzytkownikHasRola> userRole = uzytkownikHasRolaDAO.getFullList(users);
        if (users != null && userRole != null) {
            loggedUser = users;
            loggedRola = userRole;
            loggedIn = true;

            session = request.getSession(true);
            session.setAttribute("user", loggedUser);
            session.setAttribute("role", loggedRola);
            return "index.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nieprawidłowe dane logowania!", ""));

            return null;
        }
    }

    public String logout() {

        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        loggedUser = null;
        loggedIn = false;
        return "login.xhtml?faces-redirect=true";
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

    public Uzytkownik getLoggedUser() {
        return loggedUser;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String loginPage() {
        return "login.xhtml?faces-redirect=true";
    }

    public String registerPage() {
        return "register.xhtml?faces-redirect=true";
    }

    public String indexPage() {
        return "index.xhtml?faces-redirect=true";
    }

}
