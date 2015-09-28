"use strict";

// Include gulp
var gulp = require('gulp');

// Include plugins
var jasmine = require('gulp-jasmine');
var uglify = require('gulp-uglify');
var source = require('vinyl-source-stream'); // makes browserify bundle compatible with gulp
var streamify = require('gulp-streamify');
var browserify = require('browserify');
var del = require('del');

// Concatenate, Browserify & Minify JS
gulp.task('compile', function () {
  return browserify('./src/app/app.js').bundle()
      .pipe(source('index.js'))
      .pipe(streamify(uglify()))
      .pipe(gulp.dest('./dist/src/'));
});

gulp.task('default', ['compile']);