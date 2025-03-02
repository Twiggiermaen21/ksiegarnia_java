/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.enums;
import com.ksiegarnia.entities.Ksiazki;
import com.ksiegarnia.entities.Zamowienia;


 import java.util.List;

public class Orders {
    private List<Ksiazki> ksiazki;
    private Zamowienia zamowienia;
    private List<Integer> iloscKsiazek;

    // Konstruktor
    public Orders(List<Ksiazki> ksiazki, Zamowienia zamowienia, List<Integer> iloscKsiazek) {
        this.ksiazki = ksiazki;
        this.zamowienia = zamowienia;
        this.iloscKsiazek = iloscKsiazek;
    }

    // Gettery
    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public Zamowienia getZamowienia() {
        return zamowienia;
    }

    public List<Integer> getIloscKsiazek() {
        return iloscKsiazek;
    }

    // Settery
    public void setKsiazki(List<Ksiazki> ksiazki) {
        this.ksiazki = ksiazki;
    }

    public void setZamowienia(Zamowienia zamowienia) {
        this.zamowienia = zamowienia;
    }

    public void setIloscKsiazek(List<Integer> iloscKsiazek) {
        this.iloscKsiazek = iloscKsiazek;
    }

    // Metoda toString do debugowania
    @Override
    public String toString() {
        return "Orders{" +
                "ksiazki=" + ksiazki +
                ", zamowienia=" + zamowienia +
                ", iloscKsiazek=" + iloscKsiazek +
                '}';
    }
}