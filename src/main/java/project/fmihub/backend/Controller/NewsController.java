package project.fmihub.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import project.fmihub.backend.Domain.News;
import project.fmihub.backend.Repository.NewsRepository;
import project.fmihub.backend.Service.NewsService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class NewsController {
    private final NewsService newsService;
    private final NewsModelAssembler newsModelAssembler;
    public NewsController(NewsService newsService, NewsModelAssembler newsModelAssembler) {
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


    @PutMapping("/news/{id}")
    ResponseEntity<?> update(@RequestBody News news, @PathVariable Long id) {
        News updatedNews = newsRepository.findById(id)
                .map(stire -> {
                    stire.setTitle(news.getTitle());
                    stire.setDate(news.getDate());
                    stire.setLocation(news.getLocation());
                    stire.setLanguage(news.getLanguage());
                    return newsRepository.save(stire);
                })
                .orElseGet(() -> newsRepository.save(news));
        EntityModel<News> newsModel = newsModelAssembler.toModel(updatedNews);
        return ResponseEntity
                .created(newsModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(newsModel);
    }

    @DeleteMapping("/news/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        newsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
