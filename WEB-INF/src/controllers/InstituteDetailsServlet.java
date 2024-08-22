package controllers;

import java.io.IOException;
import java.sql.Time;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Institute;

@WebServlet("/institute_details.do")
public class InstituteDetailsServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        
        Institute institute = (Institute)session.getAttribute("institute");
        
        String details = request.getParameter("details");
        String website = request.getParameter("website");
        Time startTime = Time.valueOf(request.getParameter("start_time")+":00");
        Time endTime = Time.valueOf(request.getParameter("end_time")+":00");
        Date dob = Date.valueOf(request.getParameter("established_on"));

        institute.setDetails(details);
        institute.setWebsite(website);
        institute.setStartTime(startTime);
        institute.setEndTime(endTime);
        institute.setDob(dob);

        institute.saveDetails();
        institute.saveDobAndLogo();

        response.sendRedirect("institute_dashboard.jsp"); 
    }
}