/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author bureau
 */
public class User {
    
    private int id;
    private String nom;
    private String prenom;
    private String email ;
    private String cin;
    private String hashedPwd ;
    private String numtel;
    private String role;

    public User(int id, String nom, String prenom, String email, String cin, String hashedPwd, String numtel, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.hashedPwd = hashedPwd;
        this.numtel = numtel;
        this.role = role;
    }

    public User(String nom, String prenom, String email, String cin, String hashedPwd, String numtel, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.hashedPwd = hashedPwd;
        this.numtel = numtel;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getHashedPwd() {
        return hashedPwd;
    }

    public void setHashedPwd(String hashedPwd) {
        this.hashedPwd = hashedPwd;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", cin=" + cin + ", hashedPwd=" + hashedPwd + ", numtel=" + numtel + ", role=" + role + '}';
    }
    
    
    
    
}
