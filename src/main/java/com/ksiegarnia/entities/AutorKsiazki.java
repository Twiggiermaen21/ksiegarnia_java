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
@Table(name = "autor_ksiazki")
@NamedQueries({
    @NamedQuery(name = "AutorKsiazki.findAll", query = "SELECT a FROM AutorKsiazki a"),
    @NamedQuery(name = "AutorKsiazki.findByIdAutorKsiazki", query = "SELECT a FROM AutorKsiazki a WHERE a.idAutorKsiazki = :idAutorKsiazki"),
    @NamedQuery(name = "AutorKsiazki.findByImie", query = "SELECT a FROM AutorKsiazki a WHERE a.imie = :imie"),
    @NamedQuery(name = "AutorKsiazki.findByNazwisko", query = "SELECT a FROM AutorKsiazki a WHERE a.nazwisko = :nazwisko"),
    @NamedQuery(name = "AutorKsiazki.findByDataurodzenia", query = "SELECT a FROM AutorKsiazki a WHERE a.dataurodzenia = :dataurodzenia"),
    @NamedQuery(name = "AutorKsiazki.findByKrajpochodzenia", query = "SELECT a FROM AutorKsiazki a WHERE a.krajpochodzenia = :krajpochodzenia")})
public class AutorKsiazki implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAutor_Ksiazki")
    private Integer idAutorKsiazki;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Imie")
    private String imie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nazwisko")
    private String nazwisko;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data_urodzenia")
    @Temporal(TemporalType.DATE)
    private Date dataurodzenia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Kraj_pochodzenia")
    private String krajpochodzenia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autorKsiazkiidAutorKsiazki")
    private Collection<KsiazkiHasAutorKsiazki> ksiazkiHasAutorKsiazkiCollection;

    public AutorKsiazki() {
    }

    public AutorKsiazki(Integer idAutorKsiazki) {
        this.idAutorKsiazki = idAutorKsiazki;
    }

    public AutorKsiazki(Integer idAutorKsiazki, String imie, String nazwisko, Date dataurodzenia, String krajpochodzenia) {
        this.idAutorKsiazki = idAutorKsiazki;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataurodzenia = dataurodzenia;
        this.krajpochodzenia = krajpochodzenia;
    }

    public Integer getIdAutorKsiazki() {
        return idAutorKsiazki;
    }

    public void setIdAutorKsiazki(Integer idAutorKsiazki) {
        this.idAutorKsiazki = idAutorKsiazki;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getDataurodzenia() {
        return dataurodzenia;
    }

    public void setDataurodzenia(Date dataurodzenia) {
        this.dataurodzenia = dataurodzenia;
    }

    public String getKrajpochodzenia() {
        return krajpochodzenia;
    }

    public void setKrajpochodzenia(String krajpochodzenia) {
        this.krajpochodzenia = krajpochodzenia;
    }

    public Collection<KsiazkiHasAutorKsiazki> getKsiazkiHasAutorKsiazkiCollection() {
        return ksiazkiHasAutorKsiazkiCollection;
    }

    public void setKsiazkiHasAutorKsiazkiCollection(Collection<KsiazkiHasAutorKsiazki> ksiazkiHasAutorKsiazkiCollection) {
        this.ksiazkiHasAutorKsiazkiCollection = ksiazkiHasAutorKsiazkiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutorKsiazki != null ? idAutorKsiazki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorKsiazki)) {
            return false;
        }
        AutorKsiazki other = (AutorKsiazki) object;
        if ((this.idAutorKsiazki == null && other.idAutorKsiazki != null) || (this.idAutorKsiazki != null && !this.idAutorKsiazki.equals(other.idAutorKsiazki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.AutorKsiazki[ idAutorKsiazki=" + idAutorKsiazki + " ]";
    }
    
}
