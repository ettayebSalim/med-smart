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

    public void ajouterRendezVous(RendezVous r) {

        try {
            String req2 = "INSERT INTO `rdv`(`date`,`id_user`,`id_etab`)" + " VALUES (?,?,?)";
            System.out.println(r.getEtablissments().getId());

            PreparedStatement ps = cnx2.prepareStatement(req2);

            System.out.println("Services.RendezVousService.ajouterRendezVous()");
            java.sql.Date sqlDate = new java.sql.Date(r.getDate().getTime());
            ps.setDate(1, sqlDate);
            ps.setInt(2, r.getUser().getId());
            ps.setInt(3, r.getEtablissments().hashCode());

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
            String req4 = "SELECT * from RendezVous WHERE id = ?";
            PreparedStatement ps = cnx2.prepareStatement(req4);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User u =new User();
                Etablissments e= new Etablissments();
                rendezVous.setId(rs.getInt(1));
                rendezVous.setDate(rs.getDate(2));
                rendezVous.setUser(u.getId(rs.getInt("id")));
                rendezVous.setEtablissments((rs.getInt(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public void deleteRendezVous(RendezVous r) {

        try {
            String req5 = "delete from RendezVous WHERE id =" + r.getId();
            PreparedStatement ps = cnx2.prepareStatement(req5);
            ps.executeUpdate(req5);
            System.out.println("RendezVous supprimé avec succés");
        } catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<RendezVous> fetchRDV() {
        List<RendezVous> RDV = new ArrayList<>();

        try {

            String req3 = "SELECT * FROM RendezVous";

            PreparedStatement ps = cnx2.prepareStatement(req3);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RendezVous rd = new RendezVous();
                rd.setId(rs.getInt(1));
                rd.setDate(rs.getDate(2));
                rd.setUser((rs.getInt(3)));
                rd.setEtablissments((rs.getInt(4)));
                RDV.add(rd);

            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return RDV;
    }

    public void updateRendezVous(RendezVous r, int id) {

        try {

            String req6 = "UPDATE `date` SET `date`='" + r.getDate()
                    + "',`id_user`='" + r.getUser()
                    + "',`id_etab`='" + r.getEtablissments()
                    + " WHERE id=" + id;

            PreparedStatement ps = cnx2.prepareStatement(req6);
            ps.executeUpdate(req6);

            System.out.println("ReNDEZ VOUS modifié avec succés ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
