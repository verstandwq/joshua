/**
 * 公共库函数
 * - 日期转换函数
 */
"use strict";

/**
 * 将给定日期与当前时间比较，并转换成高可读性的文本
 * @param targetDate 目标日期
 */
var getDisplayDate = function (targetDate) {
    if (date instanceof Date) {
        var currentDate = new Date();
        var currentTime = currentDate.getTime();
        var targetTime = date.getTime();

        var deltaTime = currentTime - targetTime;

        if (deltaTime > 0) {
            var min = deltaTime / 1000 / 60;

            if (min <= 5) {
                return "刚刚";
            }

            var hours = min / 60;

            if (hours < 1) {
                return Math.ceil(min) + " 分钟之前";
            }

            var day = hours / 24;

            if (day < 1) {
                return Math.ceil(hours) + " 小时之前";
            }

            var week = day / 7;

            if (week < 1) {
                return Math.ceil(day) + " 天之前";
            } else if (week < 2) {
                return "一周前";
            } else {
                return date;
            }

        }
    }

    return date;
};