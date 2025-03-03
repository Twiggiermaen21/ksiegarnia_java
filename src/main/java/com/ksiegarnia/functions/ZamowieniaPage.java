/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.functions;

import com.ksiegarnia.dao.ZamowieniaDAO;
import com.ksiegarnia.dao.ZamowieniaKsiazkiDAO;

import com.ksiegarnia.entities.*;

import com.ksiegarnia.enums.Orders;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import org.primefaces.component.api.UIColumn;
import org.primefaces.event.ColumnToggleEvent;
import org.primefaces.model.Visibility;

@Named
@ViewScoped
public class ZamowieniaPage implements Serializable {

    private static final String PAGE_Orders = "orderedBooks?faces-redirect=true";
    private List<Zamowienia> list;
    private List<ZamowieniaHasKsiazki> zhklist;
    private Uzytkownik user;

    @Inject
    UserData data;

    @EJB
    ZamowieniaDAO zamowieniaDAO;
    @EJB
    ZamowieniaKsiazkiDAO zamowieniaKsiazkiDAO;

    public int idUzytkownik() {
        user = data.getLoggedInUser();
        return user.getIdUzytkownik();
    }

    public List<Zamowienia> getList() {
        list = zamowieniaDAO.getFullList(data.getUser());
        return list;
    }

    public List<Orders> getZHK() {
        List<Orders> ListaZbady = new ArrayList<>();
        zhklist = zamowieniaKsiazkiDAO.getFullList(getList());
        for (int i = 0; i < list.size(); i++) {
            List<Ksiazki> ksiazki = new ArrayList<>();
            List<Integer> bookCount = new ArrayList<>();
            for (int j = 0; j < zhklist.size(); j++) {
                if (list.get(i).equals(zhklist.get(j).getZamowieniaIdzamowienia())) {
                    ksiazki.add(zhklist.get(j).getKsiazkiidKsiazki());
                    bookCount.add(zhklist.get(j).getIloscksiazek());
                }

            }
            Orders pom = new Orders(ksiazki, list.get(i), bookCount);
            ListaZbady.add(pom);
        }
        return ListaZbady;
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
        return PAGE_Orders;
    }
}
