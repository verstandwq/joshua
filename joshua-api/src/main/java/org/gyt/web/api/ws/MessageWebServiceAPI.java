package org.gyt.web.api.ws;

import org.gyt.web.api.service.MessageService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.gyt.web.model.Message;
import org.gyt.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * 留言消息web接口
 * Created by Administrator on 2016/9/18.
 */
@RestController
@RequestMapping("/message")
public class MessageWebServiceAPI {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ModelAndView publish(@ModelAttribute Message message) {
        ModelAndView modelAndView = ModelAndViewUtils.newModelAndView("staticPage/contactPage");

        message.setCreatedDate(new Date());
        message.setOwner((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (messageService.create(message)) {
            modelAndView.setViewName("redirect:/contact?publishSuccess=true");
        } else {
            modelAndView.setViewName("redirect:/contact?publishFailed=true");
        }

        return modelAndView;
    }
}
