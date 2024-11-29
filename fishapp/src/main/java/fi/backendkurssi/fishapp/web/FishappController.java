package fi.backendkurssi.fishapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FishappController {
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
}
