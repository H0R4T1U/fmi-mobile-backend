package project.fmihub.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.fmihub.backend.Domain.Tuition;
import project.fmihub.backend.Domain.TuitionId;

import java.util.List;

public interface TuitionRepository extends JpaRepository<Tuition, TuitionId> {
    List<Tuition> findByIdPayer(String Payer);
}
