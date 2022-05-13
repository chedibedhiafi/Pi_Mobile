/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Pointdevente;
import com.mycompany.gui.back.AccueilBack;
import com.mycompany.services.ServicePointdevente;
import java.util.ArrayList;

/**
 *
 * @author chedi
 */
public class ListPointdeventes extends Form {
    public ListPointdeventes(){
        setUIID("SignIn");
    ArrayList<Pointdevente> list = ServicePointdevente.getInstance().affichagePts();
        
            
        setTitle("AFFICHAGE POINTS DE VENTES");
        setLayout(BoxLayout.y());
        
        //RETOUR BTN
        Button modif = new Button("retour");
        modif.addActionListener(ev->{
        Form form = new AccueilBack();
        form.show();
        });
        this.add(modif);
    
        
        for (Pointdevente pt :list)
        {
            
            this.add(point(pt));
        }
        
        
        Button btnajout = new Button("ajouter Un Point");
        btnajout.addActionListener(eva->{
        Form ajoutform = new AjouterPointdevente();
        ajoutform.show();
        });
        
        
        this.add(btnajout);
    }
    public Container point(Pointdevente pt)
    {
        
        //Label Reference = new Label ("Reference = "+ pt.getReference());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(pt.getDateOuverture());
        
        //LABELS
        Label Name = new Label("Name = " + pt.getName());
        Label Date = new Label ("Date = "+ strDate);
        Label Proprietaire = new Label ("Proprietaie = "+ pt.getProprietaire());
        Label Adresse = new Label ("Addresse = "+ pt.getAdresse());
        Button modif = new Button("MODIFIER");
        Button supp = new Button("SUPPRIMER");
        
        modif.addActionListener(ev->{
        Form modifform = new ModifierPointdevente(pt);
        modifform.show();
        });
        
    supp.addActionListener(e->{
    ServicePointdevente.getInstance().supprimercateg(pt.getReference());
    Dialog.show("Success", "suuprimer avec success", "ok","cancel");
    Form listform = new ListPointdeventes();
    listform.show();
    });
    
    return new Container(BoxLayout.yCenter()).addAll(Name,Proprietaire,Adresse,Date,modif,supp);
    }
    
}
