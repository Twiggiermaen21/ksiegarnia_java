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
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;

import jakarta.inject.Named;
import java.util.List;
import com.ksiegarnia.functions.UserData;

@Named
@RequestScoped
public class ZamowieniaPage {
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
         
        
         return zhklist;
     }
    
    
    
    public String OrdersPage() {
        //1. Pass object through session
        //HttpSession session = (HttpSession) extcontext.getSession(true);
        //session.setAttribute("person", person);

        return PAGE_Orders;
    }
}
