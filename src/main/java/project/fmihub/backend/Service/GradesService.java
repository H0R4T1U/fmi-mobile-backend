package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Repository.GradesRepository;

import java.util.List;

@Service
public class GradesService {
    @Autowired
    private final GradesRepository gradesRepository;

    public GradesService(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    public List<Grades> findByIdStudent(String Student) {
        return gradesRepository.findByIdStudent(Student);
    }
}
