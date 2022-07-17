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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bureau
 */
public class UserService {
     //var
    Connection cnx2;
    public UserService() {
    cnx2 = MyConnection.getInstance().getCnx();
}
    PreparedStatement ps = null;
    User utilisateur = new User();

      // Add user 
    public void ajouterUtilisateur(User utilisateur) {

        try {
            String req2 = "INSERT INTO `user`(`nom`,`prenom`,`email`,`cin`,`hashedpwd`,`role`,`numtel`)"
                    + " VALUES (?,?,?,?,?,?,?)";

            ps = cnx2.prepareStatement(req2);
             
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
    
        //Select All Users
    public ObservableList<User> fetchUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        
        try {

            String req3 = "SELECT * FROM user";

            ps = cnx2.prepareStatement(req3);
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
    

        //Select User by id
    public User getUserByID(int id){
        
        User user = new User();
        
        try {
            String req4 = "SELECT * from user WHERE id = ?";
            ps = cnx2.prepareStatement(req4);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                user.setId(rs.getInt(1));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setCin(rs.getString(5));
                user.setHashedPwd(rs.getString(6));
                user.setNumtel(rs.getString(7));
                user.setRole(rs.getString(8));

            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }

   //Delete User    
    public void deleteUser(int id){
        
        
        try {
            String req5 = "delete from user WHERE id ="+id;
            ps = cnx2.prepareStatement(req5);
            ps.executeUpdate(req5);
            System.out.println("Utilisateur supprimé avec succés");
            }
            
            
         catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //
    public void updateUser(User u,int id) {
        
         try{
        
       String req6="UPDATE `user` SET `nom`='"+u.getNom()
               +"',`prenom`='"+u.getPrenom()
               +"',`email`='"+u.getEmail()
               +"',`cin`='"+u.getCin()
               +"',`hashedpwd`='"+u.getHashedPwd()
               +"',`numtel`='"+u.getNumtel()
               +"',`role`='"+u.getRole()
               +"' WHERE id= "+id;
       
            ps = cnx2.prepareStatement(req6);
            ps.executeUpdate(req6);
            
        System.out.println("Utilisateur modifié avec succés ");
        }catch(SQLException e){
        System.out.println(e.getMessage());
    
    }     
    }


}
