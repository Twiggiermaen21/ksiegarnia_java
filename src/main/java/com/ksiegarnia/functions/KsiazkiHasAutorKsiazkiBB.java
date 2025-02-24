/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

import com.ksiegarnia.dao.KsiazkiHasAutorKsiazkiDAO;
import com.ksiegarnia.entities.KsiazkiHasAutorKsiazki;

@Named
@RequestScoped
public class KsiazkiHasAutorKsiazkiBB {
 

	
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	KsiazkiHasAutorKsiazkiDAO ksiazkiHasAutorKsiazkiDAO;
		
	

	public List<KsiazkiHasAutorKsiazki> getFullList(){
		return ksiazkiHasAutorKsiazkiDAO.getFullList();
	}

	public List<KsiazkiHasAutorKsiazki> getList(){
		List<KsiazkiHasAutorKsiazki> list = null;
		
		
		
		//2. Get list
		list = ksiazkiHasAutorKsiazkiDAO.getList();
		
		return list;
	}


}
