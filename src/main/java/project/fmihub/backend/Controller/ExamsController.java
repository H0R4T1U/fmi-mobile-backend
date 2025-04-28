package project.fmihub.backend.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.ExamsDTO;
import project.fmihub.backend.Domain.Exams;
import project.fmihub.backend.Service.ExamsService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExamsController {
    private final ExamsService examService;
    private final ExamsModelAssembler examModelAssembler;

    public ExamsController(ExamsService examService, ExamsModelAssembler examModelAssembler) {
        this.examService = examService;
        this.examModelAssembler = examModelAssembler;
    }

    @GetMapping("/api/exams/{student}")
    CollectionModel<EntityModel<ExamsDTO>> byStudent(@PathVariable String student) {
        List<EntityModel<ExamsDTO>> exams = examService.findByIdStudent(student).stream()
                .map(this::toDTO)
                .map(examModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(exams, linkTo(methodOn(ExamsController.class).byStudent(student)).withSelfRel());
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
        return examDTO;
    }
}
