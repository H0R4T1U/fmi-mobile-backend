package project.fmihub.backend.Controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.fmihub.backend.Domain.Student;
import project.fmihub.backend.Service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/api/students")
    public List<Student> byEmail(@AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getClaim("upn");
        return new ArrayList<>(service.findByEmail(email));

    }
}
