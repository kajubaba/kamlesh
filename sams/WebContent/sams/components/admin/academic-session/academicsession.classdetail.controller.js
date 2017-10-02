
samsApp.controller('academicsession.classdetails.controller', function(academicSessionClassService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.classDetails = null;
	$scope.classes = null;
	
	$scope.sumNewAdmissionFee = 0;
	$scope.sumRegularAdmissionFee = 0;
	
	this.init = function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		this.getAcademicSessionClassDetails();
		this.getAcademicSessionClasses();
	}
	
	
	this.getAcademicSessionClassDetails= function(){
		
		academicSessionClassService.getAcademicSessionClassDetails($routeParams['courseYearSettingId'], this.getAcademicSessionClassDetailsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAcademicSessionClassDetailsSuccess = function(response, status){
		$scope.classDetails = response;
		
		var sumNewFee = 0;
		
		angular.forEach($scope.classDetails.feeDetails, function(feeDetails){
			 if(feeDetails.newAdmissionFee.fee != null){
				 sumNewFee += parseInt(feeDetails.newAdmissionFee.fee);
			 }
		 });
		
		$scope.sumNewAdmissionFee = sumNewFee;
		
		var sumRegFee = 0;
		
		angular.forEach($scope.classDetails.feeDetails, function(feeDetails){
			 if(feeDetails.regularAdmissionFee.fee != null){
				 sumRegFee += parseInt(feeDetails.regularAdmissionFee.fee);
			 }
		 });
		
		$scope.sumRegularAdmissionFee = sumRegFee;
	}
	
	this.sumRegularAdmissionFee = function(){
		 
		var sum = 0;
		
		angular.forEach($scope.classDetails.feeDetails, function(feeDetails){
			 if(feeDetails.regularAdmissionFee.fee != null){
				 sum += parseInt(feeDetails.regularAdmissionFee.fee);
			 }
		 });
		
		$scope.sumRegularAdmissionFee = sum;
	}
	
	this.sumNewAdmissionFee = function(){
		var sum = 0;
		
		angular.forEach($scope.classDetails.feeDetails, function(feeDetails){
			 if(feeDetails.newAdmissionFee.fee != null){
				 sum += parseInt(feeDetails.newAdmissionFee.fee);
			 }
		 });
		
		$scope.sumNewAdmissionFee = sum;
	}
	
	this.saveClassFee = function(){
		academicSessionClassService.saveAcademicSessionClassFee($scope.classDetails, this.saveClassFeeSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveClassFeeSuccess = function(){
		$route.reload();
	}
	
	this.getAcademicSessionClasses = function(){
		academicSessionClassService.getAcademicSessionClassesDetails($scope.selectedAcademicSession, this.getAcademicSessionClassesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAcademicSessionClassesSuccess = function(response, status){
		$scope.classes = response;
	}
	
	this.changeClass = function(){
		$location.path("/admin/academicsession/setup/classfee/"+$scope.selectedAcademicSession+"/"+$scope.classDetails.id);
	}
	
});






