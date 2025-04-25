package project.fmihub.backend.Domain.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.fmihub.backend.Domain.Exceptions.GradeNotFoundException;

@RestControllerAdvice
public class GradeNotFoundAdvice {
    @ExceptionHandler(GradeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String gradeNotFoundHandler(GradeNotFoundException ex) {
        return ex.getMessage();
    }
}
