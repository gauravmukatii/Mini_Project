package in.springboot.Student_Enquiry_Project.service;

import in.springboot.Student_Enquiry_Project.binding.DashboardResponse;
import in.springboot.Student_Enquiry_Project.binding.EnquiryForm;
import in.springboot.Student_Enquiry_Project.binding.EnquirySearchCriteria;

import java.util.List;

public interface EnquiryService {

    public DashboardResponse getdashboardData(Integer userId);
    public List<EnquiryForm> getCourseName();
    public List<EnquiryForm> getEnquririesStatus();
    public String upsertEnquiry(EnquiryForm form);

    public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria);

    public EnquiryForm getEnquiry(Integer enqId);
}
