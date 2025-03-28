package project.fmihub.backend.Domain;

public class StudentNotFoundException extends RuntimeException {
    StudentNotFoundException(Long id) {
        super("Could not find Student " + id );
    }
}
