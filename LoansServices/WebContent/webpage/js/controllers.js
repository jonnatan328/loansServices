var loans = angular.module('Loans');


/**
 * Controlador para el control de inicio de sesión de los diferentes usuarios.
 * utiliza diferentes dependencias para controlar el usuario y las rutas a las cuales puede acceder.
 */
loans.controller('loginCtrl', ['$scope', '$location' ,'loginSvc', '$rootScope', function($scope, $location, loginSvc, $rootScope) {
	$scope.authenticate = function() {
		user = {
			usuario: $scope.username,
			password: $scope.password
		}
		
		// Se intenga logear un usuario, si el logeo es exitoso se identifica que tipo de usuario es
		// y se es redirigido a la ruta correspondiente del usuario.
		loginSvc.authenticate(user)
		.success(function(loginResponse) {
			if(loginResponse.authenticated) {
				
				loginSvc.getUser({usuario: $scope.username})
				.success(function(userResponse) {
					console.log(userResponse)
					$rootScope.user = userResponse;
					if(userResponse.role.id === 'INV') {
						$location.path('deviceList');
					} else if(userResponse.role.id === 'ADM') {
						$location.path('requests');
					}
				})
			} else {
				alert("Los datos ingresados son incorrectos.");
			}
		})
		.error(function(err) {
			alert('Un error ha ocurrida, porfavor intente de nuevo.')
		});
	}
}]);


/**
 * Controlador con el cual un administrador puede revisar las solicitudes pendientes y aceptar o rechazar según su criterio.
 */
loans.controller('requestsCtrl', ['$scope', 'requestsSvc', function($scope, requestsSvc) {
	
	// Se obtiene la lista de pedidos pendientes de aprobación.
	requestsSvc.getRequests('pendiente')
	.success(function(response) {
		if(response) {
			if(response.requestWs.length) {
				$scope.requests = response.requestWs;

			} else {
				$scope.requests = [];
				$scope.requests.push(response.requestWs);
			}
		}
	});
	
	// Mediante esta funcion se acepta una solicitud.
	$scope.acceptRequest = function(request, index) {
		requestsSvc.acceptRequest(request)
		.success(function() {
			$scope.requests.splice(index,1);
		} );
		
	}
	
	// Mediante esta funcion se rechaza una solicitud.
	$scope.rejectRequest = function(request,index) {
		console.log()
		requestsSvc.rejectRequest(request)
		.success(function() {
			$scope.requests.splice(index,1);

		} );
		
	}

}]);


/**
 * Controllador con el cual se obtiene la lista de dispositivos disponibles para ser prestados.
 */ 
loans.controller('deviceListCtrl', ['$scope', 'deviceSvc', '$location', function($scope, deviceSvc, $location) {
	
	// se obtiene toda la lista de dispositivos disponibles
	deviceSvc.getDevices()
	.success(function (response) {
		if(response) {
			if(response.deviceWs.length) {
				$scope.devices = response.deviceWs;
			} else {
				$scope.devices = [];
				$scope.devices.push(response.deviceWs);
				}
			}
	
	});
	
	// esta funcion redirige a una pagina donde se podrá hacer la peticion de un dispositivo especifico.
	$scope.requestDevice = function(id, name) {
		$location.path('/deviceList/' + id + '/' + name)
	}
}]);


/**
 * Controlador con el cual se hacen las solicitudes de un dispositivo especifico
 */
loans.controller('requestDeviceCtrl', ['$scope', 'deviceSvc', '$routeParams','$location','$rootScope', 
	function($scope, deviceSvc, $routeParams,$location, $rootScope) {
	
	// Se obtienen los datos del dispositivo atravez de la ruta de la pagina
	$scope.device = {
		id: $routeParams.id,
		name: $routeParams.name
	}
	
	// Se hace la solicitud del dispositivo con los datos ingresados en el formulario de peticion.	
	$scope.makeRequest = function() {
		request = {
			dateRequest : $scope.dateRequest,
			startTime : $scope.startTime,
			endTime : $scope.endTime,
			state : 'pendiente',
			device : $scope.device.id,
			researcher : $rootScope.user.username
		}
		deviceSvc.makeRequest(request)
		.success(function(response) {
			if(response.created) {
				$location.path("/deviceList");
			}
		});
	}	

}]);



