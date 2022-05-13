/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Blog;
import com.mycompany.services.ServiceBlog;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author chedi
 */
public class ListFormBlog extends Form {
    public ListFormBlog(){
        setUIID("SignIn");
    ArrayList<Blog> list = ServiceBlog.getInstance().affichageBlog();
        setTitle("affiche_blog");
        setLayout(BoxLayout.y());
        for (Blog b :list)
        {
            
            String titre = b.getTitre();
            String sujet = b.getSujet();
            String contenu = b.getContenu();
            String date = b.getDate();
            int id = b.getId();
            this.add(create(id,titre,sujet,contenu,date));
        }
        Button btnajout = new Button("ajouter un Blog");
        btnajout.addActionListener(eva->{
        Form ajoutform = new BlogForm();
        ajoutform.show();
        });
        this.add(btnajout);
    }
    public Container create(int id , String titre, String sujet, String contenu, String date)
    {
    Label Titre = new Label("titre blog ="+ titre);
    Label Id = new Label ("id ="+ id);
    Label Sujet = new Label("sujet blog ="+ sujet);
    Label Contenu = new Label("contenu blog ="+ contenu);
    Label Date = new Label("date blog ="+ date);
        Button modif = new Button("modifier");
        Button supp = new Button("supprimer");
        modif.addActionListener(ev->{
        Form modifform=new ModifFormBlog(id);
        modifform.show();
        });
    supp.addActionListener(e->{
    ServiceBlog.getInstance().supprimerBlog(id);
    Dialog.show("succe", "suuprimer avec succer", "ok","cancel");
    Form listform = new ListFormBlog();
    listform.show();
    });
    
    return new Container(BoxLayout.yCenter()).addAll(Id,Titre,Sujet,Contenu,Date,modif,supp);
    }
    
}
