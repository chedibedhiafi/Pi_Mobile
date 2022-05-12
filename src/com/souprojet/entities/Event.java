package com.souprojet.entities;

import java.util.Date;
import com.souprojet.utils.*;

public class Event {
    
    private int id;
     private Date date;
     private String titre;
     private String prix;
     private String image;
     private String description;
     private int nbPlaces;
    
    public Event() {}

    public Event(int id, Date date, String titre, String prix, String image, String description, int nbPlaces) {
        this.id = id;
        this.date = date;
        this.titre = titre;
        this.prix = prix;
        this.image = image;
        this.description = description;
        this.nbPlaces = nbPlaces;
    }

    public Event(Date date, String titre, String prix, String image, String description, int nbPlaces) {
        this.date = date;
        this.titre = titre;
        this.prix = prix;
        this.image = image;
        this.description = description;
        this.nbPlaces = nbPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
    
    
    
}