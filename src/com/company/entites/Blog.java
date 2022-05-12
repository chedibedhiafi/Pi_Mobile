/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author Haroun
 */
public class Blog {
    private int id;
    private String titre;
    private String sujet;
    private String contenu;
    private String date;

    public Blog() {
    }

    public Blog(String titre, String sujet, String contenu, String date) {
        this.titre = titre;
        this.sujet = sujet;
        this.contenu = contenu;
        this.date = date;
    }

    public Blog(int id, String titre, String sujet, String contenu, String date) {
        this.id = id;
        this.titre = titre;
        this.sujet = sujet;
        this.contenu = contenu;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", titre=" + titre + ", sujet=" + sujet + ", contenu=" + contenu + ", date=" + date + '}';
    }
    
    
    
    
}
