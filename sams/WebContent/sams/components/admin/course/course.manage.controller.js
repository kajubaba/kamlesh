
samsApp.controller('course.manage.controller', function(courseService, affiliationAuthorityService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.courseForm ={
			id : "",
			name : "",
			displayName : "",
			affiliationAuthorityId : "",
			duration : ""
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
	$scope.affiliationAuthorities= null;
	
    this.getCourse = function(){
		
    	if($routeParams['courseId'] != "new"){
    		courseService.getCourse($routeParams['courseId'], this.getCourseSuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    	}
    	
    	this.getAffiliationauthorities();
	}
	
    this.getAffiliationauthorities = function(){
		affiliationAuthorityService.getAffiliationAuthorities(this.getAffiliationauthoritiesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAffiliationauthoritiesSuccess = function(response, status){
		$scope.affiliationAuthorities= response;
		console.log($scope.affiliationAuthorities);
	}
    
    
	this.getCourseSuccess = function(response, status){
		$scope.courseForm.id = response.id;
		$scope.courseForm.name = response.name;
		$scope.courseForm.displayName = response.displayName;
		$scope.courseForm.affiliationAuthorityId = response.affiliationAuthorityId;
		$scope.courseForm.duration = response.duration;
		
	}
	
	this.saveCourse = function(){
		courseService.saveCourse($scope.courseForm, this.saveCourseSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveCourseSuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.courseForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
    
});






