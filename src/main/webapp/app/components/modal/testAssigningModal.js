angular.module('stApp.testAssign', [])
    .service('TestAssignSrv', function($modal){
        return {
            show : function(group, getAll){
                return $modal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/testAssigning.html',
                    size : 'lg',
                    resolve : {
                        group : function(){
                            return group;
                        },
                        getAll : function(){
                            return getAll;
                        }
                    },
                    controller : function($scope, $rootScope, TestSrv, $modalInstance, UserGroupSrv, alert, group, getAll){
                        $scope.group =  group;

                        $scope.testsToAssign = [];

                        if(angular.isUndefined($scope.group.tests)){
                            $scope.group.tests = [];
                        } else {
                            $scope.testsToAssign = $scope.group.tests;
                        }

                        $scope.getAllTests = function(){
                            TestSrv.tests()
                                .success(function(result){
                                    $scope.tests = result;
                                });
                        }
                        $scope.getAllTests();

                        $scope.addTest = function($index, test){
                            $scope.testsToAssign.push(test);
                            $scope.tests.splice($index, 1);
                        }

                        $scope.save = function(){
                            $scope.group.tests = $scope.testsToAssign;

                            UserGroupSrv.userGroupToUpdate($scope.group)
                                .success(function(){
                                    $scope.close();
                                    getAll();
                                }).error(function(){
                                    alert.add("Error", "danger", 6000);
                                })
                        }

                        $scope.removeTest = function($index, test){
                            $scope.testsToAssign.splice($index, 1);
                            $scope.tests.push(test);
                        }

                        $scope.close = function(){
                            $modalInstance.close();
                        }

                    }
                })
            }

        }
    })