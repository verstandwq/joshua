package org.gyt.web.api.ws;

import org.gyt.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 重置密码接口
 * Created by Administrator on 2016/9/18.
 */
@RestController
public class ForgetWebServiceAPI {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public String changeInfo(@RequestParam String username, @RequestParam String email) {
        return "未知原因";
    }
}
