package models;

import java.sql.Date;
import java.sql.Time;

public class Batch {
    private Integer batchId;
    private String name;
    private Course course;
    private Boolean mode;
    private Boolean frequency;
    private Date startDate;
    private Integer duration;
    private Time classTime;
    private Teacher teacher;
    private Status status;
    private Integer studentCount;
    private Integer courseFees;
    private Integer concession;
    
    public Integer getBatchId() {
        return batchId;
    }
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public Boolean getMode() {
        return mode;
    }
    public void setMode(Boolean mode) {
        this.mode = mode;
    }
    public Boolean getFrequency() {
        return frequency;
    }
    public void setFrequency(Boolean frequency) {
        this.frequency = frequency;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Time getClassTime() {
        return classTime;
    }
    public void setClassTime(Time classTime) {
        this.classTime = classTime;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Integer getStudentCount() {
        return studentCount;
    }
    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }
    public Integer getCourseFees() {
        return courseFees;
    }
    public void setCourseFees(Integer courseFees) {
        this.courseFees = courseFees;
    }
    public Integer getConcession() {
        return concession;
    }
    public void setConcession(Integer concession) {
        this.concession = concession;
    }

    
}