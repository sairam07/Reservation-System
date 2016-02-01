(function() {
    'use strict';

    angular
        .module('reservation')
        .controller('TableListController', TableListController);

    TableListController.$inject = ['ownerService'];

    function TableListController(ownerService) {
        var tableListVm = this;

        tableListVm.tabdata = [];

        ownerService
            .getTable()
            .then(function(data) {
                tableListVm.tabdata = data;
            }, function(error) {
                console.log(error);
            });

        console.log('TableListController');
    }
})();