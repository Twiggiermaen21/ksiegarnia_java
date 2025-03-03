
package com.ksiegarnia.enums;

import com.ksiegarnia.entities.Ksiazki;

public class Basket {
    private Ksiazki ksiazka;
    private int ilosc;

    public Basket(Ksiazki ksiazka, int ilosc) {
        this.ksiazka = ksiazka;
        this.ilosc = ilosc;
    }

  
    public Ksiazki getKsiazka() {
        return ksiazka;
    }

    public void setKsiazka(Ksiazki ksiazka) {
        this.ksiazka = ksiazka;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
               "ksiazka=" + ksiazka.getTytul() +
               ", ilosc=" + ilosc +
               '}';
    }
}

