package org.gyt.web.api.ws;

import org.gyt.web.api.service.RoleService;
import org.gyt.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户web接口
 * Created by Administrator on 2016/9/18.
 */
@RestController
@RequestMapping("/api/user")
public class UserWebServiceAPI {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/lock/{username}", method = RequestMethod.POST)
    public boolean lock(@PathVariable String username) {
        return userService.lock(username);
    }

    @RequestMapping(value = "/unlock/{username}", method = RequestMethod.POST)
    public boolean unlock(@PathVariable String username) {
        return userService.unlock(username);
    }

    @RequestMapping(value = "/enable/{username}", method = RequestMethod.POST)
    public boolean enable(@PathVariable String username) {
        return userService.enable(username);
    }

    @RequestMapping(value = "/disable/{username}", method = RequestMethod.POST)
    public boolean disable(@PathVariable String username) {
        return userService.disable(username);
    }
}
