
samsApp.controller('studentcategory.manage.controller', function(studentCategoryService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.studentCategoryForm  ={
			id : "",
			name : "",
			active : ""
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
    this.getStudentCategory = function(){
		
    	if($routeParams['studentCategoryId'] != "new"){
    		studentCategoryService.getStudentCategory($routeParams['studentCategoryId'], this.getStudentCategorySuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    		$scope.studentCategoryForm.active = true;
    	}
    	
    	
	}
	
	this.getStudentCategorySuccess = function(response, status){
		$scope.studentCategoryForm.id = response.id;
		$scope.studentCategoryForm.name = response.name;
		$scope.studentCategoryForm.active = response.active;
		
	}
	
	this.saveStudentCategory = function(){
		studentCategoryService.saveStudentCategory($scope.studentCategoryForm, this.saveStudentCategorySuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveStudentCategorySuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.studentCategoryForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
});






