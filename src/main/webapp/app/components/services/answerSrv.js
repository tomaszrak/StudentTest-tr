angular.module('stApp.answerSrv', [])
    .service('AnswerSrv', function($http){
        return {
            answer : function(answer){
                return $http.post('../rest/answer/', answer);
            }
        }
    })