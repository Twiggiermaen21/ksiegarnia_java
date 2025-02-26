/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.UserDAO;
import com.ksiegarnia.entities.Uzytkownik;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author kacpe
 */
@Named
@ViewScoped
public class UserPageEdit implements Serializable {
    private Uzytkownik user ;
    
    @Inject
   UserData data;

@PostConstruct
    public void init() {
        user = data.getUser();
       
    }
    public Uzytkownik  getuser() {
        return user;
    }
public void setService(UserData service) {
        this.data = service;
    }

    public void onRowEdit(RowEditEvent<Uzytkownik> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getIdUzytkownik()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Uzytkownik> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getIdUzytkownik()));
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