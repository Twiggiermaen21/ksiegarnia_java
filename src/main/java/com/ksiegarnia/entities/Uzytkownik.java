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
@Table(name = "uzytkownik")
@NamedQueries({
    @NamedQuery(name = "Uzytkownik.findAll", query = "SELECT u FROM Uzytkownik u"),
    @NamedQuery(name = "Uzytkownik.findByIdUzytkownik", query = "SELECT u FROM Uzytkownik u WHERE u.idUzytkownik = :idUzytkownik"),
    @NamedQuery(name = "Uzytkownik.findByImie", query = "SELECT u FROM Uzytkownik u WHERE u.imie = :imie"),
    @NamedQuery(name = "Uzytkownik.findByNazwisko", query = "SELECT u FROM Uzytkownik u WHERE u.nazwisko = :nazwisko"),
    @NamedQuery(name = "Uzytkownik.findByDataaktualizacji", query = "SELECT u FROM Uzytkownik u WHERE u.dataaktualizacji = :dataaktualizacji"),
    @NamedQuery(name = "Uzytkownik.findByIdaktualizacji", query = "SELECT u FROM Uzytkownik u WHERE u.idaktualizacji = :idaktualizacji"),
    @NamedQuery(name = "Uzytkownik.findByEmail", query = "SELECT u FROM Uzytkownik u WHERE u.email = :email"),
    @NamedQuery(name = "Uzytkownik.findByHaslo", query = "SELECT u FROM Uzytkownik u WHERE u.haslo = :haslo"),
    @NamedQuery(name = "Uzytkownik.findByDatautworzenia", query = "SELECT u FROM Uzytkownik u WHERE u.datautworzenia = :datautworzenia"),
    @NamedQuery(name = "Uzytkownik.findByAktywny", query = "SELECT u FROM Uzytkownik u WHERE u.aktywny = :aktywny")})
public class Uzytkownik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUzytkownik")
    private Integer idUzytkownik;
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
    @Column(name = "Data_aktualizacji")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataaktualizacji;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_aktualizacji")
    private int idaktualizacji;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Haslo")
    private String haslo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data_utworzenia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datautworzenia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aktywny")
    private Boolean aktywny;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uzytkownikidUzytkownik")
    private Collection<UzytkownikHasRola> uzytkownikHasRolaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uzytkownikidUzytkownik")
    private Collection<Zamowienia> zamowieniaCollection;

    public Uzytkownik() {
    }

    public Uzytkownik(Integer idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public Uzytkownik(Integer idUzytkownik, String imie, String nazwisko, Date dataaktualizacji, int idaktualizacji, String email, String haslo, Date datautworzenia, Boolean aktywny) {
        this.idUzytkownik = idUzytkownik;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataaktualizacji = dataaktualizacji;
        this.idaktualizacji = idaktualizacji;
        this.email = email;
        this.haslo = haslo;
        this.datautworzenia = datautworzenia;
        this.aktywny = aktywny;
    }

    public Integer getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(Integer idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
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

    public Date getDataaktualizacji() {
        return dataaktualizacji;
    }

    public void setDataaktualizacji(Date dataaktualizacji) {
        this.dataaktualizacji = dataaktualizacji;
    }

    public int getIdaktualizacji() {
        return idaktualizacji;
    }

    public void setIdaktualizacji(int idaktualizacji) {
        this.idaktualizacji = idaktualizacji;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Date getDatautworzenia() {
        return datautworzenia;
    }

    public void setDatautworzenia(Date datautworzenia) {
        this.datautworzenia = datautworzenia;
    }

    public Boolean getAktywny() {
        return aktywny;
    }

    public void setAktywny(Boolean aktywny) {
        this.aktywny = aktywny;
    }

    public Collection<UzytkownikHasRola> getUzytkownikHasRolaCollection() {
        return uzytkownikHasRolaCollection;
    }

    public void setUzytkownikHasRolaCollection(Collection<UzytkownikHasRola> uzytkownikHasRolaCollection) {
        this.uzytkownikHasRolaCollection = uzytkownikHasRolaCollection;
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
        hash += (idUzytkownik != null ? idUzytkownik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownik)) {
            return false;
        }
        Uzytkownik other = (Uzytkownik) object;
        if ((this.idUzytkownik == null && other.idUzytkownik != null) || (this.idUzytkownik != null && !this.idUzytkownik.equals(other.idUzytkownik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.Uzytkownik[ idUzytkownik=" + idUzytkownik + " ]";
    }
    
}
