package in.springboot.Student_Enquiry_Project.service;

import in.springboot.Student_Enquiry_Project.binding.DashboardResponse;
import in.springboot.Student_Enquiry_Project.binding.EnquiryForm;
import in.springboot.Student_Enquiry_Project.binding.EnquirySearchCriteria;

import java.util.List;

public interface EnquiryService {

    public DashboardResponse getdashboardData(Integer userId);
    public List<String> getCourseNames();

    public List<String> getEnquririesStatus();
    public boolean upsertEnquiry(EnquiryForm form);

    public List<EnquiryForm> getEnquiries(Integer userId, EnquirySearchCriteria criteria);

    public EnquiryForm getEnquiry(Integer enqId);
}
