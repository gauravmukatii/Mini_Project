package in.springboot.Student_Enquiry_Project.repos;

import in.springboot.Student_Enquiry_Project.entity.UsersDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDtlsRepo extends JpaRepository<UsersDtlsEntity, Integer> {

    public UsersDtlsEntity findByEmail(String email);
}
