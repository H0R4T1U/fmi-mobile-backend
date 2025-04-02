package project.fmihub.backend.Domain;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NewsNotFoundAdvice {
    @ExceptionHandler(NewsNotFoundException.class)
    String newsNotFoundHandler(NewsNotFoundException ex){
        return ex.getMessage();
    }
}
