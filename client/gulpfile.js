"use strict";

// Include gulp
var gulp = require('gulp');

// Include plugins
var jasmine = require('gulp-jasmine');
var gutil = require('gulp-util');
var uglify = require('gulp-uglify');
var source = require('vinyl-source-stream'); // makes browserify bundle compatible with gulp
var streamify = require('gulp-streamify');
var browserify = require('browserify');
var del = require('del');

var config = require('./gulp.config.js')();

// Concatenate, Browserify & Minify JS
gulp.task('build', ['copy-static'], function () {
    gutil.log("Browserifying JS files...");
    return browserify('./src/app/app.js').bundle()
        .pipe(source('index.js'))
        .pipe(streamify(uglify()))
        .pipe(gulp.dest('./dist/src/'));
});

gulp.task('copy-static:css', function () {
    gutil.log("Copying CSS...");
    gulp.src(['./node_modules/bootstrap/dist/css/bootstrap.min.css'])
        .pipe(gulp.dest(config.distDirectory + 'css'));
});

gulp.task('copy-static:fonts', function () {
    gutil.log("Copying fonts...");
    gulp.src(['./node_modules/bootstrap/fonts/*.*'])
        .pipe(gulp.dest(config.distDirectory + 'fonts'));
});

gulp.task('copy-static:index', function () {
    gutil.log("Copying index...");
    gulp.src([config.sourceDirectory + 'index.html'])
        .pipe(gulp.dest(config.distDirectory));
});

gulp.task('copy-static:partials', function () {
    gutil.log("Copying partials...");
    gulp.src([config.partialsDirectory + '*.html'])
        .pipe(gulp.dest(config.distDirectory + 'partials'));
});

gulp.task('copy-static', ['copy-static:css', 'copy-static:fonts', 'copy-static:partials', 'copy-static:index'], function () {
    gutil.log("Finished copying static resources.")
});

gulp.task('clean', function (cb) {
    gutil.log("Cleaning JS and CSS files.");
    del(config.distDirectory + '**/*.*', {'force': true});
});

gulp.task('default', ['build'], function () {
    gutil.log("Finished building moviequiz-client");
});
