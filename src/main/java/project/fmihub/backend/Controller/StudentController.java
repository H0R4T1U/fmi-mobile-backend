package project.fmihub.backend.Controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.Domain.Student;
import project.fmihub.backend.Service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StudentController {
    private final StudentService service;
    private final StudentModelAssembler assembler;

    public StudentController(StudentService service, StudentModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    /**
     * Dragi prieteni, folositi cu grija asta ca daca nu exista mail in baza de date va incerca conexiune la un server pe care nu l aveti
     *
     * @param email email existent in baza de date
     * @return detaliile studentului cu contu respectiv, este o lista pt ca is mai multe conturi scs pe un mail uneori
     */
    @GetMapping("/api/students/{email}")
    CollectionModel<EntityModel<Student>> byEmail(@PathVariable String email) {
        List<EntityModel<Student>> students = service.findByEmail(email).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(students, linkTo(methodOn(StudentController.class).byEmail(email)).withSelfRel());
    }
}
