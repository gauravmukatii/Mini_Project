package in.springboot.Student_Enquiry_Project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "AIT_STUDENT_ENQUIRIES")
public class  StudentEnquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enqId;
    private String studentName;
    private Long studentPhno;
    private String classMode;
    private String courseName;
    private String enqStatus;
    private LocalDate datecreated;
    private LocalDate lastupdated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersDtlsEntity user;

}
