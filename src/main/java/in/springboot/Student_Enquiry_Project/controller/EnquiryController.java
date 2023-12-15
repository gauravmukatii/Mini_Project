package in.springboot.Student_Enquiry_Project.controller;

import in.springboot.Student_Enquiry_Project.binding.DashboardResponse;
import in.springboot.Student_Enquiry_Project.binding.EnquiryForm;
import in.springboot.Student_Enquiry_Project.binding.EnquirySearchCriteria;
import in.springboot.Student_Enquiry_Project.entity.StudentEnquiry;
import in.springboot.Student_Enquiry_Project.service.EnquiryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EnquiryController {

    @Autowired
    private HttpSession session;

    @Autowired
    private EnquiryService enqService;
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "index";
    }
    @GetMapping("/dashboard")
    public String dashboardPage(Model model){

        Integer userId = (Integer) session.getAttribute("userId");

        DashboardResponse dashboardData = enqService.getdashboardData(userId);

        model.addAttribute("dashboardData", dashboardData);

        return "dashboard";
    }

    @GetMapping("/enquiry")
    public String addEnquiry(Model model){

        // set data in model obj

        model.addAttribute("courseNames", enqService.getCourseNames());
        model.addAttribute("enqStatusNames", enqService.getEnquririesStatus());
        model.addAttribute("formObj", new EnquiryForm());

        return "add-enquiry";
    }

    @PostMapping("/addEnq")
    public String handleAddEnquiry(@ModelAttribute("formObj") EnquiryForm formObj, Model model){

        boolean status = enqService.upsertEnquiry(formObj);

        if(status){
            model.addAttribute("succMsg", "Student enquiry added Successfully!!");
        }else{
            model.addAttribute("errMsg", "Problem occured !! ");
        }

        model.addAttribute("addenquiry", new EnquiryForm());

        return "add-enquiry";
    }

    private void initForm(Model model){
        List<String> courses = enqService.getCourseNames();
        List<String> enqStatuses = enqService.getEnquririesStatus();

        EnquiryForm formObj = new EnquiryForm();

        model.addAttribute("courseNames", courses);
        model.addAttribute("statusNames", enqStatuses);
        model.addAttribute("formObj", formObj);

    }


    @GetMapping("/enquiries")
    public String viewEnquiries(Model model){
        initForm(model);
        List<StudentEnquiry> enquiries = enqService.getEnquiries();
        model.addAttribute("enquiries", enquiries);
        return "view-enquiries";
    }

    @GetMapping("/filterenq")
    public String getFilteredEnqs(@RequestParam("cname") String cname,
                                  @RequestParam("cstatus") String status,
                                  @RequestParam("mode") String mode,
                                  Model model){
        EnquirySearchCriteria criteria = new EnquirySearchCriteria();
        criteria.setCourseName(cname);
        criteria.setClassMode(mode);
        criteria.setEnqStatus(status);

        Integer userId = (Integer) session.getAttribute("userId");

        List<StudentEnquiry> filteredEnqs = enqService.getFilteredEnqs(criteria, userId);
        model.addAttribute("enquiries", filteredEnqs);

        return "filtered-table-page";
    }

}
