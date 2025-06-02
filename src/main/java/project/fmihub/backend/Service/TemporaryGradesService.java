package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Domain.TemporaryGrades;
import project.fmihub.backend.Repository.GradesRepository;
import project.fmihub.backend.Repository.TemporaryGradesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TemporaryGradesService {
    private static final Logger logger = LoggerFactory.getLogger(TemporaryGradesService.class);

    @Autowired
    private final TemporaryGradesRepository temporarygradesRepository;

    public TemporaryGradesService(TemporaryGradesRepository gradesRepository) {
        this.temporarygradesRepository = gradesRepository;
    }

    public List<TemporaryGrades> findByIdStudent(String Student) {
        logger.info("Finding temporary grades for student " + Student);
        return temporarygradesRepository.findByTemporaryGradesIdStudent(Student);
    }
}
