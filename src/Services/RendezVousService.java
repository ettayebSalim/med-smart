/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Etablissments;
import Models.Panier;
import Models.RendezVous;
import Models.User;
import Utiles.MyConnection;
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
        Date now = new Date(System.currentTimeMillis());
        RendezVous p = new RendezVous(now); 

  
        PreparedStatement ps = null;
    

    public void ajouterRendezVous(RendezVous r) {

        try {
            String req2 = "INSERT INTO `rdv`(`date`,`id_user`,`id_etab`)"
                    + " VALUES (?,?,?)";

            ps = new MyConnection().getCnx().prepareStatement(req2);
            ps.setDate(1, (Date) p.getDate());
            
            
            //id user
            //id etalb
            ps.executeUpdate();

            System.out.println("rendezVous Ajouté avec Succés");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  public RendezVous getByID(int id){
        
        RendezVous rendezVous = new RendezVous();
        try {
            String req4 = "SELECT * from RendezVous WHERE id = ?";
            ps = new MyConnection().getCnx().prepareStatement(req4);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
             
            while (rs.next())
            {                
              
                                Etablissments e=new Etablissments();

                  rendezVous.setId(rs.getInt(1));
                rendezVous.setDate(rs.getDate(2));
                User u = new User();
                u.setId(rs.getInt(3));
                e.setId(rs.getInt(4));
                rendezVous.setUser(u);
                rendezVous.setEtablissments(e);
            }
             
                } catch (SQLException ex) {
                    Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
                }
                
             return rendezVous;
          
    }
public void deleteRendezVous(RendezVous r){
        
        
        try {
            String req5 = "delete from RendezVous WHERE id ="+r.getId();
            ps = new MyConnection().getCnx().prepareStatement(req5);
            ps.executeUpdate(req5);
            System.out.println("RendezVous supprimé avec succés");
            }
            
            
         catch (SQLException ex) {
            Logger.getLogger(RendezVousService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
public List<RendezVous> fetchRDV() {
        List<RendezVous> RDV = new ArrayList<>();
        
        try {

            String req3 = "SELECT * FROM RendezVous";

            ps = new MyConnection().getCnx().prepareStatement(req3);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                RendezVous rendezVous = new RendezVous();
                  rendezVous.setId(rs.getInt(1));
                rendezVous.setDate(rs.getDate(2));
                User u = new User();
                u.setId(rs.getInt(3));
                rendezVous.setUser(u);
                Etablissments e=new Etablissments();
                e.setId(rs.getInt(4));
                rendezVous.setEtablissments(e);
                
     

                RDV.add(rendezVous);
                
            }
        

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return RDV;
    }    
}
