package project.fmihub.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.fmihub.backend.Domain.PaidTuition;
import project.fmihub.backend.Domain.PaidTuitionId;

import java.util.List;

public interface PaidTuitionRepository extends JpaRepository<PaidTuition, PaidTuitionId> {
    List<PaidTuition> findByIdPayer(String payer);
}
