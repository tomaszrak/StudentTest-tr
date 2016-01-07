angular.module('stApp.test', [])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/test', {
            templateUrl: 'app/partials/test/test.html',
            controller: 'TestCtrl'
        })
    }])
    .controller('TestCtrl', ['$scope', '$rootScope', 'TestSrv', 'alert', 'QuestionAnswerSrv',
        function ($scope, $rootScope, TestSrv, alert, QuestionAnswerSrv) {

            $scope.test = {}

            $scope.getAllTests = function () {
                TestSrv.tests()
                    .success(function (result) {
                        $scope.tests = result;
                    }).error(function () {

                    })
            }

            $scope.questionTypes = [
                {type: "MULTIPLE", name: $rootScope.msg['st.multipleType']},
                {type: "ONE_ANSWER", name: $rootScope.msg['st.oneAnswer']},
                // {type: "MATCHING", name: $rootScope.msg['st.matching']},
                {type: "TRUE_FALSE", name: $rootScope.msg['st.trueFalse']},
                {type: "NUMBER", name: $rootScope.msg['st.number']},
                {type: "SHORT_ANSWER", name: $rootScope.msg['st.shortAnswer']}];

            $scope.getAllTests();

            $scope.questionAnswers = [];


            $scope.addRadioQ = function () {
                QuestionAnswerSrv.show("ONE_ANSWER", $scope.questionFunction);
            }

            $scope.questionFunction = function (question) {
                $scope.questionAnswers.push(question);
            }

            $scope.addCheckQ = function () {
                QuestionAnswerSrv.show("MULTIPLE", $scope.questionFunction);
            }

            $scope.addTextQ = function () {
                QuestionAnswerSrv.show("SHORT_ANSWER", $scope.questionFunction);
            }

            $scope.addNumberQ = function () {
                QuestionAnswerSrv.show("NUMBER", $scope.questionFunction);
            }

            $scope.removeQuestion = function ($index) {
                $scope.questionAnswers.splice($index, 1);
            }

            $scope.saveTest = function () {
                $scope.test.questions = [];

                for (var i = 0; i < $scope.questionAnswers.length; i++) {
                    $scope.test.questions.push($scope.questionAnswers[i][0].question);
                }

                $scope.criteria = {
                    test: $scope.test,
                    answers: $scope.answers
                }
                TestSrv.test($scope.criteria).success(function () {

                })
            }

        }])