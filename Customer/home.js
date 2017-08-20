(function(){
    'use strict';

    angular
        .module('reservation', ['ngRoute'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];
    function moduleConfig ($routeProvider) {

        $routeProvider
            .when('/guest', {
                templateUrl: 'Customer/customer.tmpl.html',

            })
            .when('/owner', {
                templateUrl: 'Customer/ownerlogin.tmpl.html',
                controller: 'OwnerLoginController',
                controllerAs: 'loginVm'
            })
            .when('/changereserve', {
                templateUrl: 'Customer/changereserve.tmpl.html',
                controller: 'UpdateController',
                controllerAs: 'updateVm'

            })
            .when('/updatereserve/:id', {
                templateUrl: 'Customer/updatereserve.tmpl.html',
                controller: 'UpdateCustomerController',
                controllerAs: 'updatecVm'

            })

            .otherwise({
                redirectTo: '/guest'
            })
    }
})();
