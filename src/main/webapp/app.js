angular.module('stApp', ['ngRoute',
    'ngResource',
    'ui.bootstrap',
    'stApp.partials',
    'stApp.services',
    'stApp.modals',
    'stApp.filter',
    'stApp.common'])

    .config(['$routeProvider', '$httpProvider', '$locationProvider', function ($routeProvider, $httpProvider, $locationProvider) {
        $routeProvider.otherwise({redirectTo: '/main'});
        $routeProvider.when('/login', {
            templateUrl: '/login.html'
        })
        $locationProvider.html5Mode(false);

    }])

    .controller('IndexCtrl', function ($scope) {
        $scope.testData = "data";
    })

    .run(['$rootScope', '$resource', function ($rootScope, $resource) {

        var messages = $resource('../rest/messages/en',
            {}, {
                query: {method: 'GET', params: {}, isArray: false}
            });
        $rootScope.msg = messages.query();

        var user = $resource('../rest/user',
            {}, {
                query: {method: 'POST', params: {}, isArray: false}
            });
        $rootScope.user = user.query();

    }])