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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author kacpe
 */
@Entity
@Table(name = "wiadomosci")
@NamedQueries({
    @NamedQuery(name = "Wiadomosci.findAll", query = "SELECT w FROM Wiadomosci w"),
    @NamedQuery(name = "Wiadomosci.findByIdWiadomosci", query = "SELECT w FROM Wiadomosci w WHERE w.idWiadomosci = :idWiadomosci"),
    @NamedQuery(name = "Wiadomosci.findByName", query = "SELECT w FROM Wiadomosci w WHERE w.name = :name"),
    @NamedQuery(name = "Wiadomosci.findByEmail", query = "SELECT w FROM Wiadomosci w WHERE w.email = :email")})
public class Wiadomosci implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWiadomosci")
    private Integer idWiadomosci;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Text")
    private String text;

    public Wiadomosci() {
    }

    public Wiadomosci(Integer idWiadomosci) {
        this.idWiadomosci = idWiadomosci;
    }

    public Wiadomosci(Integer idWiadomosci, String name, String email, String text) {
        this.idWiadomosci = idWiadomosci;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    public Integer getIdWiadomosci() {
        return idWiadomosci;
    }

    public void setIdWiadomosci(Integer idWiadomosci) {
        this.idWiadomosci = idWiadomosci;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWiadomosci != null ? idWiadomosci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wiadomosci)) {
            return false;
        }
        Wiadomosci other = (Wiadomosci) object;
        if ((this.idWiadomosci == null && other.idWiadomosci != null) || (this.idWiadomosci != null && !this.idWiadomosci.equals(other.idWiadomosci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ksiegarnia.entities.Wiadomosci[ idWiadomosci=" + idWiadomosci + " ]";
    }
    
}
