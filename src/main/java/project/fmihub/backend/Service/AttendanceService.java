package project.fmihub.backend.Service;


import org.springframework.stereotype.Service;
import project.fmihub.backend.Client.AttendanceClient;

import java.util.Set;

@Service
public class AttendanceService {
    private final AttendanceClient client;

    public AttendanceService(AttendanceClient client) {
        this.client = client;
    }

    public Set<String> printUniqueCourses(String uid) {
        String url = "https://www.cs.ubbcluj.ro/apps/orar/api/user/classes/" +uid + "/ro-RO";
        return client.fetchUniqueCourseNames(url);
    }
}
