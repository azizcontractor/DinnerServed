/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.OracleConnection;

/**
 *
 * @author Aziz
 */

public class Restaurant {
   private static Connection conn = null;

    /**
     * @return the conn
     */
    public static Connection getConn() {
        return conn;
    }

    /**
     * @param aConn the conn to set
     */
    public static void setConn(Connection aConn) {
        conn = aConn;
    }
   private String rName;
   private String rID;
   private String password;
   private String address;
   private String phone;
   private double dMin;
   private String styleName;
   
    public Restaurant(){
        
    }
    
    public void createRestaurant(int rid, String password, String address, String rName, String phone, double dMin, String sName)
    {
            setConn(OracleConnection.getConnection());
        try{
            PreparedStatement ps;
            String sql = "insert into restaurant values (?, ?, ?, ?, ?, ?, ?)";
            ps = getConn().prepareStatement(sql);
            ps.setInt(1, rid);
            ps.setString(2, password);
            ps.setString(3, address);
            ps.setString(4, rName);
            ps.setString(5, phone);
            ps.setDouble(6, dMin);
            ps.setString(7, sName);

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();

        }finally{ OracleConnection.closeConnection();

        }
    }

    public boolean validStyle(String style){
        setConn(OracleConnection.getConnection());
        boolean valid = false;
        try{
            PreparedStatement ps;
            String sql = "select * from cuisinestyle where stylename = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, style);
            ResultSet r = ps.executeQuery();
            if (r.next())
                valid = true;               
        }catch(SQLException e){
            e.printStackTrace();

        }finally{ 
            OracleConnection.closeConnection();
        }
        return valid;
    }

    public List<Restaurant> getRestaurant (){
        conn = OracleConnection.getConnection();
        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        try{
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select * from restaurant");
            while (r.next()){
                Restaurant res = new Restaurant();
                res.setrID(r.getString(1));
                res.setPassword(r.getString(2));
                res.setAddress(r.getString(3));
                res.setrName(r.getString(4));
                res.setPhone(r.getString(5));
                res.setdMin(r.getDouble(6));
                res.setStyleName(r.getString(7));
                restaurants.add(res);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }  
        return restaurants;
    }

    public int genRID(){
        conn = OracleConnection.getConnection();
        Integer rid = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("select rid_seq.nextval from dual");
            if(r.next()){
                String rk = r.getString(1);
                rid = Integer.parseInt(rk);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }  
        return rid;
    }
    
    public boolean validateRestaurant(int rid, String pw){
        conn = OracleConnection.getConnection();
        boolean valid = false;
        try{
            String sql = "select rpassword from restaurant where rid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ResultSet r = ps.executeQuery();
            if(r.next())
                if (r.getString(1).equals(pw))
                    valid = true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }
    
    public void updateRestaurant(Restaurant r){
        conn = OracleConnection.getConnection();
        try{
            String sql = "update restaurant set address = ?, rname = ?, phone = ?, deliverymin = ?"
                    + "where rid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, r.getAddress());
            ps.setString(2, r.getrName());
            ps.setString(3, r.getPhone());
            ps.setDouble(4, r.getdMin());
            ps.setInt(5, Integer.parseInt(r.getrID()));
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public List<Restaurant> getByRID(int rID){
        conn = OracleConnection.getConnection();
        ArrayList<Restaurant> rests = new ArrayList<Restaurant>();
        try{
            String sql = "select * from restaurant where rid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rID);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                Restaurant res = new Restaurant();
                res.setrID(r.getString(1));
                res.setPassword(r.getString(2));
                res.setAddress(r.getString(3));
                res.setrName(r.getString(4));
                res.setPhone(r.getString(5));
                res.setdMin(r.getDouble(6));
                res.setStyleName(r.getString(7));
                rests.add(res);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return rests;
    }
    
    public List<Restaurant> getByCStyle(String search){
        conn = OracleConnection.getConnection();
        ArrayList<Restaurant> rests = new ArrayList<Restaurant>();
        try{
            String sql = "select * from restaurant where stylename = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, search);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                Restaurant res = new Restaurant();
                res.setrID(r.getString(1));
                res.setPassword(r.getString(2));
                res.setAddress(r.getString(3));
                res.setrName(r.getString(4));
                res.setPhone(r.getString(5));
                res.setdMin(r.getDouble(6));
                res.setStyleName(r.getString(7));
                rests.add(res);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return rests;
    }
    
    public boolean deleteRestaurant(int rID){
        conn = OracleConnection.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("delete from restaurant where rid = ?");
            ps.setInt(1, rID);
            int k = ps.executeUpdate();
            if(k == 0)
                return false;
            else
                return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public boolean changePwd(int rID, String oldPwd, String newPwd){
        boolean valid = false;
        conn = OracleConnection.getConnection();
        try{
            String sql = "select rpassword from restaurant where rid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rID);
            ResultSet r = ps.executeQuery();
            if(r.next())
                if (r.getString(1).equals(oldPwd))
                    valid = true;
            if (valid){
                sql = "update restaurant set rpassword = ? where rid = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, newPwd);
                ps.setInt(2, rID);
                ps.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }

    @Override
    public String toString() {
        return "Restaurant{" + "rName=" + rName + ", rID=" + rID + ", password=" + password + ", address=" + address + ", phone=" + phone + ", deliverymin=" + dMin + ", styleName=" + styleName + '}';
    }

    

    /**
     * @return the rName
     */
    public String getrName() {
        return rName;
    }

    /**
     * @param rName the rName to set
     */
    public void setrName(String rName) {
        this.rName = rName;
    }

    /**
     * @return the rID
     */
    public String getrID() {
        return rID;
    }

    /**
     * @param rID the rID to set
     */
    public void setrID(String rID) {
        this.rID = rID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the dMin
     */
    public double getdMin() {
        return dMin;
    }

    /**
     * @param deliverymin the dMin to set
     */
    public void setdMin(double deliverymin) {
        this.dMin = deliverymin;
    }

    /**
     * @return the styleName
     */
    public String getStyleName() {
        return styleName;
    }

    /**
     * @param styleName the styleName to set
     */
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
    
    
    public static void main (String[] args){
        Restaurant r = new Restaurant();
        //r.createRestaurant(r.genRID(),"2134", "none", "free", "4041123344", 12.34, "Fast Food");
        
        ArrayList<Restaurant> ress = (ArrayList<Restaurant>) r.getRestaurant();
        for (Restaurant k: ress)
            System.out.println(k);
        System.out.println(r.validStyle("Fast Food"));
        System.out.println(r.validateRestaurant(45675, "121212"));
        System.out.println(r.deleteRestaurant(45659));
        //r.createRestaurant(1211, "211", "my house", "Ghar Ka Khana", "7702234534", 12.12, "American");
        //r.updateRestaurant(r);
        //System.out.println(r.changePwd(1211, "211", "1234"));
        /*r.setAddress("lol");
        r.setdMin(12.11);
        r.setPhone("1234543245");
        r.setrName("gj");
        r.setrID("1211");
        r.updateRestaurant(r);*/
    }
   
}

        