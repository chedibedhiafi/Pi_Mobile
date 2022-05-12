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
public class ModifForm extends Form {
    public ModifForm(int id){
         setTitle("modifier-categorie");
        setLayout(BoxLayout.y());
        TextField tfcateg = new TextField("","donner une categorie");
        Button btn = new Button("modifier");
        
      
      //  categ.setNom(nom);
        this.addAll(tfcateg,btn);
    
        btn.addActionListener(l->{
        //  System.out.println( );
        ServiceCategorie.getInstance().modifiercateg(id, tfcateg.getText().toString());
           Dialog.show("succe", "modifier  avec succer", "ok", "cancel");
        Form listform = new ListForm();
    listform.show();
        });
    }
}
