package models;

import java.sql.*;

public class Teacher extends User {
    private Integer teacherId;
    private String qualification;
    private Integer experience;

    public Teacher(String qualification, Integer experience) {
        this.qualification = qualification;
        this.experience = experience;
    }

    public Teacher(String email, String password, UserType userType) {
        super(email, password, userType);
    }

    public int login() {
        int result = super.login();

        if(result == 3) {
            try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e) { e.printStackTrace(); }
            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234")) {
                String query = "select teacher_id,qualification,experience from teachers where user_id=?";

                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, getUserId());

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    teacherId = rs.getInt(1);
                    qualification = rs.getString(2);
                    experience = rs.getInt(3);
                }
                
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public void saveTeacher(Integer userId) {
        try{ Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e) { e.printStackTrace(); }
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234")) {
            String query = "insert into teachers (user_id, qualification, experience) value (?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, qualification);
            ps.setInt(3, experience);

            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Integer getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public Integer getExperience() {
        return experience;
    }
    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    
}
