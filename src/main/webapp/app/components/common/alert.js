angular
    .module('stApp.alert', [])
    .factory(
    'alert',
    [
        '$timeout',
        '$filter',
        function($timeout, $filter) {
            var alerts = [];

            var closeAlert = function(index) {
                alerts.splice(index, 1);
            };

            return {
                add : function(msg, type, timeout) {
                    var alert = {
                        msg : msg,
                        type : type
                    };
                    alerts.push(alert);

                    if (timeout) {
                        $timeout(function() {
                            closeAlert(alerts.indexOf(alert));
                        }, timeout);
                    }
                },

                handleErrors : function(data) {
                    if (data.status == 500) {
                        this.add("Oops! Something went wrong!",
                            'danger', 3000);
                    } else if (data.status == 901) {
                        this
                            .add(
                            "Session expired. Please refresh page and log in again.",
                            'danger');
                    } else {
                        this.add(data.response, 'danger');
                    }
                },
                closeAlert : closeAlert,
                alerts : alerts
            };
        } ]);