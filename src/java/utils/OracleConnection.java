/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aziz
 */
public class OracleConnection {
    private static Connection connection = null;
    
    public static Connection getConnection(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection
                ("jdbc:oracle:thin:@168.28.51.8:1521:csuit", "acontractor", "acontractor&");
        } 
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            System.out.println("==========Connection Failed===========");
        }
        return connection;
    }
    
    public static void closeConnection(){
        try{
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args){
        try{
            Connection conn = OracleConnection.getConnection();
            String sql = "select sysdate as current_date from dual";
            PreparedStatement state = conn.prepareCall(sql);
            ResultSet result = state.executeQuery();
            while(result.next()){
                System.out.print("Current date and time of Oracle Server" + result.getString("current_date"));
            }
            sql = "select * from customer";
            state = conn.prepareCall(sql);
            result = state.executeQuery();
            while(result.next()){
                System.out.println("\nName = " + result.getString(3) + " " + result.getString(4));
                System.out.println("Email = "+ result.getString(7));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            OracleConnection.closeConnection();
        }
    }
}
