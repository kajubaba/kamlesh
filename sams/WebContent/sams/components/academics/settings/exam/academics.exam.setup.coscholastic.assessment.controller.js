

samsApp.controller('academics.exam.setup.coscholastic.assessment.controller', function(gradeService, evaluationTermService,evaluationTypeService, $scope, blockUI, $routeParams, $route, $location, $filter){
	
	//$scope.examPattern = null;
	$scope.assessmentType = null;
	$scope.gradeScales = null;
	$scope.selectedExamPatternId = null;
	
	$scope.termForm = {
			id : null,
			name : null,
			displayName : null,
			displayOrder : null,
			evaluationTypeId : null,
			weightage : null,
			isScholastic : false
	}
	
	$scope.gradeScoreForm = {
			assessmentTypeId : null,
			gradeScaleId : null,
			scoringMethod : null,
			gradeCalculationMethod : null,
			maxMarks : null
	}
	
	this.init = function(){
		$scope.selectedExamPatternId = $routeParams['examPatternId'];
		evaluationTypeService.getCoScholasticAssessmentTypeDetails($scope.selectedExamPatternId, this.renderAssessmentTypeDetails, this.errorFuntion);
		gradeService.getGradeScales(this.renderGradeScales, this.errorFuntion);
	}
	
	this.renderGradeScales = function(response, status){
		$scope.gradeScales = response;
	}
	
	this.renderAssessmentTypeDetails = function(response, status){
		$scope.assessmentType = response;
		$scope.gradeScoreForm.gradeScaleId =response.gradeScaleId;
		$scope.gradeScoreForm.scoringMethod = response.scoringMethod;
		$scope.gradeScoreForm.gradeCalculationMethod = response.gradeCalculationMethod;
		console.log($scope.gradeScoreForm.gradeCalculationMethod);
		$scope.gradeScoreForm.maxMarks = response.maxMarks;
		
		
		if($scope.gradeScoreForm.scoringMethod == "GRADE_BASED"){
			$scope.gradeScoreForm.gradeCalculationMethod = "NO_CONVERSION";
			$("#rd1").attr("disabled",true);
			$("#rd2").attr("disabled",true);
			$("#rd3").attr("disabled",false);
			$scope.gradeScoreForm.maxMarks = null;
			$("#maxMarks").attr("disabled",true);
		}else{
			$("#rd3").attr("disabled",true);
			$("#rd1").attr("disabled",false);
			$("#rd2").attr("disabled",false);
			$("#maxMarks").attr("disabled",false);
			
		}
	}
	
	this.openTermPopup = function(id, name, displayName, displayOrder, evaluationTypeId){
		$scope.termForm.id = id;
		$scope.termForm.name = name;
		$scope.termForm.displayName = displayName;
		$scope.termForm.displayOrder = displayOrder;
		$scope.termForm.evaluationTypeId = evaluationTypeId;
		this.validateForm();
		$("#modalAddUpdateTerm").modal('show');
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
	
	this.saveGradeScaleAndScoringMethod = function(){
		$scope.gradeScoreForm.assessmentTypeId = $scope.assessmentType.id;
		console.log($scope.gradeScoreForm);
		evaluationTypeService.saveGradeScaleAndScoringMethod($scope.gradeScoreForm, this.refreshPage, this.errorFuntion);
	}
	
	this.changeScoringMethod = function(){
		this.enableDisableRadioButton();
	}
	
	this.changeGradeConversionMethod = function(){
		if($scope.gradeScoreForm.gradeCalculationMethod == "NO_CONVERSION"){
			$scope.gradeScoreForm.maxMarks = null;
			$("#maxMarks").attr("disabled",true);
		}else{
			$("#maxMarks").attr("disabled",false);
		}
	}
	
	this.enableDisableRadioButton = function(){
		if($scope.gradeScoreForm.scoringMethod == "GRADE_BASED" || $scope.gradeScoreForm.scoringMethod == "GRADE_TO_INDICATOR_BASED"){
			$scope.gradeScoreForm.gradeCalculationMethod = "NO_CONVERSION";
			$("#rd1").attr("disabled",true);
			$("#rd2").attr("disabled",true);
			$("#rd3").attr("disabled",false);
			$scope.gradeScoreForm.maxMarks = null;
			$("#maxMarks").attr("disabled",true);
		}else{
			$scope.gradeScoreForm.gradeCalculationMethod = "MARKS_TO_GRADE";
			$("#rd3").attr("disabled",true);
			$("#rd1").attr("disabled",false);
			$("#rd2").attr("disabled",false);
			$("#maxMarks").attr("disabled",false);
			
		}
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}

});






