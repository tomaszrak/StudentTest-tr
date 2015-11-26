angular.module('st.answerModal', [])
    .service('AnswerModalSrv', function($modal){
        return {
            show : function(questionId){
                return $modal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/answerModal.html',
                    size : 'md',
                    resolve : {
                        questionId : function(){
                            return questionId;
                        }
                    },
                    controller : function($modalInstance, $scope, $rootScope, questionId, AnswerSrv){
                        $scope.questionId = questionId;

                        $scope.save = function(answer){
                            AnswerSrv.answer(answer);
                        }

                        $scope.cancel = function(){
                            $modalInstance.close();
                        }
                    }
                })

            }
        }

    })