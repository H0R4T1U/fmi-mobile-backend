package project.fmihub.backend.Controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import project.fmihub.backend.DTO.GradesDTO;
import project.fmihub.backend.DTO.TemporaryGradesDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TemporaryGradesModelAssembler implements RepresentationModelAssembler<TemporaryGradesDTO, EntityModel<TemporaryGradesDTO>> {
    @Override
    public EntityModel<TemporaryGradesDTO> toModel(TemporaryGradesDTO entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder.linkTo(methodOn(TemporaryGradesController.class).byStudent(entity.getStudent())).withSelfRel());
    }
}
