package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Client.StudentRestClient;
import project.fmihub.backend.Domain.Student;
import project.fmihub.backend.Repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final StudentRestClient studentRestClient;

    public StudentService(StudentRepository studentRepository,StudentRestClient studentRestClient) {
        this.studentRepository = studentRepository;
        this.studentRestClient = studentRestClient;
    }
    public List<Student> findByEmail(String email) {
        logger.info("Searching students by email: {}", email);
        List<Student> students = studentRepository.findByEmail(email);
        if(students.isEmpty()) {
            logger.info("No students found locally for email: {}. Fetching from external API...", email);
            students = studentRestClient.fetchStudentsFromApi("http://localhost:8080/api/students/"+email);
            studentRepository.saveAll(students);
        }
        return students;
    }
}
