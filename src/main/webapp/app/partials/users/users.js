/**
 * Created by ROLO on 03.11.2015.
 */
angular.module('stApp.users', ['ui.router'])
    .config(['$stateProvider', function($stateProvider){
        $stateProvider.state('users', {
            url : '/users',
            templateUrl : 'app/partials/users/users.html',
            controller : 'UserCtrl'
        })
    }])
    .controller('UserCtrl', function(UserSrv, $scope){

        //$scope.$on('viewContentLoaded', function(){
        //    $scope.getUsers();
        //})

        $scope.getUsers = function(){
            var promise = UserSrv.users()
                .success(function(result){
                    $scope.users = result;
                })
                .error(function(){

                })
        }

        $scope.getUsers();

        $scope.addUser = function(){
            var ptomise = UserSrv.user($scope.user)
                .success(function(){
                    $scope.getUsers();
                })
                .error(function(){

                });
        }

    })
