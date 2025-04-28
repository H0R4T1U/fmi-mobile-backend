package project.fmihub.backend.DTO;

import java.time.LocalDate;

public class ExamsDTO {
    private String student;
    private Integer number;
    private String sessionType;
    private String dateType;
    private String type;
    private LocalDate date;
    private Integer duration;
    private String room;
    private String teacher;
    private String course;
    private String group;

    public String getStudent() {
        return student;
    }
    public void setStudent(String student) {
        this.student = student;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getSessionType() {
        return sessionType;
    }
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }
    public String getDateType() {
        return dateType;
    }
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }


}
