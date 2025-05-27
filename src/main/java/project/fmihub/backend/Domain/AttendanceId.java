package project.fmihub.backend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AttendanceId implements Serializable {
    private static final long serialVersionUID = 2673152457098827800L;
    @Size(max = 64)
    @NotNull
    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Size(max = 10)
    @NotNull
    @Column(name = "class", nullable = false, length = 10)
    private String classField;

    @Size(max = 6)
    @NotNull
    @Column(name = "type", nullable = false, length = 6)
    private String type;

    @NotNull
    @Column(name = "\"position\"", nullable = false)
    private Integer position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AttendanceId entity = (AttendanceId) o;
        return Objects.equals(this.position, entity.position) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.classField, entity.classField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, type, email, classField);
    }

}