/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.*;



/**
 *
 * @author user
 */
public class RendezVous {
    private int id;
    private Date date;
    private User user;
    private Etablissments etablissments;

    public RendezVous() {
    }

    public RendezVous(Date date, User user, Etablissments etablissments) {
        this.date = date;
        this.user = user;
        this.etablissments = etablissments;
    }

    public RendezVous(int id, Date date, User user, Etablissments etablissments) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.etablissments = etablissments;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
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

    public Etablissments getEtablissments() {
        return etablissments;
    }

    public void setEtablissments(Etablissments etablissments) {
        this.etablissments = etablissments;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", date=" + date + ", user=" + user + ", etablissments=" + etablissments + '}';
    }
    
    
}
    