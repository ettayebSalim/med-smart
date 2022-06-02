/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.User;
import Utiles.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bureau
 */
public class UserService {
     //var
    PreparedStatement ps = null;
    User utilisateur = new User();

      // Add user 
    public void ajouterUtilisateur(User utilisateur) {

        try {
            String req2 = "INSERT INTO `user`(`nom`,`prenom`,`email`,`cin`,`hashedpwd`,`role`,`numtel`)"
                    + " VALUES (?,?,?,?,?,?,?)";

            ps = new MyConnection().getCnx().prepareStatement(req2);
             
            ps.setString(1,utilisateur.getNom());
            ps.setString(2,utilisateur.getPrenom());
            ps.setString(3,utilisateur.getEmail());
            ps.setString(4,utilisateur.getCin());
            ps.setString(5,utilisateur.getHashedPwd());
            ps.setString(6,utilisateur.getRole());
            ps.setString(7,utilisateur.getNumtel());

            ps.executeUpdate();

            System.out.println("Utilisateur Ajouté avec Succés");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
        //Select
    public List<User> fetchUsers() {
        List<User> users = new ArrayList<>();
        
        try {

            String req3 = "SELECT * FROM user";

            ps = new MyConnection().getCnx().prepareStatement(req3);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                User utilisateur = new User();
                utilisateur.setId(rs.getInt(1));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setCin(rs.getString("cin"));
                utilisateur.setHashedPwd(rs.getString("hashedPwd"));
                utilisateur.setNumtel(rs.getString("numtel"));
                utilisateur.setRole(rs.getString("role"));

                users.add(utilisateur);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
    

}
