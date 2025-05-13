package project.fmihub.backend.Controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.TuitionDTO;
import project.fmihub.backend.Domain.Tuition;
import project.fmihub.backend.Service.TuitionService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TuitionController {
    private final TuitionService service;

    public TuitionController(TuitionService service) {
        this.service = service;
    }

    @GetMapping("/api/tuitions")
    public List<TuitionDTO> byPayer(@AuthenticationPrincipal Jwt jwt) {

        String payer = jwt.getClaim("upn");
        return service.findByIdPayer(payer).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
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
