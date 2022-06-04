/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Fichier;
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
 * @author AGuizani
 */
public class FichierService {
       //var 
    Connection cnx = MyConnection.getInstance().getCnx();
    UserService us = new UserService();

    //Insert Fichier into BD
    
    public void insertFichier(Fichier f) {
        try {

            String req = "INSERT INTO `fichier`( `type`, `id_physique`, `id_user`) VALUES (?,?,?)";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, f.getType());
            ps.setString(2, f.getIdPhysique());
            ps.setLong(3, f.getUser().getId());
            ps.executeUpdate();
            System.out.println("File added successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//list all Fichier

    public List<Fichier> fetchFichiers() {

        List<Fichier> fichiers = new ArrayList<>();

        try {
            String req = "SELECT * FROM `fichier`";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fichier f = new Fichier();
                f.setId(rs.getLong(1));
                f.setType(rs.getString("type"));
                f.setIdPhysique(rs.getString("id_physique"));
                f.setUser(us.getUserByID(rs.getInt(4)));
                fichiers.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fichiers;
    }

    //get  Fichier by id_physique
    public Fichier getFichierByIdPhysique(String s) {
        Fichier f = new Fichier();
       
        try {

            String req = "SELECT `id`, `type`, `id_physique`, `id_user` FROM `fichier` WHERE id_physique =? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(3, s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f.setId(rs.getInt(1));
                f.setType(rs.getString("type"));
                f.setIdPhysique(rs.getString("id_physique"));
                f.setUser(us.getUserByID(rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    //get Fichier By id
    public Fichier getFichierById(long numb) {
        Fichier f = new Fichier();

        try {

            String req = "SELECT `id`, `type`, `id_physique`, `id_user` FROM `fichier` WHERE id =?";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setLong(1, numb);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f.setId(rs.getInt(1));
                f.setType(rs.getString("type"));
                f.setIdPhysique(rs.getString("id_physique"));
                f.setUser(us.getUserByID(rs.getInt(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    //delete Fichier By id
    public void DeleteFichierById(long numb) {

        try {
            String req = "DELETE FROM `fichier` WHERE id=?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setLong(1, numb);
            ps.executeUpdate();
            System.out.println("Fichier wich Id is :"+numb +"is deleted successfully ");
        } catch (SQLException ex) {
            Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
     
     //Update Fichier by Id 
 public void updateFichierById(Fichier f,long numb) {

        try {
            String req="UPDATE `fichier` SET `type`='"+f.getType()+"',`id_physique`='"+f.getIdPhysique()+"',`id_user`='"+f.getUser().getId()+"' WHERE id=?";
            
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setLong(1, numb);
            ps.executeUpdate();
            System.out.println("Fichier wich Id is :"+numb +"is updated successfully ");
        } catch (SQLException ex) {
            Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
