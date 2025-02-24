
package com.ksiegarnia.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.ksiegarnia.dao.AutorKsiazkiDAO;
import com.ksiegarnia.entities.*;

@Named
@RequestScoped
public class AutorKsiazkiBB {
    private static final String PAGE_BOOK_SHOW = "bookShow?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
        
	private String nazwisko;
		
                @Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	AutorKsiazkiDAO autorKsiazkiDAO;
		

	public List<AutorKsiazki> getFullList(){
		return autorKsiazkiDAO.getFullList();
	}

	public List<AutorKsiazki> getList(){
		List<AutorKsiazki> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (nazwisko != null && nazwisko .length() > 0){
			searchParams.put("nazwisko", nazwisko );
		}
		
		//2. Get list
		list = autorKsiazkiDAO.getList(searchParams);
		
		return list;
	}
	
}
