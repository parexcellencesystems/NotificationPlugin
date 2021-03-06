var notification = {
	start: function(getUrl, authorizationHeader, intervalMillis, title, icon, successCallback, errorCallback) {
		cordova.exec(
			successCallback,
			errorCallback,
			'NotificationPlugin',
			'start', 
			[{
				"getUrl": getUrl,
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