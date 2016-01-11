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
                        $scope.questions = $scope.test.questions;
                    }).error(function () {
                        alert.add("Error", "danger", 6000);
                    });
            }

            $scope.questionAnswer = false;

            $scope.getTest();



            $scope.addAnswer = function(answer, question){
                $scope.question.questionAnswer.push($scope.answer);
            }

            $scope.userAnswers = [];
4
            $scope.submitTest = function(){
                for(var i = 0; i<$scope.questions.length; i++){
                    $scope.userAnswer = {
                        testId : $scope.test.id,
                        question : $scope.questions[i],
                        answer : $scope.questions[i].questionAnswer,
                        user : $rootScope.user
                    }
                    $scope.userAnswers.push($scope.userAnswer);
                }

                TestSrv.solvedTest($scope.userAnswers)
                    .success(function(){

                    }).error(function(){
                        alert.add("ERROR", "danger", 6000);
                    })

            }

        }])