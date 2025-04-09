package project.fmihub.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import project.fmihub.backend.Domain.News;
import project.fmihub.backend.Repository.NewsRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class NewsController {
    private final NewsRepository newsRepository;
    private final NewsModelAssembler newsModelAssembler;
    public NewsController(NewsRepository newsRepository, NewsModelAssembler newsModelAssembler) {
         this.newsRepository = newsRepository;
         this.newsModelAssembler = newsModelAssembler;
    }

    @GetMapping("/api/news/{language}")
    CollectionModel<EntityModel<News>> all(@PathVariable String language) {
        List<EntityModel<News>> news = newsRepository.findAllByLanguageOrderByDateAsc(language).stream()
                .map(newsModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(news, linkTo(methodOn(NewsController.class).all(language)).withSelfRel());
    }

}
