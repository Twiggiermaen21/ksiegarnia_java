/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ksiegarnia.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author kacpe
 */
@Entity
@Table(name = "zamowienia_has_ksiazki")
@NamedQueries({
    @NamedQuery(name = "ZamowieniaHasKsiazki.findAll", query = "SELECT z FROM ZamowieniaHasKsiazki z"),
    @NamedQuery(name = "ZamowieniaHasKsiazki.findByIdZamowieniahasksiazki", query = "SELECT z FROM ZamowieniaHasKsiazki z WHERE z.idZamowieniahasksiazki = :idZamowieniahasksiazki"),
    @NamedQuery(name = "ZamowieniaHasKsiazki.findByIloscksiazek", query = "SELECT z FROM ZamowieniaHasKsiazki z WHERE z.iloscksiazek = :iloscksiazek")})
public class ZamowieniaHasKsiazki implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idZamowienia_has_ksiazki")
    private Integer idZamowieniahasksiazki;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ilosc_ksiazek")
    private int iloscksiazek;
    @JoinColumn(name = "Ksiazki_idKsiazki", referencedColumnName = "idKsiazki")
    @ManyToOne(optional = false)
    private Ksiazki ksiazkiidKsiazki;
    @JoinColumn(name = "zamowienia_idzam\u00f3wienia", referencedColumnName = "idzamowienia")
    @ManyToOne(optional = false)
    private Zamowienia zamowieniaIdzamówienia;

    public ZamowieniaHasKsiazki() {
    }

    public ZamowieniaHasKsiazki(Integer idZamowieniahasksiazki) {
        this.idZamowieniahasksiazki = idZamowieniahasksiazki;
    }

    public ZamowieniaHasKsiazki(Integer idZamowieniahasksiazki, int iloscksiazek) {
        this.idZamowieniahasksiazki = idZamowieniahasksiazki;
        this.iloscksiazek = iloscksiazek;
    }

    public Integer getIdZamowieniahasksiazki() {
        return idZamowieniahasksiazki;
    }

    public void setIdZamowieniahasksiazki(Integer idZamowieniahasksiazki) {
        this.idZamowieniahasksiazki = idZamowieniahasksiazki;
    }

    public int getIloscksiazek() {
        return iloscksiazek;
    }

    public void setIloscksiazek(int iloscksiazek) {
        this.iloscksiazek = iloscksiazek;
    }

    public Ksiazki getKsiazkiidKsiazki() {
        return ksiazkiidKsiazki;
    }

    public void setKsiazkiidKsiazki(Ksiazki ksiazkiidKsiazki) {
        this.ksiazkiidKsiazki = ksiazkiidKsiazki;
    }

    public Zamowienia getZamowieniaIdzamówienia() {
        return zamowieniaIdzamówienia;
    }

    public void setZamowieniaIdzamówienia(Zamowienia zamowieniaIdzamówienia) {
        this.zamowieniaIdzamówienia = zamowieniaIdzamówienia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZamowieniahasksiazki != null ? idZamowieniahasksiazki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZamowieniaHasKsiazki)) {
            return false;
        }
        ZamowieniaHasKsiazki other = (ZamowieniaHasKsiazki) object;
        if ((this.idZamowieniahasksiazki == null && other.idZamowieniahasksiazki != null) || (this.idZamowieniahasksiazki != null && !this.idZamowieniahasksiazki.equals(other.idZamowieniahasksiazki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.ZamowieniaHasKsiazki[ idZamowieniahasksiazki=" + idZamowieniahasksiazki + " ]";
    }
    
}
