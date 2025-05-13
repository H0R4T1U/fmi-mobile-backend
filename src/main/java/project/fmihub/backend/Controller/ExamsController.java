package project.fmihub.backend.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.ExamsDTO;
import project.fmihub.backend.Domain.Exams;
import project.fmihub.backend.Service.ExamsService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExamsController {
    private final ExamsService examService;

    public ExamsController(ExamsService examService) {
        this.examService = examService;
    }

    @GetMapping("/api/exams")
    public List<ExamsDTO> byStudent(@AuthenticationPrincipal Jwt jwt) {

        String student = jwt.getClaim("upn");
        return examService.findByIdStudent(student).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }

    private ExamsDTO toDTO(Exams exam) {
        ExamsDTO examDTO = new ExamsDTO();
        examDTO.setStudent(exam.getId().getStudent());
        examDTO.setNumber(exam.getId().getNumber());
        examDTO.setSessionType(exam.getSessionType());
        examDTO.setDateType(exam.getDateType());
        examDTO.setType(exam.getType());
        examDTO.setDate(exam.getDate());
        examDTO.setDuration(exam.getDuration());
        examDTO.setRoom(exam.getRoom());
        examDTO.setTeacher(exam.getTeacher());
        examDTO.setCourse(exam.getCourse());
        examDTO.setGroup(exam.getGroup());
        examDTO.setSemester(exam.getSemester());
        return examDTO;
    }
}
