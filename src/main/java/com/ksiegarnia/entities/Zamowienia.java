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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author kacpe
 */
@Entity
@Table(name = "zamowienia")
@NamedQueries({
    @NamedQuery(name = "Zamowienia.findAll", query = "SELECT z FROM Zamowienia z"),
    @NamedQuery(name = "Zamowienia.findByIdzamowienia", query = "SELECT z FROM Zamowienia z WHERE z.idzamowienia = :idzamowienia"),
    @NamedQuery(name = "Zamowienia.findByDatazamowienia", query = "SELECT z FROM Zamowienia z WHERE z.datazamowienia = :datazamowienia"),
    @NamedQuery(name = "Zamowienia.findByAdres", query = "SELECT z FROM Zamowienia z WHERE z.adres = :adres"),
    @NamedQuery(name = "Zamowienia.findByKodZamowienia", query = "SELECT z FROM Zamowienia z WHERE z.kodZamowienia = :kodZamowienia")})
public class Zamowienia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idzamowienia")
    private Integer idzamowienia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data_zamowienia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datazamowienia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Adres")
    private String adres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "KodZamowienia")
    private String kodZamowienia;
    @JoinColumn(name = "Platnosci_idPlatnosci", referencedColumnName = "idPlatnosci")
    @ManyToOne(optional = false)
    private Platnosci platnosciidPlatnosci;
    @JoinColumn(name = "Uzytkownik_idUzytkownik", referencedColumnName = "idUzytkownik")
    @ManyToOne(optional = false)
    private Uzytkownik uzytkownikidUzytkownik;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zamowieniaIdzam\u00f3wienia")
    private Collection<ZamowieniaHasKsiazki> zamowieniaHasKsiazkiCollection;

    public Zamowienia() {
    }

    public Zamowienia(Integer idzamowienia) {
        this.idzamowienia = idzamowienia;
    }

    public Zamowienia(Integer idzamowienia, Date datazamowienia, String adres, String kodZamowienia) {
        this.idzamowienia = idzamowienia;
        this.datazamowienia = datazamowienia;
        this.adres = adres;
        this.kodZamowienia = kodZamowienia;
    }

    public Integer getIdzamowienia() {
        return idzamowienia;
    }

    public void setIdzamowienia(Integer idzamowienia) {
        this.idzamowienia = idzamowienia;
    }

    public Date getDatazamowienia() {
        return datazamowienia;
    }

    public void setDatazamowienia(Date datazamowienia) {
        this.datazamowienia = datazamowienia;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKodZamowienia() {
        return kodZamowienia;
    }

    public void setKodZamowienia(String kodZamowienia) {
        this.kodZamowienia = kodZamowienia;
    }

    public Platnosci getPlatnosciidPlatnosci() {
        return platnosciidPlatnosci;
    }

    public void setPlatnosciidPlatnosci(Platnosci platnosciidPlatnosci) {
        this.platnosciidPlatnosci = platnosciidPlatnosci;
    }

    public Uzytkownik getUzytkownikidUzytkownik() {
        return uzytkownikidUzytkownik;
    }

    public void setUzytkownikidUzytkownik(Uzytkownik uzytkownikidUzytkownik) {
        this.uzytkownikidUzytkownik = uzytkownikidUzytkownik;
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
        hash += (idzamowienia != null ? idzamowienia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zamowienia)) {
            return false;
        }
        Zamowienia other = (Zamowienia) object;
        if ((this.idzamowienia == null && other.idzamowienia != null) || (this.idzamowienia != null && !this.idzamowienia.equals(other.idzamowienia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.Zamowienia[ idzamowienia=" + idzamowienia + " ]";
    }
    
}
