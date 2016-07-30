/**
 * Created by Administrator on 2016/7/30.
 */
var gulp = require('gulp');
var cleanCSS = require('gulp-clean-css');
var concatCss = require('gulp-concat-css');

gulp.task('minify-css', function () {
    return gulp.src('css/*.css')
        .pipe(concatCss("joshua.min.css"))
        .pipe(cleanCSS({debug: true}, function(details) {
            console.log(details.name + ': ' + details.stats.originalSize);
            console.log(details.name + ': ' + details.stats.minifiedSize);
        }))
        .pipe(gulp.dest('../resources/public/assets/css/'));
});