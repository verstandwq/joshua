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
            roleService.create("ROOT");
            roleService.add("ROOT", Authority.ROLE_ADMIN_LOGIN);
            roleService.add("ROOT", Authority.ROLE_MANAGE_SYSTEM_ADMIN);
            roleService.add("ROOT", Authority.ROLE_MANAGE_SYSTEM_ROLE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_STATIC_PAGE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_NOTIFICATION);
            roleService.add("ROOT", Authority.ROLE_MANAGE_MESSAGE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_MEDIA_RESOURCE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_FELLOWSHIP);
            roleService.add("ROOT", Authority.ROLE_MANAGE_FELLOWSHIP_ADMIN);
            roleService.add("ROOT", Authority.ROLE_PUBLISH_ARTICLE);
            roleService.add("ROOT", Authority.ROLE_MANAGE_ARTICLE);
            roleService.add("ROOT", Authority.ROLE_DOWNLOAD_MEDIA);
            roleService.add("ROOT", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "ROOT"));
        }

        if (null == roleService.get("SYSTEM_ADMIN")) {
            roleService.create("SYSTEM_ADMIN");
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_ADMIN_LOGIN);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_SYSTEM_ROLE);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_STATIC_PAGE);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_NOTIFICATION);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_MESSAGE);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_MEDIA_RESOURCE);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_FELLOWSHIP);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_FELLOWSHIP_ADMIN);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_PUBLISH_ARTICLE);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_MANAGE_ARTICLE);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_DOWNLOAD_MEDIA);
            roleService.add("SYSTEM_ADMIN", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "SYSTEM_ADMIN"));
        }

        if (null == roleService.get("FELLOWSHIP_OWNER")) {
            roleService.create("FELLOWSHIP_OWNER");
            roleService.add("FELLOWSHIP_OWNER", Authority.ROLE_ADMIN_LOGIN);
            roleService.add("FELLOWSHIP_OWNER", Authority.ROLE_MANAGE_FELLOWSHIP_ADMIN);
            roleService.add("FELLOWSHIP_OWNER", Authority.ROLE_MANAGE_ARTICLE);
            roleService.add("FELLOWSHIP_OWNER", Authority.ROLE_DOWNLOAD_MEDIA);
            roleService.add("FELLOWSHIP_OWNER", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "FELLOWSHIP_OWNER"));
        }

        if (null == roleService.get("FELLOWSHIP_ADMIN")) {
            roleService.create("FELLOWSHIP_ADMIN");
            roleService.add("FELLOWSHIP_ADMIN", Authority.ROLE_ADMIN_LOGIN);
            roleService.add("FELLOWSHIP_ADMIN", Authority.ROLE_MANAGE_ARTICLE);
            roleService.add("FELLOWSHIP_ADMIN", Authority.ROLE_DOWNLOAD_MEDIA);
            roleService.add("FELLOWSHIP_ADMIN", Authority.ROLE_SEND_MESSAGE);

            LOGGER.info(String.format("创建角色成功：%s", "FELLOWSHIP_ADMIN"));
        }

        if (null == roleService.get("USER")) {
            roleService.create("USER");
            roleService.add("USER", Authority.ROLE_DOWNLOAD_MEDIA);
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

        for (int i = 0; i < 100; i++) {
            String username = String.format("testUser%3d", i);

            if (null == userService.get(username)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword("12345678");
                user.setNickname(String.format("测试用户昵称%3d", i));
                user.setName(String.format("测试用户名字%3d", i));
                user.setTelephone(String.format("13588880%d", i));
                user.setEmail(String.format("testUser%d@gyt.com", i));

                if (new Random().nextInt(100) <= 3) {
                    user.getRoles().add(roleService.get("SYSTEM_ADMIN"));
                }

                if (new Random().nextInt(100) <= 10) {
                    user.getRoles().add(roleService.get("FELLOWSHIP_OWNER"));
                }

                if (new Random().nextInt(100) <= 30) {
                    user.getRoles().add(roleService.get("FELLOWSHIP_ADMIN"));
                }

                user.getRoles().add(roleService.get("USER"));

                userService.create(user);
                LOGGER.info(String.format("创建测试用户成功：%s", userService.get(username)));
            }
        }
    }
}
