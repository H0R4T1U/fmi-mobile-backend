package project.fmihub.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.fmihub.backend.Domain.Student;
import project.fmihub.backend.Domain.TemporaryGrades;
import project.fmihub.backend.Domain.TemporaryGradesId;

import java.util.List;

public interface TemporaryGradesRepository extends JpaRepository<TemporaryGrades, TemporaryGradesId> {
    List<TemporaryGrades> findByIdStudent(String student);
}
