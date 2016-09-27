package org.gyt.web.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * 模型和视图工具
 * Created by y27chen on 2016/9/20.
 */
@Component
public class ModelAndViewUtils {

    @Autowired
    private CountModelComponent countModelComponent;

    private ModelAndViewUtils() {
    }

    public ModelAndView newModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }

    public ModelAndView newAdminModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("userCountModel", countModelComponent.getUserCountModel());
        modelAndView.addObject("articleCountModel", countModelComponent.getArticleCountModel());
        return modelAndView;
    }
}
