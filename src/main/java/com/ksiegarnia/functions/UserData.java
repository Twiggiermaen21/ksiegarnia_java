
package com.ksiegarnia.functions;

import jakarta.inject.Named;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.UzytkownikHasRola;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserData implements Serializable {
    private Uzytkownik user;
    private static final String PAGE_USER = "userPage?faces-redirect=true";
    
    @Inject
    ExternalContext extcontext;

    public Uzytkownik getLoggedInUser() {

        HttpSession session = (HttpSession) extcontext.getSession(false);
        if (session != null) {
            return (Uzytkownik) session.getAttribute("user");
        }
        return null;
    }

    public List<UzytkownikHasRola> getUserRoles() {
        HttpSession session = (HttpSession) extcontext.getSession(false);
        if (session != null) {
            return (List<UzytkownikHasRola>) session.getAttribute("role");
        }
        return null;
    }

    public Uzytkownik getUser() {
        user = getLoggedInUser();
        return user;
    }

    public String userPage() {
        return PAGE_USER;
    }

}
