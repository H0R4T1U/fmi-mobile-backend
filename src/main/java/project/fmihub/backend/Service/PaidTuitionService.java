package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.PaidTuition;
import project.fmihub.backend.Repository.PaidTuitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class PaidTuitionService {
    private static final Logger log = LoggerFactory.getLogger(PaidTuitionService.class);

    @Autowired
    private final PaidTuitionRepository paidTuitionRepository;

    public PaidTuitionService(PaidTuitionRepository tuitionRepository) {
        this.paidTuitionRepository = tuitionRepository;
    }

    public List<PaidTuition> findByIdPayer(String Payer) {
        log.info("Fetching Paid Tuition for Student: " + Payer);
        return paidTuitionRepository.findByIdPayer(Payer);
    }
}
