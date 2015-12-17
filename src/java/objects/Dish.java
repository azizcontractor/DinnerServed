/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Dish {
    
    private int did;
    private String dname;
    private String desc;
    private double price;
    private String mtype;
    private int rid;
    private String smcat;
    private Connection conn;
    
    public Dish(){
        
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getSmcat() {
        return smcat;
    }

    public void setSmcat(String smcat) {
        this.smcat = smcat;
    }
    
    public List<Dish> getDish(String smcat, String mtype, int rid){
        conn = OracleConnection.getConnection();
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        try{
            String sql = "Select * from dishes where rid = ? and mtype = ? and smcategory = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.setString(2, mtype);
            ps.setString(3, smcat);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                Dish d = new Dish();
                d.setDid(r.getInt(1));
                d.setDname(r.getString(2));
                d.setPrice(r.getDouble(3));
                d.setDesc(r.getString(4));
                d.setMtype(r.getString(5));
                d.setRid(r.getInt(6));
                d.setSmcat(r.getString(7));
                dishes.add(d);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return dishes;
    }
    
    public void addDish(int did, String dname, double price, String desc, String mtype, String smcat, int rid){
        conn = OracleConnection.getConnection();
        try{
            String sql = "insert into dishes values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, did);
            ps.setString(2, dname);
            ps.setDouble(3, price);
            ps.setString(4, desc);
            ps.setString(5, mtype);
            ps.setInt(6, rid);
            ps.setString(7, smcat);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public int genID(){
        conn = OracleConnection.getConnection();
        Integer did = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select did_seq.nextval from dual");
            if(r.next()){
                String rk = r.getString(1);
                did = Integer.parseInt(rk);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }  
        return did;
    }
    
    public boolean updateDish(int did, double price, String name, String desc){
        boolean done = false;
        conn = OracleConnection.getConnection();
        ArrayList<Order> orders = new ArrayList<Order>();
        try{
            String sql = "update dishes set price = ?, dname = ?, description = ? where did = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setString(2, name);
            ps.setString(3, desc);
            ps.setInt(4, did);
            ps.executeUpdate();
            done = true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return done;
    }
    
    public boolean deleteDish(int did){
        boolean done = false;
        conn = OracleConnection.getConnection();
        ArrayList<Order> orders = new ArrayList<Order>();
        try{
            String sql = "delete from dishes where did = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, did);
            ps.executeUpdate();
            done = true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return done;
    }
    
    public Dish getByDID(int did){
        conn = OracleConnection.getConnection();
        Dish d = new Dish();
        try{
            String sql = "select * from dishes where did = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, did);
            ResultSet r = ps.executeQuery();
            if(r.next()){
                d.setDid(r.getInt(1));
                d.setDname(r.getString(2));
                d.setPrice(r.getDouble(3));
                d.setDesc(r.getString(4));
                d.setMtype(r.getString(5));
                d.setRid(r.getInt(6));
                d.setSmcat(r.getString(7));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return d;
    }
    
    public static void main (String[] args){
        Dish d = new Dish();
        ArrayList<Dish> dishes = (ArrayList<Dish>) d.getDish("Breakfast Sandwiches", "Breakfast", 45654);
        for (Dish k: dishes){
            System.out.println(k.getDname() + " " + k.getDesc());
        }
    }
    
}
