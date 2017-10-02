samsApp.controller('transportation.student.detail.controller', function($scope, $route, $routeParams, studentTransportationDetailService, serverErrorHandlerService){
	
	$scope.studentDetails = null;
	
	this.getStudentDetails = function(){
		studentTransportationDetailService.getStduentDetails($routeParams["studentId"], this.getStudentDetailsSucess, serverErrorHandlerService.handleError);
	}
	
	this.getStudentDetailsSucess = function(response, status){
		$scope.studentDetails = response;
	}
	
	this.updateStudentPickupPoint = function(){
		studentTransportationDetailService.updateStduentPickupPoint($scope.studentDetails.id, $scope.studentDetails.arrivalInfo.pointId,  this.updatePointSucess, serverErrorHandlerService.handleError);
	}
	
	this.updateStudentDropPoint = function(){
		studentTransportationDetailService.updateStduentDropPoint($scope.studentDetails.id, $scope.studentDetails.departureInfo.pointId, this.updatePointSucess, serverErrorHandlerService.handleError);
	}
	
	this.updatePointSucess = function(response, status){
		$route.reload();
	}
	
	
});