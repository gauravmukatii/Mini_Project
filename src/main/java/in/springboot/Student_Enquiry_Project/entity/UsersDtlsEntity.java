package in.springboot.Student_Enquiry_Project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "AIT_USER_DTLS")
public class UsersDtlsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private String pwd;
    private String phno;
    private String accStatus;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<StudentEnquiry> enquiries;
}
