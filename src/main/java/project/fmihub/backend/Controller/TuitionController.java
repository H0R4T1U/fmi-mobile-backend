package project.fmihub.backend.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.TuitionDTO;
import project.fmihub.backend.Domain.Tuition;
import project.fmihub.backend.Service.TuitionService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TuitionController {
    private final TuitionService service;
    private final TuitionModelAssembler assembler;

    public TuitionController(TuitionService service, TuitionModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/api/tuitions/{payer}")
    CollectionModel<EntityModel<TuitionDTO>> byPayer(@PathVariable String payer) {
        List<EntityModel<TuitionDTO>> tuitions = service.findByIdPayer(payer).stream()
                .map(this::toDTO)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(tuitions, linkTo(methodOn(TuitionController.class).byPayer(payer)).withSelfRel());
    }

    private TuitionDTO toDTO(Tuition tuition) {
        TuitionDTO dto = new TuitionDTO();
        dto.setPayer(tuition.getId().getPayer());
        dto.setNumber(tuition.getId().getNumber());
        dto.setType(tuition.getType());
        dto.setDescription(tuition.getDescription());
        dto.setYear(tuition.getYear());
        dto.setPrice(tuition.getPrice());
        dto.setPaymentTerm(tuition.getPaymentTerm());
        return dto;
    }
}
