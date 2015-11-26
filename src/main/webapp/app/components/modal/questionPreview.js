angular.module('stApp.questionPreview', [])
    .service('QuestionPreviewSrv', function ($modal) {
        return {
            show: function (questionId) {
                return $modal.open({
                        animation: true,
                        templateUrl: 'app/components/modal/questionPreview.html',
                        size : 'md',
                        resolve: {
                            questionId: function () {
                                return questionId
                            }
                        },
                        controller : function($scope, QuestionSrv, $rootScope, $modalInstance){
                            $scope.close = function(){
                                $modalInstance.close();
                            }
                        }
                    }
                )
            }
        }
    })