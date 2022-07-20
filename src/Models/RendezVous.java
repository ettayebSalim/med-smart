/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.*;
import java.time.LocalDate;



/**
 *
 * @author user
 */
public class RendezVous {
    private int id;
    private Date date;
    private User user;
    private Etablissments etablissments;
    private int id_User;
    private int id_etab;

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

    public RendezVous(int id, Date date, int id_User, int id_etab) {
        this.id = id;
        this.date = date;
        this.id_User = id_User;
        this.id_etab = id_etab;
    }

    public RendezVous(int id, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RendezVous(int id, LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
    