package project.fmihub.backend.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.fmihub.backend.Client.AttendanceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.fmihub.backend.Domain.Attendance;
import project.fmihub.backend.Domain.AttendanceId;
import project.fmihub.backend.Repository.AttendanceRepository;

import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);
    private final AttendanceClient client;
    @Autowired
    private final AttendanceRepository repo;
    public AttendanceService(AttendanceClient client, AttendanceRepository repo) {
        this.client = client;
        this.repo = repo;
        logger.info("AttendanceService initialized with AttendanceClient");
    }

    public Map<String, Map<String, List<Integer>>> printUniqueCourses(String uid) {
        logger.info("Fetching unique courses for user id: {}", uid);
        String url = "https://www.cs.ubbcluj.ro/apps/orar/api/user/classes/" +uid + "/ro-RO";
        logger.debug("Constructed URL: {}", url);
        return client.fetchCourseFrequenciesByType(url);
    }
    public List<Attendance> getAttendanceByEmailAndClassField(String email, String classField) {
        return repo.findByIdEmailAndIdClassField(email, classField);
    }

    public void updateAttendance(List<Attendance> attendances) {
        if (attendances.isEmpty()) {
            return;
        }

        Attendance newAttendance = attendances.getFirst();
        AttendanceId id = newAttendance.getId();
        String email = id.getEmail();
        String classField = id.getClassField();

        List<Attendance> existingAttendances = repo.findByIdEmailAndIdClassField(email, classField);

        for (Attendance att : attendances) {
            if (existingAttendances.contains(att)) {
                existingAttendances.stream()
                        .filter(a -> a.getId().equals(att.getId()))
                        .findFirst()
                        .ifPresent(existing -> {
                            existing.setAttendance(att.getAttendance());
                            repo.save(existing);
                        });

            } else {
                repo.save(att);
            }
        }
    }

}
