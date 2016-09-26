package org.gyt.web.admin;

import org.gyt.web.api.repository.ArticleRepository;
import org.gyt.web.api.repository.MessageRepository;
import org.gyt.web.api.service.FellowshipService;
import org.gyt.web.api.service.UserService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台页面路由器
 * Created by Administrator on 2016/9/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private FellowshipService fellowshipService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = ModelAndViewUtils.newModelAndView("admin-home");
        modelAndView.addObject("resistedCount", userService.count());
        modelAndView.addObject("fellowshipCount", fellowshipService.getAll().size());
        modelAndView.addObject("articleCount", articleRepository.count());
        modelAndView.addObject("messageCount", messageRepository.count());
        return modelAndView;
    }
}
