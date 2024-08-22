package utils;

import java.util.Random;
import java.util.Date;

public class AppUtil {
    static Random random = new Random();
    
    public static String generateEmailVerificationCode() {
        return new Date().getTime()+"_"+Math.abs(random.nextLong());
    }

    public static int generateOTP() {
        return random.nextInt(888889) + 111111;
    }    

    public static String partiallyHiddenEmail(String email) {
        String[] arr = email.split("@");
        
        String emailFirstPart = arr[0];
        String emailSecondPart = arr[1];

        int count = emailFirstPart.substring(2).length();
        StringBuilder bfr = new StringBuilder(); 

        for(int i=0;i<count;i++) 
            bfr.append("*");

        String hiddenEmail = emailFirstPart.substring(0,2)+ bfr.toString() +"@"+emailSecondPart;
        
        return hiddenEmail;
    }
}   
