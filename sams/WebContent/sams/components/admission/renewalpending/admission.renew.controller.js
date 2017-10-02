

samsApp.controller('admission.renew.controller', function(newAdmissionService, $routeParams, $scope,  $location, blockUI){
	
	var block_renew_section = blockUI.instances.get('block-renew-section');
	$scope.affiliationAuthorities = null;
	$scope.busStops = null;
	$scope.admissionSchemes = null;
	$scope.formData = {
			formNo : "",
			affiliationAuthorityId     : "",
			academicYearClassId : "",
			busStopId : "",
			studentId : "",
			admissionSchemeId : ""	
	};
	
	$scope.isEligibleForRenew = null;
	
	
	
	this.showAdmissionsPendingForRenewal = function(){
		$("#modalRenewalSuccess").modal('hide');
		
		$location.path("/admissions/toberenewed"); 
		
	}
	
	this.validateForm = function(){
		var hasError = false;
		if(!this.validateClassRequired()){
			hasError = true;
		}
		if(!this.validateAffiliationauthorityRequired()){
			hasError = true;
		}
		return hasError;
		
	}
	
	this.renewAdmission = function(){
		block_renew_section.start();
		if(this.validateForm()){
			$("#alert-error").show();
			block_renew_section.stop();
			$("html, body").animate({ scrollTop: $(document).height() }, 1000);
			return;
		}
		$("#alert-error").hide();
		$scope.formData.studentId=$("#studentId").html();
		newAdmissionService.renewAdmission($scope.formData, this.renewAdmissionSuccess, this.errorFuntion)
	}
	
	this.fetchAdmissionSchemes = function(){
		block_renew_section.start();
		newAdmissionService.getActiveAdmissionSchemes(this.renderAdmissionSchemes, this.errorFuntion);
		
	}
	
	this.renewAdmissionSuccess = function(response, status){
		$("#modalRenewalSuccess").modal('show');
		block_renew_section.stop();
	}
	
	this.initializeScreen = function(){
		this.fetchAffiliationAuthorities();
		this.fetchActiveBusStops();
		this.fetchAdmissionSchemes();
	}
	
	this.fetchAffiliationAuthorities = function(){
		block_renew_section.start();
		newAdmissionService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
		block_renew_section.stop();
	}
	
	this.renderAdmissionSchemes = function(response, status){
		$scope.admissionSchemes = response;
		block_renew_section.stop();
	}
	
	this.fetchActiveClasses = function(){
		
		if(this.validateAffiliationauthorityRequired()){
			block_renew_section.start();
			newAdmissionService.getActiveClasses($scope.formData.affiliationAuthorityId, this.renderActiveClasses, this.errorFuntion);
		}else{
			var listItems = "";
			listItems+= "<option value=''> --- Select Class --- </option>";
			$("#drpDwnAcademicYearClass").html(listItems);
			$("#requiredClass").addClass("has-error");
		}
		
	}
	
	this.renderActiveClasses = function(response, status){
		if(response.length > 0){
			var listItems = "";
			listItems+= "<option value=''> --- Select Class --- </option>";
			$.each(response, function(i){
			    
				listItems+= "<option value='" + response[i].classId + "'>" + response[i].className + "</option>";
				
			});
			
			$("#drpDwnAcademicYearClass").html(listItems);
		}
		block_renew_section.stop();
	}
	
	this.fetchActiveBusStops = function(){
		block_renew_section.start();
		newAdmissionService.getActiveBusStopps(this.renderBusStops, this.errorFuntion);
	}
	
	this.renderBusStops = function(response, status){
		$scope.busStops = response;
		block_renew_section.stop();
	}
	
	this.errorFuntion = function(response){
		block_renew_section.stop();
	}
	
	
	this.validateAffiliationauthorityRequired = function(){
		if($scope.formData.affiliationAuthorityId == ""){
			$("#requiredAffiliationAuthority").addClass("has-error");
			return false;
		}else{
			$("#requiredAffiliationAuthority").removeClass("has-error");
			return true;
		}
	}
	
	this.validateClassRequired = function(){
		if($scope.formData.academicYearClassId == ""){
			$("#requiredClass").addClass("has-error");
			return false;
		}else{
			$("#requiredClass").removeClass("has-error");
			return true;
		}
	}
	
	this.hideAlertBox = function(){
		
		$("#alert-box").hide();
	}
	
	this.isStudentEligibleForRenewal = function(){
		newAdmissionService.isStudentEligibleForRenewal($routeParams['studentId'], this.isStudentEligibleForRenewalSuccess, this.errorFuntion)
	}
	
	this.isStudentEligibleForRenewalSuccess = function(response){
		if(response.status=="true"){
			$scope.isEligibleForRenew = true;
		}else{
			$scope.isEligibleForRenew = false;
		}
	}
	
});






