/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Models.MCategory;
import Utiles.MyConnection;

import static java.util.Collections.list;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mo5fl
 */
public class ForumService {
    
    public void ajouterCategorie(){
        try {
            String requete = "INSERT INTO categorie (nom)"
                    + " VALUES ('Santé mentale') ";
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("categorie ajoutee avec succes");
        } catch (SQLException ex) {
System.err.println(ex.getMessage());
        }
    }
        public void ajouterCategorie2(MCategory G){
            try {
            String requete2 = "INSERT INTO categorie (nom)"
                    + " VALUES (?,?) ";
       
        
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete2);
            pst.setString(1, G.getNom());
            pst.executeUpdate();
            System.out.println("votre categorie est ajoutee");
            
            } catch (SQLException ex) {
             System.err.println(ex.getMessage());
        }
            
        
        }
        public List<MCategory> afficherCategorie(){
                 List<MCategory> myList = new ArrayList<>();

        try {
            String requete3 = "SELECT * FROM personne";
            
            Statement st =new MyConnection().getCnx() .createStatement();
                   ResultSet rs = st.executeQuery (requete3) ;
                   while(rs.next()){
                   MCategory G = new MCategory();
                   G.setId(rs.getInt(1));
                   G.setNom(rs.getString("nom"));
                   myList.add(G);
                           }
                   
                 
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
        }
           return myList;
        
        }
    
}
