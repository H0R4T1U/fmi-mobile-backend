package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Repository.GradesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class GradesService {
    private static final Logger logger = LoggerFactory.getLogger(GradesService.class);

    @Autowired
    private final GradesRepository gradesRepository;

    public GradesService(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    public List<Grades> findByIdStudent(String Student) {
        logger.info("Fetching grades for student " + Student);
        return gradesRepository.findByIdStudent(Student);
    }
}
