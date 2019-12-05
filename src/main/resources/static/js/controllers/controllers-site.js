
var controllersSite = angular.module('controllersSite', ['ngRoute'] );

controllersSite.controller('siteProducts',['$scope', '$filter','$http','cartSrv', function($scope,$filter,$http,cartSrv){


    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'api/products/all'
    }).then(function successCallback(response) {
        $scope.products = response.data;
    }, function errorCallback(response) {
        console.log("blad");
    });


    $scope.addToCart = function (product) {
        cartSrv.add(product);
    }

}]);

controllersSite.controller('siteProduct',['$scope', '$filter','$http', '$routeParams','cartSrv', function($scope,$filter,$http, $routeParams,cartSrv){

    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'api/products/all'
    }).then(function successCallback(response) {
        $scope.products = response.data;
        $scope.product = $scope.products[$routeParams.id];
    }, function errorCallback(response) {
        alert('blad');
    });

    $scope.addToCart = function (product) {
        cartSrv.add(product);
    }

}]);

controllersSite.controller('siteOrders',['$scope','$http', '$routeParams', function($scope,$http, $routeParams){

    $scope.cartList = null;

    $http({
        method: 'GET',
        url: 'api/order/userorder'
    }).then(function successCallback(response) {
        $scope.orderList = response.data;
    }, function errorCallback(response) {
        alert('blad');
    });

    $http({
        method : 'GET',
        url: 'api/order/usercart'
    }).then(function successCallback(response) {
        $scope.cartList = response.data;
    }, function errorCallback(response){
        console.log('nie udalo sie pobrac koszyka');
    });

    $scope.getOrderId = function (id) {
        controllersSite.orderId = id;
    }

}]);

//Cart controllers
controllersSite.controller('cartCtrl',['$scope','$http','cartSrv','usernameSrv', function($scope,$http, cartSrv,usernameSrv){

    $scope.cart = cartSrv.show();

    $scope.lenght = function(){
        return $scope.cart.lenght;
    };

    $scope.emptyCart = function () {
        $scope.cart = null;
        cartSrv.empty();
    };

    $scope.init = function(){
        $scope.cart = cartSrv.show();
    };

    $scope.total = function () {
        var total = 0;
        angular.forEach($scope.cart, function (item) {
            total += item.qty * item.price;
        });
        return total;
    };

    $scope.removeItem = function ($index) {
        $scope.cart.splice($index,1);
        cartSrv.removeItem($scope.cart);
    };

    $scope.setOrder = function ($event) {
        console.log('zamowienie');

        var loggedIn = true;

        if(!loggedIn){
            $scope.alert = {type : 'warning', msg: 'Musisz się najpierw zalogować ! '};
            $event.preventDefault();
            return false;
        }

        $scope.alert = {type : 'success', msg: 'Zamówienie złożone, nie odświeżaj strony... Trwa przekierowywanie do płatności '};
        $scope.emptyCart();

        $event.preventDefault();

        console.log(cartSrv.show());

        $scope.cartToSent = cartSrv.show();


        $http({
            method : 'POST',
            url : 'api/order/cart',
            data : $scope.cartToSent
        }).then(function success(response) {
            console.log('koszyk zostal wyslany poprawnie');
        }, function error() {
            console.log('blad nie udalo sie wyslac koszyka');
        });

        //$('#paypalForm').submit();
    };

    $scope.$watch(function () {
        cartSrv.update($scope.cart);
    });

}]);

//orders controllers
controllersSite.controller('siteOrder',['$scope','$http', '$routeParams', function($scope,$http, $routeParams){

    console.log(controllersSite.orderId);
    $http({
        method : 'GET',
        url: 'api/order/usercart',
        params : {
            id : controllersSite.orderId
        }
    }).then(function successCallback(response) {
        $scope.cartList = response.data;
        console.log($scope.cartList);
    }, function errorCallback(response){
        console.log('nie udalo sie pobrac szczegółów zamówienia  ');
    });

}]);