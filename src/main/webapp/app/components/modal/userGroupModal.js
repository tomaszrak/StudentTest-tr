angular.module('stApp.userGroupEdit', [])
    .service('UserGroupEditSrv', function ($modal) {
        return {
            show: function (getAll, group, action) {
                return $modal.open({
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
                    controller: function ($scope, $rootScope, UserSrv, $modalInstance, UserGroupSrv, alert, getAll, group) {

                        $scope.groupUsers = [];

                        if (angular.isUndefined(group)) {
                            $scope.group = {
                                users: undefined
                            }
                        } else {
                            $scope.group = group;
                            $scope.groupUsers = $scope.group.users;
                        }

                        $scope.getAllUsers = function () {
                            UserSrv.users()
                                .success(function (result) {
                                    if(null != $scope.group.users){
                                        var users = result;

                                        for(var i=0; i<users.length; i++){
                                            for(var j=0; j<$scope.group.users.length; j++){
                                                if(angular.equals(users[i].id, $scope.group.users[j].id)){
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
                        }

                        $scope.close = function () {
                            $modalInstance.close();
                        }

                    }
                })
            }

        }
    })