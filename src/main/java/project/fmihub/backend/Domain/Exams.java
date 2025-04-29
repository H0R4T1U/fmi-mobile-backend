package project.fmihub.backend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "exams")
public class Exams {
    @EmbeddedId
    private ExamsId id;

    @Column(name = "session_type", nullable = false, length = 15)
    private String sessionType;

    @Column(name = "date_type", nullable = false, length = 15)
    private String dateType;

    @Column(name = "type", nullable = false, length = 11)
    private String type;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "room", nullable = false, length = 100)
    private String room;

    @Column(name = "teacher", nullable = false, length = 60)
    private String teacher;

    @Column(name = "course", nullable = false, length = 63)
    private String course;

    @Column(name = "group", nullable = false, length = 13)
    private String group;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    public Integer getSemester() {
        return semester;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    public ExamsId getId() {
        return id;
    }
    public void setId(ExamsId id) {
        this.id = id;
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
