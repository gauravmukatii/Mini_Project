package in.springboot.Student_Enquiry_Project.service;

import in.springboot.Student_Enquiry_Project.binding.DashboardResponse;
import in.springboot.Student_Enquiry_Project.binding.EnquiryForm;
import in.springboot.Student_Enquiry_Project.binding.EnquirySearchCriteria;
import in.springboot.Student_Enquiry_Project.entity.StudentEnquiry;
import in.springboot.Student_Enquiry_Project.entity.UsersDtlsEntity;
import in.springboot.Student_Enquiry_Project.repos.UsersDtlsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private UsersDtlsRepo usersDtlsRepo;
    @Override
    public DashboardResponse getdashboardData(Integer userId) {

        Optional<UsersDtlsEntity> findById = usersDtlsRepo.findById(userId);

        DashboardResponse response = new DashboardResponse();

        if(findById.isPresent()){
            UsersDtlsEntity userEntity = findById.get();

            List<StudentEnquiry> enquiries = userEntity.getEnquiries();

            Integer totalCnt = enquiries.size();

            Integer lostCnt = enquiries.stream()
                    .filter(e -> e.getEnqStatus().equals("LOST"))
                    .collect(Collectors.toList()).size();
            Integer EnrolledCnt = enquiries.stream()
                    .filter(e -> e.getEnqStatus().equals("ENROLLED"))
                    .collect(Collectors.toList()).size();

            response.setEnrolledCnt(EnrolledCnt);
            response.setLostCnt(lostCnt);
            response.setTotalEnquiriesCnt(totalCnt);

        }

        return response;
    }

    @Override
    public List<EnquiryForm> getCourseName() {
        return null;
    }

    @Override
    public List<EnquiryForm> getEnquririesStatus() {
        return null;
    }

    @Override
    public String upsertEnquiry(EnquiryForm form) {
        return null;
    }

    @Override
    public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria) {
        return null;
    }

    @Override
    public EnquiryForm getEnquiry(Integer enqId) {
        return null;
    }
}
