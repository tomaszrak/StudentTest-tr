angular.module('stApp.testPreview', [])
    .service('TestPreviewSrv', function ($modal) {
        return {
            show : function(test){
                return $modal.open({
                    animation : true,
                    templateUrl : 'app/components/modal/testPreview.html',
                    size : 'md',
                    resolve : {
                        test : function(){
                            return test;
                        }
                    },
                    controller : function($scope, $rootScope, test, $modalInstance){

                        $scope.test = test;
                        $scope.questions = $scope.test.questions;

                        $scope.cancel = function(){
                            $modalInstance.close();
                        }

                    }
                })

            }
        }

    })