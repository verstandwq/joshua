package org.gyt.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Nokia ChengDu Engine Team
 * Created by y27chen on 2016/7/12.
 */
@RestController
public class HomePageController {

    @RequestMapping("/")
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }
}
