package in.springboot.Student_Enquiry_Project.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PwdUtils {

    public static String generateRandomPwd(){
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")).toCharArray();
        String pwd = RandomStringUtils.random(6, possibleCharacters);
        return pwd;
    }
}
