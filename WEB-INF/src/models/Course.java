package models;

import java.util.*;
import java.sql.*;

public class Course {
    private Integer courseId;
    private String name;
    private Institute institute;
    private String details;
    private String syllabusPdf;
    private String coursePic;
    private Integer starRank;
    private Integer starRankCount;

    public Course() {

    }    

    public Course(Integer courseId, Institute institute) {
        this.courseId = courseId;
        this.institute = institute;
    }

    public Course(Integer courseId, String name, Institute institute, String details, String syllabusPdf,
            String coursePic, Integer starRank, Integer starRankCount) {
        this.courseId = courseId;
        this.name = name;
        this.institute = institute;
        this.details = details;
        this.syllabusPdf = syllabusPdf;
        this.coursePic = coursePic;
        this.starRank = starRank;
        this.starRankCount = starRankCount;
    }

    public Course(String name, Institute institute, String details, String syllabusPdf) {
        this.name = name;
        this.institute = institute;
        this.details = details;
        this.syllabusPdf = syllabusPdf;
    }

    public static String getCoursePicPath(Integer courseId) {
        String coursePicPath = null;

        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e ) { e.printStackTrace(); }
    
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234")) { 
            
            String query = "select course_pic from courses where course_id=?";
            
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, courseId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                coursePicPath = rs.getString(1);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return coursePicPath;
    }

    public static void saveCoursePic(Integer courseId, String picPath) {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e ) { e.printStackTrace(); }
    
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234")) { 
            
            String query = "update courses set course_pic=? where course_id=?";
            
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, picPath);
            ps.setInt(2, courseId);

            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public void fetchCourseDetails() {

        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e ) { e.printStackTrace(); }
    
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234")) { 
            
            String query = "select name,details,course_pic,syllabus_pdf,star_rank,star_rank_count from courses where course_id=?";
            
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, courseId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                name = rs.getString(1);
                details = rs.getString(2);
                coursePic = rs.getString(3);
                syllabusPdf = rs.getString(4);
                starRank = rs.getInt(5);
                starRankCount = rs.getInt(6);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Course> collectAllCourses(Institute institute) {
        ArrayList<Course> courses = new ArrayList<>();

        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e ) { e.printStackTrace(); }
    
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234")) { 
            
            String query = "select * from courses where institute_id=?";
            
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, institute.getInstituteId());

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                courses.add(new Course(rs.getInt("course_id"), rs.getString("name"), institute, rs.getString("details"), rs.getString("syllabus_pdf"),
                rs.getString("course_pic"), rs.getInt("star_rank"), rs.getInt("star_rank_count")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    public boolean saveCourse() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234");

            String query = "insert into courses (name,institute_id,details,syllabus_pdf) value (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            System.out.println(institute);
            ps.setInt(2, institute.getInstituteId());
            ps.setString(3, details);
            ps.setString(4, syllabusPdf);

            int val = ps.executeUpdate();

            if(val == 1) 
                flag = true;

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return flag;
    }
    
    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Institute getInstitute() {
        return institute;
    }
    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    
    public String getCoursePic() {
        return coursePic;
    }
    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }
    public Integer getStarRank() {
        return starRank;
    }
    public void setStarRank(Integer starRank) {
        this.starRank = starRank;
    }
    public Integer getStarRankCount() {
        return starRankCount;
    }
    public void setStarRankCount(Integer starRankCount) {
        this.starRankCount = starRankCount;
    }

    public String getSyllabusPdf() {
        return syllabusPdf;
    }

    public void setSyllabusPdf(String syllabusPdf) {
        this.syllabusPdf = syllabusPdf;
    }
}