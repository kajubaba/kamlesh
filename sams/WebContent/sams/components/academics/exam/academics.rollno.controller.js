samsApp.controller('academics.rollno.controller', function(commonService, 
																	classSectionService, 
																	
																	
																	studentSectionService,
																	rollNoService,
																	$scope, 
																	blockUI, 
																	$routeParams, 
																	$route, 
																	$location){

	$scope.selectedAcademicYearId = null;
	
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	$scope.sectionList = null;
	$scope.students = null;
	$scope.showSaveButton = false;
	
	$scope.filterCrt ={
			auId : "",
			academicYearId : null,
			classId : "",
			sectionId : ""
			
	}
	
	$scope.classRollNoForm ={
			classId : "",
			rollNoForms : []
	}
	
	this.init = function(){
		
		$scope.selectedAcademicYearId = $routeParams['academicYearId'];
		
		$scope.filterCrt.auId = $routeParams['auId'];
		$scope.filterCrt.classId = $routeParams['classId'];
		$scope.filterCrt.sectionId = $routeParams['sectionId'];
		
		
		if($scope.filterCrt.auId=="null"){
			$scope.filterCrt.auId = "";
		}
		
		if($scope.filterCrt.classId=="null"){
			$scope.filterCrt.classId = "";
		}
		
		if($scope.filterCrt.sectionId=="null"){
			$scope.filterCrt.sectionId = "";
		}
		
		
		
		this.fetchAffiliationAuthorities();
		this.getClasses();
		this.getClassSections();
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
		
		console.log("Reloading page...");
		
		$location.path('academics/rollno/'+$scope.selectedAcademicYearId+'/'+$scope.filterCrt.auId+'/'+$scope.filterCrt.classId+"/"+$scope.filterCrt.sectionId);
	}
	
	this.refreshPage  = function(){
		$route.reload();
	}
	
	this.changeAcademicYear = function(academicYearId){
		$location.path('academics/attendance/'+academicYearId+'/null/null/null/null');
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
	
	this.getStudents = function(){
		if($scope.filterCrt.classId != ""){
			rollNoService.getStudentsForRollNo($scope.filterCrt.classId, $scope.filterCrt.sectionId, this.renderStudents, this.errorFuntion);
		}
	}
	
	this.saveRollNos = function(){
		$scope.classRollNoForm.classId = $scope.filterCrt.classId;
		
		
		angular.forEach($scope.students, function(studentRollNo){
			var student = new Object();
			student.id=studentRollNo.id;
			student.studentId=studentRollNo.studentDBId;
			student.rollNo=studentRollNo.rollNo;
			
			$scope.classRollNoForm.rollNoForms.push(student);
		})
		
		console.log($scope.classRollNoForm);
		rollNoService.saveRollNos($scope.classRollNoForm, this.refreshPage, this.errorFuntion);
	}
	
	
	
	this.renderStudents = function(response, status){
		$scope.students = response;
		
		
		
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
	
	this.errorFuntion = function(response){
	}
	
});






