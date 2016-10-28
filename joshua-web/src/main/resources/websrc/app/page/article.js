/**
 * 前端文章页面
 * Created by y27chen on 2016/10/29.
 */

import BasePage from "../joshua-base-page";

class ArticlePage extends BasePage {
    constructor() {
        super();
        console.log("Article Page");
    }
}

$(document).ready(() => {
    new ArticlePage();
});