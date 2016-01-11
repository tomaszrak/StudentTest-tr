angular.module('stApp.questionPreview', [])
    .service('QuestionPreviewSrv', function ($modal) {
        return {
            show: function (question) {
                return $modal.open({
                        animation: true,
                        templateUrl: 'app/components/modal/questionPreview.html',
                        size: 'md',
                        resolve: {
                            question: function () {
                                return question
                            }
                        },
                        controller: function ($scope, QuestionSrv, $rootScope, $modalInstance, question, alert) {
                            $scope.question = question;

                            $scope.correctAnswers = [];

                            $scope.correctAnswer = {
                                question: $scope.question.question
                            }

                            $scope.close = function () {
                                $modalInstance.close();
                            }

                            //$scope.getAnswers = function () {
                            //    QuestionSrv.byId($scope.question.question.id)
                            //        .success(function (result) {
                            //            $scope.question = result;
                            //        })
                            //}

                            //$scope.setMultipleAnswers = function () {
                            //    $scope.correctAnswers.push($scope.correctAnswer);
                            //}

                            $scope.save = function () {
                                if (angular.equals($scope.question.question.type, "ONE_ANSWER") ||
                                    angular.equals($scope.question.question.type, "NUMBER")) {

                                    $scope.correctAnswer.answer = $scope.question.question.correctAnswer;
                                    $scope.correctAnswers.push($scope.correctAnswer);
                                } else if(angular.equals($scope.question.question.type, "MULTIPLE")) {
                                    $scope.correctAnswer = {
                                        question : $scope.question.question
                                    }
                                    for(var i=0; i<$scope.question.question.answers.length; i++){
                                        if($scope.question.question.answers[i].correct){
                                            //$scope.correctAnswer.answer = $scope.question.answers[i];
                                            $scope.correctAnswers.push(
                                                {
                                                    question : $scope.question.question,
                                                    answer : $scope.question.question.answers[i]
                                                });
                                        }
                                    }
                                }
                                QuestionSrv.correctAnswers($scope.correctAnswers)
                                    .success(function () {

                                    }).error(function () {
                                        alert.add("ERROR", "danger", 6000);
                                    })
                            }

                           // $scope.getAnswers();

                        }
                    }
                )
            }
        }
    })