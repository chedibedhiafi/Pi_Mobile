/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.categorie;
import com.mycompany.services.ServiceCategorie;

/**
 *
 * @author chedi
 */
public class CategorieForm extends Form {
    public CategorieForm (){
        setUIID("SignIn");
        setTitle("ajout-categorie");
        setLayout(BoxLayout.y());
        TextField tfcateg = new TextField("","donner une categorie");
        Button btnajout = new Button("ajouter");
        categorie categ =new categorie();
      
      //  categ.setNom(nom);
        this.addAll(tfcateg,btnajout);
    
        btnajout.addActionListener(l->{
        //  System.out.println( );
        ServiceCategorie.getInstance().ajoutCategorie(tfcateg.getText().toString());
           Dialog.show("succe", "ajouter avec succer", "ok", "cancel");
        Form listform = new ListForm();
        listform.show();
        });

    }
    
}
