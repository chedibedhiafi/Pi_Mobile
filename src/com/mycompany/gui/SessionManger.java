/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author bilel
 */
public class SessionManger {
    
    public static Preferences pref ; 
    
    private static int id ; 
    private static String username;
    private static String email;
    private static String nom;
    private static String prenom;
    private static String password;
    private static String roles;

    
    
    public static Preferences getPref(){
        return pref;
    }
    public static void setPref(Preferences pref){
        SessionManger.pref = pref ; 
        
    }

    public static int getId() {
        return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id", id);
    }
    
    public static String getRoles() {
        return pref.get("roles",roles);
    }

    public static void setRoles(String roles) {
        pref.set("roles", roles);
    }

    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
        pref.set("nom", nom);
    }

    public static String getPrenom() {
        return pref.get("prenom", prenom);
    }

    public static void setPrenom(String prenom) {
         pref.set("prenom", prenom);
    }
    
    

    public static String getUsername() {
       return pref.get("username",username);
    }

    public static void setUsername(String username) {
         pref.set("username", username);
        
    }

    public static String getEmail() {
         return pref.get("email",email);
    }

    public static void setEmail(String email) {
      pref.set("email", email);
    }

    public static String getPassword() {
         return pref.get("password",password);
    }

    public static void setPassword(String password) {
           pref.set("password", password);
    }


    static class getEmail {

        public getEmail() {
        }
    }
    
   
    
}
