package com.mycompany.gui.back;

import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.SignInForm;


public class AccueilBack extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;
    Resources res;

    public AccueilBack() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        res = UIManager.initFirstTheme("/theme");
    }

    private void addGUIs() {
        ImageViewer userImage = new ImageViewer(theme.getImage("profile-pic.jpg").fill(200, 200));
        userImage.setUIID("candidatImage");
        label = new Label("Admin"/*MainApp.getSession().getEmail()*/);
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

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeEventsButton(), 
                makeReservationsButton(),
                categories(),
                blog()
                
        );

        this.add(menuContainer);
    }

    private Button makeEventsButton() {
        Button button = new Button("Events");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.back.event.ShowAll(this).show());
        return button;
    }
    private Button makeReservationsButton() {
        Button button = new Button("Reservations");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.back.reservation.ShowAll(this).show());
        return button;
    }
    private Button categories() {
        Button button = new Button("Categories");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.ListForm().show());
        return button;
    }
    
    private Button blog() {
        Button button = new Button("Blog");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.mycompany.gui.ListFormBlog().show());
        return button;
    }
}
