package in.springboot.Student_Enquiry_Project.controller;

import in.springboot.Student_Enquiry_Project.binding.LoginForm;
import in.springboot.Student_Enquiry_Project.binding.SignUpForm;
import in.springboot.Student_Enquiry_Project.binding.UnlockForm;
import in.springboot.Student_Enquiry_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/unlock")
    public String unlockPage(@RequestParam String email, Model model){
        UnlockForm unlockFormObj = new UnlockForm();
        unlockFormObj.setEmail(email);

        model.addAttribute("unlock",unlockFormObj);

        return "unlock";
    }

    @PostMapping("/unlock")
    public String handleUnlock(@ModelAttribute("unlock") UnlockForm form, Model model){
        System.out.println(form);

        if(form.getNewPwd().equals(form.getConfirmPwd())){
            boolean status = userService.unlockAccount(form);

            if(status){
                model.addAttribute("succMsg", "Your account unlocked successfully");
            }else{
                model.addAttribute("errMsg", "Given TEmperory password is wrong check your email");
            }
        }else{
            model.addAttribute("errMsg", "New password and confirm password should be same!!");
        }

        return "unlock";
    }

    @GetMapping("/login")
    public String loginPage(Model model){

        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
    @PostMapping("/login")
    public String handleLoginPage(LoginForm form, Model model){
        String status = userService.login(form);

        if(status.contains("success")){
            //display dashboard
            return "redirect:/dashboard";
        }

        model.addAttribute("errMsg", status);

        return "login";
    }

    @GetMapping("/forget")
    public String forgetPwdPage(){
        return "forgetpwd";
    }

    @PostMapping("/forget")
    public String forgetPwd(@RequestParam("email") String email, Model model){

        boolean status = userService.forgetpwd(email);

        if(status){
            model.addAttribute("succMsg", "Password sent successfully on your Email");
        }else{
            model.addAttribute("errMsg", "Email Id not found/Incorrect");

        }

        return "forgetpwd";
    }


}
