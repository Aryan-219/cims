package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Course;
import models.Institute;

@WebServlet("/institute_dashboard.do")
public class InstituteDashboardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Institute institute = (Institute)session.getAttribute("user");

        String nextPage = "login.do";

        if(institute != null) {
            ArrayList<Course> courses = Course.collectAllCourses(institute);
            request.setAttribute("record_size", courses.size());
            request.setAttribute("courses", courses);
            nextPage = "institute_dashboard.jsp";
        }
        
        request.getRequestDispatcher(nextPage).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
