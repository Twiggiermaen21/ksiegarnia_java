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
@Table(name = "rola")
@NamedQueries({
    @NamedQuery(name = "Rola.findAll", query = "SELECT r FROM Rola r"),
    @NamedQuery(name = "Rola.findByIdRola", query = "SELECT r FROM Rola r WHERE r.idRola = :idRola"),
    @NamedQuery(name = "Rola.findByNazwaRoli", query = "SELECT r FROM Rola r WHERE r.nazwaRoli = :nazwaRoli"),
    @NamedQuery(name = "Rola.findByAktywnosc", query = "SELECT r FROM Rola r WHERE r.aktywnosc = :aktywnosc"),
    @NamedQuery(name = "Rola.findByOstatnieUzycie", query = "SELECT r FROM Rola r WHERE r.ostatnieUzycie = :ostatnieUzycie")})
public class Rola implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRola")
    private Integer idRola;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NazwaRoli")
    private String nazwaRoli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Aktywnosc")
    private short aktywnosc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OstatnieUzycie")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ostatnieUzycie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolaidRola")
    private Collection<UzytkownikHasRola> uzytkownikHasRolaCollection;

    public Rola() {
    }

    public Rola(Integer idRola) {
        this.idRola = idRola;
    }

    public Rola(Integer idRola, String nazwaRoli, short aktywnosc, Date ostatnieUzycie) {
        this.idRola = idRola;
        this.nazwaRoli = nazwaRoli;
        this.aktywnosc = aktywnosc;
        this.ostatnieUzycie = ostatnieUzycie;
    }

    public Integer getIdRola() {
        return idRola;
    }

    public void setIdRola(Integer idRola) {
        this.idRola = idRola;
    }

    public String getNazwaRoli() {
        return nazwaRoli;
    }

    public void setNazwaRoli(String nazwaRoli) {
        this.nazwaRoli = nazwaRoli;
    }

    public short getAktywnosc() {
        return aktywnosc;
    }

    public void setAktywnosc(short aktywnosc) {
        this.aktywnosc = aktywnosc;
    }

    public Date getOstatnieUzycie() {
        return ostatnieUzycie;
    }

    public void setOstatnieUzycie(Date ostatnieUzycie) {
        this.ostatnieUzycie = ostatnieUzycie;
    }

    public Collection<UzytkownikHasRola> getUzytkownikHasRolaCollection() {
        return uzytkownikHasRolaCollection;
    }

    public void setUzytkownikHasRolaCollection(Collection<UzytkownikHasRola> uzytkownikHasRolaCollection) {
        this.uzytkownikHasRolaCollection = uzytkownikHasRolaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRola != null ? idRola.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rola)) {
            return false;
        }
        Rola other = (Rola) object;
        if ((this.idRola == null && other.idRola != null) || (this.idRola != null && !this.idRola.equals(other.idRola))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.Rola[ idRola=" + idRola + " ]";
    }
    
}
