angular.module('st.answerModal', [])
    .service('AnswerModalSrv', function($modal){
        return {
            show : function(question){
                return $modal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/answerModal.html',
                    size : 'md',
                    resolve : {
                        question : function(){
                            return question;
                        }
                    },
                    controller : function($modalInstance, $scope, $rootScope, question, AnswerSrv, alert){
                        $scope.question = question;

                        $scope.save = function(answer){
                            answer.question = $scope.question;
                            AnswerSrv.answer(answer)
                                .success(function(){
                                    alert.add($rootScope.msg['st.answerAdded'], 'success', 4000)
                                }).error(function(){

                                });
                        }

                        $scope.cancel = function(){
                            $modalInstance.close();
                        }
                    }
                })

            }
        }

    })