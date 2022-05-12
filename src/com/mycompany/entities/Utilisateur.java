/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author bilel
 */
public class Utilisateur {
    private int id;
    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String roles;
    private Date datedenaissance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String mdp, String roles, Date datedenaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = mdp;
        this.roles = roles;
        this.datedenaissance = datedenaissance;
    }

    public Utilisateur(String nom, String prenom, String email, String mdp,String roles,Date datedenaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = mdp;
        this.roles = roles;
        this.datedenaissance = datedenaissance;
    }

    public Utilisateur(String nom, String prenom, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = mdp;
    }
    

    public Utilisateur() {
        
    }
   

    public String getRoles() {
        return roles;
    }

   
    public void setRoles(String roles) {
        this.roles = roles;
    }
   
    

   

     
}
