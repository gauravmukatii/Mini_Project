package in.springboot.Student_Enquiry_Project.service;

import in.springboot.Student_Enquiry_Project.binding.LoginForm;
import in.springboot.Student_Enquiry_Project.binding.SignUpForm;
import in.springboot.Student_Enquiry_Project.binding.UnlockForm;
import in.springboot.Student_Enquiry_Project.entity.UsersDtlsEntity;
import in.springboot.Student_Enquiry_Project.repos.UsersDtlsRepo;
import in.springboot.Student_Enquiry_Project.utils.EmailUtils;
import in.springboot.Student_Enquiry_Project.utils.PwdUtils;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersDtlsRepo usersDtlsRepo;
    @Autowired
    private EmailUtils emailUtils;
    @Override
    public String login(LoginForm form) {
        return null;
    }

    @Override
    public boolean signup(SignUpForm form) {

        UsersDtlsEntity user = usersDtlsRepo.findByEmail(form.getEmail());

        if(user != null){
            return false;
        }

        //TODO: Copy data from binding obj to entity obj
        UsersDtlsEntity entity = new UsersDtlsEntity();
        BeanUtils.copyProperties(form, entity);

        //TODO: generate random pwd and set to object
        String tempPwd = PwdUtils.generateRandomPwd();
        entity.setPwd(tempPwd);

        //TODO: Set account status as LOCKED
        entity.setAccStatus("LOCKED");

        //TODO: Insert record
        usersDtlsRepo.save(entity);

        //TODO: Send email to unlock the account
        String to = form.getEmail();
        String subject = "Unlock Your Account | Ashok IT";
        StringBuffer body = new StringBuffer("");
        body.append("<h1>Use below temporary pwd to unlock your account</h1>");
        body.append("Temporary Password : " + tempPwd);
        body.append("<br/>");
        body.append("<a href=\"http://localhost:7070/unlock?email=" + to + "\">Click here to unlock your account!!</a>");

        emailUtils.sendEmail(to, subject, body.toString()) ;

        return true;
    }

    @Override
    public String unlock(UnlockForm form) {
        return null;
    }

    @Override
    public String forgetpwd(String email) {
        return null;
    }
}
