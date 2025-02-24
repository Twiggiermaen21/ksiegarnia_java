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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author kacpe
 */
@Entity
@Table(name = "uzytkownik_has_rola")
@NamedQueries({
    @NamedQuery(name = "UzytkownikHasRola.findAll", query = "SELECT u FROM UzytkownikHasRola u"),
    @NamedQuery(name = "UzytkownikHasRola.findByDatanadania", query = "SELECT u FROM UzytkownikHasRola u WHERE u.datanadania = :datanadania"),
    @NamedQuery(name = "UzytkownikHasRola.findByDatazabrania", query = "SELECT u FROM UzytkownikHasRola u WHERE u.datazabrania = :datazabrania"),
    @NamedQuery(name = "UzytkownikHasRola.findByIdUzytkownikhasRola", query = "SELECT u FROM UzytkownikHasRola u WHERE u.idUzytkownikhasRola = :idUzytkownikhasRola")})
public class UzytkownikHasRola implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data_nadania")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datanadania;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Data_zabrania")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datazabrania;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUzytkownik_has_Rola")
    private Integer idUzytkownikhasRola;
    @JoinColumn(name = "Rola_idRola", referencedColumnName = "idRola")
    @ManyToOne(optional = false)
    private Rola rolaidRola;
    @JoinColumn(name = "Uzytkownik_idUzytkownik", referencedColumnName = "idUzytkownik")
    @ManyToOne(optional = false)
    private Uzytkownik uzytkownikidUzytkownik;

    public UzytkownikHasRola() {
    }

    public UzytkownikHasRola(Integer idUzytkownikhasRola) {
        this.idUzytkownikhasRola = idUzytkownikhasRola;
    }

    public UzytkownikHasRola(Integer idUzytkownikhasRola, Date datanadania, Date datazabrania) {
        this.idUzytkownikhasRola = idUzytkownikhasRola;
        this.datanadania = datanadania;
        this.datazabrania = datazabrania;
    }

    public Date getDatanadania() {
        return datanadania;
    }

    public void setDatanadania(Date datanadania) {
        this.datanadania = datanadania;
    }

    public Date getDatazabrania() {
        return datazabrania;
    }

    public void setDatazabrania(Date datazabrania) {
        this.datazabrania = datazabrania;
    }

    public Integer getIdUzytkownikhasRola() {
        return idUzytkownikhasRola;
    }

    public void setIdUzytkownikhasRola(Integer idUzytkownikhasRola) {
        this.idUzytkownikhasRola = idUzytkownikhasRola;
    }

    public Rola getRolaidRola() {
        return rolaidRola;
    }

    public void setRolaidRola(Rola rolaidRola) {
        this.rolaidRola = rolaidRola;
    }

    public Uzytkownik getUzytkownikidUzytkownik() {
        return uzytkownikidUzytkownik;
    }

    public void setUzytkownikidUzytkownik(Uzytkownik uzytkownikidUzytkownik) {
        this.uzytkownikidUzytkownik = uzytkownikidUzytkownik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUzytkownikhasRola != null ? idUzytkownikhasRola.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UzytkownikHasRola)) {
            return false;
        }
        UzytkownikHasRola other = (UzytkownikHasRola) object;
        if ((this.idUzytkownikhasRola == null && other.idUzytkownikhasRola != null) || (this.idUzytkownikhasRola != null && !this.idUzytkownikhasRola.equals(other.idUzytkownikhasRola))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.UzytkownikHasRola[ idUzytkownikhasRola=" + idUzytkownikhasRola + " ]";
    }
    
}
