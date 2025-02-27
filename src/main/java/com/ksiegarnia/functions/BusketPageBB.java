/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.entities.Ksiazki;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author kacpe
 */

@Named
@SessionScoped
public class BusketPageBB implements Serializable{
    private List<Ksiazki> busket;
 
    @Inject
    ExternalContext extcontext;
    
    public List< Ksiazki> getBusket(){
   HttpSession session = (HttpSession) extcontext.getSession(false);
 
    
    busket = (List<Ksiazki>) session.getAttribute("busket");

     
        
        return busket;
    }
    
    
}
