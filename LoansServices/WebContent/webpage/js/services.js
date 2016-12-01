var loans = angular.module('Loans');

/**
 * Servicios de angular con el cual se maneja la autenticacion de usuarios atravez del servidor de aplicaciones.
 */
loans.factory('loginSvc', [ '$http', function($http) {
	var userResponse = null;
	return {
		authenticate : function(user) {
			return $http({
				url : '/LoansServices/rest/user/login',
				method : 'POST',
				params : user
			});
		},

		getUser : function(user) {
			return $http({
				url : '/LoansServices/rest/user/user',
				method : 'GET',
				params : user
			});
		}
	}
} ]);


/**
 * Servicios de angular con el cual se maneja las peticiones relacionadas con los dispositivos tales como obtener la lista
 * de dispositivos y hacer una solicitud de un dispositivo.
 */
loans.factory('deviceSvc', [ '$http', function($http) {
	var userResponse = null;
	return {
		getDevices : function(user) {
			return $http({
				url : '/LoansServices/rest/device/getAll',
				method : 'GET',
			});
		},
		makeRequest : function(request) {
			return $http({
				url : '/LoansServices/rest/request/new/' + request.dateRequest + "/" + request.startTime + "/" + 
				request.endTime + "/pendiente/" + request.device + "/" + request.researcher,
				method: 'POST'
			});
		}
	}
} ]);

/**
 * Servicios de angular con el cual se maneja las peticiones relacionadas con las solicitudes de dispositivos pendientes.
 */
loans.factory('requestsSvc', [ '$http', function($http, index) {
	var userResponse = null;
	return {
		getRequests : function(state) {
			return $http({
				url : '/LoansServices/rest/request/byState/' + state,
				method : 'GET',
			});
		},
		acceptRequest : function(request) {
			return $http({
				url : '/LoansServices/rest/request/accept/' + request,
				method : 'GET',
			});
		},
		rejectRequest : function(request) {
			return $http({
				url : '/LoansServices/rest/request/reject/' + request,
				method : 'GET',
			});
		}
	}
} ]);







