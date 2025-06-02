package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Exams;
import project.fmihub.backend.Repository.ExamsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ExamsService {
    private static final Logger log = LoggerFactory.getLogger(ExamsService.class);

    @Autowired
    private final ExamsRepository examRepository;

    public ExamsService(ExamsRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exams> findByIdStudent(String Student) {
        log.info("Fetching exams for student id: {}", Student);
        return examRepository.findByIdStudent(Student);
    }
}
