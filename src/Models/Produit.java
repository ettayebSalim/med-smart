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
    private int id_physique;
    private int id_etab;
    private String nom_prod;
    private float qte_prod;

//constructeur 
    public Produit() {
    }

    public Produit(int id_prod, String nom_prod, float qte_prod, int id_physique, int id_etab) {
        this.id_prod = id_prod;
        this.nom_prod = nom_prod;
        this.qte_prod = qte_prod;
        this.id_etab = id_etab;
        this.id_physique = id_physique;
    }

    public Produit(String nom_prod, float qte_prod, int id_physique, int id_etab) {
        this.nom_prod = nom_prod;
        this.qte_prod = qte_prod;
        this.id_etab = id_etab;
        this.id_physique = id_physique;
    }
    //getters and setters

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getId_physique() {
        return id_physique;
    }

    public void setId_physique(int id_physique) {
        this.id_physique = id_physique;
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public float getQte_prod() {
        return qte_prod;
    }

    public void setQte_prod(float qte_prod) {
        this.qte_prod = qte_prod;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", id_physique" + id_physique +'}';
    }

}
