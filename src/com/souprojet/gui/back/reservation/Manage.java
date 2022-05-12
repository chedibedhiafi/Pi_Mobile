package com.souprojet.gui.back.reservation;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.souprojet.entities.*;
import com.souprojet.services.*;
import com.souprojet.utils.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Manage extends Form {

    

    Reservation currentReservation;

    TextField nbPlacesTF;TextField totalTF;
    Label nbPlacesLabel;Label totalLabel;
    PickerComponent dateAchatTF;
    
    ArrayList<Event> listEvents;
    PickerComponent eventPC;
    Event selectedEvent = null;
    
    
    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentReservation == null ?  "Ajouter" :  "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentReservation = ShowAll.currentReservation;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {
        
        String[] eventStrings;
        int eventIndex;
        eventPC = PickerComponent.createStrings("").label("Event");
        listEvents = EventService.getInstance().getAll();
        eventStrings = new String[listEvents.size()];
        eventIndex = 0;
        for (Event event : listEvents) {
            eventStrings[eventIndex] = event.getTitre();
            eventIndex++;
        }
        if(listEvents.size() > 0) {
            eventPC.getPicker().setStrings(eventStrings);
            eventPC.getPicker().addActionListener(l -> selectedEvent = listEvents.get(eventPC.getPicker().getSelectedStringIndex()));
        } else {
            eventPC.getPicker().setStrings("");
        }
        

        
        
        nbPlacesLabel = new Label("NbPlaces : ");
        nbPlacesLabel.setUIID("labelDefault");
        nbPlacesTF = new TextField();
        nbPlacesTF.setHint("Tapez le nbPlaces");
        
        
        dateAchatTF = PickerComponent.createDate(null).label("DateAchat");
        
        
        
        totalLabel = new Label("Total : ");
        totalLabel.setUIID("labelDefault");
        totalTF = new TextField();
        totalTF.setHint("Tapez le total");
        
        
        
        
        

        if (currentReservation == null) {
            
            
            manageButton = new Button("Ajouter");
        } else {
            nbPlacesTF.setText(String.valueOf(currentReservation.getNbPlaces()));
            dateAchatTF.getPicker().setDate(currentReservation.getDateAchat());
            totalTF.setText(currentReservation.getTotal());
            
            
            eventPC.getPicker().setSelectedString(currentReservation.getEvent().getTitre());
            selectedEvent = currentReservation.getEvent();
            
            

            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(
            
            nbPlacesLabel, nbPlacesTF,
            dateAchatTF,
            totalLabel, totalTF,
            
            eventPC,
            manageButton
        );

        this.addAll(container);
    }

    private void addActions() {
        
        if (currentReservation == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ReservationService.getInstance().add(
                            new Reservation(
                                    
                                    
                                    (int) Float.parseFloat(nbPlacesTF.getText()),
                                    dateAchatTF.getPicker().getDate(),
                                    totalTF.getText(),
                                    selectedEvent
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Reservation ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de reservation. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ReservationService.getInstance().edit(
                            new Reservation(
                                    currentReservation.getId(),
                                    
                                    
                                    (int) Float.parseFloat(nbPlacesTF.getText()),
                                    dateAchatTF.getPicker().getDate(),
                                    totalTF.getText(),
                                    selectedEvent

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Reservation modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de reservation. Code d'erreur : " + responseCode, new Command("Ok"));
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
        
        
        
        
        
        
        if (dateAchatTF.getPicker().getDate() == null) {
            Dialog.show("Avertissement", "Veuillez saisir la dateAchat", new Command("Ok"));
            return false;
        }
        
        
        if (totalTF.getText().equals("")) {
            Dialog.show("Avertissement", "Total vide", new Command("Ok"));
            return false;
        }
        
        
        
        
        
        

        
        if (selectedEvent == null) {
            Dialog.show("Avertissement", "Veuillez choisir un event", new Command("Ok"));
            return false;
        }
        

        
             
        return true;
    }
}