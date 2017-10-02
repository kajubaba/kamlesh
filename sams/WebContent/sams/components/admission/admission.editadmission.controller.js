/*
 * @ Author Narendra Patidar
 * @ Date Jan-10-2016
 * 
 * This is Angular JS based controller. It can be declared into HTML pages with 'ng-controller' directive.
 * 
 * 
 * It is used to register new admission in academic year.
 *  
 * 
 *  
 */

samsApp.controller('admission.editadmission.controller', function(newAdmissionService, $scope, blockUI, $routeParams){
	
	
	$scope.formData = null;
	
	var block_select_class = blockUI.instances.get('block-select-class');
	
	var oriFormData = angular.copy($scope.formData);
	
	this.resetForm = function(){
		$scope.formData = angular.copy(oriFormData);
		$("#modalRegistrationSuccess").modal('hide');
	}
	
	this.validateForm = function(){

		
		var hasError = false;
		
		if(!this.validateStudentNameRequired()){
			hasError = true;
		}
		
		if(!this.validateGenderRequired()){
			hasError = true;
		}
		
		if(!this.validateClassRequired()){
			hasError = true;
		}
		
		if(!this.validateAffiliationauthorityRequired()){
			hasError = true;
		}
		
		if(!this.validateDOB()){
			hasError = true;
		}
		
		return hasError;
		
	}
	
	this.submitForm = function(){
		$scope.formData.dobStr = $("#admissionDOB").val();
		blockUI.start();
		if(this.validateForm()){
			$("#alert-error").show();
			$("#infoUpdatedSuccessfully").hide();
			blockUI.stop();
			$("html, body").animate({ scrollTop: $(document).height() }, 1000);
			return;
		}
		$("#alert-error").hide();
		
		newAdmissionService.updateStudentInformation($scope.formData, this.submitFormSuccess, this.errorFuntion)
	}
	
	
	this.submitFormSuccess = function(response, status){
		blockUI.stop();
		if(response.status = "Ok"){
			$("#infoUpdatedSuccessfully").show();
		//	window.clearTimeout(1000);
		//	$("#infoUpdatedSuccessfully").hide();
			/*setTimeout(function() {
		        $("#infoUpdatedSuccessfully").hide('blind', {}, 500)
		    }, 5000);*/
		}
		
	}
	
	
	
	
	
	
	
	this.fetchActiveClasses = function(){
		
		if(this.validateAffiliationauthorityRequired()){
			block_select_class.start();
			newAdmissionService.getActiveClasses($scope.formData.affiliationAuthorityId, this.renderActiveClasses, this.errorFuntion);
		}else{
			$scope.formData.academicYearClassId = null;
			$scope.formData.academicYearClasses={};
			$("#requiredClass").addClass("has-error");
		}
		
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.formData.academicYearClasses=response;
		block_select_class.stop();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	this.errorFuntion = function(response){
		
	}
	
	
	this.validateAffiliationauthorityRequired = function(){
		if($scope.formData.affiliationAuthorityId == null){
			$("#requiredAffiliationAuthority").addClass("has-error");
			return false;
		}else{
			$("#requiredAffiliationAuthority").removeClass("has-error");
			return true;
		}
	}
	
	this.validateClassRequired = function(){
		if($scope.formData.academicYearClassId == null){
			$("#requiredClass").addClass("has-error");
			return false;
		}else{
			$("#requiredClass").removeClass("has-error");
			return true;
		}
	}
	
	this.validateStudentNameRequired = function(){
		if($scope.formData.studentName == ""){
			$("#requiredStudentName").addClass("has-error");
			return false;
		}else{
			$("#requiredStudentName").removeClass("has-error");
			return true;
		}
	}
	
	this.validateGenderRequired = function(){
		
		if($scope.formData.gender == ""){
			$("#requiredGender").addClass("has-error");
			return false;
		}else{
			$("#requiredGender").removeClass("has-error");
			return true;
		}
	}
	
	this.validateDOB = function(){
		
		if($scope.formData.dobStr == ""){
			$("#requiredDOB").addClass("has-error");
			return false;
		}else{
			$("#requiredDOB").removeClass("has-error");
			return true;
		}
	}
	
	this.hideAlertBox = function(){
		
		$("#alert-box").hide();
	}
	
	this.openDatePicker = function(){
		$("#admissionDOB").datepicker("show");
	}
	
	this.loadStudentDetails = function(){
		newAdmissionService.loadStudentDetails($routeParams['studentId'], this.renderStudentDetails, this.errorFuntion)
	}
	
	this.renderStudentDetails = function(response, status){
		
		$scope.formData = response;
	}
	
});






