package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.AppUtil;
import utils.MessageTemplate;
import utils.EmailSender;
import utils.EmailTemplates;

import models.User;

@WebServlet("/resend_verification_mail.do")
public class ReSendEmailVerificationLinkServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");

        String verificationCode = AppUtil.generateEmailVerificationCode();
        User user = new User(email, verificationCode);
        user.getEmailVerificationDetails();
        
        String subject = "Email Verification Mail";
        String emailContent = EmailTemplates.generateWelcomeMail(user.getName(), email, verificationCode, user.getUserType().getUserTypeId());
        EmailSender.sendEmail(email, subject, emailContent);
         
        String nextPage = "message.jsp?color=alert-success&message="+MessageTemplate.getResendEmailVerificationSuccessMessage(email);
        request.getRequestDispatcher(nextPage).forward(request,response);
    }
}
