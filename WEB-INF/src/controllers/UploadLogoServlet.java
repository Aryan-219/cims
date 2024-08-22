package controllers;

import java.io.File;
import java.io.IOException;
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

@WebServlet("/logo_upload.do")
public class UploadLogoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Institute institute = (Institute)session.getAttribute("institute");

        ServletContext context = getServletContext();

        if(institute!=null) {
            if(ServletFileUpload.isMultipartContent(request)) {
                try {
                    List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                    FileItem item = items.get(0);
    
                    System.out.println(item.getName());
                    // String fileName = item.getName();
                    String fileName = "logo."+item.getName().split("\\.")[1];
                    institute.setLogo(fileName);

                    String uploadPath = context.getRealPath("/WEB-INF/uploads/institutes/" + institute.getEmail()+"/logo");

                    File file = new File(uploadPath, fileName);

                    // institute.saveLogo(fileName);
                    
                    try{ item.write(file); } catch(Exception e) { e.printStackTrace(); }    
                    
                } catch(FileUploadException e) {
                    e.printStackTrace();
                }                 
            }
        } else {
            //handle session invalidated
        }
    }
}