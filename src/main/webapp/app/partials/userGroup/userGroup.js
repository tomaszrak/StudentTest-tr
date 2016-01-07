angular.module('stApp.userGroup', [])
    .config(function ($routeProvider) {
        $routeProvider.when('/userGroup', {
            templateUrl: 'app/partials/userGroup/userGroup.html',
            controller: 'UserGroupCtrl'
        })
    })
    .controller('UserGroupCtrl', ['$scope', 'UserGroupSrv', '$rootScope', 'alert', 'UserGroupEditSrv', 'TestAssignSrv',
        function ($scope, UserGroupSrv, $rootScope, alert, UserGroupEditSrv, TestAssignSrv) {


            $scope.getAll = function(){
                UserGroupSrv.userGroups()
                    .success(function(result){
                        $scope.groups = result;
                    }).error(function(){
                        alert.add("Error", "danger", 6000);
                    })
            }
            $scope.getAll();

            $scope.addUserGroup = function(){
                UserGroupEditSrv.show($scope.getAll, undefined);
            }

            $scope.editGroup = function(group){
                UserGroupEditSrv.show($scope.getAll, group);
            }

            $scope.assignTests = function(group){
                TestAssignSrv.show(group, $scope.getAll);
            }

            $scope.removeGroup = function(group){
                UserGroupSrv.groupToRemove(group)
                    .success(function(){
                        $scope.getAll();
                    }).error(function(){
                        alert.add("Error", "danger", 6000);
                    });
            }

        }])