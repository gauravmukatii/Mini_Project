package in.springboot.Student_Enquiry_Project.runner;

import in.springboot.Student_Enquiry_Project.entity.CourseEntity;
import in.springboot.Student_Enquiry_Project.entity.EnqStatusEntity;
import in.springboot.Student_Enquiry_Project.repos.CoursesRepository;
import in.springboot.Student_Enquiry_Project.repos.EnqStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private EnqStatusRepo enqStatusRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        coursesRepository.deleteAll();
        CourseEntity c1 = new CourseEntity();
        c1.setCourseName("Java");
        CourseEntity c2 = new CourseEntity();
        c2.setCourseName("DevOps");
        CourseEntity c3 = new CourseEntity();
        c3.setCourseName("Angular");

        enqStatusRepo.deleteAll();
        EnqStatusEntity e1 = new EnqStatusEntity();
        e1.setStatusName("New");
        EnqStatusEntity e2 = new EnqStatusEntity();
        e2.setStatusName("Enrolled");
        EnqStatusEntity e3 = new EnqStatusEntity();
        e3.setStatusName("Lost");

        enqStatusRepo.saveAll(Arrays.asList(e1, e2, e3));
        coursesRepository.saveAll(Arrays.asList(c1, c2, c3));


    }
}
