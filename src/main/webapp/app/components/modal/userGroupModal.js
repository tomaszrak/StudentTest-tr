angular.module('stApp.userGroupEdit', [])
    .service('UserGroupEditSrv', function ($uibModal) {
        return {
            show: function (getAll, group, action) {
                return $uibModal.open({
                    animation: true,
                    templateUrl: 'app/components/modal/userGroupModal.html',
                    size: 'lg',
                    resolve: {
                        getAll: function () {
                            return getAll;
                        },
                        group: function () {
                            return group;
                        },
                        action: function () {
                            return action;
                        }
                    },
                    controller: function ($scope, $rootScope, UserSrv, $uibModalInstance, UserGroupSrv, alert, getAll, group) {

                        $scope.groupUsers = [];
                        $scope.insertedUsers = [];

                        if (angular.isUndefined(group)) {
                            $scope.group = {
                                users: undefined
                            }
                            $scope.create = true;
                        } else {
                            $scope.create = false;
                            $scope.group = group;
                            angular.copy(group.users, $scope.groupUsers);
                        }

                        $scope.getAllUsers = function () {
                            UserSrv.users()
                                .success(function (result) {
                                    if (null != $scope.groupUsers) {
                                        var users = result;

                                        for (var j = 0; j < $scope.groupUsers.length; j++) {
                                            for (var i = 0; i < users.length; i++) {

                                                if (angular.equals(users[i].id, $scope.groupUsers[j].id)) {
                                                    users.splice(i, 1);
                                                    break;
                                                }
                                            }
                                        }
                                        $scope.users = users;
                                    } else {
                                        $scope.users = result;
                                    }
                                }).error(function () {
                                    alert.add("Error", "danger", 6000);
                                })
                        }
                        $scope.getAllUsers();


                        $scope.addUser = function ($index, user) {
                            $scope.groupUsers.push(user);
                            $scope.insertedUsers.push(user);
                            $scope.users.splice($index, 1);
                        }

                        $scope.save = function () {
                            $scope.group.users = $scope.groupUsers;
                            if (null != $scope.group.id) {
                                UserGroupSrv.userGroupToUpdate($scope.group)
                                    .success(function () {
                                        $scope.close();
                                        getAll();
                                    }).error(function () {
                                        alert.add("Error", "danger", 6000);
                                    })
                            } else {
                                UserGroupSrv.userGroup($scope.group)
                                    .success(function () {
                                        $scope.close();
                                        getAll();
                                    }).error(function () {
                                        alert.add("Error", "danger", 6000);
                                    })
                            }

                        }

                        $scope.removeUser = function ($index, user) {
                            $scope.groupUsers.splice($index, 1);
                            $scope.users.push(user);
                            $scope.insertedUsers.splice($index,1);
                        }

                        $scope.close = function () {
                            $uibModalInstance.close();
                            $scope.insertedUsers = [];
                        }

                    }
                })
            }

        }
    })