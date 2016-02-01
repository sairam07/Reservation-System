(function(){

    'use strict';

    angular
        .module('reservation')
        .controller('UpdateCustomerController', UpdateCustomerController);

    UpdateCustomerController.$inject = ['dataService','$location','$window','$routeParams'];

    function UpdateCustomerController(dataService,$location,$window,$routeParams) {
        var updatecVm = this;

        updatecVm.customer = [];
        console.log($routeParams.id);
        dataService
            .getuser($routeParams.id)
            .then(function (data) {
                updatecVm.customer = data;
            }, function (error) {
                console.log(error);
            })
        console.log(updatecVm.customer);


        updatecVm.updatecust = function (resobj, id) {
            updatecVm.custup = resobj;

            console.log('Updatecustomer');
            console.log(updatecVm.custup);
            dataService
                .updatecust(updatecVm.custup, $routeParams.id)
                .then(function (response) {
                    console.log(response.cust_id);
                    $window.alert("Updated Successfully");

                });

        }

    }
})();
