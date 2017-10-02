
samsApp.controller('sams.academicsession.controller', function($scope, $routeParams, academicsessionService){
	
	$scope.admissionAcademicYear = ""; 
	$scope.admissionAcademicYearId = "";
	$scope.enquiryAcademicYear = ""; 
	$scope.enquiryAcademicYearId = "";
	$scope.academicYear = null;
	
	this.getCurrentAdmissionAcademicYear = function(){
		academicsessionService.getCurrentAdmissionAcademicYear(this.displayAdmissionAcademicYear, this.errorFunction);
	}
	
	this.getCurrentEnquiryAcademicYear = function(){
		academicsessionService.getCurrentEnquiryAcademicYear(this.displayEnquiryAcademicYear, this.errorFunction);
	}
	
	this.displayAdmissionAcademicYear = function(response, status){
		$scope.admissionAcademicYear = response.academicYearName; 
		$scope.admissionAcademicYearId = response.academicYearId;
	}
	
	this.displayEnquiryAcademicYear = function(response, status){
		$scope.enquiryAcademicYear = response.academicYearName; 
		$scope.enquiryAcademicYearId = response.academicYearId;
	}
	
	this.getAllAcademicYears = function(){
		academicsessionService.getAllAcademicYears($routeParams['academicYearId'], this.renderAllAcademicYears, this.errorFunction);
	}
	
	this.renderAllAcademicYears = function(response, status){
		$scope.academicYear = response;
	}
	
	this.errorFunction = function(){
		
	}
	
	this.getAllAcademicYears();
	
});






