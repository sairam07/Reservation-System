(function(){
    'use strict';

    angular
        .module('reservation', ['ngRoute'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];
    function moduleConfig ($routeProvider) {

        $routeProvider
            .when('/reserveList', {
                templateUrl: 'reservlist.tmpl.html',
                controller: 'ReserveListController',
                controllerAs: 'reserveListVm'
            })
            .when('/tableList', {
                templateUrl: 'tablelist.tmpl.html',
                controller: 'TableListController',
                controllerAs: 'tableListVm'
            })
            .when('/settings', {
                templateUrl: 'settings.tmpl.html',


            })
            .when('/custList', {
                templateUrl: 'custlist.tmpl.html',
                controller: 'CustomerListController',
                controllerAs: 'customerListVm'
            })
            .when('/actions/:id', {
                templateUrl: 'actions.tmpl.html',
                controller: 'ActionController',
                controllerAs: 'actionVm'
            })
            .when('/actionsassign/:id', {
                templateUrl: 'actionassign.tmpl.html',
                controller: 'ActionController',
                controllerAs: 'actionVm'
            })
            .when('/actionsupdate/:id', {
                templateUrl: 'actionupdate.tmpl.html',
                controller: 'ActionController',
                controllerAs: 'actionVm'
            })


            .otherwise({
                redirectTo: '/reserveList'
            })
    }
})();