package project.fmihub.backend.Domain;

public class NewsNotFoundException extends Exception {
    public NewsNotFoundException(Long id) {
        super("News with id " + id + " not found");
    }
}
