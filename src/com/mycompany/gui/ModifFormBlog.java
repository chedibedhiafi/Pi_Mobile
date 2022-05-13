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
import com.mycompany.entities.Blog;
import com.mycompany.services.ServiceBlog;

/**
 *
 * @author chedi
 */
public class ModifFormBlog extends Form {
    public ModifFormBlog(int id){
        setUIID("SignIn");
         setTitle("modifier-blog");
        setLayout(BoxLayout.y());
        TextField blogTitre = new TextField("","donner un titre pour le Blog");
        TextField blogSujet = new TextField("","donner un sujet pour le blog");
        TextField blogContenu = new TextField("","donner un contenu pour le blog");
        TextField blogDate = new TextField("","donner une date pour le blog");
        Button btn = new Button("modifier");
        
      
      //  categ.setNom(nom);
        this.addAll(blogTitre,blogSujet,blogContenu,blogDate,btn);
    
        btn.addActionListener(l->{
        //  System.out.println( );
        ServiceBlog.getInstance().modifierBlog(id, blogTitre.getText(),blogSujet.getText() ,blogContenu.getText(),blogDate.getText());
           Dialog.show("succe", "modifier  avec succer", "ok", "cancel");
        Form listform = new ListFormBlog();
    listform.show();
        });
    }
}
