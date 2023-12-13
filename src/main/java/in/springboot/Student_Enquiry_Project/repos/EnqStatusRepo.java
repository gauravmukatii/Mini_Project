package in.springboot.Student_Enquiry_Project.repos;

import in.springboot.Student_Enquiry_Project.entity.EnqStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity ,Integer> {
}
