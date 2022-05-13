/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.util.Resources;


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author hp
 */
public class HomeForm extends Form {

    Form current;
Resources res;
    public HomeForm(Integer i) {
        setUIID("SignIn");
        getTitleArea().setUIID("Container");
                 res = UIManager.initFirstTheme("/theme");

        setUIID("SignIn");
        current = this; //Back 
         getToolbar().setUIID("Container");
        setLayout(new BorderLayout());
        Tabs t = new Tabs();
        Style s = UIManager.getInstance().getComponentStyle("Tab");
        t.addTab("Profile", FontImage.createMaterial(FontImage.MATERIAL_ASSIGNMENT_IND, s), new ProfileForm());
        t.setSelectedIndex(i);
        add(BorderLayout.CENTER, t);

    }

}
