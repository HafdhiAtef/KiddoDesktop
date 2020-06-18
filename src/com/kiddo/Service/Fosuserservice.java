/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kiddo.Service;
import com.kiddo.Entite.Fos_user;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.kiddo.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.kiddo.Service.BCrypt;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


 
/**
 *
 * @author jha
 */
public class Fosuserservice  {
    Connection Conn;
    Statement stm;

    ObservableList<Fos_user> obList = FXCollections.observableArrayList();
    public Fosuserservice() {
        try {
            Conn = DataBase.getInstance().getConnection();
            stm = Conn.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean login(String username, String motpass) {

        try {
            PreparedStatement pt = Conn.prepareStatement("select * from fos_user where email=? ");
            pt.setString(1, username);
            ResultSet rs = pt.executeQuery();
           while (rs.next()) {
                if (BCrypt.checkpw(motpass, rs.getString("password")) == true) {
                    
                return true;
            }}
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 public int getIdbymail(String mail) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where email=?");
            st.setString(1, mail);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
 public String getRolebyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("roles");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }
public String getUsenamebyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

 
  public Boolean CheckIfUsernameExist(String username) {
        boolean check = false;
        try {
            String req = "select * from fos_user where username=? ";
            PreparedStatement st = Conn.prepareStatement(req);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }
   public Boolean CheckIfUserExist(String email) {
        boolean check = false;
        try {
            String req = "select * from fos_user where email=? ";
            PreparedStatement st = Conn.prepareStatement(req);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return check;
    }
   

     public Fos_user getuser(Fos_user u) throws SQLException {  
        Fos_user us = new Fos_user();
        PreparedStatement pre = Conn.prepareStatement("SELECT * FROM fos_user WHERE email LIKE ? ;");

        pre.setString(1, u.getEmail());
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int id_user = rs.getInt(1);
            String nom = rs.getString(2);
            String email = rs.getString(4);
            String phone = rs.getString(15);
            String datenaiss = rs.getString(16); 
            String roles = rs.getString(12);
                    
            us.setId(id_user);
            us.setUsername(nom);
            us.setPhone(phone);
            us.setDatenaiss(datenaiss);
            us.setEmail(email);
            us.setRoles(roles);
        }
        return us;

    }
       public Fos_user getuser_id(int id) throws SQLException {  
        Fos_user us = new Fos_user();
        PreparedStatement pre = Conn.prepareStatement("SELECT * FROM fos_user WHERE id LIKE ? ;");

        pre.setInt(1,id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
              int id_user = rs.getInt(1);
            String nom = rs.getString(2);
            String email = rs.getString(4);
            String phone = rs.getString(15);
            String datenaiss = rs.getString(16); 
            String roles = rs.getString(12);
                    
            us.setId(id_user);
            us.setUsername(nom);
            us.setPhone(phone);
            us.setDatenaiss(datenaiss);
            us.setEmail(email);
            us.setRoles(roles);
           
        }
        return us;

    }
          
    public String getNombyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public String getPrenombyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("prenom");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public String getDatebyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getDate("datenaiss").toString();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public Date getDateDbyId(int id) {
        Date d = new Date(1, 1, 1);
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getDate("datenaiss");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public String getTelbyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("phone");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
      public String getMailbyId(int id) {
        try {
            PreparedStatement st = Conn.prepareStatement("select * from fos_user where id=?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Fosuserservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }


      

}

