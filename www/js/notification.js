var notification = {
	start: function(apiUrl, authorizationHeader, intervalMillis, title, icon, successCallback, errorCallback) {
		cordova.exec(
			successCallback,
			errorCallback,
			'NotificationPlugin',
			'start', 
			[{
				"apiUrl": apiUrl,
				"authorizationHeader": authorizationHeader,
				"intervalMillis": intervalMillis,
				"title": title,
				"icon": icon
			}]
		);
	},

	stop: function(successCallback, errorCallback) {
		cordova.exec(
			successCallback,
			errorCallback,
			'NotificationPlugin',
			'stop',
			[]
		);
	}
}

module.exports = notification;