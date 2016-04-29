/**
 * Created by ROLO on 03.11.2015.
 */
angular.module('stApp.users', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider.state('users', {
            url: '/users',
            templateUrl: 'app/partials/users/users.html',
            controller: 'UserCtrl'
        })
    }])
    .controller('UserCtrl', ['UserSrv', '$scope', 'UserModalSrv', '$translate', 'ConfirmModalSrv', 'alert', '$location',
        function (UserSrv, $scope, UserModalSrv, $translate, ConfirmModalSrv, alert, $location) {

            $scope.userStatuses = [
                {type: 'ACTIVE', name: $translate.instant('st.status.active')},
                {type: 'BLOCKED', name: $translate.instant('st.status.blocked')},
                {type: 'SUSPENDED', name: $translate.instant('st.status.suspended')}
            ];

            $scope.userRoles = [
                {type: 'STUDENT', name: $translate.instant('st.role.student')},
                {type: 'TEACHER', name: $translate.instant('st.role.teacher')},
                {type: 'ADMIN', name: $translate.instant('st.role.admin')}
            ];

            $scope.searchParams = [
                'sdasd', 'sdsds', 'sawe'
            ]

            $scope.getUsers = function () {
                var promise = UserSrv.users()
                    .success(function (result) {
                        $scope.users = result;
                    })
            }

            $scope.getUsers();

            $scope.addUser = function (user) {
                UserModalSrv.show(user, $scope.getUsers, $scope.userStatuses, $scope.userRoles);
            }

            $scope.remove = function (user) {
                $scope.user = user;
                ConfirmModalSrv.show($translate.instant('st.removeConfirm'), $scope.removeUser);
            }

            $scope.removeUser = function () {
                UserSrv.userToDelete($scope.user)
                    .success(function (result) {
                        alert.success($translate.instant('st.userRemoved', {user: $scope.user.lastName
                            + ' ' + $scope.user.firstName}));
                        $scope.getUsers();
                    });
            }

            $scope.resetPassword = function(user){
                UserSrv.password(user)
                    .success(function(){
                        alert.success($translate.instant('st.passwordResetSuccess', {email: user.email}));
                    });
            }

            $scope.showStatistic = function(user){
                $location.path("/userTestStatistic/" + user.id);
            }

        }])
