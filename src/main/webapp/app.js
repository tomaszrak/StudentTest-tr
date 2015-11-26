angular.module('stApp', ['ngRoute',
    'ngResource',
    'ui.bootstrap',
    'stApp.partials',
    'stApp.services',
    'stApp.modals',
    'stApp.filter'])

    .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {
        $routeProvider.otherwise({redirectTo: '/login'});
        $routeProvider.when('/login', {
            templateUrl: 'app/partials/login/login.html'
        });

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

    }])