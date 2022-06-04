/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;
import Models.User;
import Services.PanierCRUD;
import Services.UserService;
import java.sql.DriverManager;
import Utiles.MyConnection;

import java.io.PrintStream;


import Services.ProduitService;
import Models.Produit;
import java.sql.SQLException;

/**
 *
 * @author 21622
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

//     MyConnection mc = new MyConnection();
    // PanierCRUD paniercrud = new PanierCRUD();
     //paniercrud.ajouterPanier();
    
     ProduitService ps = new ProduitService();
     Produit p = new Produit();
     p.setId_prod(1);
     ps.Supprimerproduits(p);
    //ps.insertProduit(p);
//     UserService userService = new UserService();
//     User user = new User("ettayeb","selim","selim.ettayeb@esprit.tn","14225520","azerty","22365478","Medecin");
  //  userService.ajouterUtilisateur(user);
//     System.out.println(userService.fetchUsers());
 //    System.out.println(userService.getUserByID(3));
    // System.out.println(userService.deleteUser(2));
//      userService.updateUser(user,3);
    }
    
}
