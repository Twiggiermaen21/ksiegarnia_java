/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;


import com.ksiegarnia.enums.Basket;
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
public class BasketPageBB implements Serializable{
    private List<Basket>  basket;
    private double totalPrice; 
    @Inject
    ExternalContext extcontext;
    
    public List<Basket> getBasket(){
   HttpSession session = (HttpSession) extcontext.getSession(false);
  if (session != null) {
        basket = (List<Basket>) session.getAttribute("basket");
        if (basket == null) {
            basket = new ArrayList<>(); // Jeśli koszyk jest null, przypisz pustą listę
        }
    } else {
        basket = new ArrayList<>(); // Jeśli sesja jest null, przypisz pustą listę
    }
        return basket;
    }
    
    public double getTotalPrice(){
        if(basket!=null){
         for (Basket item : basket) {
                totalPrice += item.getKsiazka().getCena() * item.getIlosc();
                
            }
        }
         return totalPrice;
    }
    
    
    
}
