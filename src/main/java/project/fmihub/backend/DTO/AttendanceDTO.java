package project.fmihub.backend.DTO;

import java.util.List;
import java.util.Map;

public class AttendanceDTO {
    private String email;
    private String classField;
    private String type;
    private Integer position;
    private Boolean attendance;
    private Map<String, Map<String, List<Integer>>> courseFrequencies;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassField() {
        return classField;
    }

    public void setClassField(String classField) {
        this.classField = classField;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public Map<String, Map<String, List<Integer>>> getCourseFrequencies() {
        return courseFrequencies;
    }

    public void setCourseFrequencies(Map<String, Map<String, List<Integer>>> courseFrequencies) {
        this.courseFrequencies = courseFrequencies;
    }
}