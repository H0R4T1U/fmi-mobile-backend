package project.fmihub.backend.Client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import project.fmihub.backend.Domain.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentRestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    public List<Student> fetchStudentsFromApi(String url) {
        String jsonResponse = restTemplate.getForObject(url, String.class);
        List<Student> students = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode studentList = root.path("_embedded").path("studentList");

            if (studentList.isArray()) {
                for (JsonNode node : studentList) {
                    String email = (node.path("id").path("email").asText());
                    String username = (node.path("id").path("username").asText());
                    String group = (node.path("id").path("group").asText());
                    String password = (node.path("password").asText());
                    String lastname = (node.path("lastname").asText());
                    String firstname = (node.path("firstname").asText());
                    Student s = new Student(firstname, lastname, email, username, password, 0, "0", group, "0");
                    students.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }
}
