package org.gyt.web.admin;

import org.apache.commons.lang3.StringUtils;
import org.gyt.web.api.service.MessageService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.gyt.web.model.Message;
import org.gyt.web.model.MessageType;
import org.gyt.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;

/**
 * 后台页面路由器
 * Created by Administrator on 2016/9/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminMessagePageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ModelAndViewUtils modelAndViewUtils;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ModelAndView tablePage(
            @RequestParam(required = false) String type
    ) {
        ModelAndView modelAndView = modelAndViewUtils.newAdminModelAndView("admin-message");

        if (StringUtils.isEmpty(type)) {
            modelAndView.addObject("items", messageService.getAll());
            modelAndView.addObject("subtitle", "所有留言");
        } else if (type.equalsIgnoreCase("ADVISE")) {
            modelAndView.addObject("items", messageService.getByType(MessageType.ADVICE));
            modelAndView.addObject("subtitle", "建议");
        } else if (type.equalsIgnoreCase("QUESTION")) {
            modelAndView.addObject("items", messageService.getByType(MessageType.QUESTION));
            modelAndView.addObject("subtitle", "咨询");
        } else if (type.equalsIgnoreCase("SUFFRAGE")) {
            modelAndView.addObject("items", messageService.getByType(MessageType.SUFFRAGE));
            modelAndView.addObject("subtitle", "代祷");
        } else {
            modelAndView.addObject("items", new ArrayList<>());
            modelAndView.addObject("subtitle", "未知类型");
        }

        return modelAndView;
    }
}
