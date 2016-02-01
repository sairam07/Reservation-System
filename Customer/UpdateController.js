(function(){

    'use strict';

    angular
        .module('reservation')
        .controller('UpdateController', UpdateController);

    UpdateController.$inject = ['dataService','$location','$window','$routeParams'];

    function UpdateController(dataService,$location,$window,$routeParams) {
        var updateVm = this;

        updateVm.customer = [];
        updateVm.select=function(id){
            id=updateVm.cust_id;
            console.log(updateVm.cust_id);
            dataService
                .getuser(id)
                .then(function(data) {
                    updateVm.customer = data;
                    id=updateVm.customer.cust_id;
                    console.log(data);
                    $location.path("/updatereserve/"+id);
                        console.log(updateVm.customer);
                }, function(error) {
                    console.log(error);
                    $window.alert("id doesn't exist");
                }
                );


        }



        console.log('CustomerUpdateController');
    }











})();