/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counsellingsystem;
import java.sql.*;

/**
 *
 * @author Rachna
 */
public class Allotment_Class {
    Statement stmt, stmt1;
    ResultSet rs, rs1, rs2, rs3, rs4, rs5;
    Connection con;
    public void connect(){ 
    try
	{
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1?autoReconnect=true&useSSL=false", "root","rachna");
            stmt = con.createStatement();
            stmt1 = con.createStatement();
	}
	catch(SQLException e1)
	{ 
            e1.printStackTrace();
        }
        catch(ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
    public Allotment_Class() {
        allotment();
    }

    public void allotment(){
        int nos = 0, i = 0;
        String clg_name = null, opt1 = null, opt2 = null, opt3 = null;
        String sql = "select * from rank";
        try{
            connect();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String u_id = rs.getString(1);
                String name = rs.getString(2);
                String erank = rs.getString(3);
                String sql1 = "select * from options where u_id = '"+u_id+"'";
                rs1 = stmt1.executeQuery(sql1);
                while(rs1.next()){
                    opt1 = rs1.getString("opt1");
                    opt2 = rs1.getString("opt2");
                    opt3 = rs1.getString("opt3");
                }
                rs1.close();
                String sql2 = "select nos, name from colleges where cid = '"+ opt1 +"'";
                rs2 = stmt1.executeQuery(sql2);
                while(rs2.next()){
                    nos = rs2.getInt(1);
                    clg_name = rs2.getString(2);
                }
                rs2.close();
                if(insert_colleges(u_id, clg_name, name, erank, nos)){
                    System.out.println("Alloted!");
                } else {
                    String sql3 = "select nos, name from colleges where cid = '"+ opt2 +"'";
                    rs3 = stmt1.executeQuery(sql3);
                    while(rs3.next()){
                        nos = rs3.getInt(1);
                        clg_name = rs3.getString(2);
                    }
                    rs3.close();
                    if(insert_colleges(u_id, clg_name, name, erank, nos)){
                        System.out.println("Alloted!");
                    }
                    else{
                        String sql4 = "select nos, name from colleges where cid = '"+ opt3 +"'";
                        rs4 = stmt1.executeQuery(sql4);
                        while(rs4.next()){
                            nos = rs4.getInt(1);
                            clg_name = rs4.getString(2);
                        }
                        rs4.close();
                        if(insert_colleges(u_id, clg_name, name, erank, nos)){
                            System.out.println("Alloted!");
                        }
                        else{
                          
                        }
                    }
                }
                i = i+1;
            }
        }    
        catch(SQLException e1){
            e1.printStackTrace();
        }
    }
    
    public boolean insert_colleges(String u_id, String clg_name, String name, String erank, int nos ){
        try{
            connect();
            if(clg_name.equals("GMC Calicut")){
                    String sql3 = "select count(*) from GMCC1";
                    rs5 = stmt1.executeQuery(sql3);
                    if(rs5.next()){
                         if(rs5.getInt(1) < nos){
                            String sql4 = "insert into GMCC1 values('"+u_id+"','"+name+"','"+erank+"')";
                            stmt1.executeUpdate(sql4);
                            String sql5 = "update allotment set allotment_name = '"+clg_name+"' where u_id = '"+u_id+"'";
                            stmt1.executeUpdate(sql5);
                            //allotment_label.setText("Allotment : "  + clg_name);
                            rs5.close();
                            return true;
                        }
                        else{
                            rs5.close();
                            return false;
                        }
                    }else{
                        System.out.println("Not in rs5.next");
                        return false;
                    }
            }
            else if(clg_name.equals("GMC Kannur")){
                String sql3 = "select count(*) from GMCK1";
                    rs5 = stmt1.executeQuery(sql3);
                    if(rs5.next()){
                        if(rs5.getInt(1) < nos){
                            String sql4 = "insert into GMCK1 values('"+u_id+"','"+name+"','"+erank+"')";
                            stmt1.executeUpdate(sql4);
                            String sql5 = "update allotment set allotment_name = '"+clg_name+"' where u_id = '"+u_id+"'";
                            stmt1.executeUpdate(sql5);
                            rs5.close();
                            return true;
                        }
                        else{
                            rs5.close();
                            return false;
                        }
                    } else{
                        System.out.println("Not in rs5.next()");
                        return false;
                    }
            }
            else if(clg_name.equals("GMC Kottayam")){
                String sql3 = "select count(*) from GMCK2";
                    rs5 = stmt1.executeQuery(sql3);
                    if(rs5.next()){
                        if(rs5.getInt(1) < nos){
                            String sql4 = "insert into GMCK2 values('"+u_id+"','"+name+"','"+erank+"')";
                            stmt1.executeUpdate(sql4);
                            String sql5 = "update allotment set allotment_name = '"+clg_name+"' where u_id = '"+u_id+"'";
                            stmt1.executeUpdate(sql5);
                            rs5.close();
                            return true;
                        }
                        else{
                            rs5.close();
                            return false;
                        }
                    }else{
                        System.out.println("Not in rs5.next()");
                        return false;
                    }
            }
            else if(clg_name.equals("GMC Trivandrum")){
                String sql3 = "select count(*) from GMCT1";
                    rs5 = stmt1.executeQuery(sql3);
                    if(rs5.next()){
                        if(rs5.getInt(1) < nos){
                            String sql4 = "insert into GMCT1 values('"+u_id+"','"+name+"','"+erank+"')";
                            stmt1.executeUpdate(sql4);
                            String sql5 = "update allotment set allotment_name = '"+clg_name+"' where u_id = '"+u_id+"'";
                            stmt1.executeUpdate(sql5);
                            rs5.close();
                            return true;
                        }
                        else{
                            rs5.close();
                            return false;
                        }
                    }else{
                        System.out.println("Not in rs5.next()");
                        return false;
                    }
                }
            else if(clg_name.equals("GMC Thrissur")){
                String sql3 = "select count(*) from GMCT2";
                    rs5 = stmt1.executeQuery(sql3);
                    if(rs5.next()){
                        if(rs5.getInt(1) < nos){
                            String sql4 = "insert into GMCT2 values('"+u_id+"','"+name+"','"+erank+"')";
                            stmt1.executeUpdate(sql4);
                            String sql5 = "update allotment set allotment_name = '"+clg_name+"' where u_id = '"+u_id+"'";
                            stmt1.executeUpdate(sql5);
                            rs5.close();
                            return true;
                        }
                        else{
                            rs5.close();
                            return false;
                        }
                    }else{
                        System.out.println("Not in rs5.next()");
                        return false;
                    }
            }   
            else{
                return false;
            }
        }
        catch(SQLException e1){
            e1.printStackTrace();
            return false;
        }
    }
    public static void main(String args[]) {
        
    }}