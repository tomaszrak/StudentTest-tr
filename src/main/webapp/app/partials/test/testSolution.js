angular.module("stApp.testSolution", [])
    .config(function ($routeProvider) {
        $routeProvider.when('/solveTests', {
            templateUrl: 'app/partials/test/testSolution.html',
            controller: 'TestSolutionCtrl'
        })
    })
    .controller("TestSolutionCtrl", ['$scope', '$rootScope', 'TestSrv', 'alert',
        function ($scope, $rootScope, TestSrv, alert) {

            $scope.getTest = function () {
                TestSrv.testToSolve($rootScope.user.id)
                    .success(function (result) {
                        $scope.test = result;
                    }).error(function () {
                        alert.add("Error", "danger", 6000);
                    });
            }

            $scope.getTest();

        }])