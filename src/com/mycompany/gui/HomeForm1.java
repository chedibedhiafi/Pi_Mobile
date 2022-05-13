/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author hassen
 */
public class HomeForm1 extends Form {
Form hi=this;
   public HomeForm1() {
   setLayout(new BoxLayout(BoxLayout.Y_AXIS));
getAllStyles().setBgColor(0xffd700);
       setTitle("Home page");
        setLayout(BoxLayout.y());
        Button btnAdd = new Button( "Ajout produit");
        Button btnShow = new Button( "Affiche produit");

        
        //new
        Button btnAjoutProduit = new Button( "Ajout produit");
        Button btnAfficheProduit = new Button( "Affiche produit");
 
        
         Button btnAjoutMarque = new Button( "Ajout marque");
        Button btnAfficheMarque = new Button( "Affiche marque");
      
        
         Button btnAjoutCategorie = new Button( "Ajout categorie");
        Button btnAfficheCategorie = new Button( "Affiche categorie");
       
        
        Button btnAjoutSousCategorie = new Button( "Ajout souscategorie");
        Button btnAfficheSousCategorie = new Button( "Affiche souscategorie");
        
        
        

        addAll(btnAdd,btnShow ,btnAjoutMarque,btnAfficheMarque,btnAjoutCategorie,btnAfficheCategorie,btnAjoutSousCategorie,btnAfficheSousCategorie);
        //ajouter les deux boutton dans notre interface 
    } 
    
}
