package utils;

public class MessageTemplate {
    public static String getSignupSuccessMessage() {
        String signupSuccessMessage = "Your Account is created!! A verification mail is sent. Please verify your email.";

        return signupSuccessMessage;
    }

    public static String getEmailVerificationSuccessMessage() {
        String emailVerificationSuccessMessage = "Your Email Verification is Done....!! We will initiate a Mannual Verification. Ones Your Manual Verification is done You will be able to login..!";

        return emailVerificationSuccessMessage;
    }

    public static String getIncorrectEmailMessage() {
        String incorrectEmailMessage = "Account with the given email does not exist or your email is incorrect.. please check";
        
        return incorrectEmailMessage;
    }

    public static String getInvalidPasswordMessage() {
        String invalidPasswordMessage = "Invalid Password... Please Try Again...!!";

        return invalidPasswordMessage;
    }

    public static String getIncompleteEmailVerificationMessage(String email) {
        String hiddenEmail = AppUtil.partiallyHiddenEmail(email);

        String incompleteEmailVerificationMessage = "Your Email Verification is Incomplete... " + 
        "Please Click Over the Link : <a href='resend_verification_mail.do?email=" + email + "'>Resend Verification " + 
        "Mail</a> to resend the Email Verification Link.... We will send you a verification mail " + 
        "on " + hiddenEmail;

        return incompleteEmailVerificationMessage;
    }

    public static String getResendEmailVerificationSuccessMessage(String email) {
        String msg = "A new email verification link has been sent to " + AppUtil.partiallyHiddenEmail(email) + " Please Verify your account...!";
        
        return msg;
    }

    public static String getIncompleteManualVerificationMessage() {
        String msg = "Your Manual Verification is Under Process... We Will Inform You Ones It Is Done..";

        return msg;
    }
}
