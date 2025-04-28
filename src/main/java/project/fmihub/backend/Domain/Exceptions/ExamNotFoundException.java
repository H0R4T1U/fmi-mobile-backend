package project.fmihub.backend.Domain.Exceptions;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException(String message) {
        super("Exam of" + message + " not found");
    }
}
