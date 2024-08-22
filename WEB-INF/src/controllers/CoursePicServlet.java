package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import models.Institute;
import models.Course;

@WebServlet("/course_pic.do")
public class CoursePicServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Institute institute = (Institute)session.getAttribute("user");

        ServletContext context = getServletContext();

        if(institute!=null) {
            if(ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    
                    Integer courseId = null;
                    for(FileItem fileItem : items) {
                        if(fileItem.isFormField()) {
                            courseId = Integer.parseInt(fileItem.getString());
                        } else {
                            String fileName = courseId+"."+fileItem.getName().split("\\.")[1];

                            String uploadPath = context.getRealPath("/WEB-INF/uploads/institutes/" + institute.getEmail());

                            File file = new File(uploadPath, fileName);

                            try{ 
                                fileItem.write(file); 

                                Course.saveCoursePic(courseId, institute.getEmail()+"/"+fileName);
                            } catch(Exception e) { 
                                e.printStackTrace(); 
                            } 
                        }
                    } 
                } catch(FileUploadException e) {
                    e.printStackTrace();
                }                 
            }
        } else {
            //handle session invalidated
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Institute institute = (Institute)session.getAttribute("user");

        ServletContext context = getServletContext();

        if(institute!=null) {
            Integer courseId = Integer.parseInt(request.getParameter("course_id"));

            String coursePicPath = Course.getCoursePicPath(courseId);

            InputStream is = context.getResourceAsStream("/WEB-INF/uploads/institutes/"+coursePicPath);

            OutputStream os = response.getOutputStream();

            byte[] arr = new byte[256];
            while(is.read(arr) != -1) {
                os.write(arr);
            }

            os.close();
        }
    }
}