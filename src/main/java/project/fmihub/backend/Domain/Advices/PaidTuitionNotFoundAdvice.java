package project.fmihub.backend.Domain.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.fmihub.backend.Domain.Exceptions.PaidTuitionNotFoundException;

@RestControllerAdvice
public class PaidTuitionNotFoundAdvice {
    @ExceptionHandler(PaidTuitionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String tuitionNotFoundHandler(PaidTuitionNotFoundException ex) {
        return ex.getMessage();
    }

}
