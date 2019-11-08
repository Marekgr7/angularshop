// uruchomienie js w trybie strict ( wywala wszystkie bledy )
'use strict';


var app = angular.module('app', ['ngRoute','myCtrls'] );

app.config(['$routeProvider','$httpProvider', function($routeProvider,$httpProvider){

    //products //
    $routeProvider.when('/products', {
        controller : 'products',
        templateUrl: 'partials/products.html'
    });

    $routeProvider.when('/product/edit/:id', {
        controller : 'productEdit',
        templateUrl : 'partials/product-edit.html'
    });

    $routeProvider.when('/product/:id', {
        controller : 'productCreate',
        templateUrl : 'partials/product-create.html'
    });

    //users//

    $routeProvider.when('/users', {
        controller : 'users',
        templateUrl : 'partials/users.html'
    });

    $routeProvider.when('/user/edit/:id', {
        controller : 'userEdit',
        templateUrl : 'partials/user-edit.html'
    });

    $routeProvider.when('/user/:id', {
        controller : 'userCreate',
        templateUrl : 'partials/user-create.html'
    });

    $routeProvider.otherwise({
        redirectTo: '/home'
    });

}]);



