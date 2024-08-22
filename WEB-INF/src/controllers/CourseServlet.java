package controllers;

import java.io.IOException;
import java.util.List;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Institute;
import models.Course;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

@WebServlet("/course.do")
public class CourseServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Institute institute = (Institute)session.getAttribute("user");

        String nextPage = "login.do?user_type_id=1";

        if(institute != null) {  
            Integer courseId = Integer.parseInt(request.getParameter("course_id"));

            Course course = new Course(courseId, institute);
            course.fetchCourseDetails();
            
            request.setAttribute("course", course);
            
            nextPage = "course_details.jsp";
        }

        request.getRequestDispatcher(nextPage).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Institute institute = (Institute)session.getAttribute("user");
        System.out.println(institute);
        String nextPage = "login.do?user_type_id=1";

        if(institute != null) {            
            if(ServletFileUpload.isMultipartContent(request)) {
                Course course = new Course();
                course.setInstitute(institute);
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                
                    for(FileItem fileItem : items) {
                        if(fileItem.isFormField()) {
                            String field = fileItem.getFieldName();
                            String value = fileItem.getString();
                            switch(field) {
                                case "name":
                                    course.setName(value);
                                    break;
                                case "details":
                                    course.setDetails(value);
                                    break;
                            }
                        } else {
                            String absPath = getServletContext().getRealPath("/WEB-INF/uploads/institutes");
                            // File fl = new File()
                            course.setSyllabusPdf(institute.getEmail()+"/"+course.getName().replace(' ', '_')+".pdf");
                            File file = new File(absPath, course.getSyllabusPdf());

                            try {fileItem.write(file); } catch(Exception e) {e.printStackTrace();}
                        }
                    }
                    System.out.println(institute);
                    if(course.saveCourse())
                        nextPage = "institute_dashboard.do";
                } catch(FileUploadException e) {
                    e.printStackTrace();
                }  
            }
        }

        response.sendRedirect(nextPage);
    }
}



