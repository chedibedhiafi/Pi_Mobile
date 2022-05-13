package com.mycompany.entities;

import com.mycompany.utils.DateUtils;
import java.util.Date;
import com.mycompany.utils.Statics;

public class Reservation implements Comparable<Reservation> {
    
    private int id;
     private int nbPlaces;
     private Date dateAchat;
     private String total;
    private Event event;
    
    public Reservation() {}

    public Reservation(int id, int nbPlaces, Date dateAchat, String total, Event event) {
        this.id = id;
        this.nbPlaces = nbPlaces;
        this.dateAchat = dateAchat;
        this.total = total;
        this.event = event;
    }

    public Reservation(int nbPlaces, Date dateAchat, String total, Event event) {
        this.nbPlaces = nbPlaces;
        this.dateAchat = dateAchat;
        this.total = total;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
    
    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
    
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
    
    @Override
    public int compareTo(Reservation reservation) {
        switch (Statics.compareVar) {
            case "NbPlaces":
                return Integer.compare(this.getNbPlaces(), reservation.getNbPlaces());
            case "DateAchat":
                DateUtils.compareDates(this.getDateAchat(), reservation.getDateAchat());
            case "Total":
                 return this.getTotal().compareTo(reservation.getTotal());
            case "Event":
                return this.getEvent().getTitre().compareTo(reservation.getEvent().getTitre());
            
            default:
                return 0;
        }
    }
    
}