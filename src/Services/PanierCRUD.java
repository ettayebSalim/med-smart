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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    Panier p = new Panier( now);

    // Add panier 
    public void ajouterPanier() {

        try {
            String req = "INSERT INTO `Panier`(`date`)"
                    + " VALUES (?)";

            ps = cnx2.prepareStatement(req);
             
            //ps.setInt(1, p.getIdUser());
            ps.setDate(1, p.getDate());

            ps.executeUpdate();

            System.out.println("Panier Ajouté avec Succé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void ajouterPanier1() {

    }

}
