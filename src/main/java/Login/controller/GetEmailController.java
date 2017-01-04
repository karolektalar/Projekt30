package Login.controller;

import Login.domain.User;
import Login.repository.UserRepository;
import Login.service.mailUser.ResetPasswordService;
import Login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;


@RestController
public class GetEmailController {
//    private final UserService userService;
//    private final UserCreateFormValidator userCreateFormValidator;
//
//    @InitBinder("form")
//    public void initBinder(WebDataBinder binder) {
//        binder.addValidators(userCreateFormValidator);
//    }
    private final UserService userService;
    private JavaMailSender javaMailSender;
    private UserRepository userRepository;

//    @Autowired
//    private ResetPasswordService resetPasswordService;

    @Autowired
    public GetEmailController(UserService userService, UserRepository userRepository,JavaMailSender javaMailSender) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }




    @RequestMapping("/getemail")
    public ModelAndView getGetemailPage(@RequestParam Optional<String> error) {

        SimpleMailMessage mail = new SimpleMailMessage();
        Random gen = new Random();
        CharSequence code;
        code = Float.toString(gen.hashCode());
        userService.getUserByEmail("dropboxjavaproject@gmail.com").
                orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found","dropboxjavaproject@gmail.com" ))))
                .setPasswordHash(new BCryptPasswordEncoder().encode(code));

        userRepository.save(userService.getUserByEmail("dropboxjavaproject@gmail.com").
                orElseThrow((() -> new NoSuchElementException(String.format("User=%s not found","dropboxjavaproject@gmail.com" ))))
        );

        mail.setTo("dropboxjavaproject@gmail.com");
        mail.setFrom("dropboxjavaproject@gmail.com");
        mail.setSubject("Password reset");
        mail.setText("Your new password is "+code);
        javaMailSender.send(mail);
        return new ModelAndView("redirect:/");
    }
}
