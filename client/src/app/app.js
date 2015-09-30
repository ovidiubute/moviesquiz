'use strict';

var angular = require('angular');
var MainModule = require('./modules/core/CoreModule.js');

var app = angular.module("ovi.moviequiz.app", [
  'ovi.moviequiz.core'
]);

app.config(function ($stateProvider, $urlRouterProvider) {
  $urlRouterProvider.otherwise("/");

  $stateProvider
      .state('account', {
        url: "/account",
        templateUrl: "partials/account.html",
        controller: "AccountController"
      })
      .state("home", {
        url: "/",
        templateUrl: "partials/home.html"
      });
});

app.run(function ($log) {
  $log.info("Movie Quiz app started.");
});

module.exports = app;