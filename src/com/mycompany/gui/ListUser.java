/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Utilisateur;
import com.mycompany.services.ServiceUtilisateur;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class ListUser extends Form {

    Form current;
    Resources res;

    public ListUser(Form previous) {
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
         res = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

        Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));

        getToolbar().addCommandToSideMenu("Utilisateur", null, (e) -> new ListUser(current).show());
            getToolbar().addCommandToSideMenu("Logout", null, (e) ->{ 
           
       new SignInForm(res).show();
      

Storage.getInstance().clearStorage();
Storage.getInstance().clearCache();
});
           
        setTitle("Liste des commandes");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        setTitle("Liste des Utilisateur");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        
        
        for (Utilisateur c : ServiceUtilisateur.getInstance().showUser()) {
            Container myContCommande = new Container(new LayeredLayout());
            Container myCont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            myCont.getAllStyles().setBorder(RoundRectBorder.create().
                    strokeColor(0).
                    strokeOpacity(120));
            myCont.getAllStyles().setBgColor(0xffffff);
            myCont.getAllStyles().setBgTransparency(255);
            myCont.getAllStyles().setMarginUnit(Style.UNIT_TYPE_DIPS);
            myCont.getAllStyles().setMargin(Component.BOTTOM, 3);
            myCont.getAllStyles().setMargin(Component.LEFT, 1);
            myCont.getAllStyles().setMargin(Component.RIGHT, 1);
            String produit = "";
            
              /*  Label l = new Label("etat : " + c.getEtat());
             
            if(c.getEtat().equals("Bloquer")){
                 l.getAllStyles().setFgColor(0xff0000);
            }else if(c.getEtat().equals("Debloquer")){
                 l.getAllStyles().setFgColor(0xff000);
            
        }*/
           
            myCont.addAll(
                    new Label("username : " + c.getUsername()),
                    new Label("nom : " + c.getNom()),
                    new Label("prenom : " + c.getPrenom()),
                    new Label("email : " + c.getEmail()),
                    new Label("mdp : " + c.getPassword()),
                    
                    new Label("id : " + c.getId())
                    
                   /* l */
                    
                    );
            Button edit = new Button("");
            edit.setUIID("Container");
            edit.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            edit.getAllStyles().setFgColor(0xffd700);
            edit.getAllStyles().setMargin(Component.RIGHT, 2);
            edit.getAllStyles().setMargin(Component.RIGHT, 1);
   
            
             edit.addActionListener(e -> {

                   ServiceUtilisateur.getInstance().bloquer(c.getId());

                   
                   
                                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                                Dialog.show("Success", "modification effectuer avec succes", "OK", null);
                                new ListUser(current).show() ;
                                
                                
                                
                        
                        //}

                    });
             
             
              Button edit1 = new Button("");
            edit1.setUIID("Container");
            edit1.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            edit1.getAllStyles().setFgColor(0xff0000);
            edit1.getAllStyles().setMargin(Component.RIGHT, 2);
            edit1.getAllStyles().setMargin(Component.RIGHT, 1);
            FontImage.setMaterialIcon(edit1, FontImage.MATERIAL_REMOVE_CIRCLE);
            myContCommande.add(LayeredLayout.encloseIn(myCont,
                    FlowLayout.encloseRight(edit1,edit)));
             edit1.addActionListener(e -> {

                   ServiceUtilisateur.getInstance().debloquer(c.getId());

                   
                   
                                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                                Dialog.show("Success", "modification effectuer avec succes", "OK", null);
                                new ListUser(current).show() ;
                                
                                
                                
                        
                        //}

                    });
                       
        
                       
        
            add(myContCommande);
            
            
       
        }
     
    }
}