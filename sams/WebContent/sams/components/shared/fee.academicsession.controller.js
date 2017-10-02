
samsApp.controller('fee.sams.academicsession.controller', function($scope, $routeParams, academicsessionService){
	
	$scope.admissionAcademicYear = ""; 
	$scope.academicYear = null;
	
	this.getCurrentAdmissionAcademicYear = function(){
		academicsessionService.getCurrentAdmissionAcademicYear(this.displayAdmissionAcademicYear, this.errorFunction);
	}
	
	this.displayAdmissionAcademicYear = function(response, status){
		$scope.admissionAcademicYear = response.academicYearName; 
	}
	
	this.getAllAcademicYears = function(){
		academicsessionService.getAllFeeAcademicYears($routeParams['academicYearId'], this.renderAllAcademicYears, this.errorFunction);
	}
	
	this.renderAllAcademicYears = function(response, status){
		$scope.academicYear = response;
	}
	
	this.errorFunction = function(){
		
	}
	
	this.getAllAcademicYears();
	
});






