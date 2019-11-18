
var controllersSite = angular.module('controllersSite', ['ngRoute'] );

controllersAdmin.controller('siteProducts',['$scope', '$filter','$http', function($scope,$filter,$http){


    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'model/produkty.json'
    }).then(function successCallback(response) {
        $scope.products = response.data;
    }, function errorCallback(response) {
        alert('blad');
    });

    $scope.delete = function(product, $index){
        //TODO : DELETE PRODUCT USING API FROM DATABASE
        $scope.products.splice($index , 1);
    };

}]);

controllersSite.controller('siteProduct',['$scope', '$filter','$http', '$routeParams', function($scope,$filter,$http, $routeParams){

    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'model/produkty.json'
    }).then(function successCallback(response) {
        $scope.products = response.data;
        $scope.product = $scope.products[$routeParams.id];
    }, function errorCallback(response) {
        alert('blad');
    });


}]);


controllersSite.controller('siteOrders',['$scope','$http', '$routeParams', function($scope,$http, $routeParams){

    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'model/orders.json'
    }).then(function successCallback(response) {
        $scope.orders = response.data;
    }, function errorCallback(response) {
        alert('blad');
    });


    //TODO : DELETE ORDERS USING API FROM DATABASE

    $scope.delete = function(order, $index){
        $scope.orders.splice($index , 1);
    };

    $scope.changeStatus = function(order, $index){
        if(order.status == 0){
            order.status = 1;
        }
        console.log(order.status);
    };


}]);