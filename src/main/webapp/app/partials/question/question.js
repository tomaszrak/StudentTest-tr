/**
 * Created by Roman on 20.10.2015.
 */

angular.module('stApp.question', ['ngRoute'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/question', {
            templateUrl: 'app/partials/question/question.html',
            controller: 'QuestionCtrl'
        })
    }])
    .controller('QuestionCtrl', function ($scope, $location, $rootScope, QuestionSrv, PaginationSrv, AnswerModalSrv,
                                          QuestionPreviewSrv) {

        $scope.questionTypes = [
            {type: "MULTIPLE", name: $rootScope.msg['st.multipleType']},
            {type: "ONE_ANSWER", name: $rootScope.msg['st.oneAnswer']},
           // {type: "MATCHING", name: $rootScope.msg['st.matching']},
            {type: "TRUE_FALSE", name: $rootScope.msg['st.trueFalse']},
            {type: "NUMBER", name: $rootScope.msg['st.number']},
            {type: "SHORT_ANSWER", name: $rootScope.msg['st.shortAnswer']}];

        $scope.addQuestion = function () {
            $scope.question.type = $scope.question.type.type;
            var promise = QuestionSrv.question($scope.question)
                .success(function () {
                    $scope.getQuestions();
                })
        }

        $scope.addAnswer = function(question){
            AnswerModalSrv.show(question    );
        }

        $scope.editQuestion = function(){

        }

        $scope.criteria = {
            limit: 10,
            offset: 0
        }

        $scope.getQuestions = function () {
            var pomise = QuestionSrv.questions($scope.criteria)
                .success(function (result) {
                    $scope.questions = result;
                })
        }

        $scope.showPreview = function(question){
            QuestionPreviewSrv.show({question: question}, false);
        }

        $scope.getQuestions();

    })
