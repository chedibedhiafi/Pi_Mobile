package com.mycompany.gui.front;

import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.ListPointdeventes;
import com.mycompany.gui.ListPointdeventesFront;
import com.mycompany.gui.Login;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SignInForm;

public class AccueilFront extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    Resources res;

    public AccueilFront() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        setUIID("SignIn");
        addGUIs();
        res = UIManager.initFirstTheme("/theme");
    }

    private void addGUIs() {
        ImageViewer userImage = new ImageViewer(theme.getImage("profile-pic.jpg").fill(200, 200));
        userImage.setUIID("candidatImage");
        label = new Label("Front");
        label.setUIID("links");
        Button logout = new Button("Logout");
        
         logout.addActionListener(e -> { 
       new SignInForm(res).show();
      

Storage.getInstance().clearStorage();
Storage.getInstance().clearCache();
}); 

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.WEST, userImage);
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, logout);

        
        //RETOUR BTN
        Button retour = new Button("retour");
        retour.addActionListener(ev->{
        Form form = new ProfileForm();
        form.show();
        });
        
        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                
                userContainer,
                retour,
                makeEventsButton(), 
                makeReservationsButton(),
                pointdevente()
                
        );

        this.add(menuContainer);
    }
    
    private Button makeEventsButton() {
        Button button = new Button("Events");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.front.event.ShowAll(this).show());
        return button;
    }
    private Button makeReservationsButton() {
        Button button = new Button("Reservations");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.front.reservation.ShowAll(this).show());
        return button;
    }
    
    private Button pointdevente() {
        Button button = new Button("Pointdeventes");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new ListPointdeventesFront().show());
        return button;
    }
    
}