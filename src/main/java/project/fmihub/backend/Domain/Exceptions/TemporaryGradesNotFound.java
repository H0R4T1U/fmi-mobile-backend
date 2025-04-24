package project.fmihub.backend.Domain.Exceptions;

public class TemporaryGradesNotFound extends RuntimeException {
    public TemporaryGradesNotFound(String message) {
        super("Grade of" + message + " not found!");
    }
}
