samsApp.controller('academics.student.controller', function(studentAcademicsService, studentService, evaluationTermService, serverErrorHandlerService, 
																	$scope, 
																	blockUI, 
																	$routeParams, 
																	$route, 
																	$location){

	$scope.scoreCard = null;
	$scope.studBriefInfo = {};
	$scope.studentClassId = null;
	$scope.evaluationTerms = null;
	$scope.selectedTerm = null;
	
	$scope.studentId = null;
	
	this.getStudentScoreCard = function(){
		$scope.studentId = $routeParams['studentId'];
		this.getStudentDetails();
		studentAcademicsService.getStudentFullScholasticScoreCard($scope.studentId, this.renderStudentScoreCard, serverErrorHandlerService.handleError);
		this.getEvaluationTermsOfClass();
	}
	
	this.getStudentDetails = function(studentId){
		studentService.getStudentFeeBriefInfo($scope.studentId, null, this.renderStudentDetails, serverErrorHandlerService.handleError);
	}
	
	this.renderStudentDetails = function(response, status){
		$scope.studBriefInfo = response;
	}
	
	this.renderStudentScoreCard = function(response, status){
		$scope.scoreCard = response;
	}
	
	this.printReportCard = function(){
		window.open(_appContextPath+"/ws/academics/student/scorecard/pdf/"+$scope.studBriefInfo.id+"?evaluationTermId="+$scope.selectedTerm);
	}
	
	this.printFullReportCard = function(){
		window.open(_appContextPath+"/ws/academics/student/scorecard/pdf/full/"+$scope.studBriefInfo.id);
	}
	
	this.getEvaluationTermsOfClass = function(){
		$scope.studentClassId = $routeParams['classId'];
		evaluationTermService.getScholasticEvaluationTermsOfClass($scope.studentClassId, this.renderScholasticEvaluationTerms, serverErrorHandlerService.handleError);
	}
	
	this.renderScholasticEvaluationTerms = function(response, status){
		$scope.evaluationTerms = response.scholasticEvaluationTerms;
	}
	
	this.renderCoScholasticEvaluationTerms = function(response, status){
		$scope.evaluationTerms = response;
	}
	
	this.changeScoecard = function(){
		
		if($scope.selectedTerm == null){
			studentAcademicsService.getStudentFullScholasticScoreCard($scope.studentId, this.renderStudentScoreCard, serverErrorHandlerService.handleError);
			$("#partial-scorecard").hide();
			$("#full-scorecard").show();
		}else{
			studentAcademicsService.getStudentScholasticScoreCardOfTerm($scope.studentId, $scope.selectedTerm, this.renderStudentScoreCard, serverErrorHandlerService.handleError);
			
			$("#full-scorecard").hide();
			$("#partial-scorecard").show();
		}
		
	}
});






