package project.fmihub.backend.Controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import project.fmihub.backend.Domain.News;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class NewsModelAssembler implements RepresentationModelAssembler<News, EntityModel<News>> {
    @Override
    public EntityModel<News> toModel(News entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(NewsController.class).one(entity.getId())).withSelfRel(),
                linkTo(methodOn(NewsController.class).all(entity.getLanguage())).withRel("news"));
    }
}
