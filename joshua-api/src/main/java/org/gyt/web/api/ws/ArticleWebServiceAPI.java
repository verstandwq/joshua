package org.gyt.web.api.ws;

import org.gyt.web.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户web接口
 * Created by Administrator on 2016/9/18.
 */
@RestController
@RequestMapping("/api/article")
public class ArticleWebServiceAPI {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    public String lock(@PathVariable Long id) {
        return articleService.get(id).getContent();
    }
}
