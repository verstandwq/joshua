package org.gyt.web.api.ws;

import org.gyt.web.api.service.ArticleService;
import org.gyt.web.model.Article;
import org.gyt.web.model.ArticleStatus;
import org.gyt.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章web接口
 * Created by Administrator on 2016/9/18.
 */
@RestController
@RequestMapping("/api/article")
public class ArticleWebServiceAPI {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public boolean publish(@RequestParam Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = articleService.get(id);

        if (article != null) {
            article.setAuditor(user);
            article.setStatus(ArticleStatus.PUBLISHED);
            return articleService.create(article);
        }

        return false;
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public boolean reject(@RequestParam Long id, @RequestParam(required = false) String message) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = articleService.get(id);

        if (article != null) {
            article.setAuditor(user);
            article.setStatus(ArticleStatus.REJECTED);
            article.setAuditComment(message);
            return articleService.create(article);
        }

        return false;
    }
}
