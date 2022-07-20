/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Default;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Bureau
 */
public class sendSMS {
    public static final String ACCOUNT_SID = System.getenv("ACf036909e6670a84aedcd7165f1ab337d");
    public static final String AUTH_TOKEN = System.getenv("ce721abffd6efbf6baf9b36d7f9be0a3");
   

    public static void sendSMS() {
        Twilio.init("ACf036909e6670a84aedcd7165f1ab337d", "ce721abffd6efbf6baf9b36d7f9be0a3");
        Message message = Message.creator(new PhoneNumber("+21652854245"),
                new PhoneNumber("+18623566984"),
                "Enregistrer avec Succés").create();
        System.out.println(message.getSid());
       
    }
}
