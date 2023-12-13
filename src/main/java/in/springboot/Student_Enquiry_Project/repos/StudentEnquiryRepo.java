package in.springboot.Student_Enquiry_Project.repos;

import in.springboot.Student_Enquiry_Project.entity.StudentEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEnquiryRepo extends JpaRepository<StudentEnquiry, Integer> {
}
