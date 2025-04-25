package project.fmihub.backend.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.PaidTuitionDTO;
import project.fmihub.backend.Domain.PaidTuition;
import project.fmihub.backend.Service.PaidTuitionService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PaidTuitionController {

    private final PaidTuitionService service;
    private final PaidTuitionModelAssembler assembler;

    public PaidTuitionController(PaidTuitionService paidTuitionService,PaidTuitionModelAssembler assembler) {
        this.assembler = assembler;
        this.service = paidTuitionService;
    }

    @GetMapping("/api/paid-tuitions/{payer}")
    CollectionModel<EntityModel<PaidTuitionDTO>> byPayer(@PathVariable String payer) {
        List<EntityModel<PaidTuitionDTO>> tuitions = service.findByIdPayer(payer).stream()
                .map(this::toDTO)
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(tuitions, linkTo(methodOn(TuitionController.class).byPayer(payer)).withSelfRel());
    }

    private PaidTuitionDTO toDTO(PaidTuition paidTuition) {
        PaidTuitionDTO dto = new PaidTuitionDTO();
        dto.setPayer(paidTuition.getId().getPayer());
        dto.setNumber(paidTuition.getId().getNumber());
        dto.setSeries(paidTuition.getSeries());
        dto.setPaymentNumber(paidTuition.getPaymentNumber());
        dto.setDate(paidTuition.getDate());
        dto.setPrice(paidTuition.getPrice());
        dto.setDescription(paidTuition.getDescription());
        dto.setMessage(paidTuition.getMessage());
        return dto;
    }
}