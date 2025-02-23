// uruchomienie js w trybie strict ( wywala wszystkie bledy )
'use strict';


var app = angular.module('app', ['ngRoute','angular-storage','controllerNavigation','controllersAdmin','controllersSite','myServices'] );

app.config(['$routeProvider','$httpProvider', function($routeProvider,$httpProvider){

    //Admin products //
    $routeProvider.when('/admin/products', {
        controller : 'products',
        templateUrl: 'partials/admin/products.html'
    });

    $routeProvider.when('/admin/product/edit', {
        controller : 'productEdit',
        templateUrl : 'partials/admin/product-edit.html'
    });

    $routeProvider.when('/admin/product/:id', {
        controller : 'productCreate',
        templateUrl : 'partials/admin/product-create.html'
    });

    //Admin users//

    $routeProvider.when('/admin/users', {
        controller : 'users',
        templateUrl : 'partials/admin/users.html'
    });

    $routeProvider.when('/admin/user/edit/:id', {
        controller : 'userEdit',
        templateUrl : 'partials/admin/user-edit.html'
    });

    $routeProvider.when('/admin/user/:id', {
        controller : 'userCreate',
        templateUrl : 'partials/admin/user-create.html'
    });


    //Admin orders//

    $routeProvider.when('/admin/orders', {
        controller : 'orders',
        templateUrl : 'partials/admin/orders.html'
    });

    //Site Products//

    $routeProvider.when('/products', {
        controller : 'siteProducts',
        templateUrl: 'partials/site/products.html'
    });

    $routeProvider.when('/product/:id', {
        controller : 'siteProduct',
        templateUrl : 'partials/site/product.html'
    });



    //Site Cart//

    $routeProvider.when('/cart', {
        controller : 'cartCtrl',
        templateUrl: 'partials/site/cart.html'
    });

    //Site Orders//

    $routeProvider.when('/orders', {
        controller: 'siteOrders',
        templateUrl: 'partials/site/orders.html'
    });

    $routeProvider.when('/order', {
        controller: 'siteOrder',
        templateUrl: 'partials/site/order.html'
    })

    //Login & Register //

    $routeProvider.when('/login', {
        controller: 'login',
        templateUrl: 'partials/site/login.html'
    });

    $routeProvider.when('/register', {
        controller: 'register',
        templateUrl: 'partials/site/register.html'
    });


    //default//

    $routeProvider.otherwise({
        redirectTo: '/products'
    });

}]);



