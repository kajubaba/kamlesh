samsApp.controller('academics.exam.scholastic.controller', function(commonService, 
																	classSectionService, 
																	classSubjectService, 
																	
																	evaluationTermService,
																	termAssessmentService,
																	scholasticScoreService,
																	$scope, 
																	blockUI, 
																	$routeParams, 
																	$route, 
																	$location){

	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	$scope.sectionList = null;
	$scope.SubjectList = null;
	$scope.evaluationTerms = null;
	$scope.termAssessments = null;
	$scope.studentScores = null;
	$scope.scorecard = null;
	$scope.maxMarks = "---";
	$scope.useAssessmentWeightageAsMaxMarks = null;
	$scope.showSaveButton = false;
	
	$scope.filterCrt ={
			auId : null,
			academicYearId : null,
			classId : null,
			sectionId : null,
			subjectId : null,
			evaluationTermId : null,
			termAssessmentId : null
	}
	
	$scope.marksCollectionForm = {
			studentScores :null
	}
	
	this.changeAcademicYear = function(academicYearId){
		$location.path("academics/assessment/sch/"+academicYearId);
	}
	
	this.fetchAffiliationAuthorities = function(){
		commonService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
	}
	
	this.selectAffiliationAuthority = function(){
		$scope.studentScores = null;
		if($scope.filterCrt.auId != null){
			commonService.getActiveClassesByAcademicYear($scope.filterCrt.auId, $scope.filterCrt.academicYearId,this.renderClasses, this.errorFuntion);
		}else{
			$scope.classes = null;
			$scope.evaluationTerms = null;
			$scope.termAssessments = null;
			
		}
	}
	
	this.selectClass = function(){
		$scope.studentScores = null;
		this.disableButton();
		if($scope.filterCrt.classId != null){
			classSectionService.getClassSections($scope.filterCrt.classId, this.renderSections, this.errorFuntion);
			classSubjectService.getClassWiseSubjectDetailsService($scope.filterCrt.classId, this.renderSubjects, this.errorFuntion);
			evaluationTermService.getScholasticEvaluationTermsOfClass($scope.filterCrt.classId, this.renderEvaluationTerms, this.errorFuntion);
			
			
		}else{
			$scope.sectionList = null;
			$scope.subjectList = null;
			$scope.evaluationTerms = null;
			$scope.termAssessments = null;
		}
	}
	
	
	
	this.renderSubjects = function(response, status){
		$scope.subjectList = response;
	}
	
	this.renderSections = function(response, status){
		$scope.sectionList = response;
	}
	
	this.renderClasses = function(response, status){
		$scope.classes = response;
	}
	
	
	
	
	this.selectEvaluationTerm = function(){
		$scope.studentScores = null;
		this.disableButton();
		if($scope.filterCrt.evaluationTermId != null){
			termAssessmentService.getTermAssessments($scope.filterCrt.evaluationTermId, this.renderTermAssessments, this.errorFuntion);
		}else{
			$scope.termAssessments = null;
		}
		
	}
	
	this.renderTermAssessments = function(response, status){
		$scope.termAssessments = response;
	}
	
	this.renderEvaluationTerms = function(response, status){
		
		/*$scope.useAssessmentWeightageAsMaxMarks = response.useAssessmentWeightageAsMaxMarks;
		if(response.useAssessmentWeightageAsMaxMarks == false || response.useAssessmentWeightageAsMaxMarks == null || response.useAssessmentWeightageAsMaxMarks == ""){
			$scope.maxMarks = 100;
		}else{
			$scope.maxMarks = "---";
		}*/
		
		$scope.evaluationTerms = response.scholasticEvaluationTerms;
	}
	
	this.getStudentScores = function(){
		
		$scope.studentScores = null;
		this.disableButton();
		/*if($scope.useAssessmentWeightageAsMaxMarks){
			angular.forEach($scope.termAssessments, function(value, key){
				
			      if(value.id == $scope.filterCrt.termAssessmentId ){
			    	  $scope.maxMarks = value.weightage;
			      }
			        
			});
		}*/
		
		if($scope.filterCrt.classId != null && $scope.filterCrt.subjectId != null && $scope.filterCrt.termAssessmentId!=null){
			if($scope.filterCrt.sectionId == null){
				scholasticScoreService.getStudentScores($scope.filterCrt.classId, "", $scope.filterCrt.subjectId, $scope.filterCrt.termAssessmentId, this.renderStudentScores, this.errorFuntion);
			}else{
				scholasticScoreService.getStudentScores($scope.filterCrt.classId, $scope.filterCrt.sectionId, $scope.filterCrt.subjectId, $scope.filterCrt.termAssessmentId, this.renderStudentScores, this.errorFuntion);
			}
			
		}
	}
	
	this.renderStudentScores = function(response, status){
		$scope.maxMarks = response.maxMarks;
		$scope.studentScores = response.studentScores;
		if($scope.studentScores.length > 0){
			$scope.showSaveButton = true;
		}
	}
	
	this.saveMarks = function(){
		
		/*angular.forEach($scope.studentScores, function(value, key){
			
			if(angular.isUndefined(value.marksObtained)){
				console.log("Errror..");
			}
			
			if(angular.isNumber(value.marksObtained) || value.marksObtained == null){
				console.log("Score:"+value.marksObtained);
			}else{
				console.log("String:"+value.marksObtained);
			}
		     
		        
		});*/
		
		var sectionId = "";
		
		if($scope.filterCrt.sectionId != null){
			sectionId = $scope.filterCrt.sectionId;
		}
		
		$scope.marksCollectionForm.studentScores = $scope.studentScores;
		scholasticScoreService.saveStduentMarks($scope.filterCrt.classId, sectionId, $scope.filterCrt.subjectId, $scope.filterCrt.termAssessmentId, $scope.marksCollectionForm, this.saveMarksSuccess, this.errorFuntion);
	}
	
	this.saveMarksSuccess = function(response, status){
		$scope.studentScores = response;
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}
	

	this.init = function(){
		$scope.filterCrt.academicYearId = $routeParams['academicYearId'];
		this.fetchAffiliationAuthorities();
	}
	
	this.getScorecard = function(){
		scholasticScoreService.getStudentScoreCard($routeParams['studentId'], $routeParams['classId'], this.renderScorecard, this.errorFuntion);
		
	}
	
	this.renderScorecard = function(response, status){
		$scope.scorecard = response;
		console.log($scope.scorecard);
	}
	
	this.validateForm = function(obj){
		console.log(obj);
	}
	
	this.disableButton = function(){
		$scope.showSaveButton = false;
	}
});






