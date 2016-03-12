angular.module('stApp.userGroupSrv', [])
    .service('UserGroupSrv', function ($http) {
        return {
            userGroups : function () {
                return $http.get('../rest/userGroup/');
            },
            userGroup: function (userGroup) {
                return $http.post('../rest/userGroup/', userGroup);
            },
            groupToRemove : function(group){
                return $http.post('../rest/userGroup/groupToRemove', group);
            },
            userGroupToUpdate : function(group){
                return $http.post("../rest/userGroup/userGroupToUpdate", group);
            },
            summary : function(id){
                return $http.post('../rest/userGroup/summary', id);
            }
        }

    })