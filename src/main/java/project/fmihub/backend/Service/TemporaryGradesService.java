package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Domain.TemporaryGrades;
import project.fmihub.backend.Repository.GradesRepository;
import project.fmihub.backend.Repository.TemporaryGradesRepository;

import java.util.List;

@Service
public class TemporaryGradesService {
    @Autowired
    private final TemporaryGradesRepository temporarygradesRepository;

    public TemporaryGradesService(TemporaryGradesRepository gradesRepository) {
        this.temporarygradesRepository = gradesRepository;
    }

    public List<TemporaryGrades> findByIdStudent(String Student) {
        return temporarygradesRepository.findByTemporaryGradesIdStudent(Student);
    }
}
