package project.fmihub.backend.DTO;

import java.time.LocalDate;

public class TemporaryGradesDTO {
    private String student;
    private Integer number;
    private Integer semester;
    private String course_code;
    private String course;
    private String grade;
    private LocalDate date;
    private String retake_grade;
    private LocalDate retake_date;

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
    public Integer getSemester() {
        return semester;
    }
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    public String getCourse_code() {
        return course_code;
    }
    public void setCourse_code(String course_code) {
        this.course_code = course_code;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getRetake_grade() {
        return retake_grade;
    }
    public void setRetake_grade(String retake_grade) {
        this.retake_grade = retake_grade;
    }
    public LocalDate getRetake_date() {
        return retake_date;
    }
    public void setRetake_date(LocalDate retake_date) {
        this.retake_date = retake_date;
    }
}
