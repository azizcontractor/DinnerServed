/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class Order {
    
    private int ordid;
    private String cusername;
    private String status;
    private String date;
    private String time;
    private double taxrate;
    private double total;
    private Connection conn;
    
    public Order(){
        
    }

    public int getOrdid() {
        return ordid;
    }

    public void setOrdid(int ordid) {
        this.ordid = ordid;
    }

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(double taxrate) {
        this.taxrate = taxrate;
    }

    public double getTotal() {
        return Math.round(total *100)/100.00;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public int getID(){
        conn = OracleConnection.getConnection();
        int ordid = 0;
        try{
            String sql = "select oid_seq.nextval from dual";
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(sql);
            if(r.next())
                ordid = r.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return ordid;
    }
    
    public void createOrd(int oid, String username, double taxrate, double subtotal){
        conn = OracleConnection.getConnection();
        try{
            String sql = "insert into ord values(?, ?, ?, sysdate, systimestamp, ?, ?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ps.setString(2, username);
            ps.setString(3, "Pending");
            ps.setDouble(4, taxrate);
            ps.setDouble(5, subtotal*(1+taxrate));
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public List<Order> getByUser(String username){
        conn = OracleConnection.getConnection();
        ArrayList<Order> orders = new ArrayList<Order>();
        try{
            String sql = "select * from ord where cusername = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                Order o = new Order();
                o.setOrdid(r.getInt(1));
                o.setCusername(r.getString(2));
                o.setDate(r.getString(4));
                o.setTime(r.getString(5));
                o.setStatus(r.getString(3));
                o.setTotal(r.getDouble(7));
                orders.add(o);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return orders;
    }
    
}
