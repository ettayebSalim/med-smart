/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;
import Models.Etablissments;
import Models.RendezVous;
import Models.User;
import Services.PanierCRUD;
import Services.RendezVousService;
import Services.UserService;
import java.sql.DriverManager;
import Utiles.MyConnection;
import java.util.Date;




/**
 *
 * @author 21622
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     MyConnection mc = new MyConnection();
    // PanierCRUD paniercrud = new PanierCRUD();
     //paniercrud.ajouterPanier();
     
   UserService userService = new UserService();
    User user = new User("ettayeb","selim","selim.ettayeb@esprit.tn","14225520","azerty","22365478","Medecin");
  //  userService.ajouterUtilisateur(user);
//     System.out.println(userService.fetchUsers());
     System.out.println(userService.getUserByID(3));
    // System.out.println(userService.deleteUser(2));
     // userService.updateUser(user,3);
        Etablissments e= new Etablissments("ameni", "kali", "info");
     RendezVousService rdv=new RendezVousService();
    
 
                    
     java.util.Date myDate = new java.util.Date("10/10/2020");
 java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
     RendezVous r=new RendezVous(sqlDate, user,e);
     rdv.ajouterRendezVous(r);
    
    

        
    }
}