/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import java.util.List;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;


import com.ksiegarnia.dao.KsiazkiHasAutorKsiazkiDAO;
import com.ksiegarnia.entities.KsiazkiHasAutorKsiazki;

@Named
@RequestScoped
public class KsiazkiHasAutorKsiazkiBB {

    @EJB
    KsiazkiHasAutorKsiazkiDAO ksiazkiHasAutorKsiazkiDAO;

    public List<KsiazkiHasAutorKsiazki> getFullList() {
        return ksiazkiHasAutorKsiazkiDAO.getFullList();
    }

    public List<KsiazkiHasAutorKsiazki> getList() {
        List<KsiazkiHasAutorKsiazki> list = null;

        list = ksiazkiHasAutorKsiazkiDAO.getList();

        return list;
    }

}
