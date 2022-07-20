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
        Connection cnx2;


    
    public ProduitService() {
        cnx2 = MyConnection.getInstance().getCnx();

    }

    PreparedStatement ps = null;

    Date now = new Date(System.currentTimeMillis());

    //Create Produit
    public void insertProduit(Produit p) throws SQLException {
        try {
            String req = "INSERT INTO `produit`(`Nom`,`id_physique_img`,`qte`,`id_etab`) VALUES (?,?,?,?)";
            PreparedStatement ps = cnx2.prepareStatement(req);
            ps.setString(1, p.getNom_prod());
            ps.setString(2, p.getId_physique());
            ps.setDouble(3, p.getQte_prod());
            ps.setDouble(4, p.getId_etab());
            System.out.println("Product " + p.getNom_prod() + " added successfully");
            ps.executeUpdate();
               System.out.println("Medicament Ajouté avec Succé");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Select
    public List<Produit> fetchProduit() {
        List<Produit> produit = new ArrayList<>();
        try {


            String req = "SELECT * FROM Produit";

            PreparedStatement ps = cnx2.prepareStatement(req);
            ResultSet rs = ps.executeQuery(req);


            while (rs.next()) {
                Produit p = new Produit();
                p.setId_prod(rs.getInt(1));
                p.setNom_prod(rs.getString(2));
                p.setId_physique(rs.getString(3));
                p.setQte_prod(rs.getInt(4));
                //p.setId_etab(rs.getInt(5));
                produit.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produit;
    }
// supprimer produit 

    public void Supprimerproduits(int id) {
        try {

            String req = "DELETE FROM `produit` WHERE id=" + id;
             ps = cnx2.prepareStatement(req);

            //ResultSet rs = ps.executeQuery();
            //Statement st = cnx2.createStatement();
            ps.executeUpdate(req);

            System.out.println("Produit supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// modifier Produit

    public void ModifProduits(Produit p) {
        List<Produit> produit = new ArrayList<>();
        try {
            //Produit p = new Produit();
            String req = "UPDATE INTO `produit`('id','Nom','id_physique_img','qte','id_etab') VALUES (?,?,?,?,?)";

            PreparedStatement ps = cnx2.prepareStatement(req);

            ps.setString(1, p.getNom_prod());
            ps.setString(2, p.getId_physique());
            ps.setDouble(3, p.getQte_prod());
            ps.setDouble(4, p.getId_etab());
            System.out.println("Product " + p.getNom_prod() + " Modified successfully");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}