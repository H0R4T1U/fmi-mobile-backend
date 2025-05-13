package project.fmihub.backend.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.GradesDTO;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Service.GradesService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GradesController {
    private final GradesService gradesService;

    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("/api/grades")
    public List<GradesDTO> byStudent(@AuthenticationPrincipal Jwt jwt) {

        String student = jwt.getClaim("upn");
        return gradesService.findByIdStudent(student).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    private GradesDTO toDTO(Grades grade) {
        GradesDTO dto = new GradesDTO();
        dto.setStudent(grade.getId().getStudent());
        dto.setNumber(grade.getId().getNumber());
        dto.setYear(grade.getYear());
        dto.setGrade(grade.getGrade());
        dto.setCourse_code(grade.getCourseCode());
        dto.setCourse(grade.getCourse());
        dto.setSemester(grade.getSemester());
        dto.setCredits(grade.getCredits());
        dto.setDate(grade.getDate());
        return dto;
    }
}
