package project.fmihub.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Domain.GradesId;

import java.util.List;

public interface GradesRepository extends JpaRepository<Grades, GradesId> {
    List<Grades> findByIdStudent(String student);
}
