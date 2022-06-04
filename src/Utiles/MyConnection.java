/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Panier;

/**
 *
 * @author 21622
 */
public class MyConnection {
    
    //finals Db config
    private final static String USERNAME = "root";
    private final static String PASSWORD = "";
    private final static String URL = "jdbc:mysql://localhost:3306/PIDEV";
    static Connection cnx;
    public static MyConnection instance;

    
    public MyConnection()  {


        try {
            //DB connection
            
             cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexon etablie avec succes");
            
        } catch (SQLException ex) {
            System.out.println(ex);       
        }
      
}

public Connection getCnx() {
return cnx ;
}
public static MyConnection getInstance() {
    if(instance==null) {
        instance = new MyConnection() ;
    }
    return instance;
}
}

