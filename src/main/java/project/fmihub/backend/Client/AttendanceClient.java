package project.fmihub.backend.Client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import project.fmihub.backend.DTO.ClassScheduleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Component
public class AttendanceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AttendanceClient() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Map<String, List<Integer>>> fetchCourseFrequenciesByType(String url) {
        try {
            String jsonResponse = restTemplate.getForObject(url, String.class);
            List<ClassScheduleDTO> schedules = objectMapper.readValue(
                    jsonResponse, new TypeReference<>() {}
            );

            // Step 1: Group course names → class types → frequencies
            Map<String, Map<String, List<Integer>>> result = schedules.stream()
                    .filter(s -> s.getCourseInstanceName() != null && s.getClassType() != null)
                    .collect(Collectors.groupingBy(
                            ClassScheduleDTO::getCourseInstanceName,
                            Collectors.groupingBy(
                                    ClassScheduleDTO::getClassType,
                                    Collectors.mapping(
                                            ClassScheduleDTO::getFrequency,
                                            Collectors.toList()
                                    )
                            )
                    ));

            // Step 2: Add -1 for missing Seminar type
            for (Map<String, List<Integer>> typeMap : result.values()) {
                typeMap.putIfAbsent("Seminar", List.of(-1));
            }

            return result;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse course data", e);
        }
    }

}
