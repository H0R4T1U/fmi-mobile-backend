package project.fmihub.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.fmihub.backend.Service.AttendanceService;

import java.util.Set;

@Controller
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Set<String>> getCourses(@AuthenticationPrincipal Jwt jwt) {
        String oid = jwt.getClaim("oid");
        String tid = jwt.getClaim("tid");
        String uid = oid + "." + tid;

        Set<String> courses = attendanceService.printUniqueCourses(uid);

        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(courses); // 200 OK with body
    }
}
