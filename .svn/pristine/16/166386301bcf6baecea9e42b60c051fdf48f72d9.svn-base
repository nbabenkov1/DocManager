package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by N.Babenkov on 21.05.2018.
 **/
@Controller
public class InitController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView redirect(){
        return new ModelAndView("index");
    }
}
