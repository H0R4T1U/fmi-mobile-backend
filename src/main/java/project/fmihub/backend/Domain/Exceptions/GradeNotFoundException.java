package project.fmihub.backend.Domain.Exceptions;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(String message) {
        super("Grade of " + message + " not found");
    }
}
