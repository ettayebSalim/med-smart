/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Services.TypeFichierService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

<<<<<<< HEAD


=======
>>>>>>> 749a3fa (fichier)
/**
 *
 * @author AGuizani
 */
public class Fichier {
<<<<<<< HEAD
     private long id;
=======
    private long id;
>>>>>>> 749a3fa (fichier)
    private String type;
    private String idPhysique;
    private User user;

    public Fichier() {
    }

    public Fichier(long id, String type, String idPhysique, User user) throws IOException {
        this.id = id;
        if (TypeFichierService.checkType(type)) {
            this.type = TypeFichierService.getType(type);
        }
        try {
            Path p = Paths.get(idPhysique);
            File f = p.toFile();

            if (f.exists()) {
                if (f.isFile()) {
                    this.idPhysique = f.getName();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.user = user;
    }

    public Fichier(String type, String idPhysique, User user) throws IOException {

        if (TypeFichierService.checkType(type)) {
            this.type = TypeFichierService.getType(type);
        }
        try {
            Path p = Paths.get(idPhysique);
            File f = p.toFile();

            if (f.exists()) {
                if (f.isFile()) {
                    this.idPhysique = f.getName();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdPhysique() {
        return idPhysique;
    }

    public void setIdPhysique(String idPhysique) {
        this.idPhysique = idPhysique;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "Fichier:{" + "id=" + id + ", type=" + type + ", idPhysique=" + idPhysique + ", IdUtilisateur=" + user.getId() + '}';
=======
        return "Fichier:{" + "id=" + id + ", type=" + type + ", idPhysique=" + idPhysique + ", User Id=" + user.getId()+ '}';
>>>>>>> 749a3fa (fichier)
    }
}
