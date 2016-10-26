$(document).ready(function () {
    $("#qr-address").qrcode({
        width: 196,
        height: 196,
        text: encodeURI(window.location.href)
    });

    $('.ui.qr.icon').popup({
        position: "bottom center",
        inline: true,
        on: "click"
    });
});