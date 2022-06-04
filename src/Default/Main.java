/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;


import Models.User;

import Models.Panier;
import Services.PanierCRUD;
import java.sql.DriverManager;
import Services.PanierCRUD;
import Services.UserService;
import Utiles.MyConnection;
import java.sql.Date;

/**
 *
 * @author 21622
 */
public class Main {

    /**
     * @param args the command line arguments
     */



    public static void main(String[] args) {
     MyConnection mc = MyConnection.getInstance();
     PanierCRUD paniercrud = new PanierCRUD();
     //paniercrud.ajouterPanier();
             Date now = new Date(System.currentTimeMillis());

    // UserService userService = new UserService();
     //User user = new User("ettayeb","selim","selim.ettayeb@esprit.tn","14225520","azerty","22365478","Medecin");
  //  userService.ajouterUtilisateur(user);
//     System.out.println(userService.fetchUsers());
 //    System.out.println(userService.getUserByID(3));
    // System.out.println(userService.deleteUser(2));
    //  userService.updateUser(user,3);

    
}}
