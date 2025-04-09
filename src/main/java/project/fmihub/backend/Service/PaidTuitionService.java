package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.PaidTuition;
import project.fmihub.backend.Repository.PaidTuitionRepository;

import java.util.List;

@Service
public class PaidTuitionService {

    @Autowired
    private final PaidTuitionRepository paidTuitionRepository;

    public PaidTuitionService(PaidTuitionRepository tuitionRepository) {
        this.paidTuitionRepository = tuitionRepository;
    }

    public List<PaidTuition> findByIdPayer(String Payer) {
        return paidTuitionRepository.findByIdPayer(Payer);
    }
}
