/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Pointdevente;
import com.mycompany.gui.front.AccueilFront;
import com.mycompany.services.ServicePointdevente;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author chedi
 */
public class ListPointdeventesFront extends Form {
    
    
    
    
    public ListPointdeventesFront(){
        setUIID("SignIn");
                ArrayList<Pointdevente> list = ServicePointdevente.getInstance().affichagePts();
        setTitle("Nos Points de ventes");
        
        
        
        BorderLayout lyt = new BorderLayout();
        
    this.setLayout(lyt);
    

    BrowserComponent browser = new BrowserComponent();
    browser.setURL(Statics.MAP_URL);
        browser.addWebEventListener("onLoad",eva->{
        for (Pointdevente pt :list)
        {
            
            
            browser.execute("addMarker("+pt.getLongitude()+","+pt.getLatitude()+")");
     
        }
        
        });
    
    Container pts = new Container(BoxLayout.yCenter());
    
    //RETOUR BTN
        Button modif = new Button("retour");
        modif.addActionListener(ev->{
        Form form = new AccueilFront();
        form.show();
        });
        pts.add(modif);
    
    
    int i = 0;
    for (Pointdevente pt :list)
        {
            if(i<2){
            pts.add(point(pt,browser));
            i++;
            }
        }
    
    
    this.addComponent(lyt.NORTH, pts);
    this.addComponent(lyt.CENTER, browser);
    

        



    }
    
     public Container point(Pointdevente pt,BrowserComponent browser)
    {   
        
        
        
        //LABELS
        Label Name = new Label("Name = " + pt.getName());
        Label Adresse = new Label ("Addresse = "+ pt.getAdresse());
        Button goTo = new Button("GO TO");
       
        
        goTo.addActionListener(ev->{
        browser.execute("goTo("+pt.getLongitude()+","+pt.getLatitude()+")");
        });
                
    return new Container(BoxLayout.yCenter()).addAll(Name,Adresse,goTo);
            }
    
    
}
