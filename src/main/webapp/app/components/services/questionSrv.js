/**
 * Created by ROLO on 03.11.2015.
 */
angular.module('stApp.questionSrv', [])
    .service('QuestionSrv', function($http){
        return {
            question : function(question){
                return $http.post('../rest/question/new', question);
            },
            questions : function(questionCriteria){
                return $http.get('../rest/question/questions', questionCriteria);
            }
        }
    })
