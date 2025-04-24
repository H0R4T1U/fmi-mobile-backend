package project.fmihub.backend.Controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;
import project.fmihub.backend.DTO.GradesDTO;

@Component
public class GradesModelAssembler implements RepresentationModelAssembler<GradesDTO, EntityModel<GradesDTO>> {

    @Override
    public EntityModel<GradesDTO> toModel(GradesDTO entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(GradesController.class).byStudent(entity.getStudent())).withSelfRel());
    }
}
