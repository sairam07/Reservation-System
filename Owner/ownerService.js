(function() {
    'use strict';

    angular
        .module('reservation')
        .service('ownerService', ownerService);

    ownerService.$inject = ['$http', '$q'];

    function ownerService ($http, $q) {

        var self = this;

        self.getReservation = function () {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/Restaurant/app/customer/status')
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.getcustomer = function () {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/Restaurant/app/customer')
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };
        self.getUserById = function (id) {
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/Restaurant/app/customer/' + id)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };
        self.puttable = function (table,id) {
            var defer = $q.defer();
            console.log(table);
            $http
                .post('http://localhost:8080/Restaurant/app/reservation/'+id,table)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };
        self.getTable = function () {
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/Restaurant/app/reservation')
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };
        self.updatetable = function (table,id) {
            var defer = $q.defer();
            console.log(table);
            $http
                .put('http://localhost:8080/Restaurant/app/reservation/'+id,table)
                .then(function (response){
                    defer.resolve(response.data);
                }, function (error){
                    defer.reject(error.status);
                });
            return defer.promise;
        };

    }
})();