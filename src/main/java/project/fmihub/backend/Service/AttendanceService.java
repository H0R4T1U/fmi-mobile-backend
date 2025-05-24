package project.fmihub.backend.Service;


import org.springframework.stereotype.Service;
import project.fmihub.backend.Client.AttendanceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Service
public class AttendanceService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);
    private final AttendanceClient client;

    public AttendanceService(AttendanceClient client) {
        this.client = client;
        logger.info("AttendanceService initialized with AttendanceClient");
    }

    public Set<String> printUniqueCourses(String uid) {
        logger.info("Fetching unique courses for user id: {}", uid);
        String url = "https://www.cs.ubbcluj.ro/apps/orar/api/user/classes/" +uid + "/ro-RO";
        logger.debug("Constructed URL: {}", url);
        return client.fetchUniqueCourseNames(url);
    }
}
