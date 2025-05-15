package project.fmihub.backend.Client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import project.fmihub.backend.DTO.ClassScheduleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AttendanceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AttendanceClient() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Set<String> fetchUniqueCourseNames(String url) {
        try {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            List<ClassScheduleDTO> schedules = objectMapper.readValue(
                    jsonResponse, new TypeReference<>() {
                    }
            );

            return schedules.stream()
                    .map(ClassScheduleDTO::getCourseInstanceName)
                    .filter(name -> name != null && !name.isBlank())
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse course data", e);
        }
    }
}
