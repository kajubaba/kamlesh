

samsApp.controller('academics.manage.subject.controller', function(commonService, classSubjectService, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.auId = null;
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	$scope.classSubjects = null;
	$scope.formData = {
			id : "",
			academicYearClassId : "",
			subjectName : "",
			subjectCode : "",
			displaySequenceNo : ""
			
	};
	
	$scope.editForm = null;
	$scope.isUpdate = false;
	$scope.isAdd = true;
	$scope.selectedAcademicYear = null;
	$scope.selectedClassId = null;
	var block_select_class = blockUI.instances.get('block-select-class');
	
	var oriFormData = angular.copy($scope.formData);
	
	this.getClasswiseSubjectCount = function(){
		if(angular.isUndefined($routeParams['academicYearId']) || $routeParams['academicYearId']=="null"){
			$scope.selectedAcademicYear = null;
		}else{
			$scope.selectedAcademicYear = $routeParams['academicYearId'];
		}
		classSubjectService.getClasswiseSubjectCount($scope.selectedAcademicYear, this.renderClasswiseSubjectCount, this.errorFuntion);
	}
	
	this.renderClasswiseSubjectCount = function(response, status){
		var datatable = $('#datatable-classwise-subjects').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	}
	
	this.getClassSubjects = function(){
		
		if(angular.isUndefined($routeParams['classId']) || $routeParams['classId']=="null"){
			$scope.selectedClassId = null;
		}else{
			$scope.selectedClassId = $routeParams['classId'];
		}
		classSubjectService.getClassWiseSubjectDetailsService($scope.selectedClassId, this.renderClasswiseSubjectDetails, this.errorFuntion);
	}
	
	
	this.openSubjectModal = function(){
		$scope.formData = oriFormData;
		this.fetchAffiliationAuthorities();
		$scope.isUpdate = false;
		$scope.isAdd = true;
		this.enableDisableSaveButton();
		$("#modalSubject").modal('show');
	}
	
	this.closeSubjectModal = function(){
		$("#modalSubject").modal('hide');
	}
	
	this.fetchAffiliationAuthorities = function(){
		commonService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
	}
	
	this.fetchActiveClasses = function(){
		$scope.formData.academicYearClassId = "";
		this.enableDisableSaveButton();
		if($scope.auId != null){
			commonService.getActiveClassesByAcademicYear($scope.auId, $scope.selectedAcademicYear, this.renderActiveClasses, this.errorFuntion);
		}else{
			$scope.classes = null;
		}
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
	}
	
	this.resetForm = function(){
		$scope.formData = angular.copy(oriFormData);
		$("#modalRegistrationSuccess").modal('hide');
	}
	
	this.enableDisableSaveButton = function(){
		if($scope.formData.academicYearClassId != "" && $scope.formData.subjectName != ""){
			$("#saveBtn").removeClass("disabled");
		}else{
			$("#saveBtn").addClass("disabled");
		}
	}
	
	this.enableDisableUpdateButton = function(){
		if($scope.formData.subjectName != ""){
			$("#saveBtn").removeClass("disabled");
		}else{
			$("#saveBtn").addClass("disabled");
		}
	}
	
	this.addSubject = function(){
		classSubjectService.saveSubject($scope.formData, this.addSubjectSuccess, this.errorFuntion);
	}
	
	this.updateSubject = function(){
		classSubjectService.saveSubject($scope.formData, this.addSubjectSuccess, this.errorFuntion);	
	}
	
	
	this.addSubjectSuccess = function(response, status){
		$("#modalSubject").modal('hide');
		$route.reload();
	}
	
	/*this.getClasswiseSubjectCount = function(){
		classSubjectService.getClasswiseSubjectCount($scope.selectedAcademicYear, this.renderClasswiseSubjectCount, this.errorFuntion);
	}*/
	
	
	
	this.renderClasswiseSubjectDetails = function(response, status){
		$scope.classSubjects = response;
	}
	
	this.openPopupToUpdateSubject = function(subjectId, subjectName, subjectCode, displaySequenceNo){
		$scope.formData.id = subjectId;
		$scope.formData.subjectName = subjectName;
		$scope.formData.subjectCode = subjectCode;
		$scope.formData.displaySequenceNo = displaySequenceNo;
		this.enableDisableUpdateButton();
		$("#modalSubject").modal('show');
		
		
	}

	
	this.errorFuntion = function(response){
		console.log(response);
	}

});






