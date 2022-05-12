package com.mycompany.gui.back.event;


import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.*;
import com.mycompany.services.*;
import com.mycompany.utils.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Manage extends Form {

    
    Resources theme = UIManager.initFirstTheme("/theme");
    String selectedImage;
    boolean imageEdited = false;
    

    Event currentEvent;

    TextField titreTF;TextField prixTF;TextField imageTF;TextField descriptionTF;TextField nbPlacesTF;
    Label titreLabel;Label prixLabel;Label imageLabel;Label descriptionLabel;Label nbPlacesLabel;
    PickerComponent dateTF;
    
    
    
    ImageViewer imageIV;
    Button selectImageButton;
    
    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentEvent == null ?  "Ajouter" :  "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentEvent = ShowAll.currentEvent;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        

        
        dateTF = PickerComponent.createDate(null).label("Date");
        
        
        
        titreLabel = new Label("Titre : ");
        titreLabel.setUIID("labelDefault");
        titreTF = new TextField();
        titreTF.setHint("Tapez le titre");
        
        
        
        prixLabel = new Label("Prix : ");
        prixLabel.setUIID("labelDefault");
        prixTF = new TextField();
        prixTF.setHint("Tapez le prix");
        
        
        
        
        
        
        
        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le description");
        
        
        
        nbPlacesLabel = new Label("NbPlaces : ");
        nbPlacesLabel.setUIID("labelDefault");
        nbPlacesTF = new TextField();
        nbPlacesTF.setHint("Tapez le nbPlaces");
        
        
        
        imageLabel = new Label("Image : ");
        imageLabel.setUIID("labelDefault");
        selectImageButton = new Button("Ajouter une image");

        if (currentEvent == null) {
            
            imageIV = new ImageViewer(theme.getImage("profile-pic.jpg").fill(1100, 500));
            
            
            manageButton = new Button("Ajouter");
        } else {
            dateTF.getPicker().setDate(currentEvent.getDate());
            titreTF.setText(currentEvent.getTitre());
            prixTF.setText(currentEvent.getPrix());
            
            descriptionTF.setText(currentEvent.getDescription());
            nbPlacesTF.setText(String.valueOf(currentEvent.getNbPlaces()));
            
            
            
            if (currentEvent.getImage() != null) {
                selectedImage = currentEvent.getImage();
                String url = Statics.EVENT_IMAGE_URL + currentEvent.getImage();
                Image image = URLImage.createToStorage(
                        EncodedImage.createFromImage(theme.getImage("profile-pic.jpg").fill(1100, 500), false),
                        url,
                        url,
                        URLImage.RESIZE_SCALE
                );
                imageIV = new ImageViewer(image);
            } else {
                imageIV = new ImageViewer(theme.getImage("profile-pic.jpg").fill(1100, 500));
            }
            imageIV.setFocusable(false);
            

            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
            imageLabel, imageIV,
            selectImageButton,
            dateTF,
            titreLabel, titreTF,
            prixLabel, prixTF,
            
            descriptionLabel, descriptionTF,
            nbPlacesLabel, nbPlacesTF,
            
            manageButton
        );

        this.addAll(container);
    }

    private void addActions() {
        
        selectImageButton.addActionListener(a -> {
            selectedImage = Capture.capturePhoto(900, -1);
            try {
                imageEdited = true;
                imageIV.setImage(Image.createImage(selectedImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
            selectImageButton.setText("Modifier l'image");
        });
        
        if (currentEvent == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EventService.getInstance().add(
                            new Event(
                                    
                                    
                                    dateTF.getPicker().getDate(),
                                    titreTF.getText(),
                                    prixTF.getText(),
                                    selectedImage,
                                    descriptionTF.getText(),
                                    (int) Float.parseFloat(nbPlacesTF.getText())
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Event ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de event. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EventService.getInstance().edit(
                            new Event(
                                    currentEvent.getId(),
                                    
                                    
                                    dateTF.getPicker().getDate(),
                                    titreTF.getText(),
                                    prixTF.getText(),
                                    selectedImage,
                                    descriptionTF.getText(),
                                    (int) Float.parseFloat(nbPlacesTF.getText())

                            ), imageEdited
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Event modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de event. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh(){
        ((ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {

        
        
        
        
        if (dateTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la date", new Command("Ok"));
            return false;
        }
        
        
        if (titreTF.getText().equals("")) {
            Dialog.show("Avertissement", "Titre vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (prixTF.getText().equals("")) {
            Dialog.show("Avertissement", "Prix vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        
        
        
        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Description vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        if (nbPlacesTF.getText().equals("")) {
            Dialog.show("Avertissement", "NbPlaces vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(nbPlacesTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", nbPlacesTF.getText() + " n'est pas un nombre valide (nbPlaces)", new Command("Ok"));
            return false;
        }
        
        
        

        

        
        if (selectedImage == null) {
            Dialog.show("Avertissement", "Veuillez choisir une image", new Command("Ok"));
            return false;
        }
        
             
        return true;
    }
}