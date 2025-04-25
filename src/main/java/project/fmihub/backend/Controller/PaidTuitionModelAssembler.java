package project.fmihub.backend.Controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import project.fmihub.backend.DTO.PaidTuitionDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaidTuitionModelAssembler implements RepresentationModelAssembler<PaidTuitionDTO, EntityModel<PaidTuitionDTO>> {

    @Override
    public EntityModel<PaidTuitionDTO> toModel(PaidTuitionDTO tuition) {

        return EntityModel.of(tuition, //
                linkTo(methodOn(PaidTuitionController.class).byPayer(tuition.getPayer())).withSelfRel());
    }
}