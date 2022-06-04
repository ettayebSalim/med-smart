/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Panier;
import Utiles.MyConnection;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21622
 */
public class PanierCRUD {

    Connection cnx2;

    public PanierCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();

    }

    PreparedStatement ps = null;

    //add panier and declare var date
    Date now = new Date(System.currentTimeMillis());
    Panier p = new Panier(now);

    // Add panier 
    public void ajouterPanier(Panier p) {

        try {
            String req = "INSERT INTO `panier`(`id_user`, `date`) VALUES (?,?)";

            PreparedStatement ps = cnx2.prepareStatement(req);

            ps.setInt(1, p.getUser().getId());
            ps.setDate(2, p.getDate());

            ps.executeUpdate();

            System.out.println("Panier Ajouté avec Succé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterPanier1(Panier P) {
        String req2 = "INSERT INTO `Panier`(`date`)"
                + " VALUES (?)";
        try {
            PreparedStatement pst = cnx2.prepareStatement(req2);

            ps.setInt(1, p.getUser().getId());

            pst.setDate(1, p.getDate());
            pst.executeUpdate();
            System.out.println("Panier a été ajouté avec succé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

   
    //Delete User    

    public void deletePanier(int id) {

        try {
            String req4 = "delete from Panier WHERE id =" + id;
            ps = cnx2.prepareStatement(req4);
            ps.executeUpdate(req4);
            System.out.println("Panier supprimé avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void updatePanier(Panier u, int id) {

        try {

            String req5 = "UPDATE `panier` SET `id`='[value-1]',`id_user`='[value-2]',`date`='[value-3]' WHERE 1";

            ps = cnx2.prepareStatement(req5);
            ps.executeUpdate(req5);

            System.out.println("Paier modifié avec succés ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

}
