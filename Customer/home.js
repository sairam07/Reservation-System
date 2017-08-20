(function(){
    'use strict';

    angular
        .module('reservation', ['ngRoute'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];
    function moduleConfig ($routeProvider) {

        $routeProvider
            .when('/guest', {
                templateUrl: 'customer.tmpl.html',

            })
            .when('/owner', {
                templateUrl: 'ownerlogin.tmpl.html',
                controller: 'OwnerLoginController',
                controllerAs: 'loginVm'
            })
            .when('/changereserve', {
                templateUrl: 'changereserve.tmpl.html',
                controller: 'UpdateController',
                controllerAs: 'updateVm'

            })
            .when('/updatereserve/:id', {
                templateUrl: 'updatereserve.tmpl.html',
                controller: 'UpdateCustomerController',
                controllerAs: 'updatecVm'

            })

            .otherwise({
                redirectTo: '/guest'
            })
    }
})();