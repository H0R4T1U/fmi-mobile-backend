package project.fmihub.backend.Domain.Exceptions;

public class TuitionNotFoundException extends RuntimeException {
    public TuitionNotFoundException(String payer) {
        super("Tuition of " + payer + " not found");
    }
}
