/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Pointdevente;
import com.mycompany.utils.Statics;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chedi
 */
public class ServicePointdevente {
    
    public static ServicePointdevente instance = null;
    private ConnectionRequest req;
    
    public static ServicePointdevente getInstance(){
        if(instance == null)
    
           instance = new ServicePointdevente();
        return instance;
        
                
    }
    public ServicePointdevente(){
        req = new ConnectionRequest();
        
    }
    
    public void ajoutPointdevente(Pointdevente pt)
    {
        //date reformat
       // String finalDate = pt.getDateOuverture().getYear()+":"+pt.getDateOuverture().getMonth()+":"+pt.getDateOuverture().getDay();
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
        String strDate = dateFormat.format(pt.getDateOuverture());

        
        
        String url=Statics.BASE_URL+"/pointdevente/AddPtJSON/new?name="+pt.getName()+"&dateOuverture="+strDate+"&proprietaire="+pt.getProprietaire()+"&adresse="+pt.getAdresse();
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
            //System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<Pointdevente>affichagePts(){
       ArrayList<Pointdevente> result = new ArrayList<>();
       String url = Statics.BASE_URL+"/pointdevente/PtsList";
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp;
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
               jsonp = new JSONParser();
               try{
                   Map<String,Object>mapPts = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   
                   List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapPts.get("root");
                   for(Map<String, Object> obj : listOfMaps){
                      
                           
                       
                       Pointdevente pt = new Pointdevente();
                       
                       
                       pt.setReference((int)Float.parseFloat(obj.get("reference").toString()));
                       pt.setName(obj.get("name").toString());
                       pt.setProprietaire(obj.get("proprietaire").toString());
                       pt.setAdresse(obj.get("adresse").toString());
                       
                       
                       Date date = format.parse(obj.get("dateOuverture").toString());
                        pt.setDateOuverture(date);
                       
                       
                       pt.setLongitude(obj.get("longitude").toString());
                       pt.setLatitude(obj.get("latitude").toString());
                       result.add(pt);
                       
                       }
               }catch(Exception ex){
                   
                   System.out.println("ok");
               }
               
           }
       });
                NetworkManager.getInstance().addToQueueAndWait(req);
                
                return result;

    }
    
    
    public void supprimercateg(int reference){
      String url=Statics.BASE_URL+"/pointdevente/deletePt/"+reference;
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
          //  System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public void modifierPt(Pointdevente pt){
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
        String strDate = dateFormat.format(pt.getDateOuverture());
                      
    String url=Statics.BASE_URL+"/pointdevente/editPt/edit?ref="+pt.getReference()+"&name="+pt.getName()+"&dateOuverture="+strDate+"&adresse="+pt.getAdresse()+"&proprietaire="+pt.getProprietaire();
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
         
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    }
    
}
