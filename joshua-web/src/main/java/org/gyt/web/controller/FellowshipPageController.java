package org.gyt.web.controller;

import org.gyt.web.api.utils.ModelAndViewUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 静态页面路由器
 * Created by y27chen on 2016/7/12.
 */
@RestController
@RequestMapping("/fellowship")
public class FellowshipPageController {

    @RequestMapping("/{name}")
    public ModelAndView getFellowshipHomePage(@PathVariable String name) {
        ModelAndView modelAndView = ModelAndViewUtils.newModelAndView("staticPage/fellowship/" + name);

        if (modelAndView.getModel().isEmpty()) {
            modelAndView = ModelAndViewUtils.newModelAndView("404");
            modelAndView.addObject("message", String.format("没有找到团契%s", name));
        }

        return modelAndView;
    }
}
