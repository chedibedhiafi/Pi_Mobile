/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import java.util.Vector;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Utilisateur;

import com.mycompany.gui.HomeForm;

import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.ProfileForm;
//import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author bilel
 */
public class ServiceUtilisateur {
    
    public static ServiceUtilisateur instance = null ;
    
    public static boolean resultOk = true;
  String json ; 
  Form current;
    
    private ConnectionRequest req;
    
    public static ServiceUtilisateur getInstance() {
        if(instance == null)
            instance = new ServiceUtilisateur();
        return instance ; 
    }
    
    public ServiceUtilisateur(){
        req = new ConnectionRequest();
    }
    
    
    //***********************************************************************signup*******************************************/

    
    public void signup(TextField username,TextField nom_user,TextField prenom_user ,TextField email_user,TextField mdp_user, TextField confirmation , Resources res){
        //role
       
        
        
        String url = Statics.BASE_URL+"/utilisateur/user/new/M?username="+username.getText().toString()+"&nom="+nom_user.getText().toString()+"&prenom="+prenom_user.getText().toString()+"&email="+email_user.getText().toString()+"&password="+mdp_user.getText().toString();
        
        
        
        req.setUrl(url);
        
        if(username.getText().equals(" ") &&nom_user.getText().equals(" ") && prenom_user.getText().equals(" ") && email_user.getText().equals(" ")) {
            
            Dialog.show("Erreur", "Veuillez rempilr les champs", "ok", null);
        }
        req.addResponseListener((e)-> {
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("data ==>"+responseData);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    
    public void signin(TextField username , TextField mdp_user , Resources rs){
        
        String url = Statics.BASE_URL+"/utilisateur/login/M?username="+username.getText().toString()+"&password="+mdp_user.getText().toString();
    
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
    
    
    
    req.addResponseListener((e) ->{
        
        JSONParser j = new JSONParser();
        
        String json = new String(req.getResponseData()) + "";
        
        try{
        
        if(json.equals("user not found")){
          Dialog.show("Echec d'authentification", "username ou mot de passe éronné", "OK", null);
            
        } else if (json.equals("bloquer")){
            
              Dialog.show("vous etes bloquer", "essayer de", "OK", null);
            
        }
        else if (json.equals("password not found")){
          Dialog.show("Echec d'authentification", " mot de passe éronné", "OK", null);
        }
        
        else {
            
            System.out.println("data =="+json);
            
            Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            //session
            
            float id_user = Float.parseFloat(user.get("id").toString());
            SessionManger.setId((int)id_user);
           SessionManger.setUsername(user.get("username").toString());
           SessionManger.setNom(user.get("nom").toString());
           SessionManger.setPrenom(user.get("prenom").toString());
            SessionManger.setPassword(user.get("password").toString());
           SessionManger.setEmail(user.get("email").toString());
            SessionManger.setRoles(user.get("roles").toString());
           
            
            
            /*if(user.get("pdp") != null)
                SessionManger.setImage(user.get("pdp").toString());*/
            
            System.out.println("current user ==>"+SessionManger.getUsername()+", "+SessionManger.getPassword()+", "+SessionManger.getRoles());
            if(user.size() >= 0 )
                if("[ROLE_ADMIN]".equals(SessionManger.getRoles())){
                 
                }else
                
                // original   new  HomeForm(0).show();
             new  HomeForm(0).show();
        }
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    });
     NetworkManager.getInstance().addToQueueAndWait(req);
    
    
  
    
    }
    
      //*********************** bloquer *****/
     public void bloquer(int id) {

      
       String url = Statics.BASE_URL+"/user/clientbloque/"+id;
    
       req.setUrl(url);
       req.setPost(false);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
      //*********************** debloquer *****/
     public void debloquer(int id) {

      
       String url = Statics.BASE_URL+"/user/clientdebloque/"+id;
    
       req.setUrl(url);
       req.setPost(false);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
     
    
    
    //*********************** list *****/
    
    
    public ArrayList<Utilisateur>showUser(){
    
        ArrayList<Utilisateur>result = new ArrayList<>();
        String url = Statics.BASE_URL+"/user/admiin/M";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() 
        {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                JSONParser json;
                json  = new JSONParser();
                try{
                        Map<String,Object> OffreListJson = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                        List<Map<String,Object>>listOfMaps= (List<Map<String,Object>>) OffreListJson.get("root");
                        for(Map<String,Object> obj : listOfMaps)
                        {
      //                //Création des tâches et récupération de leurs données
                             Utilisateur off = new Utilisateur();
                            float id_user = Float.parseFloat(obj.get("id").toString());
                            String username = obj.get("username").toString();
                            String nom_user = obj.get("nom").toString();
                            String prenom_user = obj.get("prenom").toString();
                             String email_user = obj.get("mail").toString();
                                String mdp = obj.get("mdp").toString();
                             System.out.println(obj);
                             
                           
                           
                            off.setUsername(username);
                             off.setNom(nom_user);
                            off.setPrenom(prenom_user);
                            off.setUsername(email_user);
                            off.setPassword(mdp);
                            
                            
                            
                           off.setId((int)id_user);
                           
                            result.add(off);
                        }
                    } 

                 catch(Exception ex){
                    ex.printStackTrace();

                 }

            }


        });
                      
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
        
        
        
    
    }
    
    
    
    /**************** email ****/
    
    public String getPasswordByEmail(String email , Resources rs){
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        
        req = new ConnectionRequest(url,false);
        
        req.setUrl(url);
        
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
             json = new String(req.getResponseData()) +"";
            
            
            try{
                System.err.println("data ==" +json);
                
                //Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                //session
                
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
            
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return json ;
    }
    
    //edit user
    
    public static void EditUser(String username,String nom_user, String prenom_user,  String email_user,String mdp_user){
        
        String url = Statics.BASE_URL +"/utilisateur/user/edit/M?username="+username+"&nom="+nom_user+"&prenom="+prenom_user+"&mail="+email_user;
        
        MultipartRequest req = new MultipartRequest();
        
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("id", String.valueOf(SessionManger.getId()));
        req.addArgument("username", username);
        req.addArgument("nom", nom_user);
        req.addArgument("prenom", prenom_user);
       // req.addArgument("tel_user", String.valueOf(tel_user));
        req.addArgument("mail", email_user);
        System.out.println(email_user);
        req.addResponseListener((response)-> {
            
            byte[] data = (byte[]) response.getMetaData();
            String s = new String(data);
            System.out.println(s);
           
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }

}




