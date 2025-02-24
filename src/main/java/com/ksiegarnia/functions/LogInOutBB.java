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
import com.ksiegarnia.entities.Uzytkownik;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;
import java.util.List;
        
@WebServlet("/LoginServlet")
public class LogInOutBB extends HttpServlet {
   private String username;
    private String password;
 private List<Uzytkownik> user=null;
    private boolean loggedIn = false;

@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (authenticateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            response.sendRedirect("index.xhtml");
        } else {
            request.setAttribute("errorMessage", "Nieprawidłowe dane logowania!");
            request.getRequestDispatcher("login.xhtml").forward(request, response);
        }
    }

     public String login() {
        if (authenticateUser(username, password)) {
            loggedIn = true;

            // Pobranie obiektu sesji HTTP
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            HttpSession session = request.getSession(true); // Tworzy nową sesję, jeśli nie istnieje
            session.setAttribute("user", username); // Zapisanie nazwy użytkownika w sesji

            return "welcome.xhtml?faces-redirect=true"; // Przekierowanie na stronę powitalną
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nieprawidłowe dane logowania!", ""));
            return null; // Pozostanie na stronie logowania
        }
    }
    
    
   private boolean authenticateUser(String email, String password) {
     user = userDAO.getList(email, password); 
    return user != null; 
}
    
    
     public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }
    
}
