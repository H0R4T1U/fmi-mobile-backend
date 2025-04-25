package project.fmihub.backend.Domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "grades")
public class Grades {
    @EmbeddedId
    private GradesId id;

    @Column(name = "year", nullable = false, length = 12)
    private String year;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "course_code", nullable = false, length = 12)
    private String courseCode;

    @Column(name = "course", nullable = false, length = 55)
    private String course;

    @Column(name = "grade", nullable = false, length = 15)
    private String grade;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    public GradesId getId() {
        return id;
    }
    public void setId(GradesId id) {
        this.id = id;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public Integer getSemester() {
        return semester;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public Integer getCredits() {
        return credits;
    }
    public void setCredits(Integer credits) {
        this.credits = credits;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }


}
