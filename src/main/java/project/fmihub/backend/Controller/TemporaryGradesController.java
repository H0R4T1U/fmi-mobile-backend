package project.fmihub.backend.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.GradesDTO;
import project.fmihub.backend.DTO.TemporaryGradesDTO;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Domain.TemporaryGrades;
import project.fmihub.backend.Service.GradesService;
import project.fmihub.backend.Service.TemporaryGradesService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TemporaryGradesController {
    private final TemporaryGradesService gradesService;
    private final TemporaryGradesModelAssembler gradesModelAssembler;

    public TemporaryGradesController(TemporaryGradesService gradesService, TemporaryGradesModelAssembler gradesModelAssembler) {
        this.gradesService = gradesService;
        this.gradesModelAssembler = gradesModelAssembler;
    }

    @GetMapping("/api/temporary_grades/{student}")
    CollectionModel<EntityModel<TemporaryGradesDTO>> byStudent(@PathVariable String student) {
        List<EntityModel<TemporaryGradesDTO>> grades = gradesService.findByIdStudent(student).stream()
                .map(this::toDTO)
                .map(gradesModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(grades, linkTo(methodOn(TemporaryGradesController.class).byStudent(student)).withSelfRel());
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
