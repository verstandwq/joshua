/**
 * 首页
 * Created by y27chen on 2016/10/29.
 */
import BasePage from "../joshua-base-page";
import IdealImageSlider from "../lib/ideal-image-slider";
import AOS from "aos";
import "../lib/ideal-image-slider-bullet-nav";

class HomePage extends BasePage {
    constructor() {
        super();
    }

    loadSlider() {
        var slider = new IdealImageSlider.Slider({
            selector: '#slider',
            height: 450,
            interval: 4000,
            effect: 'fade'
        });
        slider.addBulletNav();
        slider.start();
    }

    loadAnimation() {
        AOS.init();
    }
}

$(document).ready(() => {
    var page = new HomePage();
    page.loadAnimation();
    page.loadSlider();
});