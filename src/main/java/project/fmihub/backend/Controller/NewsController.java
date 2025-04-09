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

    @GetMapping("/news")
    CollectionModel<EntityModel<News>> all(){
        List<EntityModel<News>> news = newsRepository.findAll().stream()
                .map(newsModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(news, linkTo(methodOn(NewsController.class).all()).withSelfRel());
    }

    @GetMapping("/news/{id}")
    EntityModel<News> one(@PathVariable Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("News not found"));
        return newsModelAssembler.toModel(news);
    }

    @PostMapping("/news")
    ResponseEntity<?> add(@RequestBody News news) {
        EntityModel<News> newsModel = newsModelAssembler.toModel(newsRepository.save(news));
        return ResponseEntity
                .created(newsModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(newsModel);
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
