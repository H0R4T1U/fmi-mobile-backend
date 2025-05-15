package project.fmihub.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.fmihub.backend.Domain.News;
import project.fmihub.backend.Service.NewsService;

import java.util.ArrayList;
import java.util.List;



@RestController
public class NewsController {
    private final NewsService newsService;
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/api/news/{lang}")
    ResponseEntity<List<News>> all(@PathVariable String lang) {
        List<News> news = new ArrayList<>(newsService.getAllNewsByLanguage(lang));
        return ResponseEntity.ok(news);

    }


}
