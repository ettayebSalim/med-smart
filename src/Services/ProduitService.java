/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Produit;
import Utiles.MyConnection;
import java.sql.Date;

/**
 *
 * @author NAD
 */
public class ProduitService {
    //var

    Date now = new Date(System.currentTimeMillis());

    //Create Produit
    public void insertProduit(Produit p) throws SQLException {
        try {
            String req = "INSERT INTO `produit`(`Nom`,`id_physique_img`,`qte`,`id_etab`) VALUES (?,?,?,?)";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(req);
            ps.setString(1, p.getNom_prod());
            ps.setString(2, p.getId_physique());
            ps.setInt(3, p.getQte_prod());
            ps.setInt(4, p.getId_etab());
            System.out.println("Product " + p.getNom_prod() + " added successfully");
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Select
    public List<Produit> fetchProduit(String Nom_prod) {
        List<Produit> produit = new ArrayList<>();
        try {
            String req = "select * FROM `produit`";
            Statement ps = new MyConnection().getCnx().createStatement();
            //ps.executeQuery(req);
            //rst= ps.executeQuery(req);
            //rst.;
            //int nbrRow = rst.getRow();
            //if (nbrRow!=0) {
            //    System.out.println("Produit trouver");
            //} else {
            //    System.out.println("Produit non trouver");
            //}
            ResultSet rs = ps.executeQuery(req);
            while (rs.next()){
                Produit p = new Produit();
                p.setId_prod(rs.getInt(1));
                p.setNom_prod(rs.getString("Nom"));
                p.setId_physique("id_physique_img");
                p.setQte_prod(0);
                p.setId_etab(0);
                produit.add(p);
              
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produit;
    }

    public void Supprimerproduits(Produit p) {
        try {
            String req = "DELETE FROM `produit` WHERE id=" + p.getId_prod();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Produit supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ModifProduits(Produit p) {
        List<Produit> produit = new ArrayList<>();
        try {
            //Produit p = new Produit();
            String req = "UPDATE INTO `produit`('id','Nom','id_physique_img','qte','id_etab') VALUES (?,?,?,?,?)";

             PreparedStatement ps = new MyConnection().getCnx().prepareStatement(req);
            ps.setString(1, p.getNom_prod());
            ps.setString(2, p.getId_physique());
            ps.setInt(3, p.getQte_prod());
            ps.setInt(4, p.getId_etab());
            System.out.println("Product " + p.getNom_prod() + " Modified successfully");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return produit;
    }
}
