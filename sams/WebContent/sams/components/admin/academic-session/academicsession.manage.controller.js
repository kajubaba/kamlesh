
samsApp.controller('academicsession.manage.controller', function(academicSessionService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.academicSessionForm ={
			id : "",
			name : "",
			from : "",
			to : "",
			orderNo : "",
			importSettingsFrom : null
	}
	
	$scope.ACADEMIC_SESSION_STATUS_PUBLISHED = $rootScope.ACADEMIC_SESSION_STATUS_PUBLISHED;
	$scope.academicSessionStatus = null;
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	$scope.academicSessions = null;
	
    this.getAcademicSession = function(){
		
    	if($routeParams['academicSessionId'] != "new"){
    		academicSessionService.getAcademicSession($routeParams['academicSessionId'], this.getAcademicSessionSuccess, serverErrorHandlerService.handleError);
    	}else{
    		this.getActiveAcademicSessions();
    		$scope.isActionNew= true;
    	}
    	
    	
	}
	
	this.getAcademicSessionSuccess = function(response, status){
		$scope.academicSessionForm.id = response.id;
		$scope.academicSessionForm.name = response.name;
		$scope.academicSessionForm.from = response.from;
		$scope.academicSessionForm.to = response.to;
		$scope.academicSessionForm.orderNo = response.orderNo;
		
		$scope.academicSessionStatus = response.status;
		
		
	}
	
	this.saveAcademicSession = function(){
		academicSessionService.saveAcademicSession($scope.academicSessionForm, this.saveAcademicSessionSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveAcademicSessionSuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.academicSessionForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
	this.publishAcademicSession = function(){
		academicSessionService.publishAcademicSession($scope.academicSessionForm.id, this.publishAcademicSessionSuccess, serverErrorHandlerService.handleError);
	}
	
	this.publishAcademicSessionSuccess = function(response, status){
		
		if("OK" == response.status){
			$route.reload(); 
		}
	}
	
	this.getActiveAcademicSessions = function(){
		academicSessionService.getActiveAcademicSessions(this.getActiveAcademicSessionsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getActiveAcademicSessionsSuccess = function(response, status){
		$scope.academicSessions = response;
	}
    
});






