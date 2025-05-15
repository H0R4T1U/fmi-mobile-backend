package project.fmihub.backend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassScheduleDTO {
    private String courseInstanceName;
}
