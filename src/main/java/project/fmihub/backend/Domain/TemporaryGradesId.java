package project.fmihub.backend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TemporaryGradesId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "student", nullable = false, length = 64)
    private String student;

    @Column(name = "number", nullable = false)
    private Integer number;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemporaryGradesId that = (TemporaryGradesId) o;
        return Objects.equals(student, that.student) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, number);
    }
}
