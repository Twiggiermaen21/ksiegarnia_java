/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.entities.Ksiazki;
import com.ksiegarnia.enums.Busket;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kacpe
 */

@Named
@SessionScoped
public class BusketPageBB implements Serializable{
    private List<Busket>  busket;
    private double totalPrice; 
    @Inject
    ExternalContext extcontext;
    
    public List<Busket> getBusket(){
   HttpSession session = (HttpSession) extcontext.getSession(false);
  if (session != null) {
        busket = (List<Busket>) session.getAttribute("busket");
        if (busket == null) {
            busket = new ArrayList<>(); // Jeśli koszyk jest null, przypisz pustą listę
        }
    } else {
        busket = new ArrayList<>(); // Jeśli sesja jest null, przypisz pustą listę
    }
        return busket;
    }
    
    public double getTotalPrice(){
        if(busket!=null){
         for (Busket item : busket) {
                totalPrice += item.getKsiazka().getCena() * item.getIlosc();
                
            }
        }
         return totalPrice;
    }
    
    
    
}
