package project.fmihub.backend.Controller;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import project.fmihub.backend.DTO.TuitionDTO;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class TuitionModelAssembler implements RepresentationModelAssembler<TuitionDTO, EntityModel<TuitionDTO>> {

    @Override
    public EntityModel<TuitionDTO> toModel(TuitionDTO tuition) {

        return EntityModel.of(tuition, //
                WebMvcLinkBuilder.linkTo(methodOn(TuitionController.class).byPayer(tuition.getPayer())).withSelfRel());
    }
}