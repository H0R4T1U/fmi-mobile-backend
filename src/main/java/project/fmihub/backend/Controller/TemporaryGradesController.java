package project.fmihub.backend.Controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.TemporaryGradesDTO;
import project.fmihub.backend.Domain.TemporaryGrades;
import project.fmihub.backend.Service.TemporaryGradesService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TemporaryGradesController {
    private final TemporaryGradesService gradesService;

    public TemporaryGradesController(TemporaryGradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("/api/temporary-grades")
    public List<TemporaryGradesDTO> byStudent(@AuthenticationPrincipal Jwt jwt) {

        String student = jwt.getClaim("upn");
        return gradesService.findByIdStudent(student).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }
    private TemporaryGradesDTO toDTO(TemporaryGrades grade) {
        TemporaryGradesDTO dto = new TemporaryGradesDTO();
        dto.setStudent(grade.getTemporaryGradesId().getStudent());
        dto.setNumber(grade.getTemporaryGradesId().getNumber());
        dto.setGrade(grade.getGrade());
        dto.setCourse_code(grade.getCourseCode());
        dto.setCourse(grade.getCourse());
        dto.setSemester(grade.getSemester());
        dto.setDate(grade.getDate());
        dto.setRetake_grade(grade.getRetakeGrade());
        dto.setRetake_date(grade.getRetakeDate());
        return dto;
    }
}
