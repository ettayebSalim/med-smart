/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author haith
 */
public class Etablissments {
    private long id;
    private String name;
    private String adresse;
    private String type;
    private String libelle;

    public Etablissments(long id, String name, String adresse, String type) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.type = type;
    }

    public Etablissments(String name, String adresse, String type) {
        this.name = name;
        this.adresse = adresse;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Etablissments{" + "id=" + id + ", name=" + name + ", adresse=" + adresse + ", type=" + type + ", libelle=" + libelle + '}';
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
}
