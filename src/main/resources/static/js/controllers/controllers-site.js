
var controllersSite = angular.module('controllersSite', ['ngRoute'] );

controllersAdmin.controller('siteProducts',['$scope', '$filter','$http','cartSrv', function($scope,$filter,$http,cartSrv){


    //TODO: CONNECT WITH API

    $http({
        method: 'GET',
        url: 'model/produkty.json'
    }).then(function successCallback(response) {
        $scope.products = response.data;
    }, function errorCallback(response) {
        alert('blad');
    });


    $scope.addToCart = function (product) {
        cartSrv.add(product);
    }

}]);

controllersSite.controller('siteProduct',['$scope', '$filter','$http', '$routeParams','cartSrv', function($scope,$filter,$http, $routeParams,cartSrv){

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

    $scope.addToCart = function (product) {
        cartSrv.add(product);
    }

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

//Cart controllers
controllersSite.controller('cartCtrl',['$scope','$http','cartSrv', function($scope,$http, cartSrv){

    $scope.cart = cartSrv.show();


    $scope.lenght = function(){
        return $scope.cart.lenght;
    };



    console.log($scope.cart);
    console.log($scope.cart.lenght);

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

    // cartSrv.show();

    $scope.removeItem = function ($index) {
        $scope.cart.splice($index,1);
        cartSrv.removeItem($scope.cart);
    };

    $scope.setOrder = function ($event) {
        console.log('zamowienie');

        //TODO: sprawdz czy uzytkownik jest zalogowany
        var loggedIn = true;

        if(!loggedIn){
            $scope.alert = {type : 'warning', msg: 'Musisz się najpierw zalogować ! '};
            $event.preventDefault();
            return false;
        }

        $scope.alert = {type : 'success', msg: 'Zamówienie złożone, nie odświeżaj strony... Trwa przekierowywanie do płatności '};
        $scope.emptyCart();
        //TODO: zapisz zamowienie do bazy danych

        $event.preventDefault();
        $('#paypalForm').submit();
    };

    $scope.$watch(function () {
        cartSrv.update($scope.cart);
    })

}]);