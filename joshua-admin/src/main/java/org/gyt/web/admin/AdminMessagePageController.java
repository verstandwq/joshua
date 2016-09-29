package org.gyt.web.admin;

import org.apache.commons.lang3.StringUtils;
import org.gyt.web.api.service.MessageService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.gyt.web.api.utils.PaginationComponent;
import org.gyt.web.model.Message;
import org.gyt.web.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private PaginationComponent paginationComponent;

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public ModelAndView tablePage(
            @RequestParam(required = false) String type,
            @RequestParam(required = false, defaultValue = "1") int pageNumber,
            @RequestParam(required = false, defaultValue = "20") int pageSize
    ) {
        ModelAndView modelAndView = modelAndViewUtils.newAdminModelAndView("admin-message");

        List<Message> messageList = new ArrayList<>();

        if (StringUtils.isEmpty(type)) {
            messageList = messageService.getAll();
            modelAndView.addObject("subtitle", "所有留言");
            type = "";
        } else if (type.equalsIgnoreCase("ADVISE")) {
            messageList = messageService.getByType(MessageType.ADVICE);
            modelAndView.addObject("subtitle", "建议");
        } else if (type.equalsIgnoreCase("QUESTION")) {
            messageList = messageService.getByType(MessageType.QUESTION);
            modelAndView.addObject("subtitle", "咨询");
        } else if (type.equalsIgnoreCase("SUFFRAGE")) {
            messageList = messageService.getByType(MessageType.SUFFRAGE);
            modelAndView.addObject("subtitle", "代祷");
        } else {
            modelAndView.addObject("subtitle", "未知类型");
        }

        modelAndView.addObject("items", paginationComponent.listPagination(messageList, pageNumber, pageSize));
        paginationComponent.addPaginationModel(modelAndView, "/admin/message?type=" + type, messageList.size(), pageNumber, pageSize);

        return modelAndView;
    }
}
