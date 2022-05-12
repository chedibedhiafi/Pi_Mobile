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
import com.company.entites.Blog;
import com.company.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chedi
 */
public class ServiceBlog {
    
    public static ServiceBlog instance = null;
    private ConnectionRequest req;
    
    public static ServiceBlog getInstance(){
        if(instance == null)
    
           instance = new ServiceBlog();
        return instance;
        
                
    }
    public ServiceBlog(){
        req = new ConnectionRequest();
        
    }
    
    public void ajoutBlog(Blog blog)
    {
        String url=Statics.BASE_URL+"/blog/AddBlogJSON/new?titre="+blog.getTitre()+"&sujet="+blog.getSujet()+"&contenu="+blog.getContenu()+"&date="+blog.getDate();
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<Blog>affichageBlog(){
       ArrayList<Blog> result = new ArrayList<>();
       String url = Statics.BASE_URL+"/blog/BlogsList";
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               JSONParser jsonp;
               jsonp = new JSONParser();
               try{
                   Map<String,Object>mapBlog = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   
                   List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapBlog.get("root");
                   for(Map<String, Object> obj : listOfMaps){
                       Blog blog = new Blog();
                       
                       float id = Float.parseFloat(obj.get("id").toString());
                       
                       String titre = obj.get("titre").toString();
                       String sujet = obj.get("sujet").toString();
                       String contenu = obj.get("sujet").toString();
                       String date = obj.get("date").toString();
                       
                       blog.setId((int)id);
                       blog.setTitre(titre);
                       blog.setSujet(sujet);
                       blog.setContenu(contenu);
                       blog.setDate(date);
                       result.add(blog);
                       
                   }
               }catch(Exception ex){
                   
                   ex.printStackTrace();
               }
               
           }
       });
                NetworkManager.getInstance().addToQueueAndWait(req);
                
                return result;

    }
    public void supprimerBlog(int id){
      String url=Statics.BASE_URL+"/blog/deleteBlogJson/"+id;
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
          //  System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void modifierBlog(int id,String titre, String sujet, String contenu, String date){
    String url=Statics.BASE_URL+"/blog/UpdateBlogJSON/"+id+"?titre="+titre+"&sujet="+sujet+"&contenu="+contenu+"&date="+date;
        req.setUrl(url);
        req.addResponseListener((e) ->{
            String str = new String(req.getResponseData());
          //  System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    
    
    }
    
}
