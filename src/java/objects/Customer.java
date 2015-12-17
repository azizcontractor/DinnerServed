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
public class Customer {
    
    private static Connection conn = null;
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String address;
    private String phone;
    private String email;
    
    public Customer(){
        
    }
    
    public void createCustomer(String username, String password, String fName, String lName, String address, String phone, String email){
            conn = OracleConnection.getConnection();
            try{
                String sql = "insert into customer values (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, fName);
                ps.setString(4, lName);
                ps.setString(5, address);
                ps.setString(6, phone);
                ps.setString(7, email);
                ps.executeUpdate();                
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                OracleConnection.closeConnection();
            }
    }
    
    public List<Customer> getCustomer(){
        conn = OracleConnection.getConnection();
        List<Customer> customers = new ArrayList<Customer>();
        try{
           Statement stmt = conn.createStatement();
           String sql = "select * from customer";
           ResultSet r = stmt.executeQuery(sql);
           while(r.next()){
               Customer cus = new Customer();
               cus.setUsername(r.getString(1));
               cus.setPassword(r.getString(2));
               cus.setfName(r.getString(3));
               cus.setlName(r.getString(4));
               cus.setAddress(r.getString(5));
               cus.setPhone(r.getString(6));
               cus.setEmail(r.getString(7));
               customers.add(cus);
           }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
        return customers;
    }
    
    public boolean deleteCustomer(String username){
        conn = OracleConnection.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("delete from customer where cusername = ?");
            ps.setString(1, username);
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
    
    public void updateCustomer(Customer c){
        conn = OracleConnection.getConnection();
        try{
            String sql = "update customer set fname = ?, lname = ?, address = ?, phone = ?, email = ? "
                    + "where cusername = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getfName());
            ps.setString(2, c.getlName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getUsername());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
    }
    
    public List<Customer> getByUsername(String username){
        conn = OracleConnection.getConnection();
        ArrayList<Customer> cus = new ArrayList<Customer>();
        try{
            String sql = "select * from customer where cusername = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet r = ps.executeQuery();
            while(r.next()){
                Customer c = new Customer();
                c.setUsername(r.getString(1));
                c.setPassword(r.getString(2));
                c.setfName(r.getString(3));
                c.setlName(r.getString(4));
                c.setAddress(r.getString(5));
                c.setPhone(r.getString(6));
                c.setEmail(r.getString(7));
                cus.add(c);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return cus;
    }
    
    public boolean changePassword(String username, String oldPassword, String newPassword){
        boolean valid = false;
        conn = OracleConnection.getConnection();
        try{
            String sql = "select cpassword from customer where cusername = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet r = ps.executeQuery();
            if(r.next())
                if (r.getString(1).equals(oldPassword))
                    valid = true;
            if (valid){
                sql = "update customer set cpassword = ? where cusername = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, newPassword);
                ps.setString(2, username);
                ps.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }
    
    public boolean validateNew(String username){
        boolean valid;
        conn = OracleConnection.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from customer where cusername = ?");
            ps.setString(1, username);
            ResultSet r = ps.executeQuery();
            if (r.next()){
                valid = false;
            }
            else{
                valid = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }
    
    public boolean validateCustomer(String username, String pwd){
        boolean valid = false;
        conn = OracleConnection.getConnection();
        try{
            String sql = "select cpassword from customer where cusername = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet r = ps.executeQuery();
            if(r.next())
                if (r.getString(1).equals(pwd))
                    valid = true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            OracleConnection.closeConnection();
        }
        return valid;
    }

    @Override
    public String toString() {
        return "Customer{" + "username=" + username + ", password=" + password + ", fName=" + fName + ", lName=" + lName + ", address=" + address + ", phone=" + phone + ", email=" + email + '}';
    }
    
       

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        Customer.conn = conn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static void main(String[] args){
        Customer cus = new Customer();
        //cus.createCustomer("rambo", "2323", "Pandu", "Bhai", "I am also Homeless", "8003427654", "homeless@pandu.com");
        ArrayList<Customer> customers = (ArrayList<Customer>) cus.getCustomer();
        for (Customer g: customers){
            System.out.println(g);
        }
        System.out.println("\n" + cus.validateCustomer("rambo", "2323"));
        System.out.println(cus.deleteCustomer("chong"));
        cus.setfName("Pandu");
        cus.setlName("Rambo");
        cus.setUsername("rambo");
        cus.setPhone("9119119111");
        cus.setAddress("Your house is my House");
        cus.setEmail("humpty@dumpty.com");
        cus.updateCustomer(cus);
        customers = (ArrayList<Customer>) cus.getCustomer();
        for (Customer g: customers){
            System.out.println(g);
        }
        System.out.println(cus.validateNew("fmomin"));
    }
    
    
}
