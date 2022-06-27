/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Etablissments;
import Utiles.MyConnection;
import java.sql.Connection;
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
 * @author haith
 */
public class EtabCRUD {

    Connection cnx2;

    public EtabCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();

    }

    PreparedStatement ps = null;

    //add Etab and declare var date
    // Add Etab 
    public void ajouterEtab(Etablissments e) {

        try {
            String req = "INSERT INTO `etablissements`(`name`,`adresse`,`type`)"
                    + " VALUES (?,?,?)";

            PreparedStatement ps = cnx2.prepareStatement(req);

            //ps.setInt(1, p.getIdUser());
            ps.setString(1, e.getName());
            ps.setString(2, e.getAdresse());
            ps.setString(3, e.getType());

            ps.executeUpdate();

            System.out.println("Etab Ajouté avec Succé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Etablissments> afficherEtab() {
        List<Etablissments> myList = new ArrayList<>();

        try {
            String req3 = "SELECT * FROM etablissements";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req3);
            while (rs.next()) {
                Etablissments p = new Etablissments();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setAdresse(rs.getString(3));
                p.setType(rs.getString(4));
                // p.setDate(rs.getDate());
                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

        return myList;
    }

    public void updateEtab(Etablissments e, int id) {
        try {
            String req3 = "UPDATE etablissements SET name=?,adresse=?,type=? WHERE id=" + id;
            PreparedStatement ps3;
            ps3 = cnx2.prepareStatement(req3);
            ps3.setString(1, e.getName());
            ps3.setString(2, e.getAdresse());
            ps3.setString(3, e.getType());
            ps3.executeUpdate();
            System.out.println("Etab with id:" + id + " updated successfully");
        } catch (SQLException ex) {
            Logger.getLogger(EtabCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Delete User    
    public void deleteEtab(int id) {

        try {
            String req4 = "delete from etablissements WHERE id =" + id;
            ps = cnx2.prepareStatement(req4);
            ps.executeUpdate(req4);
            System.out.println("Etab deleted successfully");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    //get Etab by id   

    public void getEtabById(int id) {

        try {
            String req = "select * from etablissements WHERE id="+id;
                        Etablissments e = new Etablissments();

            Statement st = cnx2.createStatement();
            
              ResultSet rs = st.executeQuery(req);
              
              while (rs.next()) {                
            e.setId(rs.getInt(1));
            e.setName(rs.getString(2));
            e.setAdresse(rs.getString(3));
            e.setType(rs.getString(4));

            }
              
            
            System.out.println(e);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

}
