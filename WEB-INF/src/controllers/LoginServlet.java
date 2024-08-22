package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import models.Institute;
import models.Teacher;
import models.Status;
import models.UserType;

import utils.MessageTemplate;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(); 
        
        Integer userTypeId = Integer.parseInt(request.getParameter("user_type_id"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String nextPage = "login.jsp";

        if(userTypeId==1) {
            Institute institute = new Institute(email, password, new UserType(userTypeId));
            int result = institute.login();

            if(result == 0) {

            } else if(result == 1) {
                request.setAttribute("email_err_message", MessageTemplate.getIncorrectEmailMessage());
            } else if(result == 2) {
                request.setAttribute("password_err_message", MessageTemplate.getInvalidPasswordMessage());
            } else if(result == 3) {
                int statusId = institute.getStatus().getStatusId();
                if(statusId == Status.ACTIVE) {
                    session.setAttribute("user", institute);
                    nextPage = "institute_dashboard.do";
                } else if(statusId == Status.INACTIVE) {
                    String msg = MessageTemplate.getIncompleteEmailVerificationMessage(email);
                    nextPage = "message.jsp?color=alert-secondary&message="+msg;
                } else if(statusId == Status.EMAIL_VERIFIED) {
                    String msg = MessageTemplate.getIncompleteManualVerificationMessage();
                    nextPage = "message.jsp?color=alert-secondary&message="+msg;
                } else if(statusId == Status.MANUAL_VERIFICATION_DONE) {
                    session.setAttribute("institute", institute);
                    nextPage = "institute_details.jsp"; 
                } else if(statusId == Status.CLOSED) {
                    
                } else if(statusId == Status.BLOCKED) {
                    
                }
            }
        } else if (userTypeId==2) {

        } else {
            Teacher teacher = new Teacher(email, password, new UserType(userTypeId));
            int result = teacher.login();

            if(result == 0) {

            } else if(result == 1) {
                request.setAttribute("email_err_message", MessageTemplate.getIncorrectEmailMessage());
            } else if(result == 2) {
                request.setAttribute("password_err_message", MessageTemplate.getInvalidPasswordMessage());
            } else if(result == 3) {
                int statusId = teacher.getStatus().getStatusId();
                if(statusId == Status.ACTIVE) {
                    session.setAttribute("user", teacher);
                    nextPage = "teacher_dashboard.do";
                }
            }
        }
        
        request.getRequestDispatcher(nextPage).forward(request, response);
    }
}