var loans = angular.module('Loans',['ngRoute', 'ngAnimate']);

/**
 * Filtro con el objetivo de dar formato a la hora obtenida de una solicitud de prestamo
 * @param input fecha la cual se le quiere dar formato de tiempo.
 * @return tiempo formateado
 */
loans.filter('timeStr', function() {
	return function(input) {
		var output = input.substr(input.indexOf('T'), 9);
		return output;
	}
});


/**
 * Definicion de rutas de la aplicación angular con ngRoute
 */
loans.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'templates/login.html',
		controller: 'loginCtrl'
	})
	.when('/deviceList', {
		templateUrl: 'templates/deviceList.html',
		controller: 'deviceListCtrl'
	})
	.when('/deviceList/:id/:name', {
		templateUrl: 'templates/requestDevice.html',
		controller: 'requestDeviceCtrl'
	})
	.when('/requests', {
		templateUrl: 'templates/requests.html',
		controller: 'requestsCtrl'
	})
	.otherwise('/');
}]);