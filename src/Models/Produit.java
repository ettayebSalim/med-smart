/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Locale.Category;

/**
 *
 * @author NAD
 */
public class Produit {

    private int id_prod;
    private String nom_prod;
    private String id_physique;
    private double qte_prod;
    private double id_etab;
     
//constructeur 
    public Produit() {
    }

    public Produit(int id_prod, String nom_prod, int qte_prod, String id_physique, int id_etab) {
        this.id_prod = id_prod;
        this.nom_prod = nom_prod;
        this.qte_prod = qte_prod;
        this.id_etab = id_etab;
        this.id_physique = id_physique;
    }

    public Produit(String nom_prod, String id_physique,int qte_prod, int id_etab) {
        this.nom_prod = nom_prod;
        this.id_physique = id_physique;
        this.qte_prod = qte_prod;
        this.id_etab = id_etab;
        
    }
    //getters and setters

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getId_physique() {
        return id_physique;
    }

    public void setId_physique(String id_physique) {
        this.id_physique = id_physique;
    }

    public double getQte_prod() {
        return qte_prod;
    }

    public void setQte_prod(double qte_prod) {
        this.qte_prod = qte_prod;
    }

    public double getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", id_physique" + id_physique +'}';
    }
    
}
