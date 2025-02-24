/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author kacpe
 */
@Entity
@Table(name = "ksiazki")
@NamedQueries({
    @NamedQuery(name = "Ksiazki.findAll", query = "SELECT k FROM Ksiazki k"),
    @NamedQuery(name = "Ksiazki.findByIdKsiazki", query = "SELECT k FROM Ksiazki k WHERE k.idKsiazki = :idKsiazki"),
    @NamedQuery(name = "Ksiazki.findByTytul", query = "SELECT k FROM Ksiazki k WHERE k.tytul = :tytul"),
    @NamedQuery(name = "Ksiazki.findByCena", query = "SELECT k FROM Ksiazki k WHERE k.cena = :cena"),
    @NamedQuery(name = "Ksiazki.findByIloscstron", query = "SELECT k FROM Ksiazki k WHERE k.iloscstron = :iloscstron"),
    @NamedQuery(name = "Ksiazki.findByOpis", query = "SELECT k FROM Ksiazki k WHERE k.opis = :opis"),
    @NamedQuery(name = "Ksiazki.findByIloscsztuk", query = "SELECT k FROM Ksiazki k WHERE k.iloscsztuk = :iloscsztuk")})
public class Ksiazki implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKsiazki")
    private Integer idKsiazki;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tytul")
    private String tytul;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cena")
    private double cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ilosc_stron")
    private int iloscstron;
    @Size(max = 1000)
    @Column(name = "opis")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ilosc_sztuk")
    private int iloscsztuk;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "img_url")
    private String imgUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ksiazkiidKsiazki")
    private Collection<KsiazkiHasAutorKsiazki> ksiazkiHasAutorKsiazkiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ksiazkiidKsiazki")
    private Collection<ZamowieniaHasKsiazki> zamowieniaHasKsiazkiCollection;

    public Ksiazki() {
    }

    public Ksiazki(Integer idKsiazki) {
        this.idKsiazki = idKsiazki;
    }

    public Ksiazki(Integer idKsiazki, String tytul, double cena, int iloscstron, int iloscsztuk, String imgUrl) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.cena = cena;
        this.iloscstron = iloscstron;
        this.iloscsztuk = iloscsztuk;
        this.imgUrl = imgUrl;
    }

    public Integer getIdKsiazki() {
        return idKsiazki;
    }

    public void setIdKsiazki(Integer idKsiazki) {
        this.idKsiazki = idKsiazki;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getIloscstron() {
        return iloscstron;
    }

    public void setIloscstron(int iloscstron) {
        this.iloscstron = iloscstron;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getIloscsztuk() {
        return iloscsztuk;
    }

    public void setIloscsztuk(int iloscsztuk) {
        this.iloscsztuk = iloscsztuk;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Collection<KsiazkiHasAutorKsiazki> getKsiazkiHasAutorKsiazkiCollection() {
        return ksiazkiHasAutorKsiazkiCollection;
    }

    public void setKsiazkiHasAutorKsiazkiCollection(Collection<KsiazkiHasAutorKsiazki> ksiazkiHasAutorKsiazkiCollection) {
        this.ksiazkiHasAutorKsiazkiCollection = ksiazkiHasAutorKsiazkiCollection;
    }

    public Collection<ZamowieniaHasKsiazki> getZamowieniaHasKsiazkiCollection() {
        return zamowieniaHasKsiazkiCollection;
    }

    public void setZamowieniaHasKsiazkiCollection(Collection<ZamowieniaHasKsiazki> zamowieniaHasKsiazkiCollection) {
        this.zamowieniaHasKsiazkiCollection = zamowieniaHasKsiazkiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKsiazki != null ? idKsiazki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ksiazki)) {
            return false;
        }
        Ksiazki other = (Ksiazki) object;
        if ((this.idKsiazki == null && other.idKsiazki != null) || (this.idKsiazki != null && !this.idKsiazki.equals(other.idKsiazki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.Ksiazki[ idKsiazki=" + idKsiazki + " ]";
    }
    
}
