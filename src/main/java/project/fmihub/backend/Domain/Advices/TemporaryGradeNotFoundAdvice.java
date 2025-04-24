package project.fmihub.backend.Domain.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.fmihub.backend.Domain.Exceptions.TemporaryGradesNotFound;

@RestControllerAdvice
public class TemporaryGradeNotFoundAdvice {
    @ExceptionHandler(TemporaryGradesNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTemporaryGradesNotFound(TemporaryGradesNotFound exception) {
        return exception.getMessage();
    }
}
