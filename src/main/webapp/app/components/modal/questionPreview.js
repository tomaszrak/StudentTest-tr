angular.module('stApp.questionPreview', [])
    .service('QuestionPreviewSrv', function ($modal) {
        return {
            show: function (question) {
                return $modal.open({
                        animation: true,
                        templateUrl: 'app/components/modal/questionPreview.html',
                        size : 'md',
                        resolve: {
                            question: function () {
                                return question
                            }
                        },
                        controller : function($scope, QuestionSrv, $rootScope, $modalInstance, question, AnswerSrv){
                            $scope.question = question;

                            $scope.close = function(){
                                $modalInstance.close();
                            }

                            $scope.getAnswers = function(){
                                AnswerSrv.answersByQuestionId($scope.question.id)
                                    .success(function(result){
                                        $scope.answers = result;
                                    })
                            }

                            $scope.getAnswers();

                        }
                    }
                )
            }
        }
    })