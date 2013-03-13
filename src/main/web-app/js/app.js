'use strict';

angular.module('netflixApp', []).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when('/archaius', {templateUrl: 'archaius.html',   controller: ArchaiusCtrl}).
            when('/hystrix', {templateUrl: 'hystrix.html', controller: HystrixCtrl}).
            otherwise({redirectTo: '/archaius'});
    }], ['$locationProvider', function($locationProvider){
        $locationProvider.html5Mode(true);
    }]);