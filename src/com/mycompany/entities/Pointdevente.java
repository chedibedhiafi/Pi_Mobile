/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import com.codename1.l10n.DateFormat;
import java.util.Date;



/**
 *
 * @author walid
 */
public class Pointdevente {
    private int reference;
    private String name;
    private String proprietaire;
    private String adresse;
    private Date dateOuverture;
    private String longitude;
    private String latitude;

    public Pointdevente() {
    }

    public Pointdevente(int reference, String name, String proprietaire, String adresse, Date dateOuverture, String longitude, String latitude) {
        this.reference = reference;
        this.name = name;
        this.proprietaire = proprietaire;
        this.adresse = adresse;
        this.dateOuverture = dateOuverture;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    

    public Pointdevente(int reference, String name) {
        this.reference = reference;
        this.name = name;
    }

    public Pointdevente(String name) {
        this.name = name;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Pointdevente{" + "reference=" + reference + ", name=" + name + ", proprietaire=" + proprietaire + ", adresse=" + adresse + ", dateOuverture=" + dateOuverture + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }
    
    
    
    
    
    
    
    
    
}
