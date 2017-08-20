(function(){

    'use strict';

    angular
        .module('reservation')
        .controller('CustomerListController', CustomerListController);

    CustomerListController.$inject = ['ownerService'];

    function CustomerListController(ownerService) {
        var customerListVm = this;

        customerListVm.customer = [];

        ownerService
            .getcustomer()
            .then(function(data) {
                customerListVm.customer = data;
            }, function(error) {
                console.log(error);
            });

        console.log('CustomerListController');
    }











})();