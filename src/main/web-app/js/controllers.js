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
