'use strict';

function ArchaiusCtrl($scope, $http) {
    $scope.checkCount = 0;
    $scope.archTimer = setInterval(function(){
        $http.get("/ws/archaius").success(function(data){
            $scope.data = data;
        });
        $scope.checkCount++;
    }, 1000);

    $scope.stopCheck = function(){
        clearInterval($scope.archTimer);
    };
}
//ArchaiusCtrl.$inject = ['$scope', '$http'];

function HystrixCtrl($scope, $http) {
    $scope.result = {
        "status": "info",
        "value": "No result"
    };

    $scope.sendHystrixRequest = function(requestType){
        $http.get("/ws/hystrix?type="+requestType).success(function(data){
            $scope.result = data;
        });

    }

    $scope.normalRequest = function(){
        $scope.sendHystrixRequest("normal");
    }

    $scope.failedRequest = function(){
        $scope.sendHystrixRequest("fail");
    }

    $scope.delayedRequest = function(){
        $scope.sendHystrixRequest("delay");
    }
}
