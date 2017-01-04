package Login.service.mailUser;

import Login.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by karol on 02.01.2017.
 */
@Service
public class ResetPasswordService {

    private JavaMailSender javaMailSender;


    @Autowired
    public ResetPasswordService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    public void sendNewPassword(User user) throws MailException {
        //send email
        SimpleMailMessage mail = new SimpleMailMessage();
        Random gen = new Random();
        CharSequence code;

        }

}
