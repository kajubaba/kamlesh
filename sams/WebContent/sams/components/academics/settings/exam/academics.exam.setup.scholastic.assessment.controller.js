

samsApp.controller('academics.exam.setup.scholastic.assessment.controller', function(evaluationTermService, evaluationTypeService, termAssessmentService, gradeService, $scope, blockUI, $routeParams, $route, $location, $filter){
	
	$scope.assessmentType = null;
	$scope.gradeScales = null;
	$scope.selectedExamPatternId = null;
	$scope.isNewTermAssessment = false;
	
	$scope.termForm = {
		id : null,
		name : null,
		displayName : null,
		displayOrder : null,
		evaluationTypeId : null,
		weightage : null,
		workingDays : null,
		isScholastic : true
	}
	
	$scope.assessmentForm = {
			id : null,
			name : null,
			displayName : null,
			displayOrder : null,
			weightage : null,
			termId : null,
			maxMarks : null
	}
	
	$scope.gradeScoreForm = {
			assessmentTypeId : null,
			gradeScaleId : null,
			scoringMethod : null,
			gradeCalculationMethod : null,
			isFASABasedExam : null,
			isTermBasedAssessment : null,
			useAssessmentWeightageAsMaxMarks : null
	}
	
	this.init = function(){
		$scope.selectedExamPatternId = $routeParams['examPatternId'];
		evaluationTypeService.getScholasticAssessmentTypeDetails($scope.selectedExamPatternId, this.renderExamPattern, this.errorFuntion);
		gradeService.getGradeScales(this.renderGradeScales, this.errorFuntion);
	}
	
	this.renderGradeScales = function(response, status){
		$scope.gradeScales = response;
	}
	
	this.openTermPopup = function(id, name, displayName, displayOrder, evaluationTypeId, weightage, workingDays){
		$scope.termForm.id = id;
		$scope.termForm.name = name;
		$scope.termForm.displayName = displayName;
		$scope.termForm.displayOrder = displayOrder;
		$scope.termForm.weightage = weightage;
		$scope.termForm.workingDays = workingDays;
		
		this.validateForm();
		$("#modalAddUpdateTerm").modal('show');
	}
	
	this.openUpdateTermAssessmentPopup = function(id, name, displayName, displayOrder, weightage, maxMarks){
		this.resetTermAssessmentForm();
		$scope.assessmentForm.id = id;
		$scope.assessmentForm.name = name;
		$scope.assessmentForm.displayName = displayName;
		$scope.assessmentForm.displayOrder = displayOrder;
		$scope.assessmentForm.weightage = weightage;
		$scope.assessmentForm.maxMarks = maxMarks;
		
		$scope.isNewTermAssessment = false;
		
		$("#modalAddUpdateTermAssessment").modal('show');
	}
	
	this.openNewTermAssessmentPopup = function(){
		this.resetTermAssessmentForm();
		$scope.isNewTermAssessment = true;
		$("#modalAddUpdateTermAssessment").modal('show');
	}
	
	this.resetTermAssessmentForm = function(){
		$scope.assessmentForm.id = null;
		$scope.assessmentForm.name = null;
		$scope.assessmentForm.displayName = null;
		$scope.assessmentForm.displayOrder = null;
		$scope.assessmentForm.weightage = null;
		$scope.assessmentForm.maxMarks = null;
	}
	
	this.closeTermAssessmentPopup = function(){
		$("#modalAddUpdateTermAssessment").modal('hide');
	}
	
	this.saveTermAssessment = function(){
		termAssessmentService.updateTermAssessment($scope.assessmentForm, this.refreshPage, this.errorFuntion);
	}
	
	this.openNewTermPopup = function(){
		$scope.termForm.evaluationTypeId = $scope.assessmentType.id;
		this.validateForm();
		$("#modalAddUpdateTerm").modal('show');
	}
	
	this.closeTermPopup = function(){
		$("#modalAddUpdateTerm").modal('hide');
	}
	
	this.refreshPage = function(){
		$("#modalAddUpdateTerm").modal('hide');
		$("#modalAddUpdateTermAssessment").modal('hide');
		$route.reload();
	}
	
	this.validateForm = function(){
		
		if($scope.termForm.name != null && $scope.termForm.displayName !=null && $scope.termForm.displayOrder != null
		&& $scope.termForm.name != "" && $scope.termForm.displayName !="" && $scope.termForm.displayOrder != ""){
			$("#btnSaveTerm").removeClass("disabled");
		}else{
			$("#btnSaveTerm").addClass("disabled");
		}
	}
	
	this.saveEvaluationTerm = function(){
		evaluationTermService.saveEvaluationTerm($scope.termForm, this.refreshPage, this.errorFuntion);
	}
	
	this.renderExamPattern = function(response, status){
		$scope.assessmentType = response;
		$scope.gradeScoreForm.gradeScaleId =response.gradeScaleId;
		
		$scope.gradeScoreForm.isFASABasedExam = response.isFASABasedExam;
		$scope.gradeScoreForm.isTermBasedAssessment = response.isTermBasedAssessment;
		$scope.gradeScoreForm.useAssessmentWeightageAsMaxMarks = response.useAssessmentWeightageAsMaxMarks;
		
		
	}
	
	this.saveGradeScaleAndScoringMethod = function(){
		$scope.gradeScoreForm.assessmentTypeId =  $scope.assessmentType.id;
		if($scope.gradeScoreForm.useAssessmentWeightageAsMaxMarks  == null){
			$scope.gradeScoreForm.useAssessmentWeightageAsMaxMarks  == false;
		}
		if($scope.gradeScoreForm.isTermBasedAssessment   == null){
			$scope.gradeScoreForm.isTermBasedAssessment   == false;
		}
		
		if($scope.gradeScoreForm.isFASABasedExam    == null){
			$scope.gradeScoreForm.isFASABasedExam    == false;
		}
		
		
		evaluationTypeService.saveGradeScaleAndScoringMethod($scope.gradeScoreForm, this.refreshPage, this.errorFuntion);
	}
	
	this.deleteAssessmentTerm = function(termId){
		evaluationTermService.deleteAssessmentTerm(termId, this.refreshPage, this.errorFuntion);
	}
	
	this.deleteTermAssessment = function(termAssessmentId){
		termAssessmentService.deleteTermAssessment($scope.assessmentForm.id, this.refreshPage, this.errorFuntion);
	}
	
	
	
	this.errorFuntion = function(response){
		console.log(response);
	}

});






