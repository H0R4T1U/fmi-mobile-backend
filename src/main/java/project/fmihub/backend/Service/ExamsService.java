package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Domain.Exams;
import project.fmihub.backend.Repository.ExamsRepository;

import java.util.List;

@Service
public class ExamsService {
    @Autowired
    private final ExamsRepository examRepository;

    public ExamsService(ExamsRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exams> findByIdStudent(String Student) {
        return examRepository.findByIdStudent(Student);
    }
}
