package in.springboot.Student_Enquiry_Project.repos;

import in.springboot.Student_Enquiry_Project.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<CourseEntity, Integer> {
}
