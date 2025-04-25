package project.fmihub.backend.Domain;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_gen")
    @SequenceGenerator(name = "student_id_gen", sequenceName = "student_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "username", nullable = false, length = 11)
    private String username;

    @Column(name = "password", length = 16)
    private String password;

    @Column(name = "number")
    private Integer number;

    @Column(name = "code", length = 11)
    private String code;

    @Column(name = "\"group\"", nullable = false, length = 13)
    private String group;

    @Column(name = "branch", nullable = false, length = 15)
    private String branch;

    public Student(String lastName, String firstName, String email, String username, String password, Integer number, String code, String group, String branch) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.number = number;
        this.code = code;
        this.group = group;
        this.branch = branch;
    }
    public Student() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

}