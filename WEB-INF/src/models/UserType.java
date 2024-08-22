package models;

public class UserType {
    private Integer userTypeId;
    private String type;

    public static String[] types = {"Institutes", "Students", "Teachers"}; 

    public UserType(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }
    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
