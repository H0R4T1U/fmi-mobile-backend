package project.fmihub.backend.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.DTO.GradesDTO;
import project.fmihub.backend.Domain.Grades;
import project.fmihub.backend.Service.GradesService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GradesController {
    private final GradesService gradesService;
    private final GradesModelAssembler gradesModelAssembler;

    public GradesController(GradesService gradesService, GradesModelAssembler gradesModelAssembler) {
        this.gradesService = gradesService;
        this.gradesModelAssembler = gradesModelAssembler;
    }

    @GetMapping("/api/grades/{student}")
    CollectionModel<EntityModel<GradesDTO>> byStudent(@PathVariable String student) {
        List<EntityModel<GradesDTO>> grades = gradesService.findByIdStudent(student).stream()
                .map(this::toDTO)
                .map(gradesModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(grades, linkTo(methodOn(GradesController.class).byStudent(student)).withSelfRel());
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
        dto.setDate(grade.getDate());
        return dto;
    }
}
