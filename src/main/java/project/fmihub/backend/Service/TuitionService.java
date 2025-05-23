package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Tuition;
import project.fmihub.backend.Repository.TuitionRepository;

import java.util.List;

@Service
public class TuitionService {
    @Autowired
    private final TuitionRepository tuitionRepository;

    public TuitionService(TuitionRepository tuitionRepository) {
        this.tuitionRepository = tuitionRepository;
    }

    public List<Tuition> findByIdPayer(String Payer) {
        return tuitionRepository.findByIdPayer(Payer);
    }
}
