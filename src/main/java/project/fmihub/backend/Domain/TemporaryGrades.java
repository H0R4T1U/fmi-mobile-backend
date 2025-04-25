package project.fmihub.backend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "temporary_grades")
public class TemporaryGrades {
    @EmbeddedId
    private TemporaryGradesId temporaryGradesId;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @Column(name = "course_code", nullable = false, length = 10)
    private String courseCode;

    @Column(name = "course", nullable = false, length = 55)
    private String course;

    @Column(name = "grade", nullable = false, length = 15)
    private String grade;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "retake_grade", nullable = false, length = 15)
    private String retakeGrade;

    @Column(name = "retake_date", nullable = false)
    private LocalDate retakeDate;

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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getRetakeGrade() {
        return retakeGrade;
    }
    public void setRetakeGrade(String retakeGrade) {
        this.retakeGrade = retakeGrade;
    }
    public TemporaryGradesId getTemporaryGradesId() {
        return temporaryGradesId;
    }
    public void setTemporaryGradesId(TemporaryGradesId temporaryGradesId) {
        this.temporaryGradesId = temporaryGradesId;
    }
    public LocalDate getRetakeDate() {
        return retakeDate;
    }
    public void setRetakeDate(LocalDate retakeDate) {
        this.retakeDate = retakeDate;
    }

}
