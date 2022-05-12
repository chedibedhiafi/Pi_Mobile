/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.entites.categorie;
import com.company.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chedi
 */
public class ServiceCategorie {
    
    public static ServiceCategorie instance = null;
    private ConnectionRequest req;
    
    public static ServiceCategorie getInstance(){
        if(instance == null)
    
           instance = new ServiceCategorie();
        return instance;
        
                
    }
    public ServiceCategorie(){
        req = new ConnectionRequest();
        
    }
    
    public void ajoutCategorie(String categorie)
    {
        String url=Statics.BASE_URL+"/addcategorieJSON/new?nom="+categorie;
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<categorie>affichageCategorie(){
       ArrayList<categorie> result = new ArrayList<>();
       String url = Statics.BASE_URL+"/AllCategorie";
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp;
               jsonp = new JSONParser();
               try{
                   Map<String,Object>mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   
                   List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapCategorie.get("root");
                   for(Map<String, Object> obj : listOfMaps){
                       categorie cat = new categorie();
                       
                       float id = Float.parseFloat(obj.get("id").toString());
                       
                       String nom = obj.get("nom").toString();
                       
                       cat.setId((int)id);
                       cat.setNom(nom);
                       result.add(cat);
                       
                   }
               }catch(Exception ex){
                   
                   ex.printStackTrace();
               }
               
           }
       });
                NetworkManager.getInstance().addToQueueAndWait(req);
                
                return result;

    }
    public void supprimercateg(int id){
      String url=Statics.BASE_URL+"/supprimercategorie/"+id;
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
          //  System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void modifiercateg(int id,String nom){
    String url=Statics.BASE_URL+"/editcategorieJSON/"+id+"?nom="+nom;
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
          //  System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    }
    
}
