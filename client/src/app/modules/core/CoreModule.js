'use strict';

var angular = require('angular');
var angularUiRouter = require('angular-ui-router');

var MainController = require('./MainController.js');
var AccountController = require('./AccountController.js');


var angularModule = angular.module('king.nivelos.core', ['ui.router'])
    .controller('MainController', ['$scope', MainController])
    .controller('AccountController', ['$scope', AccountController]);


module.exports = angularModule;
