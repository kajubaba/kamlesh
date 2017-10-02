

samsApp.controller('academics.exam.pattern.controller', function(examPatternService, academicsessionService, serverErrorHandlerService, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.examPatterns= null;
	$scope.baseExamPatterns= null;
	$scope.baseAcademicSession= null;
	
	$scope.baseAcademicSessionId = null;
	$scope.createExamPatternFrom = "Master";
	
	$scope.examPatternCreationForm = {
			academicSessionId : null,
			baseExamPatternId : null,
			examPatternName : null
			
	}
	
	this.createExamPattern = function(){
		examPatternService.createExamPattern($scope.examPatternCreationForm, this.createExamPatternSuccess, serverErrorHandlerService.handleError);	
	}
	
	this.createExamPatternSuccess = function(response, status){
		$("#modalExamPattern").modal('hide');
		$route.reload();
	}
	
	this.getExamPatterns = function(){
		examPatternService.getExamPatterns($routeParams['academicYearId'], this.renderExamPatterns, serverErrorHandlerService.handleError);
	}
	
	this.deleteExamPattern = function(examPatternId){
		examPatternService.deleteExamPattern(examPatternId, this.refreshPage, serverErrorHandlerService.handleError);
	}
	
	this.renderExamPatterns = function(response, status){
		$scope.examPatterns= response;
	}
	
	this.refreshPage = function(){
		$route.reload();
	}
	
	this.openModal = function(){
		examPatternService.getMasterExamPatterns(this.renderBaseExamPatterns, serverErrorHandlerService.handleError);
		$("#modalExamPattern").modal('show');
	}
	
	this.toggelBaseExamPattern = function(){
		$scope.baseExamPatterns= null;
		if("Master" == $scope.createExamPatternFrom){
			examPatternService.getMasterExamPatterns(this.renderBaseExamPatterns, serverErrorHandlerService.handleError);
		}else if("Academic Session" == $scope.createExamPatternFrom){
			academicsessionService.getAllAcademicYears(null, this.renderBaseAcademicSessions, serverErrorHandlerService.handleError);
		}
	}
	
	this.getAcademicSessionExamPatterns = function(){
		examPatternService.getExamPatterns($scope.baseAcademicSessionId, this.renderBaseExamPatterns, serverErrorHandlerService.handleError);
	}
	
	this.renderBaseExamPatterns = function(response, status){
		$scope.baseExamPatterns= response;
	}
	
	this.renderBaseAcademicSessions = function(response, status){
		$scope.baseAcademicSession= response.academicYears;
	}
	
	
	$scope.examPatternCreationForm.academicSessionId = $routeParams['academicYearId'];
	
	

});






