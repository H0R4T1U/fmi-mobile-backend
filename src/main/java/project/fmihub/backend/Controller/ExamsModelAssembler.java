package project.fmihub.backend.Controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import project.fmihub.backend.DTO.ExamsDTO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ExamsModelAssembler implements RepresentationModelAssembler<ExamsDTO, EntityModel<ExamsDTO>> {
    @Override
    public EntityModel<ExamsDTO> toModel(ExamsDTO entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(ExamsController.class).byStudent(entity.getStudent())).withSelfRel());
    }
}
