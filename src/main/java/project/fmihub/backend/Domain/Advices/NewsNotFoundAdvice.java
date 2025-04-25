package project.fmihub.backend.Domain.Advices;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.fmihub.backend.Domain.Exceptions.NewsNotFoundException;

@RestControllerAdvice
public class NewsNotFoundAdvice {
    @ExceptionHandler(NewsNotFoundException.class)
    String newsNotFoundHandler(NewsNotFoundException ex){
        return ex.getMessage();
    }
}
