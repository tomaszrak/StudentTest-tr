angular.module("stApp.testSolution", [])
    .config(function ($stateProvider) {
        $stateProvider.state('solveTests', {
            url : '/solveTests',
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

            $scope.getTest();

            $scope.userAnswers = [];

            $scope.addAnswer = function(question){

            }

            $scope.submitTest = function () {
                for (var i = 0; i < $scope.questions.length; i++) {
                    if (angular.equals($scope.questions[i].type, "ONE_ANSWER")
                        || angular.equals($scope.questions[i].type, 'NUMBER')
                        || angular.equals($scope.questions[i].type, 'SHORT_ANSWER')) {

                        if(angular.equals($scope.questions[i].type, 'NUMBER')
                            || angular.equals($scope.questions[i].type, 'SHORT_ANSWER')){
                            $scope.answer = {
                                name : $scope.questions[i].questionAnswer
                            }
                        } else {
                            $scope.answer = $scope.questions[i].questionAnswer;
                        }

                        $scope.userAnswer = {
                            testId: $scope.test.id,
                            question: $scope.questions[i],
                            answer: $scope.answer,
                            user: $rootScope.user
                        }

                        $scope.questionAnswer = undefined;

                        $scope.userAnswers.push($scope.userAnswer);

                    } else {
                        $scope.questionAnswer = undefined;
                        for(var j = 0; j< $scope.questions[i].answers.length; j++){
                            if($scope.questions[i].answers[j].marked){
                                $scope.userAnswer = {
                                    testId: $scope.test.id,
                                    question: $scope.questions[i],
                                    answer: $scope.questions[i].answers[j],
                                    user: $rootScope.user
                                }
                                $scope.userAnswers.push($scope.userAnswer);
                                $scope.questions[i].answers[j].marked = undefined;
                            }

                        }
                    }

                }

                TestSrv.solvedTest($scope.userAnswers)
                    .success(function () {
                        alert.add("Test was resolved! Your result will be later", "success", 4000)
                    }).error(function () {
                        alert.add("ERROR", "danger", 6000);
                    })

            }

        }])