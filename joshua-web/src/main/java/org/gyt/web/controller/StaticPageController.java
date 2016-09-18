package org.gyt.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 静态页面路由器
 * Created by y27chen on 2016/7/12.
 */
@RestController
public class StaticPageController {

    @RequestMapping("/")
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }

    @RequestMapping("/index")
    public ModelAndView getHomePage2() {
        return new ModelAndView("index");
    }

    @RequestMapping("/home")
    public ModelAndView getHomePage3() {
        return new ModelAndView("index");
    }

    /* 在线圣经 */
    @RequestMapping("/bible")
    public ModelAndView biblePage() {
        return new ModelAndView("staticPage/biblePage");
    }

    /* 基督信仰 */
    @RequestMapping("/believe")
    public ModelAndView christBelievePage() {
        return new ModelAndView("staticPage/christBelievePage");
    }

    /* 联系我们 */
    @RequestMapping("/contact")
    public ModelAndView contactPage() {
        return new ModelAndView("staticPage/contactPage");
    }

    /* 财务报告 */
    @RequestMapping("/financial")
    public ModelAndView financialPage() {
        return new ModelAndView("staticPage/financialPage");
    }

    /* 团契介绍 */
    @RequestMapping("/group")
    public ModelAndView groupPage() {
        return new ModelAndView("groupPage");
    }

    /* 教堂简介 */
    @RequestMapping("/about")
    public ModelAndView introductionPage() {
        return new ModelAndView("introductionPage");
    }

    /* 教堂婚礼 */
    @RequestMapping("/wedding")
    public ModelAndView marriagePage() {
        return new ModelAndView("marriagePage");
    }

    /* 媒体资源 */
    @RequestMapping("/media")
    public ModelAndView mediaPage() {
        return new ModelAndView("mediaPage");
    }

    /* 新人 */
    @RequestMapping("/newcomer")
    public ModelAndView newComerPage() {
        return new ModelAndView("newComerPage");
    }

    /* 主任牧师 */
    @RequestMapping("/pastor")
    public ModelAndView pastorPage() {
        return new ModelAndView("pastorPage");
    }

    /* 每周讲道 */
    @RequestMapping("/preach")
    public ModelAndView preachPage() {
        return new ModelAndView("preachPage");
    }

    /* 好文推荐 */
    @RequestMapping("/recommend")
    public ModelAndView recommendPage() {
        return new ModelAndView("recommendPage");
    }

    /* 事工报告 */
    @RequestMapping("/report")
    public ModelAndView reportPage() {
        return new ModelAndView("reportPage");
    }

    /* 儿童主日学 */
    @RequestMapping("/sunday")
    public ModelAndView sundaySchoolPage() {
        return new ModelAndView("sundaySchoolPage");
    }

    /* 见证奉献 */
    @RequestMapping("/testimony")
    public ModelAndView testimonyPage() {
        return new ModelAndView("testimonyPage");
    }

    /* 主日崇拜 */
    @RequestMapping("/worship")
    public ModelAndView worshipPage() {
        return new ModelAndView("worshipPage");
    }
}
