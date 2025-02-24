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
@Table(name = "platnosci")
@NamedQueries({
    @NamedQuery(name = "Platnosci.findAll", query = "SELECT p FROM Platnosci p"),
    @NamedQuery(name = "Platnosci.findByIdPlatnosci", query = "SELECT p FROM Platnosci p WHERE p.idPlatnosci = :idPlatnosci"),
    @NamedQuery(name = "Platnosci.findByKwota", query = "SELECT p FROM Platnosci p WHERE p.kwota = :kwota"),
    @NamedQuery(name = "Platnosci.findByDataPlatnosci", query = "SELECT p FROM Platnosci p WHERE p.dataPlatnosci = :dataPlatnosci"),
    @NamedQuery(name = "Platnosci.findByRodzajp\u0142atnosci", query = "SELECT p FROM Platnosci p WHERE p.rodzajp\u0142atnosci = :rodzajp\u0142atnosci"),
    @NamedQuery(name = "Platnosci.findByIdUzytkownik", query = "SELECT p FROM Platnosci p WHERE p.idUzytkownik = :idUzytkownik"),
    @NamedQuery(name = "Platnosci.findByKodPlatnosci", query = "SELECT p FROM Platnosci p WHERE p.kodPlatnosci = :kodPlatnosci")})
public class Platnosci implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlatnosci")
    private Integer idPlatnosci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kwota")
    private double kwota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data_Platnosci")
    @Temporal(TemporalType.DATE)
    private Date dataPlatnosci;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Rodzaj_p\u0142atnosci")
    private String rodzajpłatnosci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idUzytkownik")
    private int idUzytkownik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "KodPlatnosci")
    private String kodPlatnosci;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "platnosciidPlatnosci")
    private Collection<Zamowienia> zamowieniaCollection;

    public Platnosci() {
    }

    public Platnosci(Integer idPlatnosci) {
        this.idPlatnosci = idPlatnosci;
    }

    public Platnosci(Integer idPlatnosci, double kwota, Date dataPlatnosci, String rodzajpłatnosci, int idUzytkownik, String kodPlatnosci) {
        this.idPlatnosci = idPlatnosci;
        this.kwota = kwota;
        this.dataPlatnosci = dataPlatnosci;
        this.rodzajpłatnosci = rodzajpłatnosci;
        this.idUzytkownik = idUzytkownik;
        this.kodPlatnosci = kodPlatnosci;
    }

    public Integer getIdPlatnosci() {
        return idPlatnosci;
    }

    public void setIdPlatnosci(Integer idPlatnosci) {
        this.idPlatnosci = idPlatnosci;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public Date getDataPlatnosci() {
        return dataPlatnosci;
    }

    public void setDataPlatnosci(Date dataPlatnosci) {
        this.dataPlatnosci = dataPlatnosci;
    }

    public String getRodzajpłatnosci() {
        return rodzajpłatnosci;
    }

    public void setRodzajpłatnosci(String rodzajpłatnosci) {
        this.rodzajpłatnosci = rodzajpłatnosci;
    }

    public int getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(int idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public String getKodPlatnosci() {
        return kodPlatnosci;
    }

    public void setKodPlatnosci(String kodPlatnosci) {
        this.kodPlatnosci = kodPlatnosci;
    }

    public Collection<Zamowienia> getZamowieniaCollection() {
        return zamowieniaCollection;
    }

    public void setZamowieniaCollection(Collection<Zamowienia> zamowieniaCollection) {
        this.zamowieniaCollection = zamowieniaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlatnosci != null ? idPlatnosci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Platnosci)) {
            return false;
        }
        Platnosci other = (Platnosci) object;
        if ((this.idPlatnosci == null && other.idPlatnosci != null) || (this.idPlatnosci != null && !this.idPlatnosci.equals(other.idPlatnosci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.Platnosci[ idPlatnosci=" + idPlatnosci + " ]";
    }
    
}
