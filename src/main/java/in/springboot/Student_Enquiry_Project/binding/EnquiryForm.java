package in.springboot.Student_Enquiry_Project.binding;

import lombok.Data;

@Data
public class EnquiryForm {

    private String studentName;
    private Long studentPhno;
    private String classMode;
    private String courseName;
    private String enqStatus;

}
