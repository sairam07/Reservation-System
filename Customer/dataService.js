(function() {
    'use strict';

    angular
        .module('reservation')
        .service('dataService', dataService);

    dataService.$inject = ['$http', '$q'];

    function dataService ($http, $q) {

        var self = this;

        self.putcustomer = function (customer) {
            var defer = $q.defer();

            $http
                .post(' http://localhost:8080/Restaurant/app/customer', customer)
                .then(function (response) {
                    defer.resolve(response.data);
                }, function (error) {
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.getuser = function (id) {
            var defer = $q.defer();
            $http
                .get('http://localhost:8080/Restaurant/app/customer/' + id)
                .then(function (response) {
                    defer.resolve(response.data);
                }, function (error) {
                    defer.reject(error.status);
                });
            return defer.promise;
        };

        self.updatecust = function (custup, id) {
            var defer = $q.defer();
            console.log(custup);
            $http
                .put(' http://localhost:8080/Restaurant/app/customer/' + id, custup)
                .then(function (response) {
                    defer.resolve(response.data);
                }, function (error) {
                    defer.reject(error.status);
                });
            return defer.promise;
        }
    }


})();