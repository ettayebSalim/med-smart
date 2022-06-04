/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


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



    private MyConnection() {
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
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

