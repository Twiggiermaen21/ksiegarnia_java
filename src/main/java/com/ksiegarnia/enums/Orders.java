
package com.ksiegarnia.enums;
import com.ksiegarnia.entities.Ksiazki;
import com.ksiegarnia.entities.Zamowienia;


 import java.util.List;

public class Orders {
    private List<Ksiazki> ksiazki;
    private Zamowienia zamowienia;
    private List<Integer> iloscKsiazek;


    public Orders(List<Ksiazki> ksiazki, Zamowienia zamowienia, List<Integer> iloscKsiazek) {
        this.ksiazki = ksiazki;
        this.zamowienia = zamowienia;
        this.iloscKsiazek = iloscKsiazek;
    }


    public List<Ksiazki> getKsiazki() {
        return ksiazki;
    }

    public Zamowienia getZamowienia() {
        return zamowienia;
    }

    public List<Integer> getIloscKsiazek() {
        return iloscKsiazek;
    }


    public void setKsiazki(List<Ksiazki> ksiazki) {
        this.ksiazki = ksiazki;
    }

    public void setZamowienia(Zamowienia zamowienia) {
        this.zamowienia = zamowienia;
    }

    public void setIloscKsiazek(List<Integer> iloscKsiazek) {
        this.iloscKsiazek = iloscKsiazek;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "ksiazki=" + ksiazki +
                ", zamowienia=" + zamowienia +
                ", iloscKsiazek=" + iloscKsiazek +
                '}';
    }
}