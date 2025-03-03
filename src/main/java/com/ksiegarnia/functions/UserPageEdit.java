
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.UserDAO;
import com.ksiegarnia.entities.Uzytkownik;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.PersistenceException;
import java.io.Serializable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;


@Named
@ViewScoped
public class UserPageEdit implements Serializable {
    private Uzytkownik user;

    @Inject
    UserData data;
    @Inject
    UserDAO userDAO;

    @PostConstruct
    public void init() {
        user = data.getUser();
    }

    public Uzytkownik getuser() {
        return user;
    }

    public void setService(UserData service) {
        this.data = service;
    }

  public void onRowEdit(RowEditEvent<Uzytkownik> event) {
    try {
        Uzytkownik userr = (Uzytkownik) event.getObject();
        if (userr != null) {
            userDAO.merge(userr);  
            FacesMessage msg = new FacesMessage("User Edited", String.valueOf(userr.getEmail()));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    } catch (PersistenceException e) {
      
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", "Something went wrong with the database.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    } catch (Exception e) {
      
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unexpected Error", "Something went wrong.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
    
    public void onRowCancel(RowEditEvent<Uzytkownik> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getEmail()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }
}
