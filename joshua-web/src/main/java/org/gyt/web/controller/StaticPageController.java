package org.gyt.web.controller;

import org.gyt.web.api.service.ArticleService;
import org.gyt.web.api.service.FellowshipService;
import org.gyt.web.api.service.HomePageService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.gyt.web.model.Fellowship;
import org.gyt.web.model.Message;
import org.gyt.web.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 静态页面路由器
 * Created by y27chen on 2016/7/12.
 */
@RestController
public class StaticPageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private FellowshipService fellowshipService;

    @Autowired
    private HomePageService homePageService;

    @Autowired
    private ModelAndViewUtils modelAndViewUtils;

    @RequestMapping("/")
    public ModelAndView getHomePage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("index");
        modelAndView.addObject("churchArticles", articleService.getChurchArticles());
        modelAndView.addObject("fellowshipArticles", articleService.getFellowshipArticles());
        modelAndView.addObject("imageGallery", homePageService.getPictures());
        return modelAndView;
    }

    @RequestMapping("/index")
    public ModelAndView getHomePage2() {
        return getHomePage();
    }

    @RequestMapping("/home")
    public ModelAndView getHomePage3() {
        return getHomePage();
    }

    /* 在线圣经 */
    @RequestMapping("/bible")
    public ModelAndView biblePage() {
        return modelAndViewUtils.newModelAndView("staticPage/biblePage");
    }

    /* 基督信仰 */
    @RequestMapping("/believe")
    public ModelAndView christBelievePage() {
        return modelAndViewUtils.newModelAndView("staticPage/christBelievePage");
    }

    /* 联系我们 */
    @RequestMapping("/contact")
    public ModelAndView contactPage(
            @RequestParam(required = false) boolean publishSuccess,
            @RequestParam(required = false) boolean publishFailed
    ) {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/contactPage");
        Message message = new Message();
        message.setType(MessageType.SUFFRAGE);
        modelAndView.addObject("message", message);
        modelAndView.addObject("publishSuccess", publishSuccess);
        modelAndView.addObject("publishFailed", publishFailed);
        return modelAndView;
    }

    /* 奉献捐赠 */
    @RequestMapping("/devotion")
    public ModelAndView devotionPage() {
        return modelAndViewUtils.newModelAndView("staticPage/devotionPage");
    }

    /* 团契介绍 */
    @RequestMapping("/fellowship")
    public ModelAndView groupPage() {
        return modelAndViewUtils.newModelAndView("staticPage/fellowshipPage");
    }

    /* 教堂简介 */
    @RequestMapping("/about")
    public ModelAndView introductionPage() {
        return modelAndViewUtils.newModelAndView("staticPage/introductionPage");
    }

    /* 教堂婚礼 */
    @RequestMapping("/wedding")
    public ModelAndView marriagePage() {
        return modelAndViewUtils.newModelAndView("staticPage/weddingPage");
    }

    /* 媒体资源 */
    @RequestMapping("/media")
    public ModelAndView mediaPage() {
        return modelAndViewUtils.newModelAndView("staticPage/mediaPage");
    }

    /* 新人 */
    @RequestMapping("/newcomer")
    public ModelAndView newComerPage() {
        return modelAndViewUtils.newModelAndView("staticPage/newComerPage");
    }

    /* 主任牧师 */
    @RequestMapping("/pastor")
    public ModelAndView pastorPage() {
        return modelAndViewUtils.newModelAndView("staticPage/pastorPage");
    }

    /* 公益活动 */
    @RequestMapping("/public")
    public ModelAndView publicPage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/publicPage");
        Fellowship fellowship = fellowshipService.get("public");
        modelAndView.addObject("items", fellowship.getArticles());
        return modelAndView;
    }

    /* 好文推荐 */
    @RequestMapping("/recommend")
    public ModelAndView recommendPage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/recommendPage");
        Fellowship fellowship = fellowshipService.get("recommend");
        modelAndView.addObject("items", fellowship.getArticles());
        return modelAndView;
    }

    /* 事工报告 */
    @RequestMapping("/report")
    public ModelAndView reportPage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/reportPage");
        Fellowship fellowship = fellowshipService.get("report");
        modelAndView.addObject("items", fellowship.getArticles());
        return modelAndView;
    }

    /* 主内服侍 */
    @RequestMapping("/service")
    public ModelAndView servicePage() {
        return modelAndViewUtils.newModelAndView("staticPage/servicePage");
    }

    /* 教会代祷 */
    @RequestMapping("/suffrage")
    public ModelAndView suffragePage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/suffragePage");
        Fellowship fellowship = fellowshipService.get("suffrage");
        modelAndView.addObject("items", fellowship.getArticles());
        return modelAndView;
    }

    /* 儿童主日学 */
    @RequestMapping("/sunday")
    public ModelAndView sundaySchoolPage() {
        return modelAndViewUtils.newModelAndView("staticPage/sundaySchoolPage");
    }

    /* 见证奉献 */
    @RequestMapping("/testimony")
    public ModelAndView testimonyPage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/testimonyPage");
        Fellowship fellowship = fellowshipService.get("testimony");
        modelAndView.addObject("items", fellowship.getArticles());
        return modelAndView;
    }

    /* 主日崇拜 */
    @RequestMapping("/worship")
    public ModelAndView worshipPage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("staticPage/worshipPage");
        Fellowship fellowship = fellowshipService.get("worship");
        modelAndView.addObject("items", fellowship.getArticles());
        return modelAndView;
    }
}
