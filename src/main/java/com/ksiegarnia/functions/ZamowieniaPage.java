/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.ZamowieniaDAO;
import com.ksiegarnia.dao.ZamowieniaKsiazkiDAO;
import com.ksiegarnia.entities.Uzytkownik;
import com.ksiegarnia.entities.Zamowienia;
import com.ksiegarnia.entities.ZamowieniaHasKsiazki;
import jakarta.ejb.EJB;

import jakarta.faces.context.ExternalContext;

import jakarta.inject.Inject;

import jakarta.inject.Named;
import java.util.List;
import com.ksiegarnia.functions.UserData;
import jakarta.faces.application.FacesMessage;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import org.primefaces.component.api.UIColumn;
import org.primefaces.event.ColumnToggleEvent;
import org.primefaces.model.Visibility;

@Named
@ViewScoped
public class ZamowieniaPage implements Serializable  {
    private static final String PAGE_Orders = "orderedBooks?faces-redirect=true";
private List<Zamowienia> list;
private List<ZamowieniaHasKsiazki> zhklist;
 private Uzytkownik user;
    @Inject
    ExternalContext extcontext;

    @Inject
    UserData data;

    @EJB
    ZamowieniaDAO zamowieniaDAO;
     @EJB
    ZamowieniaKsiazkiDAO zamowieniaKsiazkiDAO;
    

       public int idUzytkownik(){
          user =data. getLoggedInUser();
        return user.getIdUzytkownik();
    }
    
     public List<Zamowienia> getList() {
        idUzytkownik();
     
        //2. Get list
        list = zamowieniaDAO.getFullList(user);

     
        return list;
    }
     
     public List<ZamowieniaHasKsiazki> getZHK(){
         getList();
         zhklist=zamowieniaKsiazkiDAO.getFullList(list);
        
         return zhklist;
     }
    
      public void onToggle(ColumnToggleEvent e) {
        Integer index = (Integer) e.getData();
        UIColumn column = e.getColumn();
        Visibility visibility = e.getVisibility();
        String header = column.getAriaHeaderText() != null ? column.getAriaHeaderText() : column.getHeaderText();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Column " + index + " toggled: " + header + " " + visibility, null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String OrdersPage() {
        //1. Pass object through session
        //HttpSession session = (HttpSession) extcontext.getSession(true);
        //session.setAttribute("person", person);

        return PAGE_Orders;
    }
}
