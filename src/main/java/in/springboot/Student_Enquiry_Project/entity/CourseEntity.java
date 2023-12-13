package in.springboot.Student_Enquiry_Project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="AIT_COURSES")
public class CourseEntity {

    @Id
    @GeneratedValue
    private Integer courseId;
    private String courseName;


}
