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
    private User user;
    private Date date ;
    


    public Panier(int id, User user, Date date) {
        this.id = id;
        this.user=user;
        this.date = date;
    }

    public Panier(User user, Date date) {
        this.user=user;
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

   

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setDate(Date date) {
        this.date = date;
    }
   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", idUser=" + idUser + ", date=" + date + '}';
    }
    
    
    
    
    
}
