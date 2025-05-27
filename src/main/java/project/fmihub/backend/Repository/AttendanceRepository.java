package project.fmihub.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.fmihub.backend.Domain.Attendance;
import project.fmihub.backend.Domain.AttendanceId;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {

    List<Attendance> findByIdEmailAndIdClassField(String email, String classField);
}
