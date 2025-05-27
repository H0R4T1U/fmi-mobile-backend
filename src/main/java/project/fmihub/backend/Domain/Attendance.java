package project.fmihub.backend.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance {
    @EmbeddedId
    private AttendanceId id;

    @Column(name = "attendance")
    private Boolean attendance;

}