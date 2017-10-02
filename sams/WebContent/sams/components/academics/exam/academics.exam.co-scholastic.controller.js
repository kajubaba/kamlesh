samsApp.controller('academics.exam.co-scholastic.controller', function(coScholasticExamService,
																		evaluationTermService,
																		studentService,
																	$scope, 
																	blockUI, 
																	$routeParams, 
																	$route, 
																	$location){

	
	//$scope.activities = null;
	$scope.studCoScholasticAssessment = null;
	$scope.CoScholasticEvalTerms = null;
	$scope.studBriefInfo = {};
	$scope.selectedTerm = null;
	
	
	$scope.coScholasticScoreForm ={
			studentId : null,
			studentClassId : null,
			termId : null,
			criteriaScores : []
	}
	
	this.changeTerm = function(termId){
		$location.path('academics/assessment/co-sch/stud/'+$scope.coScholasticScoreForm.studentId+'/'+$scope.coScholasticScoreForm.studentClassId+'/'+termId);
	}
	
	this.getStudentCoScholasticScore = function(){
		
		$scope.coScholasticScoreForm.studentId= $routeParams['studnetId'];
		$scope.coScholasticScoreForm.studentClassId= $routeParams['classId'];
		$scope.selectedTerm= $routeParams['termId'];
		$scope.coScholasticScoreForm.termId= $routeParams['termId'];
		
		this.getStudentDetails($scope.coScholasticScoreForm.studentId);
		coScholasticExamService.getStudentCoScholasticScore($scope.coScholasticScoreForm.studentId, $scope.coScholasticScoreForm.studentClassId, $scope.selectedTerm, this.displayStudentCoScholasticScore, this.errorFuntion);
		this.getClassCoScholasticEvaluationTerms($scope.coScholasticScoreForm.studentClassId);
	}
	
	this.getClassCoScholasticEvaluationTerms = function(classId){
		evaluationTermService.getCoScholasticEvaluationTermsOfClass(classId, this.renderderEvaluationTerms, this.errorFuntion);
	}
	
	this.renderderEvaluationTerms = function(response, status){
		$scope.CoScholasticEvalTerms = response;
	}
	
	this.displayStudentCoScholasticScore = function(response, status){
		//$scope.activities = response;
		$scope.studCoScholasticAssessment = response;
	}
	
	this.saveCoScholasticScore = function(){
		$scope.studCoScholasticAssessment.activities.forEach(function(activity){
			if(activity.subActivities.length > 0){
				activity.subActivities.forEach(function(subActivity){
					subActivity.skills.forEach(function(skill){
						skill.assessmentCriterias.forEach(function(criteria){
							var score ={};
							
							score.criteriaId=criteria.criteriaId;
							score.scoreId=criteria.scoreId;
							score.score=criteria.score;
							score.gradeScalePointId=criteria.gradeScalePointId;
							console.log("Value:"+criteria.freeTextValue);
							score.freeTextValue=criteria.freeTextValue;
							$scope.coScholasticScoreForm.criteriaScores.push(score);
						})
					})
				})
			}else{
				activity.skills.forEach(function(skill){
					skill.assessmentCriterias.forEach(function(criteria){
						var score ={};
						
						score.criteriaId=criteria.criteriaId;
						score.scoreId=criteria.scoreId;
						score.score=criteria.score;
						score.gradeScalePointId=criteria.gradeScalePointId;
						console.log("Value:"+criteria.freeTextValue);
						score.freeTextValue=criteria.freeTextValue;
						$scope.coScholasticScoreForm.criteriaScores.push(score);
					})
				})
			}
		});
		
		console.log($scope.coScholasticScoreForm);
		
		coScholasticExamService.saveStudentCoscholasticScore($scope.coScholasticScoreForm, this.realodPage, this.errorFuntion);
		
	}
	
	this.realodPage = function(response, status){
		$route.reload();
	}
	
	this.getStudentDetails = function(studentId){
		studentService.getStudentFeeBriefInfo(studentId, null, this.renderStudentDetails, this.errorFuntion);
	}
	
	this.renderStudentDetails = function(response, status){
		$scope.studBriefInfo = response;
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}
	
});






