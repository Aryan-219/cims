package models;

import java.util.ArrayList;
import java.sql.*;

public class City {
    // #################### Proprerties ####################################
    private Integer cityId;
    private String name;
    private State state;

    // #################### Constructors ####################################
    public City() {

    }

    public City(Integer cityId) {
        this.cityId = cityId;
    }

    public City(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public City(Integer cityId, String name, State state) {
        this.cityId = cityId;
        this.name = name;
        this.state = state;
    }

    // #################### Methods ####################################

    public boolean equals(Object obj) {
        boolean flag = false;

        if(obj instanceof City) {
            City c1 = this;
            City c2 = (City)obj;

            String nm1 = c1.name;
            String nm2 = c2.name;

            String nm3 = c1.state.getName();
            String nm4 = c2.state.getName();

            flag = nm1.equals(nm2) && nm3.equals(nm4);
        }

        return flag;
    }

    public static ArrayList<City> collectAllCities() {
        ArrayList<City> cities = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234");

            String query = "select city_id,c.name,s.name from cities as c inner join states as s where c.state_id=s.state_id";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                cities.add(new City(rs.getInt(1), rs.getString(2), new State(rs.getString(3))));
            }

            con.close();
        } catch(ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }

        return cities;
    } 

    // #################### getter-setters ####################################
    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
}
