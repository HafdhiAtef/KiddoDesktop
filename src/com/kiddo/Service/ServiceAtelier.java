/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kiddo.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kiddo.Entite.Atelier;
import com.kiddo.Entite.Reclamation;
import com.kiddo.Utils.DataBase;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TR3x
 */
public class ServiceAtelier {
    
    private Connection con;
    private Statement ste;

    public ServiceAtelier() {
        con = DataBase.getInstance().getConnection();

    }
    
     public void ajouter(Atelier r) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `atelier` (`id`, `nom`, `description`, `date_debut`, `date_fin`, `path`) VALUES (NULL, '"+r.getNom()+"', '"+r.getDescription()+"', '"+r.getDate_debut()+"', '"+r.getDate_fin()+"', '"+r.getPath()+"')";
        ste.executeUpdate(requeteInsert);
    }
     
     public List<Atelier> readAll() throws SQLException, ParseException {
    List<Atelier> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from atelier");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String description=rs.getString(3);
               String sDate1=rs.getString(4);  
               String sDate2=rs.getString(5);  
               String path =rs.getString(6);  
               Atelier a = new Atelier(id, nom, description, sDate2, sDate2, path);
     arr.add(a);
     }
    return arr;
    }
     
      public void supprimer (int id_rec) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `atelier` WHERE `id`='"+id_rec+"'";
        ste.executeUpdate(requeteInsert);
    }
      
       public boolean update(Atelier r , int id_rec ) throws SQLException {
        ste = con.createStatement();
        String requeteInsert;
        requeteInsert = "UPDATE `kiddo`.`atelier` SET `nom`='"+r.getNom()+"', `description`='"+r.getDescription()+"', `date_debut`='"+r.getDate_debut()+"', `date_fin`='"+r.getDate_fin()+"', `path`='"+r.getPath()+"' WHERE `id`='"+id_rec+"'";
        ste.executeUpdate(requeteInsert);
        return true;        
    }
       
    public void getPDF(){
            
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("C:/Users/TR3x/Desktop/PDF/Ateliers.pdf"));
            document.open();
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addContent(Document subCatPart)
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.addCell("Nom");
        table.addCell("Description");
        table.addCell("Date debut");
        table.addCell("Date fin");
            try {
            PreparedStatement pt =con.prepareStatement("SELECT nom,description,date_debut, date_fin FROM atelier");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                table.addCell(rs.getString(1));
                table.addCell(rs.getString(2));
                table.addCell(rs.getString(3));
                table.addCell(rs.getString(4));
                
              
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAtelier.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        subCatPart.add(table);

    }
    
}
