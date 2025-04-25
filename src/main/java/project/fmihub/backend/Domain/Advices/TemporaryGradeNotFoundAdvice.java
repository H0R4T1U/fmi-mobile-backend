package project.fmihub.backend.Domain.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.fmihub.backend.Domain.Exceptions.TemporaryGradesNotFoundException;

@RestControllerAdvice
public class TemporaryGradeNotFoundAdvice {
    @ExceptionHandler(TemporaryGradesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTemporaryGradesNotFound(TemporaryGradesNotFoundException exception) {
        return exception.getMessage();
    }
}
