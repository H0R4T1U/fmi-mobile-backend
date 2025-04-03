package project.fmihub.backend.Controller;

import org.hibernate.persister.entity.mutation.InsertCoordinatorStandard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import project.fmihub.backend.Domain.News;
import project.fmihub.backend.Repository.NewsRepository;
import project.fmihub.backend.Service.NewsService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class NewsController {
    private final NewsRepository newsRepository;
    private final NewsService newsService;
    private final NewsModelAssembler newsModelAssembler;
    public NewsController(NewsRepository newsRepository, NewsService newsService, NewsModelAssembler newsModelAssembler) {
        this.newsRepository = newsRepository;
        this.newsService = newsService;
        this.newsModelAssembler = newsModelAssembler;
    }

    @GetMapping("/api/news/{lang}")
    CollectionModel<EntityModel<News>> all(@PathVariable String lang) {
        List<EntityModel<News>> news = newsService.getAllNewsByLanguage(lang).stream()
                .map(newsModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(news, linkTo(methodOn(NewsController.class).all(lang)).withSelfRel());
    }

    @GetMapping("/api/news/{id}")
    EntityModel<News> one(@PathVariable Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("News not found"));
        return newsModelAssembler.toModel(news);
    }

}
