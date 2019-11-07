var controllers = angular.module('controllers', ['ngRoute'] );

controllers.controller('products',['$scope', '$filter','$http', function($scope,$filter,$http){

    // ZAPISZ NOWE DANE W BAZIE

    // POBIERZ DANE Z BAZY I WYSWIETL JE W WIDOKU


    // $http.get('model/produkty.json').
    // success(function(data){
    //     $scope.products = data;
    // }).
    // error(function(data){
    //     alert('blad');
    // });

    $http({
        method: 'GET',
        url: 'model/produkty.json'
    }).then(function successCallback(response) {
        $scope.products = response.data;
    }, function errorCallback(response) {
        alert('blad');
    });

}]);

controllers.controller('product',['$scope', '$filter','$http', '$routeParams', function($scope,$filter,$http, $routeParams){

    $http({
        method: 'GET',
        url: 'model/produkty.json'
    }).then(function successCallback(response) {
        $scope.products = response.data;
        $scope.products = $scope.products[$routeParams.id];
    }, function errorCallback(response) {
        alert('blad');
    });

}]);