
var controllersAdmin = angular.module('controllersAdmin', ['ngRoute'] );

controllersAdmin.controller('products',['$scope', '$filter','$http', function($scope,$filter,$http){

    $http({
        method: 'GET',
        url: 'api/products/all'
    }).then(function successCallback(response) {
        $scope.products = response.data;
    }, function errorCallback(response) {
        console.log("error");
    });

    $scope.delete = function(product, id,$index) {

         $scope.products.splice($index, 1);

        $http({
            method: 'DELETE',
            url: 'api/products',
            params: {
                id: id
            }
        }).then(function successCallback(response) {
            console.log("success - produkt usuniety")
        }, function errorCallback(response) {
            console.log("error");
        });
    };

    $scope.getProduct = function(id){
        controllersAdmin.productId = id;
    };

}]);

controllersAdmin.controller('productEdit',['$scope', '$filter','$http', '$routeParams', function($scope,$filter,$http, $routeParams){

    console.log("dziala " + controllersAdmin.productId);

        $http({
           method: 'GET',
           url: 'api/products',
           params : {
               id : controllersAdmin.productId
           }
       }).then(function success(response) {
           $scope.product = response.data;
       }, function error(response){
           console.log("nie udalo sie pobrac produktu");
       });

        $scope.saveChanges = function (product) {

            console.log("funkcja save ");
            $http({
                method: 'POST',
                url: 'api/products',
                data : product
            }).then(function successCallback(response) {
                alert("Produkt został zmodyfikowany");
                console.log("success - produkt dodany");
            }, function errorCallback(response) {
                console.log("error - nie udało się zmodyfikowac produktu");
            });
        }

}]);

controllersAdmin.controller('productCreate',['$scope','$http', function($scope,$http){

    $scope.createProduct = function (product) {

        console.log(product);
        $http({
            method: 'POST',
            url: 'api/products',
            data : product
        }).then(function successCallback(response) {
            alert("Produkt został dodany");
            console.log("success - produkt dodany");
        }, function errorCallback(response) {
            console.log("error - nie udało się dodać produktu");
        });

        // $http.post('api/products', product).then(function (response) {
        //
        //     console.log("produkt zostal dodany");
        //
        // }, function (response) {
        //
        //     console.log("error - nie udalo sie dodac produktu")
        //
        // });

    }

}]);

controllersAdmin.controller('users',['$scope','$http', '$routeParams', function($scope,$http, $routeParams){

    $http({
        method: 'GET',
        url: 'api/users/all'
    }).then(function successCallback(response) {
        $scope.users = response.data;
    }, function errorCallback(response) {
        alert('blad');
    });

    $scope.delete = function (user , id ,$index) {

        $scope.users.splice($index , 1);

        $http({
            method: 'DELETE',
            url: 'api/users',
            params : {
                id : id
            }
        }).then(function successCallback(response) {
            alert("Użytkownik został usunięty");
        }, function errorCallback(response) {
            alert("error - nie udalo się usunąć użytkownika");
        });
    }

    $scope.getUser = function(id){
        controllersAdmin.userId = id;
    }

}]);


controllersAdmin.controller('userEdit',['$scope', '$filter','$http', '$routeParams', function($scope,$filter,$http, $routeParams){


    $scope.saveChanges = function(user) {
        console.log('formularz zostal przeslany' + user);
    };

    $scope.newUser={};

    $http({
        method: 'GET',
        url: 'api/users',
        params : {
            id : controllersAdmin.userId
        }
    }).then(function success(response) {
        $scope.user = response.data;
    }, function error(response){
        console.log("nie udalo sie pobrac produktu");
    });

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
// LOGIN AND REGISTER CONTROLLERS

controllersAdmin.controller('login', ['$scope', '$http', function ($scope , $http) {
    $scope.user = {};

    $scope.formSubmit = function (user) {

            $http({
                method: 'POST',
                data: user
            });
    }
}]);

controllersAdmin.controller('register', ['$scope', '$http', function ($scope , $http) {
    //TODO : uwierzytelnic z  baza danych

    $scope.user = {};

    $scope.formSubmit = function (user) {

        console.log(user);

        $http({
            method: 'POST',
            url: 'api/users',
            data : user
        }).then(function successCallback(response) {
            alert("Produkt został dodany");
            console.log("Zostałeś zarejestrowany !");
        }, function errorCallback(response) {
            console.log("error - nie udało się dodać produktu");
        });
    }

}]);