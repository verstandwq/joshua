var gulp = require('gulp');
var concat = require('gulp-concat');
var cleanCSS = require('gulp-clean-css');
var concatCss = require('gulp-concat-css');
var minify = require('gulp-minify');
var print = require('gulp-print');
var jshint = require('gulp-jshint');
var babel = require('gulp-babel');
var del = require('del');

gulp.task('clean', function () {
    return del([
        'build/'
    ]);
});

gulp.task('css', function () {
    return gulp.src('css/*.css')
        .pipe(concatCss("joshua.min.css"))
        .pipe(cleanCSS({debug: true}, function (details) {
            console.log(details.name + ': ' + details.stats.originalSize);
            console.log(details.name + ': ' + details.stats.minifiedSize);
        }))
        .pipe(gulp.dest('build/css'));
});

gulp.task("js", function () {
    return gulp.src("js/joshua.js")
        .pipe(print())
        .pipe(babel({presets: ["es2015"]}))
        .pipe(gulp.dest("build"))
});