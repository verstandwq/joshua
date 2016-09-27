package org.gyt.web.controller;

import org.gyt.web.api.service.ArticleService;
import org.gyt.web.api.service.FellowshipService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.gyt.web.model.Fellowship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 团契页面路由器
 * Created by y27chen on 2016/7/12.
 */
@RestController
@RequestMapping("/fellowship")
public class FellowshipPageController {

    @Autowired
    private FellowshipService fellowshipService;

    @Autowired
    private ModelAndViewUtils modelAndViewUtils;

    @RequestMapping("/{name}")
    public ModelAndView getFellowshipHomePage(@PathVariable String name) {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/fellowship/" + name);

        Fellowship fellowship = fellowshipService.get(name);
        modelAndView.addObject("items", fellowship.getArticles());

        return modelAndView;
    }
}
