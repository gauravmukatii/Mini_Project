package in.springboot.Student_Enquiry_Project.controller;

import in.springboot.Student_Enquiry_Project.binding.SignUpForm;
import in.springboot.Student_Enquiry_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signUpPage(Model model){
        model.addAttribute("user", new SignUpForm());
        return "signup";
    }
    @PostMapping("/signup")
    public String handleSignup(@ModelAttribute("user") SignUpForm form, Model model){
        boolean status = userService.signup(form);

        if(status){
            model.addAttribute("succMsg", "Temp Password sent Check Your Email");
        }else{
            model.addAttribute("errMsg", "User already registered with same Email id");
        }

        return "signup";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }



    @GetMapping("/forget")
    public String forgetPwdPage(){
        return "forgetpwd";
    }

    @GetMapping("/unlock.html")
    public String unlockPage(){
        return "unlock.html";
    }
}
