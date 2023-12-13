package in.springboot.Student_Enquiry_Project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "AIT_ENQUIRY_STATUS")
public class EnqStatusEntity {

    @Id
    @GeneratedValue
    private Integer statusId;
    private String statusName;
}
