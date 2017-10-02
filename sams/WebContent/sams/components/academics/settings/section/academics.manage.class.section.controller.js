

samsApp.controller('acad.manage.class.section.controller', function(commonService, classSectionService, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.auId = null;
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	$scope.classSections = null;
	
	$scope.formData = {
			id: "",
			academicYearClassId : null,
			sectionName : "",
			sectionCode : ""
	};
	
	$scope.selectedAcademicYear = null;
	$scope.selectedClassId = null;
	var block_select_class = blockUI.instances.get('block-select-class');
	
	this.getClasswiseSectionCount = function(){
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		classSectionService.getClasswiseSectionCount($scope.selectedAcademicYear, this.renderClasswiseSectionCount, this.errorFuntion);
	}
	
	this.renderClasswiseSectionCount = function(response, status){
		var datatable = $('#datatable-classwise-sections-counts').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	}
	
	this.getClassSections = function(){
		$scope.selectedClassId = $routeParams['classId'];
		$scope.formData.academicYearClassId = $scope.selectedClassId;
		classSectionService.getClassSections($scope.selectedClassId, this.renderClassSections, this.errorFuntion);
	}
	
	this.renderClassSections = function(response, status){
		$scope.classSections = response;
	}
	
	this.openPopupToUpdateSection = function(id, sectionName, sectionCode){
		$scope.formData.id= id;
		$scope.formData.sectionName= sectionName;
		$scope.formData.sectionCode= sectionCode;
		$("#modalAddUpdateClassSection").modal('show');
	}

	this.openSectionModal = function(){
		this.fetchAffiliationAuthorities();
		$("#modalSection").modal('show');
	}
	
	this.closeSectionModal = function(){
		$("#modalSection").modal('hide');
	}
	
	this.openAddUpdateSectionModal = function(){
		$("#modalAddUpdateClassSection").modal('show');
	}
	
	this.closeAddUpdateSectionModal = function(){
		$("#modalAddUpdateClassSection").modal('hide');
	}
	
	this.fetchAffiliationAuthorities = function(){
		commonService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
	}
	
	this.fetchActiveClasses = function(){
		if($scope.auId != null){
			commonService.getActiveClassesByAcademicYear($scope.auId, $scope.selectedAcademicYear, this.renderActiveClasses, this.errorFuntion);
		}else{
			$scope.classes = null;
		}
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
	}
	
	
	this.enableDisableSaveButton = function(){
		if($scope.formData.academicYearClassId != null && $scope.formData.sectionName != ""){
			$("#saveBtn").removeClass("disabled");
		}else{
			$("#saveBtn").addClass("disabled");
		}
	}
	
	this.addSection= function(){
		classSectionService.saveSection($scope.formData, this.addSectionSuccess, this.errorFuntion)
	}
	
	/*this.updateSection = function(){
		classSectionService.addNewSection($scope.formData, this.addSectionSuccess, this.errorFuntion)
	}*/
	
	
	this.addSectionSuccess = function(response, status){
		$("#modalSection").modal('hide');
		$("#modalAddUpdateClassSection").modal('hide');
		$route.reload();
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}

});






