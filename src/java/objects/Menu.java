/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Menu {
    
    private String mtype;
    private int rid;
    protected Connection conn;
    private ArrayList<Submenu> smenus;
    
    public Menu(){
        
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public ArrayList<Submenu> getSmenus() {
        return smenus;
    }

    public void setSmenus(ArrayList<Submenu> smenus) {
        this.smenus = smenus;
    }
    
    public boolean createMenu(String mtype, int rid){
        conn = OracleConnection.getConnection();
        try{
            String sql = "insert into menu values (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.setString(2, mtype);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public List<Menu> getByRid(int rid){
        conn = OracleConnection.getConnection();
        ArrayList<Menu> menus = new ArrayList<Menu>();
        try{
            String sql = "Select * from menu where rid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                Menu m = new Menu();
                m.setMtype(r.getString(2));
                m.setRid(r.getInt(1));
                m.getSubmenus();
                menus.add(m);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return menus;
    }
    
    public void getSubmenus(){
        Submenu s = new Submenu();
        smenus = (ArrayList<Submenu>) s.getByRidMtype(rid, mtype);
        for(Submenu t: smenus){
            System.out.println(t.getSmcategory());
        }
    }
    
}
