var notification = {
    start: function(apiUrl, authorizationHeader, intervalMillis, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'NotificationPlugin',
            'start', 
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
            'stop'
        );
    }
}

module.exports = notification;