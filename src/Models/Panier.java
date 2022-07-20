/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.time.LocalDateTime;


/**
 *
 * @author 21622
 */
public class Panier {
    private int id;
    private String nomProd;
    private String date ;

    public Panier(int id, String nomProd, String date) {
        this.id = id;
        this.nomProd = nomProd;
        this.date = date;
    }

    public Panier(String nomProd, String date) {
        this.nomProd = nomProd;
        this.date = date;
    }

    public Panier() {
    }


    public int getId() {
        return id;
    }

    public String getNomProd() {
        return nomProd;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", nomProd=" + nomProd + ", date=" + date + '}';
    }

    
    
}
