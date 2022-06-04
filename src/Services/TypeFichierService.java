/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.TypeFichier;

/**
 *
 * @author AGuizani
 */
public class TypeFichierService {

    
    public static Boolean checkType(String s) {

        boolean bool = false;
        for (TypeFichier tf : TypeFichier.values()) {
            if ((tf.toString().equalsIgnoreCase(s))) {
                bool = true;
            }
        }
        return bool;
    }

    public static String getType(String s) {
        String mess = "Not Supported type!";
        for (TypeFichier tf : TypeFichier.values()) {
            if ((tf.toString().equalsIgnoreCase(s))) {
                mess = tf.toString();
            }
        }
        return mess;
    }
}
