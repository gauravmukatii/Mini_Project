package in.springboot.Student_Enquiry_Project.service;

import in.springboot.Student_Enquiry_Project.binding.LoginForm;
import in.springboot.Student_Enquiry_Project.binding.SignUpForm;
import in.springboot.Student_Enquiry_Project.binding.UnlockForm;

public interface UserService {

    public String login(LoginForm form);

    public boolean signup(SignUpForm form);

    public boolean unlockAccount(UnlockForm form);

    public boolean forgetpwd(String email);
}
