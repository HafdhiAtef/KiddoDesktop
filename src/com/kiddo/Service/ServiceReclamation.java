/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kiddo.Service;

import com.kiddo.Entite.Reclamation;
import com.kiddo.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TR3x
 */
public class ServiceReclamation {
    
    private Connection con;
    private Statement ste;

    public ServiceReclamation() {
        con = DataBase.getInstance().getConnection();

    }
    
     public void ajouter(Reclamation r) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `kiddo`.`reclamation` (`id`, `titre`, `contenu`, `etat` , `id_user` ) VALUES (NULL, '" + r.getTitre() + "', '" + r.getContenu() + "', " + r.getEtat() + ", " + r.getId_user() + ");";
        ste.executeUpdate(requeteInsert);
    }
     
     public List<Reclamation> readAll() throws SQLException {
    List<Reclamation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String titre=rs.getString("titre");
               String contenu=rs.getString(3);
               int etat=rs.getInt("etat");
               int id_user=rs.getInt("id_user");
               Reclamation rec = new Reclamation(id, titre, contenu, etat, id_user);
     arr.add(rec);
     }
    return arr;
    }
     
      public void supprimer (int id_rec) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `kiddo`.`reclamation` WHERE `id`="+id_rec;
        ste.executeUpdate(requeteInsert);
    }
      
       public boolean update(Reclamation r , int id_rec ) throws SQLException {
        ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "UPDATE `kiddo`.`reclamation` SET `titre`='"+r.getTitre()+"', `contenu`='"+r.getContenu()+"', `etat`="+r.getEtat()+", `id_user`="+r.getId_user()+" WHERE `id`="+id_rec;
        ste.executeUpdate(requeteInsert);
        return true;        
    }
       public int countReaclamation() throws SQLException {
    int nb=0;
    ste=con.createStatement();
    ResultSet rs = ste.executeQuery("SELECT COUNT(*) FROM reclamation WHERE `id_user`=1");
    while (rs.next()) {    
               
        
        nb = (int)rs.getInt(1);
        
    }
        return nb;
    }
      
       public void Testing() throws SQLException
       {
           int a = countReaclamation();
           try {
               if (a<3)
               {
                   
               }
               
           } catch (Exception e) {
           }
       }
    
}
