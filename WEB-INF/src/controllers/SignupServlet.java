package controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.Teacher;
import models.City;
import models.UserType;
import utils.AppUtil;
import utils.EmailSender;
import utils.EmailTemplates;
import utils.MessageTemplate;

@WebServlet("/signup.do")
public class SignupServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext context = getServletContext();
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String contact = request.getParameter("contact");
        Integer cityId = Integer.parseInt(request.getParameter("city_id"));
        Integer userTypeId = Integer.parseInt(request.getParameter("user_type_id"));

        String verificationCode = AppUtil.generateEmailVerificationCode();
        User user = new User(name, email, password, new City(cityId), contact, new UserType(userTypeId), verificationCode);
        
        if(userTypeId == 1) 
            user.setAddress(request.getParameter("address"));
        
        if(user.saveUser()) {
            if(userTypeId == 3) {
                String qualification = request.getParameter("qualification");
                Integer experience = Integer.parseInt(request.getParameter("experience"));
                Teacher teacher = new Teacher(qualification, experience);
                
                teacher.saveTeacher(user.getUserId());
            }

            String subject = "Email Verification Mail";
            String emailContent = EmailTemplates.generateWelcomeMail(name, email, verificationCode, userTypeId);
            EmailSender.sendEmail(email, subject, emailContent);
            
            String userPath = context.getRealPath("/WEB-INF/uploads/"+UserType.types[userTypeId-1].toLowerCase());
            File file = new File(userPath, email+"/logo");
            file.mkdirs();
        }
        
        // request.getRequestDispatcher("login.jsp").forward(request, response);
        String signupSuccessMessage = MessageTemplate.getSignupSuccessMessage();
        response.sendRedirect("message.jsp?color=alert-success&message="+signupSuccessMessage);
    }
}