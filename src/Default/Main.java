/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import Models.User;
import Services.PanierCRUD;
import java.sql.DriverManager;
import Utiles.MyConnection;
import Services.EtabCRUD;
import Models.Etablissments;

/**
 *
 * @author Haithem
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//     MyConnection mc = new MyConnection();
        // PanierCRUD paniercrud = new PanierCRUD();
        //paniercrud.ajouterPanier();

        EtabCRUD etab = new EtabCRUD();
        Etablissments e = new Etablissments("name2", "adresse2", "type2");
        // etab.deleteEtab(1);
        // etab.updateEtab(e, 1);
        //etab.ajouterEtab(e);
        etab.getEtabById(2);
        //System.out.println(etab.afficherEtab());

    }

}
