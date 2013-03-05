'use strict';

angular.module('netflixApp', []).
    config(['$routeProvider', function($routeProvider) {
        $routeProvider.
            when('/archaius', {templateUrl: 'archaius.html',   controller: ArchaiusCtrl}).
            //when('/edit/:eventId', {templateUrl: 'editView', controller: EventEditCtrl}).
            otherwise({redirectTo: '/archaius'});
    }], ['$locationProvider', function($locationProvider){
        $locationProvider.html5Mode(true);
    }]);