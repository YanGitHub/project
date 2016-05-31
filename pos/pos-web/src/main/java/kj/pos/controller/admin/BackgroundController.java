package kj.pos.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 16-5-23.
 */
@Controller
@RequestMapping("/background")
public class BackgroundController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String background(){
        return "background";
    }
}
