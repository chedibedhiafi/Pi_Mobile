/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUtilisateur;


import java.io.IOException;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
    private Resources theme;

    private static String i;
Resources res;

    public ProfileForm() {
        
        super("Profile", BoxLayout.y());
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
                res = UIManager.initFirstTheme("/theme");
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        //setTitle("Profile");
        getContentPane().setScrollVisible(false);

       
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Button modiff = new Button("modifier");
        Button logout = new Button("Logout");
        
         logout.addActionListener(e -> { 
       new SignInForm(res).show();
      

Storage.getInstance().clearStorage();
Storage.getInstance().clearCache();
}); 
//Label pp= new Label(ServiceUser.UriImage(SessionManager.getPhoto()),"PictureWhiteBackground");
        add(LayeredLayout.encloseIn(sl, BorderLayout.south(GridLayout.encloseIn(3, FlowLayout.encloseCenter()))));

        
        TextField username = new TextField(SessionManger.getUsername());
        //username.setUIID("TextFieldBlack");
        addStringValue("username", username);
      
        TextField nom = new TextField(SessionManger.getNom());
        //nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);
        
        TextField prenom = new TextField(SessionManger.getPrenom());
        //.setUIID("TextFieldBlack");
        addStringValue("prenom", prenom);

        
        TextField email_user = new TextField(SessionManger.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        //email_user.setUIID("TextFieldBlack");
        addStringValue("email_user", email_user);

        TextField password = new TextField(SessionManger.getPassword(), "Password", 20, TextField.PASSWORD);
        //password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        
        logout.setUIID("Update");
        modiff.setUIID("Edit");
        
        addStringValue("", modiff);
        addStringValue("", logout);
        
        TextField path = new TextField("");
        modiff.addActionListener(edit -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();
            ServiceUtilisateur.EditUser( username.getText(),nom.getText(), prenom.getText() , email_user.getText(), password.getText());
            SessionManger.setUsername(email_user.getText());
            SessionManger.setPassword(password.getText());
            
          
            SessionManger.setUsername(email_user.getText());
            Dialog.show("success", "modification des coordonnees avec succees", "OK", null);
            ipDlg.dispose();
            refreshTheme();
        });
        
        addGUIs();
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
    
    private void addGUIs() {

        
        Button frontendBtn = new Button("Front");
        frontendBtn.addActionListener(l -> new com.mycompany.gui.front.AccueilFront().show());
        this.add(frontendBtn);
        
        
        Button backendBtn = new Button("Back");
        backendBtn.addActionListener(l -> new com.mycompany.gui.back.AccueilBack().show());

        this.add(backendBtn);
    }
}