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
import com.mycompany.entities.categorie;
import com.mycompany.services.ServiceCategorie;
import java.util.ArrayList;

/**
 *
 * @author chedi
 */
public class ListForm extends Form {
    public ListForm(){
        setUIID("SignIn");
    ArrayList<categorie> list = ServiceCategorie.getInstance().affichageCategorie();
        setTitle("affiche_catigorie");
        setLayout(BoxLayout.y());
        for (categorie c :list)
        {
            String nom = c.getNom();
            int id = c.getId();
            this.add(create(id,nom));
        }
        Button btnajout = new Button("ajouter une categorie");
        btnajout.addActionListener(eva->{
        Form ajoutform = new CategorieForm();
        ajoutform.show();
        });
        this.add(btnajout);
    }
    public Container create(int id , String nom)
    {
    Label Nom = new Label("nom categorie ="+ nom);
    Label Id = new Label ("id ="+ id);
        Button modif = new Button("modifier");
        Button supp = new Button("supprimer");
        modif.addActionListener(ev->{
        Form modifform=new ModifForm(id);
        modifform.show();
        });
    supp.addActionListener(e->{
    ServiceCategorie.getInstance().supprimercateg(id);
    Dialog.show("succe", "suuprimer avec succer", "ok","cancel");
    Form listform = new ListForm();
    listform.show();
    });
    
    return new Container(BoxLayout.yCenter()).addAll(Id,Nom,modif,supp);
    }
    
}
