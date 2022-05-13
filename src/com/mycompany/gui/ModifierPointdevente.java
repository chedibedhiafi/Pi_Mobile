/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.mycompany.gui.ListPointdeventes;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Pointdevente;
import com.mycompany.services.ServicePointdevente;

/**
 *
 * @author chedi
 */
public class ModifierPointdevente extends Form {
    public ModifierPointdevente(Pointdevente pt){
        setUIID("SignIn");
         setTitle("modifier-categorie");
        setLayout(BoxLayout.y());
        TextField Name = new TextField(pt.getName(),"Name");
        TextField Proprietaire = new TextField(pt.getProprietaire(),"Proprietaire");
        TextField Adresse = new TextField(pt.getAdresse(),"Adresse");
        
        
        Button btn = new Button("modifier");
        
        //DATE PICKER
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(pt.getDateOuverture());

        
      
      //  categ.setNom(nom);
        this.addAll(Name,Proprietaire,Adresse,datePicker,btn);
    
        btn.addActionListener(l->{
            pt.setName(Name.getText());
            pt.setProprietaire(Proprietaire.getText());
            pt.setAdresse(Adresse.getText());
            pt.setDateOuverture(datePicker.getDate());
       
        ServicePointdevente.getInstance().modifierPt(pt);
           Dialog.show("succe", "modifier  avec succer", "ok", "cancel");
        Form listform = new ListPointdeventes();
        listform.show();
        });
    }
}
