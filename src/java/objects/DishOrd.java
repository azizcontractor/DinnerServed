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
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */
public class DishOrd {
    
    private int rid;
    private int did;
    private int ordid;
    private int qty;
    private String dname;
    private String status;
    private double price;
    private String rname;
    private Connection conn;
    
    public DishOrd(){
        
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getOrdid() {
        return ordid;
    }

    public void setOrdid(int ordid) {
        this.ordid = ordid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void addQ(){
        qty++;
    }
    
    public void fromDish(Dish d){
        rid = d.getRid();
        did = d.getDid();
        qty = 1;
        price = d.getPrice();
        status = "Pending";
        dname = d.getDname();
    }
    
    public void addToOrd(int oid){
        ordid = oid;
        conn = OracleConnection.getConnection();
        try{
            String sql = "insert into dishord values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, did);
            ps.setInt(2, ordid);
            ps.setInt(3, rid);
            ps.setInt(4, qty);
            ps.setString(5, status);
            ps.setDouble(6, price);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public List<DishOrd> getByOID(int oid){
        conn = OracleConnection.getConnection();
        ArrayList<DishOrd> dishes = new ArrayList<DishOrd>();
        Restaurant res = new Restaurant();
        Dish m = new Dish();
        try{
            String sql = "select * from dishord where ordid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                DishOrd d = new DishOrd();
                d.setDid(r.getInt(1));
                d.setDname(m.getByDID(d.getDid()).getDname());
                d.setOrdid(r.getInt(2));
                d.setRid(r.getInt(3));
                d.setQty(r.getInt(4));
                d.setStatus(r.getString(5));
                d.setPrice(r.getDouble(6));
                d.setRname(res.getByRID(d.getRid()).get(0).getrName());
                dishes.add(d);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return dishes;
    }
    
}
