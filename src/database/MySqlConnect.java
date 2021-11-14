/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rachna
 */
package database;
import counsellingsystem.Enter_mark;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Rachna
 */
public class MySqlConnect {
    
    private String pwd, user;
    Connection con = null;
    Statement stmt;
    ResultSet rs;
    String e_rank;
    public void Connect(){ 
    try
	{
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1?autoReconnect=true&useSSL=false", "root","rachna");
            stmt = con.createStatement();
	}
	catch(SQLException e1)
	{ 
            e1.printStackTrace();
        }
        catch(ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
    public boolean get_username(String username, String password){
        String sql = "select * from adminlogin where id = '" + username + "';";
        try{
            rs = stmt.executeQuery(sql);
            if(rs.next()){
            pwd = rs.getString("pwd"); }
            if( !password.equals(pwd)){
                return false;
            }
            else{
                return true;
            }
        }
        catch(SQLException e3){
            e3.printStackTrace();
        }
        return false;
    }
     
    public boolean get_rank(){
         String sql1 = "insert into rank(user_id, name, erank) select u_id, name, erank from students order by erank asc;";
         String sql2 = "insert into allotment(u_id, name, dob, sex, age, erank) select u_id, name, dob, sex, age, erank from students order by erank;";
         Connect();
        try{
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            return true;
            }
        
        catch(SQLException e4){
            e4.printStackTrace();
            return false;
        }
        //return false;
    }
    public boolean student_user(String username, String password){
        String sql = "select dob from regstudents where u_id = '" + username + "';";
        System.out.println(username);
        try{
            rs = stmt.executeQuery(sql);
            if(rs.next()){
            pwd = rs.getString("dob");
            
            if( !password.equals(pwd)){
                return false;
                //System.out.println("o");
            }
            else{
                Enter_mark em = new Enter_mark();
            em.get_erank(username);
                return true;
            }
            }
        }
        catch(SQLException e5){
            e5.printStackTrace();
        }
        return false;
    }
    public boolean search_adminusername(String username){
         String sql = "select id from adminlogin ;";//where id = '" + username + "';";
         
        try{
            rs = stmt.executeQuery(sql);
            rs.next();
            user = rs.getString("id");
            if(username.equals(user)){
                return false;
            }
            else{
                return true;
            }
        }
        catch(SQLException e6){
            e6.printStackTrace();
        }
        return false;
    }
    public String display_studentname(String username){
        String name;
        try{
            stmt = con.createStatement();
            ResultSet rs1=stmt.executeQuery("select * from regstudents where u_id='"+username+"'");
            rs1.next();
            name = rs1.getString("name");
            return name;
        } 
        catch(Exception e3){
            e3.printStackTrace();
        }
    return null;
    }
    public boolean createnewad(String username, String password, String f_name, String l_name){
        String sqlstmt = "insert into adminlogin values('" + username + "', '" + password + "', '" + f_name + "', '" 
                + l_name + "' )";
        try{
            stmt.executeUpdate(sqlstmt);
        }   
        catch( SQLException e ){
            e.printStackTrace();
	}
        return false;        
    }
    

    }



