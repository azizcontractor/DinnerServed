/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Submenu extends Menu{
    
    private String smcategory;
    
    public Submenu(){
        
    }

    public String getSmcategory() {
        return smcategory;
    }

    public void setSmcategory(String smcategory) {
        this.smcategory = smcategory;
    }
    
    public boolean createSubmenu(String mtype, int rid, String smcategory){
        conn = OracleConnection.getConnection();
        try{
            String sql = "insert into submenu values (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.setString(2, mtype);
            ps.setString(3, smcategory);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public List<Submenu> getByRidMtype(int rid, String mtype){
        conn = OracleConnection.getConnection();
        ArrayList<Submenu> smenu = new ArrayList<Submenu>();
        try{
            String sql = "Select * from submenu where rid = ? and mtype = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.setString(2, mtype);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                Submenu s = new Submenu();
                s.setMtype(r.getString(2));
                s.setRid(r.getInt(1));
                s.setSmcategory(r.getString(3));
                smenu.add(s);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return smenu;
    }
    
}
