/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;
<<<<<<< HEAD



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

=======
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
>>>>>>> etab

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
<<<<<<< HEAD
    public static MyConnection instance ;

=======
    public static MyConnection instance;
>>>>>>> etab
    
    private MyConnection()  {


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
<<<<<<< HEAD
public static MyConnection  getInstance() {
    if(instance == null) {
        instance = new MyConnection() ;
        
    }
    return instance ;
=======
public static MyConnection getInstance(){
    if(instance==null){
        instance = new MyConnection();
    }
    return instance;
>>>>>>> etab
}
}

