/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;


import Models.Produit;
import Services.ProduitService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
/**
 *
 * @author NAD
 */
 

/**
 *
 * @author NAD
 */
public class QRGenerate {

    public QRGenerate(String chaine) {

        {
           // String essai = "Najd Chaabouni CINFO 01";
            ByteArrayOutputStream out = QRCode.from(chaine).to(ImageType.JPG).stream();
            File f = new File("\\D:\\qrcode\\maProduit.jpg");
            try {
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(out.toByteArray());

                fos.flush();
            } catch (IOException ex) {
                Logger.getLogger(QRGenerate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

