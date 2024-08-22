package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/syllabus.do")
public class SyllabusDownloadServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getParameter("path");

        InputStream is = getServletContext().getResourceAsStream("/WEB-INF/uploads/institutes/"+path);
        OutputStream os = response.getOutputStream();

        byte[] arr = new byte[256];
        while(is.read(arr) != -1) {
            os.write(arr);
        }

        os.close();
    }
}
