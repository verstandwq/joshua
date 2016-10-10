package org.gyt.web;

import org.gyt.web.api.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.mail.MessagingException;
import java.util.Locale;

/**
 * Nokia ChengDu Engine Team
 * Created by y27chen on 2016/7/12.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        ApplicationResourceHelper.createDefaultRoles(applicationContext);
        ApplicationResourceHelper.createRootUser(applicationContext);
        ApplicationResourceHelper.createDefaultFellowship(applicationContext);

        if (args.length > 0 && args[0].equals("test")) {
            ApplicationTestResourceHelper.createTestUsers(applicationContext);
            ApplicationTestResourceHelper.createTestArticles(applicationContext);
            ApplicationTestResourceHelper.createTestMessage(applicationContext);
        }

        Locale.setDefault(new Locale("zh_CN"));

        sendTestMail(applicationContext);
    }

    private static void sendTestMail(ApplicationContext applicationContext) {
        MailService mailService = applicationContext.getBean(MailService.class);
        try {
            mailService.sendText("156816772@qq.com", "测试邮件主题", "测试邮件内容");
            System.out.println("发送邮件成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
