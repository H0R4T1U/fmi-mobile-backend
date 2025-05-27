package project.fmihub.backend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.fmihub.backend.DTO.AttendanceDTO;
import project.fmihub.backend.Domain.Attendance;
import project.fmihub.backend.Domain.AttendanceId;
import project.fmihub.backend.Service.AttendanceService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/courses")
    public ResponseEntity<Map<String, Map<String, List<Integer>>>> getCourses(@AuthenticationPrincipal Jwt jwt) {
        String oid = jwt.getClaim("oid");
        String tid = jwt.getClaim("tid");
        String uid = oid + "." + tid;

        Map<String, Map<String, List<Integer>>> courses = attendanceService.printUniqueCourses(uid);

        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(courses); // 200 OK with body
    }
    @GetMapping("/attendance/{classField}")
    public ResponseEntity<List<AttendanceDTO>> getAttendance(@AuthenticationPrincipal Jwt jwt,
                                                             @PathVariable String classField) {
        String email = jwt.getClaim("upn");

        List<AttendanceDTO> attendanceList = attendanceService.getAttendanceByEmailAndClassField(email, classField)
                .stream()
                .map(this::toDTO)
                .toList();

        if (attendanceList.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(attendanceList); // 200 OK with body
    }
    @PutMapping("/attendance/{classField}")
    public ResponseEntity<Void> updateAttendance(@AuthenticationPrincipal Jwt jwt,
                                                          @PathVariable String classField,
                                                          List<AttendanceDTO> attendanceDTOs) {


        List<Attendance> attendances = attendanceDTOs.stream()
                .map(this::fromDTO)
                .toList();
        attendanceService.updateAttendance(attendances);
        return ResponseEntity.noContent().build();
    }
    private AttendanceDTO toDTO(Attendance attendance) {
        AttendanceDTO attendanceDTO = new AttendanceDTO();
        attendanceDTO.setEmail(attendance.getId().getEmail());
        attendanceDTO.setClassField(attendance.getId().getClassField());
        attendanceDTO.setType(attendance.getId().getType());
        attendanceDTO.setPosition(attendance.getId().getPosition());
        attendanceDTO.setAttendance(attendance.getAttendance());
        return attendanceDTO;
    }

    private Attendance fromDTO(AttendanceDTO attendanceDTO) {
        Attendance attendance = new Attendance();
        AttendanceId attendanceId = new AttendanceId();
        attendanceId.setEmail(attendanceDTO.getEmail());
        attendanceId.setClassField(attendanceDTO.getClassField());
        attendanceId.setType(attendanceDTO.getType());
        attendanceId.setPosition(attendanceDTO.getPosition());
        attendance.setId(attendanceId);
        attendance.setAttendance(attendanceDTO.getAttendance());
        return attendance;
    }
}
