package project.fmihub.backend.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.PaidTuitionDTO;
import project.fmihub.backend.Domain.PaidTuition;
import project.fmihub.backend.Service.PaidTuitionService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PaidTuitionController {

    private final PaidTuitionService service;

    public PaidTuitionController(PaidTuitionService paidTuitionServic) {
        this.service = paidTuitionServic;
    }

    @GetMapping("/api/paid-tuitions")
    public List<PaidTuitionDTO> byPayer(@AuthenticationPrincipal Jwt jwt) {

        String payer = jwt.getClaim("upn");
        return  service.findByIdPayer(payer).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

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