var myServices = angular.module('myServices',[]);

myServices.factory('cartSrv',['store',function ( store ) {

    new function() {
     console.log('konstruktor');
    };

    if(store.get('cart')) {
        var cart = store.get('cart');
    } else {
        var cart = [];
    }

    cart.show = function () {
      return cart;
    };

    cart.add = function (product) {

        if(!cart.length){
            product.qty = 0;
            cart.push(product);
        }

        var addNew = true;
        angular.forEach( cart , function (value, key) {

            if(value.name == product.name){
                addNew = false;
                cart[key].qty++;
            }
        });

        if(addNew)
        {
            product.qty = 1;
            cart.push(product);
        }

        store.set('cart', cart);
         console.log(cart);
    };

    cart.empty = function () {
        store.remove('cart');
        cart.lenght = 0;
    };

    cart.removeItem = function (newCart) {
        store.set('cart',newCart);
    };

    cart.update = function (newCart) {
        store.set('cart',newCart);
    };

    return cart;
}]);