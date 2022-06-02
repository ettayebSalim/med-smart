/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Panier;
import Utiles.MyConnection;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 21622
 */
public class PanierCRUD {

    PreparedStatement ps = null;

    //add panier and declare var date
    Date now = new Date(System.currentTimeMillis());
    Panier p = new Panier( now);

    // Add panier 
    public void ajouterPanier() {

        try {
            String req = "INSERT INTO `Panier`(`date`)"
                    + " VALUES (?)";

            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(req);
             
            //ps.setInt(1, p.getIdUser());
            ps.setDate(1, p.getDate());

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
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(req2);
            pst.setDate(1, p.getDate());
            pst.executeUpdate();
                        System.out.println("Panier a été ajouté avec succé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public List<Panier> afficherPanier() {
                     List<Panier> myList = new ArrayList<>();

        try {
              String req3 =  "SELECT * FROM panier";
              Statement st = new MyConnection().getCnx().createStatement();
              ResultSet rs = st.executeQuery(req3);
              while (rs.next()) {
                  Panier p = new Panier();
                 p.setId(rs.getInt(1));
                // p.setDate(rs.getDate());
                 myList.add(p);
              }
            
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());    
                System.out.println("abdesslam");
        }
     
        
        
        return myList;
    }

}
