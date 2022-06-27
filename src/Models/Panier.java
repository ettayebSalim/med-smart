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
    private int idUser ;
    private Date date ;

    public Panier(int id, int idUser, Date date) {
        this.id = id;
        this.idUser = idUser;
        this.date = date;
    }

    public Panier(int idUser, Date date) {
        this.idUser = idUser;
        this.date = date;
    }

    public Panier() {
    }

    public Panier(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", idUser=" + idUser + ", date=" + date + '}';
    }
    
    
    
    
    
}
