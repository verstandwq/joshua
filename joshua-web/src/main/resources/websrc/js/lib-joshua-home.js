/*
 首页脚本组件
 */
$(document).ready(function () {
    if (document.getElementById("slider")) {
        var slider = new IdealImageSlider.Slider({
            selector: '#slider',
            height: 450,
            interval: 4000,
            effect: 'fade'
        });
        slider.addBulletNav();
        slider.start();
    }
});







