

samsApp.controller('academics.manage.csa.controller', function(commonService, classSectionService, $scope, blockUI, $routeParams, $route, $location){
	
	
	this.openAddUpdateSchemeModal = function(){
		$("#modalAddUpdateScheme").modal('show');
	}
	
	this.closeAddUpdateSchemeModal = function(){
		$("#modalAddUpdateScheme").modal('hide');
	}
	
	/*$scope.auId = null;
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	
	$scope.formData = {
			academicYearClassId : null,
			sectionName : "",
			sectionCode : ""
	};
	
	$scope.selectedAcademicYear = null;
	$scope.selectedClassId = null;
	var block_select_class = blockUI.instances.get('block-select-class');
	
	this.getClasswiseSectionCount = function(){
		
		if(angular.isUndefined($routeParams['academicYearId']) || $routeParams['academicYearId']=="null"){
			$scope.selectedAcademicYear = null;
		}else{
			$scope.selectedAcademicYear = $routeParams['academicYearId'];
		}
		classSectionService.getClasswiseSectionCountService($scope.selectedAcademicYear, this.renderClasswiseSectionCount, this.errorFuntion);
	}
	
	this.renderClasswiseSectionCount = function(response, status){
		var datatable = $('#datatable-classwise-sections-counts').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	}
	
	this.getClassSections = function(){
		
		if(angular.isUndefined($routeParams['classId']) || $routeParams['classId']=="null"){
			$scope.selectedClassId = null;
		}else{
			$scope.selectedClassId = $routeParams['classId'];
			$scope.formData.academicYearClassId = $scope.selectedClassId;
		}
		classSectionService.getClassWiseSectionDetailsService($scope.selectedClassId, this.renderClassSections, this.errorFuntion);
	}
	
	this.renderClassSections = function(response, status){
		var datatable = $('#datatable-classwise-sections').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
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
			commonService.getActiveClasses($scope.auId, this.renderActiveClasses, this.errorFuntion);
		}else{
			$scope.classes = null;
		}
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
	}
	
	
	this.enableDisableSaveButton = function(){
		console.log($scope.formData.academicYearClassId);
		if($scope.formData.academicYearClassId != null && $scope.formData.sectionName != ""){
			$("#saveBtn").removeClass("disabled");
		}else{
			$("#saveBtn").addClass("disabled");
		}
	}
	
	this.addSection= function(){
		classSectionService.addNewSection($scope.formData, this.addSectionSuccess, this.errorFuntion)
	}
	
	this.updateSection = function(){
		classSectionService.addNewSection($scope.formData, this.addSectionSuccess, this.errorFuntion)
	}
	
	
	this.addSectionSuccess = function(response, status){
		$("#modalSection").modal('hide');
		$("#modalAddUpdateClassSection").modal('hide');
		$route.reload();
	}*/
	
	this.errorFuntion = function(response){
		console.log(response);
	}

});






