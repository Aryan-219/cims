package models;

import java.sql.*;

public class Institute extends User {
    private Integer instituteId;
    private String details;
    private String website;
    private Time startTime;
    private Time endTime;

    public Institute() {

    }

    public Institute(String email, String password, UserType userType) {
        super(email, password, userType);
    }

    public void saveDetails() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234");

            String query = "insert into institutes (user_id,details,website,start_time,end_time) value (?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, getUserId());
            ps.setString(2, details);
            ps.setString(3, website);
            ps.setTime(4, startTime);
            ps.setTime(5, endTime);

            ps.executeUpdate();

            con.close();
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int login() {
        int result = super.login();

        if(result == 3 && getStatus().getStatusId() == Status.ACTIVE) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234");

                String query = "select * from institutes where user_id=?";

                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, getUserId());

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    instituteId = rs.getInt("institute_id");
                    details = rs.getString("details");
                    website = rs.getString("website");
                    startTime = rs.getTime("start_time");
                    endTime = rs.getTime("end_time");
                }
                
                con.close();
            } catch(SQLException|ClassNotFoundException e) {
                e.printStackTrace();
            } 
        }

        return result;
    }


    public Integer getInstituteId() {
        return instituteId;
    }
    public void setInstituteId(Integer instituteId) {
        this.instituteId = instituteId;
    }
    
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public Time getStartTime() {
        return startTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    
}
