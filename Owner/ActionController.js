(function() {
    'use strict';

    angular
        .module('reservation')
        .controller('ActionController', ActionController);

    ActionController.$inject = ['ownerService', '$routeParams','$window'];

    function ActionController(ownerService, $routeParams, $window) {
        var actionVm = this;

        actionVm.detail = null;

        ownerService
            .getUserById($routeParams.id)
            .then(function(data){
                actionVm.detail = data;
            }, function (error){
                console.log(error);
            })
        console.log('ActionController');

        actionVm.addtable = function(resobj,id){
            actionVm.table=resobj;
            actionVm.table.date=actionVm.detail.date;
            actionVm.table.time=actionVm.detail.time;
            ownerService
                .puttable(actionVm.table,$routeParams.id)
                .then(function(response){
                    console.log(response.tab_id);
                    $window.alert("Table Assigned");
                });

                    }
        actionVm.updatetable = function(resobj,id){
            actionVm.table=resobj;

            console.log('Updatetable');
            ownerService
                .updatetable(actionVm.table,$routeParams.id)
                .then(function(response){
                    console.log(response.tab_id);
                    $window.alert("Record Updated");
                });

        }

    }
})();