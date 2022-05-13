/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Pointdevente;
import com.mycompany.services.ServicePointdevente;
import java.util.Date;


/**
 *
 * @author chedi
 */
public class AjouterPointdevente extends Form {
    public AjouterPointdevente (){
        setUIID("SignIn");
        setTitle("Ajouter u point de vente");
        setLayout(BoxLayout.y());
        
        //TF
        TextField Nom = new TextField("","Name");
        TextField Proprietaire = new TextField("","Proprietaire");
        TextField Adresse = new TextField("","Adresse");
        
        //Button
        Button btnajout = new Button("ajouter");
        
        //DATE PICKER
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        String testDate = "2000-04-04";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datePicker.setDate(format.parse(testDate));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        
        
      

        this.addAll(Nom,Proprietaire,Adresse,datePicker,btnajout);
    
        btnajout.addActionListener(l->{
            Pointdevente pt =new Pointdevente();
            pt.setName(Nom.getText());
            pt.setProprietaire(Proprietaire.getText());
            pt.setAdresse(Adresse.getText());
            pt.setDateOuverture(datePicker.getDate());
            ServicePointdevente.getInstance().ajoutPointdevente(pt);
            Dialog.show("success", "Point Ajouté avec success", "ok", "cancel");
            Form listform = new ListPointdeventes();
            listform.show();
        });

    }
    
}
