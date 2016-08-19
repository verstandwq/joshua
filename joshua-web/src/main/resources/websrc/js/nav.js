// 登录页面的弹出和关闭

// 登录注册标签切换
$(document).ready(function(){
    // 开始状态
    $(".logon").hide();
    $(".login").show();
    $("#loginIn").addClass("active");
    // 点击注册状态
    $("#logonIn").click(function(){
        $("#logonIn").addClass("active");
        $("#loginIn").removeClass("active");
        $(".login").hide();
        $(".logon").show();
    })
    // 点击登录
    $("#loginIn").click(function(){
        $("#loginIn").addClass("active");
        $("#logonIn").removeClass("active");
        $(".logon").hide();
        $(".login").show();
    })
})


// 登录表单验证
$(document).ready(function () {

    $('.ui.form').form({
        userName: {
            identifier: 'userName',
            rules: [{
                type: 'empty',
                prompt: '请输入用户名'
            }]
        },
        password: {
            identifier: 'password',
            rules: [{
                type: 'empty',
                prompt: '请输入密码'
            }, {
                type: 'length[6]',
                prompt: '密码至少为 6 位'
            }]
        }
    }, {
        inline: true,
        on: 'blur',
        onSuccess: submitForm
    });

    $('.ui.form').submit(function (e) {
        return false;
    });
    //checkbox init
    $('.ui.checkbox').checkbox();

    $('.ui.dropdown').dropdown({on: 'hover'});
});
function submitForm() {
    alert('dd');
    var formData = $('.ui.form input').serializeArray(); //or .serialize();
    $.ajax({
        type: 'POST',
        url: '',
        data: formData
    });
}
// 注册表单验证