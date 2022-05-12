/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.company.entites.Blog;


/**
 *
 * @author chedi
 */
public class BlogForm extends Form {
    public BlogForm (){
        setTitle("ajout-blog");
        setLayout(BoxLayout.y());
        TextField titre = new TextField("","donner un titre blog");
        TextField sujet = new TextField("","donner un sujet blog");
        TextField contenu = new TextField("","donner un contenu blog");
        TextField date = new TextField("","donner une date blog");
        Picker datep = new Picker();
        Button btnajout = new Button("ajouter");
        Blog blog =new Blog();
      
      //  categ.setNom(nom);
        this.addAll(titre,sujet,contenu,datep,btnajout);
    
        btnajout.addActionListener(l->{
        //  System.out.println( );
        ServiceBlog.getInstance().ajoutBlog(new Blog(titre.getText(),sujet.getText().toString(),contenu.getText().toString(),datep.getText()));
           Dialog.show("succes", "ajouter avec succes", "ok", "cancel");
        Form listform = new ListForm();
        listform.show();
        });

    }
    
}
