/**
 * 导航栏模块
 * Created by y27chen on 2016/10/29.
 */
import "../common";

export default class Navigation {
    constructor() {
        $('.ui.admin.menu .ui.dropdown').dropdown();
        $('.ui.admin.menu .ui.accordion').accordion('open', this.getNavigationIndex() - 1);
    }

    getNavigationIndex() {
        var location = window.location.pathname;

        if (location.startsWith("/admin/static")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".static.item"));
        } else if (location.startsWith("/admin/user/fellowship") || location.startsWith("/admin/fellowship")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".fellowship.item"));
        } else if (location.startsWith("/admin/navigation")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".navigation.item"));
        } else if (location.startsWith("/admin/notification")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".notification.item"));
        } else if (location.startsWith("/admin/message")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".message.item"));
        } else if (location.startsWith("/admin/user")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".user.item"));
        } else if (location.startsWith("/admin/article")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".article.item"));
        } else if (location.startsWith("/admin/info") || location.startsWith("/admin/password")) {
            return $(".ui.admin.menu .ui.accordion > .item").index($(".info.item"));
        }
    }
}
