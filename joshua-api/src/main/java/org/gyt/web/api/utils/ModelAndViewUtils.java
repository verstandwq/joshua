package org.gyt.web.api.utils;

import org.springframework.web.servlet.ModelAndView;

/**
 * 模型和视图工具
 * Created by y27chen on 2016/9/20.
 */
public class ModelAndViewUtils {

    private ModelAndViewUtils() {
    }

    public static ModelAndView newModelAndView(String viewName) {
        ModelAndView modelAndView = new ModelAndView(viewName);
        return modelAndView;
    }
}
