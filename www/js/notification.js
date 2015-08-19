var notification = {
    start: function(apiUrl, authorizationHeader, intervalMillis, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'NotificationPlugin',
            'ACTION_START', 
            [{
                "apiUrl": apiUrl,
                "authorizationHeader": authorizationHeader,
                "intervalMillis": intervalMillis
            }]
        );
    },

    stop: function(successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'NotificationPlugin',
            'ACTION_STOP'
        );
    }
}

module.exports = notification;