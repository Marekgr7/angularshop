
var controllersAdmin = angular.module('controllersAdmin', ['ngRoute'] );

controllersAdmin.controller('products',['$scope', '$filter','$http', function($scope,$filter,$http){

    // ZAPISZ NOWE DANE W BAZIE

    // POBIERZ DANE Z BAZY I WYSWIETL JE W WIDOKU


    // $http.get('model/produkty.json').
    // success(function(data){
    //     $scope.products = data;
    // }).
    // error(function(data){
    //     alert('blad');
    // });

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

controllersAdmin.controller('productEdit',['$scope', '$filter','$http', '$routeParams', function($scope,$filter,$http, $routeParams){

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

    $scope.saveChanges = function (product) {
        console.log('formularz zostal przeslany' + product);
    };

}]);

controllersAdmin.controller('productCreate',['$scope','$http', function($scope,$http){


    //TODO : CONNECT WITH API

    // $http({
    //     method: 'GET',
    //     url: 'model/produkty.json'
    // }).then(function successCallback(response) {
    //     $scope.products = response.data;
    //     $scope.product = $scope.products[$routeParams.id];
    // }, function errorCallback(response) {
    //     alert('blad');
    // });
    //
    // $scope.saveChanges = function (product) {
    //     console.log('formularz zostal przeslany' + product);
    // };

}]);

controllersAdmin.controller('users',['$scope','$http', '$routeParams', function($scope,$http, $routeParams){

    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'model/users.json'
    }).then(function successCallback(response) {
        $scope.users = response.data;
    }, function errorCallback(response) {
        alert('blad');
    });


    //TODO : DELETE PRODUCT USING API FROM DATABASE

    $scope.delete = function(user, $index){
        $scope.users.splice($index , 1);
    };

}]);


controllersAdmin.controller('userEdit',['$scope', '$filter','$http', '$routeParams', function($scope,$filter,$http, $routeParams){

    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'model/users.json'
    }).then(function successCallback(response) {
        $scope.users = response.data;
        $scope.user = $scope.users[$routeParams.id];
    }, function errorCallback(response) {
        alert('blad');
    });

    $scope.saveChanges = function (user) {
        console.log('formularz zostal przeslany' + user);
    };

}]);

controllersAdmin.controller('userCreate',['$scope','$http', function($scope,$http){


    //TODO : CONNECT WITH API

    // $http({
    //     method: 'GET',
    //     url: 'model/produkty.json'
    // }).then(function successCallback(response) {
    //     $scope.products = response.data;
    //     $scope.product = $scope.products[$routeParams.id];
    // }, function errorCallback(response) {
    //     alert('blad');
    // });
    //
    // $scope.saveChanges = function (product) {
    //     console.log('formularz zostal przeslany' + product);
    // };

}]);


controllersAdmin.controller('orders',['$scope','$http', '$routeParams', function($scope,$http, $routeParams){

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