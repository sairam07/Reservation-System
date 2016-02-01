(function(){
    'use strict';
    angular
        .module('reservation')
        .controller('OwnerLoginController',OwnerLoginController)
    OwnerLoginController.$inject=['$location','$window']
        function OwnerLoginController($location,$window){
            var loginVm = this;

            loginVm.login=function(obj){
                var uname=obj.id;
                var pass=obj.pass;
                var path="D:\Rresevation\Owner\owner.html";
                if(uname=="venkata"&& pass=="2093"){
                    $window.location.href="/Rresevation/Owner/owner.html#/reserveList";


                }
                else{
                    $window.alert("Invalid Username or password");
                }
            }

        }




})();