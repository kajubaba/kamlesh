
samsApp.controller('studentstatus.manage.controller', function(studentStatusService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.studentStatusForm  ={
			id : null,
			name : null,
			active : null
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
    this.getStudentStatus = function(){
		
    	if($routeParams['studentStatusId'] != "new"){
    		studentStatusService.getStudentStatus($routeParams['studentStatusId'], this.getStudentStatusSuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    		$scope.studentStatusForm.active = true;
    	}
    	
    	
	}
	
	this.getStudentStatusSuccess = function(response, status){
		$scope.studentStatusForm = response
	}
	
	this.saveStudentStatus = function(){
		if($scope.studentStatusForm.id == null){
			studentStatusService.addStudentStatus($scope.studentStatusForm, this.saveStudentStatusSuccess, serverErrorHandlerService.handleError);
		}else{
			studentStatusService.updateStudentStatus($scope.studentStatusForm, this.saveStudentStatusSuccess, serverErrorHandlerService.handleError);
		}
		
	}
	
	this.saveStudentStatusSuccess = function(response, status){
	
		if("SUCCESS" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.studentStatusForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
});






