package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Course;
import models.Institute;

import com.google.gson.Gson;

public class InstituteCoursesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // HttpSession session = request.getSession();

        // Institute institute = (Institute)session.getAttribute("user");

        // if(institute != null) {
        //     ArrayList<Course> courses = Course.collectAllCourses(institute);
        //     request.setAttribute("record_size", courses.size());
        //     request.setAttribute("courses", courses);            
        // }
    }
}
