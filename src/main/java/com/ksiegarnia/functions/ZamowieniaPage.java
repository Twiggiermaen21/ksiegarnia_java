/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.ZamowieniaDAO;
import com.ksiegarnia.entities.Zamowienia;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Inject;

import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author kacpe
 */
@Named
@RequestScoped
public class ZamowieniaPage {
    private static final String PAGE_Orders = "orderedBooks?faces-redirect=true";
private List<Zamowienia> list;
    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    ZamowieniaDAO zamowieniaDAO;

    public List< Zamowienia> getFullList() {
        return zamowieniaDAO.getFullList();
    }

     public List<Zamowienia> getList() {

     
        //2. Get list
        list = getFullList();

     
        return list;
    }
    
    
    
    public String OrdersPage() {
        //1. Pass object through session
        //HttpSession session = (HttpSession) extcontext.getSession(true);
        //session.setAttribute("person", person);

        return PAGE_Orders;
    }
}
