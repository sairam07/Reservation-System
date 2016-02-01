(function() {
    'use strict';

    angular
        .module('reservation')
        .controller('ReserveListController', ReserveListController);

    ReserveListController.$inject = ['ownerService'];

    function ReserveListController(ownerService) {
        var reserveListVm = this;

        reserveListVm.reservetion = [];

        ownerService
            .getReservation()
            .then(function(data) {
                reserveListVm.reservation = data;
            }, function(error) {
                console.log(error);
            });

        console.log('ReserveListController');
    }
})();