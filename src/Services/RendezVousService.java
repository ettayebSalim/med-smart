/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Etablissments;

import Models.RendezVous;
import Models.User;
import Utiles.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.*;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class RendezVousService {

    Connection cnx2;
    Connection Connection;

// <editor-fold defaultstate="collapsed" desc="Init Creation Instance -> Singleton">
    public RendezVousService() {
        cnx2 = MyConnection.getInstance().getCnx();

    }
    private static RendezVousService instance = new RendezVousService();

    public static RendezVousService GetInstance() {
        return instance;
    }
    // </editor-fold>
    //var
      Date now = new Date(System.currentTimeMillis());
   
      RendezVous r = new RendezVous();
     
    public void ajouterRendezVous(RendezVous r) {

        try {
            String req2 = "INSERT INTO `rdv`(`date`,`id_user`,`id_etab`)" + " VALUES (?,?,?)";

            PreparedStatement ps = cnx2.prepareStatement(req2);

            System.out.println("Services.RendezVousService.ajouterRendezVous()");
            ps.setDate(1,new java.sql.Date(r.getDate().getTime()));
       
            ps.setInt(2,r.getUser().getId() );
            ps.setLong(3,r.getEtablissments().getId() );
 
            //id user
            //id etalb
            ps.executeUpdate();

            System.out.println("rendezVous Ajouté avec Succés");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public RendezVous getByID(int id) {

        RendezVous rendezVous = new RendezVous();
        try {
            String req = "SELECT * FROM rdv WHERE Id = " + id + ";";
           PreparedStatement ps = Connection.prepareStatement(req);
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                try {
                    return InitRdv(result);
                } catch (Exception ex) {
                    System.err.println("[Exception] " + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.err.println("[SQL Exception] " + ex.getMessage());
        }

        return null;
    }
    public void deleteRendezVous(int id) {

        try {
            
            String req5 = "delete from rdv WHERE id =" +id;
            PreparedStatement ps = cnx2.prepareStatement(req5);
            ps.executeUpdate(req5);
            System.out.println("RendezVous supprimé avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 public List<RendezVous> fetchRDV() {
        ArrayList<RendezVous> resultList = new ArrayList<RendezVous>();
        try {
            String req = "SELECT * FROM rdv";
            PreparedStatement ps = Connection.prepareStatement(req);
            ResultSet result = ps.executeQuery();

            while (result.next()) {

                try {
                    resultList.add(InitRdv(result));
                } catch (Exception ex) {
                    System.err.println("[Exception] " + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.err.println("[SQL Exception] " + ex.getMessage());
        }

        return resultList;
    }

    public void updateRendezVous(RendezVous r, int id) {

        try {

            String req = "UPDATE `rdv` SET  date = ?"
                    + " WHERE id=" + id;

            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.executeUpdate(req);

            System.out.println("ReNDEZ VOUS modifié avec succés ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  private RendezVous InitRdv(ResultSet result) {
        try {
            return new RendezVous(
                    result.getInt("id"),
                    result.getDate("date"),
                    result.getInt("id_User"),
                    result.getInt("id_etab"));


        } catch (SQLException ex) {
            System.err.println("[Exception] " + ex.getMessage());
        }

        return null;
    }

}
