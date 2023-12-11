package in.springboot.Student_Enquiry_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String IndexPage(){
        return "index";
    }
}
