package project.fmihub.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Client.StudentRestClient;
import project.fmihub.backend.Domain.Student;
import project.fmihub.backend.Repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final StudentRestClient studentRestClient;

    public StudentService(StudentRepository studentRepository,StudentRestClient studentRestClient) {
        this.studentRepository = studentRepository;
        this.studentRestClient = studentRestClient;
    }
    public List<Student> findByEmail(String email) {
        List<Student> students = studentRepository.findByEmail(email);
        if(students.isEmpty()) {
            students = studentRestClient.fetchStudentsFromApi("http://localhost:8080/api/students/"+email);
            studentRepository.saveAll(students);
        }
        return students;
    }
}
