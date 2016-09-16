package org.gyt.web.model;

/**
 * 权限定义
 * Created by Administrator on 2016/9/12.
 */
public enum Authority {

    /**
     * 后台登录权限
     * - 可以登录到后台
     */
    ROLE_ADMIN_LOGIN,

    /**
     * 管理系统管理员权限：
     * - 创建系统管理员
     * - 锁定、解锁系统管理员
     * - 禁用、激活系统管理员
     */
    ROLE_MANAGE_SYSTEM_ADMIN,

    /**
     * 管理角色权限
     * - 添加角色
     * - 禁用角色
     */
    ROLE_MANAGE_SYSTEM_ROLE,

    /**
     * 管理静态页面权限：
     * - 首页大图
     * - 首页介绍
     * - 其他静态页面
     */
    ROLE_MANAGE_STATIC_PAGE,

    /**
     * 管理网站通知权限：
     * - 发布网站通知
     * - 删除网站通知
     */
    ROLE_MANAGE_NOTIFICATION,

    /**
     * 管理留言信息权限：
     * - 查看留言信息
     * - 删除留言信息
     */
    ROLE_MANAGE_MESSAGE,

    /**
     * 管理媒体资源权限：
     * - 上传媒体资源
     * - 删除媒体资源
     * - 下载媒体资源
     * - 查询媒体资源
     */
    ROLE_MANAGE_MEDIA_RESOURCE,

    /**
     * 管理团契权限：
     * - 创建团契
     * - 禁用、激活团契
     * - 设置团契管理员
     */
    ROLE_MANAGE_FELLOWSHIP,

    /**
     * 管理团契管理员权限：
     * - 添加管理员
     * - 移除管理员
     */
    ROLE_MANAGE_FELLOWSHIP_ADMIN,

    /**
     * 发布文章权限：
     * - 审核文章
     * - 拒绝文章
     * - 发布文章
     */
    ROLE_PUBLISH_ARTICLE,

    /**
     * 文章管理权限：
     * - 创建文章到制定团契
     * - 删除文章
     * - 申请发布文章
     */
    ROLE_MANAGE_ARTICLE,

    /**
     * 媒体资源下载权限：
     * - 下载媒体资源
     */
    ROLE_DOWNLOAD_MEDIA,

    /**
     * 发布留言权限：
     * - 发布留言信息
     */
    ROLE_SEND_MESSAGE

}
