/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.Fichier;
import Models.User;
import Services.PanierCRUD;
import Services.UserService;
import java.sql.DriverManager;
import Utiles.MyConnection;
import java.io.IOException;

import java.io.PrintStream;

import Services.ProduitService;
import Models.Produit;
import Services.FichierService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21622
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        MyConnection.getInstance();
//     MyConnection mc = new MyConnection();
        // PanierCRUD paniercrud = new PanierCRUD();
        //paniercrud.ajouterPanier();

        // ProduitService ps = new ProduitService();
        //Produit p = new Produit();
        //p.setId_prod(1);
        //ps.Supprimerproduits(p);
        //ps.insertProduit(p);
        //UserService userService = new UserService();
        //User user = new User("ettayeb","selim","selim.ettayeb@esprit.tn","14225520","azerty","22365478","Medecin");
        //userService.ajouterUtilisateur(user);
        // System.out.println(userService.fetchUsers());
        // System.out.println(userService.getUserByID(3));
        //System.out.println(userService.deleteUser(2));
        //userService.updateUser(user,3);
        // PanierCRUD paniercrud = new PanierCRUD();
        //paniercrud.ajouterPanier();
        
       /* 
        //call service
        FichierService fs = new FichierService();
        UserService userService = new UserService();
        // adding a user to table user, @author Fares
        User user;
        user= new User(1, "ettayeb", "selim", "selim.ettayeb@esprit.tn", "14225520", "azerty", "22365478", "Medecin");
        userService.ajouterUtilisateur(user);
         // adding a file to table fichier, 
        Fichier fichier;
        fichier= new Fichier(1,"IRM", "C:\\Users\\AGuizani\\Desktop\\test_care\\dataset\\00000141_000.png", user);
        fs.insertFichier(fichier);
        System.err.println(fs.fetchFichiers());
       // get fichier by id_physique 
        System.out.println(fs.getFichierByIdPhysique("00000141_000.png"));
        // get fichier by id 
        System.out.println(fs.getFichierById(1));
        // update fichier by id 
        Fichier fichier2= new Fichier(2,"IRM", "C:\\Users\\AGuizani\\Desktop\\test_care\\dataset\\00001287_000.png", user);
        fs.updateFichierById(fichier2, 1);
        // delete fichier by id 
        fs.DeleteFichierById(1);
        */
        
    }

}
