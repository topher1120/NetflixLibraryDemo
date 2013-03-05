'use strict';

function ArchaiusCtrl($scope, $http) {
    $http.get("/ws/archaius").success(function(data){
        $scope.data = data;
    });
}
//ArchaiusCtrl.$inject = ['$scope', '$http'];
