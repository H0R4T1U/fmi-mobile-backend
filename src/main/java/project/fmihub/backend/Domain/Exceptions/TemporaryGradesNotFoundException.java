package project.fmihub.backend.Domain.Exceptions;

public class TemporaryGradesNotFoundException extends RuntimeException {
    public TemporaryGradesNotFoundException(String message) {
        super("Grade of" + message + " not found!");
    }
}
