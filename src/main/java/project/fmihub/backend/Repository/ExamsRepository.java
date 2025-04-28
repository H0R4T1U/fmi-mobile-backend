package project.fmihub.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.fmihub.backend.Domain.Exams;
import project.fmihub.backend.Domain.ExamsId;

import java.util.List;

public interface ExamsRepository extends JpaRepository<Exams, ExamsId> {
    List<Exams> findByIdStudent(String student);
}
