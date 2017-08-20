(function() {
    'use strict';

    angular
        .module('reservation')
        .controller('CustomerController', CustomerController)
    CustomerController.$inject=['dataService','$window']
    function CustomerController(dataService,$window) {
        var customerVm = this;
        customerVm.customer={};

        /*console.log(customerVm.customer);*/
        /*dataService.putcustomer(customerVm.customer);*/
        customerVm.addReservation = function(resObj){
             dataService
                .putcustomer(resObj)
                 .then(function(response){
                     console.log(response.cust_id);
                    $window.alert("your id is:"+response.cust_id)
                 });

            console.log("ResCreate up and running!");
        }
    }
})();