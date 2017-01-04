package Login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class GetEmailController {


    @RequestMapping("/getemail")
    public ModelAndView getGetemailPage(@RequestParam Optional<String> error) {
        return new ModelAndView("getemail", "error", error);

    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public ModelAndView getRedirect(@RequestParam Optional<String> error) {

        return new ModelAndView("redirect:/newpass", "error", error);
    }

    @RequestMapping(value = "/newpass", method = RequestMethod.GET)
    public ModelAndView getFinalPage(@RequestParam Optional<String> error) {

        return new ModelAndView("/newpass", "error", error);
    }

}
