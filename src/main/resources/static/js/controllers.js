var myCtrls = angular.module('myCtrls', ['ngRoute'] );

myCtrls.controller('products',['$scope', '$filter','$http', function($scope,$filter,$http){

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

myCtrls.controller('productEdit',['$scope', '$filter','$http', '$routeParams', function($scope,$filter,$http, $routeParams){

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

myCtrls.controller('productCreate',['$scope','$http', function($scope,$http){


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