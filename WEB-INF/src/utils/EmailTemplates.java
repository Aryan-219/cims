package utils;

public class EmailTemplates {
    public static String generateWelcomeMail(String name, String email, String verificationCode, Integer userTypeId) {
        String welcomeMail = "<h1>Welcome "+ name+"</h1><hr><p style='font-weight:bold;font-size:22px;color:red'>" +
        "Please click over the account activation link <a href='http://localhost:8080/cims_v2/email_verify.do?email="+email+"&verification_code="+ verificationCode+"&user_type_id="+userTypeId+"'>" + 
        "Activation Link</a></p>";
        
        return welcomeMail;
    }
}