package org.gyt.web;

import org.gyt.web.api.service.RoleService;
import org.gyt.web.api.service.UserService;
import org.gyt.web.model.Authority;
import org.gyt.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Locale;
import java.util.Random;

/**
 * Nokia ChengDu Engine Team
 * Created by y27chen on 2016/7/12.
 */
@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        createDefaultRoles(applicationContext);
        createRootUser(applicationContext);
        createTestUsers(applicationContext);

        Locale.setDefault(new Locale("zh_CN"));
    }

    private static void createDefaultRoles(ApplicationContext applicationContext) {
        RoleService roleService = applicationContext.getBean(RoleService.class);

        if (null == roleService.get("ROOT")) {
            roleService.create("ROOT", "超级用户");
            roleService.add("ROOT", Authority.ROLE_ADMIN_ACCESS);
            roleService.add("ROOT", Authority.ROLE_MANAGE_USER_ROLE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_USER_STATUS);
            roleService.add("ROOT", Authority.ROLE_MANAGE_STATIC_PAGE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_NAVIGATION);
            roleService.add("ROOT", Authority.ROLE_MANAGE_NOTIFICATION);
            roleService.add("ROOT", Authority.ROLE_MANAGE_MESSAGE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_RESOURCE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_FELLOWSHIP);
            roleService.add("ROOT", Authority.ROLE_MANAGE_ARTICLE);
            roleService.add("ROOT", Authority.ROLE_DOWNLOAD_RESOURCE);
            roleService.add("ROOT", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "ROOT"));
        }

        if (null == roleService.get("ADMIN")) {
            roleService.create("ADMIN", "系统管理员");
            roleService.add("ROOT", Authority.ROLE_ADMIN_ACCESS);
            roleService.add("ROOT", Authority.ROLE_MANAGE_USER_ROLE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_USER_STATUS);
            roleService.add("ROOT", Authority.ROLE_MANAGE_STATIC_PAGE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_NAVIGATION);
            roleService.add("ROOT", Authority.ROLE_MANAGE_NOTIFICATION);
            roleService.add("ROOT", Authority.ROLE_MANAGE_MESSAGE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_RESOURCE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_FELLOWSHIP);
            roleService.add("ROOT", Authority.ROLE_MANAGE_ARTICLE);
            roleService.add("ROOT", Authority.ROLE_DOWNLOAD_RESOURCE);
            roleService.add("ROOT", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "ADMIN"));
        }

        if (null == roleService.get("EDITOR")) {
            roleService.create("EDITOR", "网站编辑");
            roleService.add("EDITOR", Authority.ROLE_MANAGE_STATIC_PAGE);
            roleService.add("EDITOR", Authority.ROLE_MANAGE_NAVIGATION);
            roleService.add("EDITOR", Authority.ROLE_MANAGE_ARTICLE);
            roleService.add("EDITOR", Authority.ROLE_MANAGE_MESSAGE);
            roleService.add("EDITOR", Authority.ROLE_ADMIN_ACCESS);
            roleService.add("EDITOR", Authority.ROLE_DOWNLOAD_RESOURCE);
            roleService.add("EDITOR", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "EDITOR"));
        }

        if (null == roleService.get("FS_ADMIN")) {
            roleService.create("FS_ADMIN", "团契管理员");
            roleService.add("EDITOR", Authority.ROLE_MANAGE_FELLOWSHIP);
            roleService.add("EDITOR", Authority.ROLE_ADMIN_ACCESS);
            roleService.add("EDITOR", Authority.ROLE_DOWNLOAD_RESOURCE);
            roleService.add("EDITOR", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "FS_ADMIN"));
        }

        if (null == roleService.get("RE_ADMIN")) {
            roleService.create("RE_ADMIN", "资源管理员");
            roleService.add("RE_ADMIN", Authority.ROLE_MANAGE_RESOURCE);
            roleService.add("RE_ADMIN", Authority.ROLE_ADMIN_ACCESS);
            roleService.add("RE_ADMIN", Authority.ROLE_DOWNLOAD_RESOURCE);
            roleService.add("RE_ADMIN", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "RE_ADMIN"));
        }

        if (null == roleService.get("MEMBER")) {
            roleService.create("MEMBER", "团契成员");
            roleService.add("MEMBER", Authority.ROLE_ADMIN_ACCESS);
            roleService.add("MEMBER", Authority.ROLE_DOWNLOAD_RESOURCE);
            roleService.add("MEMBER", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "MEMBER"));
        }

        if (null == roleService.get("USER")) {
            roleService.create("USER", "注册用户");
            roleService.add("USER", Authority.ROLE_DOWNLOAD_RESOURCE);
            roleService.add("USER", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "USER"));
        }
    }

    private static void createRootUser(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        RoleService roleService = applicationContext.getBean(RoleService.class);

        if (null == userService.get("administrator")) {
            User user = new User();
            user.setUsername("administrator");
            user.setPassword("gyt20131013");
            user.setNickname("光音堂超级用户");
            user.getRoles().add(roleService.get("ROOT"));
            userService.create(user);

            LOGGER.info(String.format("创建超级用户成功：%s", userService.get("administrator")));
        }
    }

    private static void createTestUsers(ApplicationContext applicationContext) {
        UserService userService = applicationContext.getBean(UserService.class);
        RoleService roleService = applicationContext.getBean(RoleService.class);

        for (int i = 0; i < 50; i++) {
            String username = String.format("testUser%03d", i);

            if (null == userService.get(username)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword("12345678");
                user.setNickname(String.format("测试用户昵称%03d", i));
                user.setName(String.format("测试用户名字%03d", i));
                user.setTelephone(String.format("13588880%03d", i));
                user.setEmail(String.format("testUser%03d@gyt.com", i));
                user.setSex(new Random().nextInt(3));

                if (new Random().nextInt(100) <= 3) {
                    user.getRoles().add(roleService.get("ADMIN"));
                }

                if (new Random().nextInt(100) <= 10) {
                    user.getRoles().add(roleService.get("EDITOR"));
                }

                if (new Random().nextInt(100) <= 30) {
                    user.getRoles().add(roleService.get("FS_ADMIN"));
                }

                user.getRoles().add(roleService.get("USER"));

                userService.create(user);
                LOGGER.info(String.format("创建测试用户成功：%s", userService.get(username)));
            }
        }
    }
}
