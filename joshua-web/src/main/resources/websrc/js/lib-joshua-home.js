/*
 首页脚本组件
 */
$(document).ready(function () {
    var slider = new IdealImageSlider.Slider({
        selector: '#slider',
        height: 450,
        interval: 4000,
        effect: 'fade'
    });
    slider.addBulletNav();
    slider.start();
});







