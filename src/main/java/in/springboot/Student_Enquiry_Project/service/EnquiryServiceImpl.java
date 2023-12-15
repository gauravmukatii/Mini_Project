package in.springboot.Student_Enquiry_Project.service;

import in.springboot.Student_Enquiry_Project.binding.DashboardResponse;
import in.springboot.Student_Enquiry_Project.binding.EnquiryForm;
import in.springboot.Student_Enquiry_Project.binding.EnquirySearchCriteria;
import in.springboot.Student_Enquiry_Project.entity.CourseEntity;
import in.springboot.Student_Enquiry_Project.entity.EnqStatusEntity;
import in.springboot.Student_Enquiry_Project.entity.StudentEnquiry;
import in.springboot.Student_Enquiry_Project.entity.UsersDtlsEntity;
import in.springboot.Student_Enquiry_Project.repos.CoursesRepository;
import in.springboot.Student_Enquiry_Project.repos.EnqStatusRepo;
import in.springboot.Student_Enquiry_Project.repos.StudentEnquiryRepo;
import in.springboot.Student_Enquiry_Project.repos.UsersDtlsRepo;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private StudentEnquiryRepo studentEnquiryRepo;
    @Autowired
    private HttpSession session;

    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private UsersDtlsRepo usersDtlsRepo;

    @Autowired
    private EnqStatusRepo enqStatusRepo;
    @Override
    public DashboardResponse getdashboardData(Integer userId) {

        Optional<UsersDtlsEntity> findById = usersDtlsRepo.findById(userId);

        DashboardResponse response = new DashboardResponse();

        if(findById.isPresent()){
            UsersDtlsEntity userEntity = findById.get();

            List<StudentEnquiry> enquiries = userEntity.getEnquiries();

            Integer totalCnt = enquiries.size();

            Integer lostCnt = enquiries.stream()
                    .filter(e -> e.getEnqStatus().equals("Lost"))
                    .collect(Collectors.toList()).size();
            Integer EnrolledCnt = enquiries.stream()
                    .filter(e -> e.getEnqStatus().equals("Enrolled"))
                    .collect(Collectors.toList()).size();

            response.setEnrolledCnt(EnrolledCnt);
            response.setLostCnt(lostCnt);
            response.setTotalEnquiriesCnt(totalCnt);

        }

        return response;
    }

    @Override
    public List<String> getCourseNames() {
        List<CourseEntity> findAll = coursesRepository.findAll();

        List<String> names = new ArrayList<>();

        for (CourseEntity entity : findAll){
               names.add(entity.getCourseName());
        }
        return names;
    }

    @Override
    public List<String> getEnquririesStatus() {
        List<EnqStatusEntity> findAll = enqStatusRepo.findAll();

        List<String> names = new ArrayList<>();

        for (EnqStatusEntity entity : findAll){
            names.add(entity.getStatusName());
        }
        return names;
    }

    @Override
    public boolean upsertEnquiry(EnquiryForm form) {

        StudentEnquiry enqEntity = new StudentEnquiry();
        BeanUtils.copyProperties(form, enqEntity);

        Integer userId = (Integer) session.getAttribute("userId");

        UsersDtlsEntity usersEntity = usersDtlsRepo.findById(userId).get();
        enqEntity.setUser(usersEntity);

        studentEnquiryRepo.save(enqEntity);
        return true;
    }

    @Override
    public List<StudentEnquiry> getEnquiries() {

        Integer userId = (Integer) session.getAttribute("userId");
        Optional<UsersDtlsEntity> findById = usersDtlsRepo.findById(userId);
        if(findById.isPresent()){
            UsersDtlsEntity usersDtlsEntity = findById.get();
            List<StudentEnquiry> enquiries = usersDtlsEntity.getEnquiries();
            return enquiries;
        }
        return null;
    }

    @Override
    public List<StudentEnquiry> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {
        Optional<UsersDtlsEntity> findById = usersDtlsRepo.findById(userId);
        if(findById.isPresent()){
            UsersDtlsEntity usersDtlsEntity = findById.get();
            List<StudentEnquiry> enquiries = usersDtlsEntity.getEnquiries();

            if(null!=criteria.getCourseName() && !"".equals(criteria.getCourseName())){
               enquiries =  enquiries.stream()
                                    .filter(e -> e.getCourseName()
                                    .equals(criteria.getCourseName()))
                                    .collect(Collectors.toList());
            }

            if(null!=criteria.getEnqStatus() && !"".equals(criteria.getEnqStatus())){
                enquiries =  enquiries.stream()
                                    .filter(e -> e.getEnqStatus()
                                    .equals(criteria.getEnqStatus()))
                                    .collect(Collectors.toList());
            }

            if(null!=criteria.getClassMode() && !"".equals(criteria.getClassMode())){
                enquiries =  enquiries.stream()
                        .filter(e -> e.getClassMode().equals(criteria.getClassMode()))
                        .collect(Collectors.toList());
            }
            return enquiries;
        }
        return null;
    }
}
