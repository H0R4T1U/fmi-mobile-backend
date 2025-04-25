package project.fmihub.backend.Domain.Exceptions;

public class PaidTuitionNotFoundException extends RuntimeException {
    public PaidTuitionNotFoundException(String payer) {
        super("Could not find paid tuition for " + payer);
    }
}
