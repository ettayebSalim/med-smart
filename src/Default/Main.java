/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;
import Models.Panier;
import Services.PanierCRUD;
import java.sql.DriverManager;
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
        Date now = new Date(System.currentTimeMillis());

     //MyConnection mc = new MyConnection();
     PanierCRUD paniercrud = new PanierCRUD();
     //paniercrud.ajouterPanier();
     //Panier p2 = new Panier(now);
     //paniercrud.ajouterPanier1(p2);
     
     // Afficher les panier
     System.out.println(paniercrud.afficherPanier());
     
    }
    
}
