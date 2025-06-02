package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Tuition;
import project.fmihub.backend.Repository.TuitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TuitionService {
    private static final Logger logger = LoggerFactory.getLogger(TuitionService.class);

    @Autowired
    private final TuitionRepository tuitionRepository;

    public TuitionService(TuitionRepository tuitionRepository) {
        this.tuitionRepository = tuitionRepository;
    }

    public List<Tuition> findByIdPayer(String Payer) {
        logger.info("Fetching tuitions for Student: " + Payer);
        return tuitionRepository.findByIdPayer(Payer);
    }
}
