samsApp.controller('academics.print.marksheet.controller', function(commonService, 
																	classSectionService, 
																	coScholasticExamService,
																	evaluationTermService,
																	studentSectionService,
																	attendanceService,
																	$scope, 
																	blockUI, 
																	$routeParams, 
																	$route, 
																	$location){

	$scope.selectedAcademicYearId = null;
	
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	$scope.sectionList = null;
	$scope.evaluationTerms = null;
	$scope.students = null;
	$scope.workingDays = "---";
	$scope.showSaveButton = false;
	
	$scope.filterCrt ={
			auId : "",
			academicYearId : null,
			classId : "",
			sectionId : "",
			evaluationTermId : null
	}
	
	$scope.attendanceForm ={
			classId : "",
			termId : "",
			attendances : ""
	}
	
	this.init = function(){
		
		$scope.selectedAcademicYearId = $routeParams['academicYearId'];
		
		$scope.filterCrt.auId = $routeParams['auId'];
		$scope.filterCrt.classId = $routeParams['classId'];
		$scope.filterCrt.sectionId = $routeParams['sectionId'];
		$scope.filterCrt.evaluationTermId = $routeParams['termId'];
		
		if($scope.filterCrt.auId=="null"){
			$scope.filterCrt.auId = "";
		}
		
		if($scope.filterCrt.classId=="null"){
			$scope.filterCrt.classId = "";
		}
		
		if($scope.filterCrt.sectionId=="null"){
			$scope.filterCrt.sectionId = "";
		}
		
		if($scope.filterCrt.evaluationTermId=="null"){
			$scope.filterCrt.evaluationTermId = "";
		}
		
		this.fetchAffiliationAuthorities();
		this.getClasses();
		this.getClassSections();
		this.getEvaluationTerms();
		this.getStudents();
	}
	
	this.reloadPage = function(){
		
		
		if($scope.filterCrt.auId== ""){
			$scope.filterCrt.auId = "null";
		}
		
		if($scope.filterCrt.classId == ""){
			$scope.filterCrt.classId = "null";
		}
		
		if($scope.filterCrt.sectionId == ""){
			$scope.filterCrt.sectionId = "null";
		}
		if($scope.filterCrt.evaluationTermId == ""){
			$scope.filterCrt.evaluationTermId = "null";
		}
		console.log("123.");
		$location.path('academics/print/reportcard/'+$scope.selectedAcademicYearId+'/'+$scope.filterCrt.auId+'/'+$scope.filterCrt.classId+"/"+$scope.filterCrt.sectionId+"/"+$scope.filterCrt.evaluationTermId);
	}
	
	this.changeAcademicYear = function(academicYearId){
		$location.path('academics/print/reportcard/'+academicYearId+'/null/null/null/null');
	}
	
	this.fetchAffiliationAuthorities = function(){
		commonService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
	}
	
	this.getClasses = function(){
		if($scope.filterCrt.auId != ""){
			commonService.getActiveClassesByAcademicYear($scope.filterCrt.auId, $scope.selectedAcademicYearId,this.renderClasses, this.errorFuntion);
		}
	}
	
	this.renderClasses = function(response, status){
		$scope.classes = response;
	}
	
	this.getClassSections = function(){
		if($scope.filterCrt.classId != ""){
			classSectionService.getClassSections($scope.filterCrt.classId, this.renderSections, this.errorFuntion);
		}
	}
	
	this.renderSections = function(response, status){
		$scope.sectionList = response;
	}
	
	this.getEvaluationTerms = function(){
		if($scope.filterCrt.classId != ""){
			evaluationTermService.getScholasticEvaluationTermsOfClass($scope.filterCrt.classId, this.renderEvaluationTerms, this.errorFuntion);
		}
	}
	
	this.renderEvaluationTerms = function(response, status){
		$scope.evaluationTerms = response.scholasticEvaluationTerms;
	}
	
	this.getStudents = function(){
		if($scope.filterCrt.evaluationTermId != ""){
			attendanceService.getStudentsForAttendance($scope.filterCrt.classId, $scope.filterCrt.sectionId, $scope.filterCrt.evaluationTermId, this.renderStudents, this.errorFuntion);
		}
	}
	
	this.renderStudents = function(response, status){
		$scope.students = response.attendances;
		
		
		
		if($scope.students.length > 0){
			$scope.showSaveButton = true;
		}
		
	}
	

	this.selectClass = function(){
		$scope.filterCrt.sectionId = "";
		$scope.filterCrt.evaluationTermId = "";
		this.reloadPage();
	}
	
	this.selectAU = function(){
		$scope.filterCrt.classId = "";
		$scope.filterCrt.sectionId = "";
		$scope.filterCrt.evaluationTermId = "";
		this.reloadPage();
	}
	
	this.printMarksheets = function(){
		window.open(_appContextPath+"/ws/academics/student/scorecard/pdf/"+$scope.filterCrt.classId+"/"+$scope.filterCrt.evaluationTermId);
	}
	
	this.errorFuntion = function(response){
	}
	
});






