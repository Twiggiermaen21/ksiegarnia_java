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
import java.io.Serializable;

/**
 *
 * @author kacpe
 */
@Entity
@Table(name = "ksiazki_has_autor_ksiazki")
@NamedQueries({
    @NamedQuery(name = "KsiazkiHasAutorKsiazki.findAll", query = "SELECT k FROM KsiazkiHasAutorKsiazki k"),
    @NamedQuery(name = "KsiazkiHasAutorKsiazki.findByIdKsiazkihasAutor", query = "SELECT k FROM KsiazkiHasAutorKsiazki k WHERE k.idKsiazkihasAutor = :idKsiazkihasAutor")})
public class KsiazkiHasAutorKsiazki implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKsiazki_has_Autor")
    private Integer idKsiazkihasAutor;
    @JoinColumn(name = "Autor_Ksiazki_idAutor_Ksiazki", referencedColumnName = "idAutor_Ksiazki")
    @ManyToOne(optional = false)
    private AutorKsiazki autorKsiazkiidAutorKsiazki;
    @JoinColumn(name = "Ksiazki_idKsiazki", referencedColumnName = "idKsiazki")
    @ManyToOne(optional = false)
    private Ksiazki ksiazkiidKsiazki;

    public KsiazkiHasAutorKsiazki() {
    }

    public KsiazkiHasAutorKsiazki(Integer idKsiazkihasAutor) {
        this.idKsiazkihasAutor = idKsiazkihasAutor;
    }

    public Integer getIdKsiazkihasAutor() {
        return idKsiazkihasAutor;
    }

    public void setIdKsiazkihasAutor(Integer idKsiazkihasAutor) {
        this.idKsiazkihasAutor = idKsiazkihasAutor;
    }

    public AutorKsiazki getAutorKsiazkiidAutorKsiazki() {
        return autorKsiazkiidAutorKsiazki;
    }

    public void setAutorKsiazkiidAutorKsiazki(AutorKsiazki autorKsiazkiidAutorKsiazki) {
        this.autorKsiazkiidAutorKsiazki = autorKsiazkiidAutorKsiazki;
    }

    public Ksiazki getKsiazkiidKsiazki() {
        return ksiazkiidKsiazki;
    }

    public void setKsiazkiidKsiazki(Ksiazki ksiazkiidKsiazki) {
        this.ksiazkiidKsiazki = ksiazkiidKsiazki;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKsiazkihasAutor != null ? idKsiazkihasAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KsiazkiHasAutorKsiazki)) {
            return false;
        }
        KsiazkiHasAutorKsiazki other = (KsiazkiHasAutorKsiazki) object;
        if ((this.idKsiazkihasAutor == null && other.idKsiazkihasAutor != null) || (this.idKsiazkihasAutor != null && !this.idKsiazkihasAutor.equals(other.idKsiazkihasAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.KsiazkiHasAutorKsiazki[ idKsiazkihasAutor=" + idKsiazkihasAutor + " ]";
    }
    
}
