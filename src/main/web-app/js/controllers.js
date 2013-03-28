'use strict';
function ArchaiusCtrl($scope, $http) {
    'use strict';
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
    'use strict';

    $scope.alerts = [];

    $scope.sendHystrixRequest = function(requestType){
        $http.get("/ws/hystrix?type="+requestType).success(function(data){
            $scope.alerts.push(data);
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
//HystrixCtrl.$inject = ['$scope', '$http'];