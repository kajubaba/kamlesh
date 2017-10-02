
samsApp.controller('student.activity.controller', function(commonService, $scope, $routeParams, blockUI, studentActivityService){
	
	$scope.activeBusStops = null;
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	$scope.admissionSchemes = null;
	
	$scope.changeBusStopForm = {studentId:"", newId:"", comments:"", isForced: false};
	$scope.changeClassForm = {studentId:"", newId:"", comments:"", isForced: false};
	$scope.changeAdmissionSchemeForm = {studentId:"", newId:"", comments:"", isForced: false};
	$scope.changeStudentStatusForm = {studentId:"", newId:"", comments:"", isForced: false};
	$scope.auId = null;
	$scope.studentStatusList = null;
	$scope.busStopChangeErrorMessage = null;
	$scope.classChangeErrorMessage = null;
	
	this.fetchActiveBusStops = function(){
		commonService.getActiveBusStops(this.renderActiveBusStops, this.errorFuntion);
	}
	
	this.fetchAffiliationAuthorities = function(){
		commonService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.fetchActiveClasses = function(){
		if($scope.auId != ""){
			commonService.getActiveClasses($scope.auId, this.renderActiveClasses, this.errorFuntion);
		}
		
	}
	
	this.fetchAdmissionSchemes = function(){
		commonService.getActiveAdmissionSchemes(this.renderAdmissionSchemes, this.errorFuntion);
	}
	
	this.renderActiveBusStops = function(response, status){
		$scope.activeBusStops = response;
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
	}
	
	this.renderAdmissionSchemes = function(response, status){
		$scope.admissionSchemes = response;
	}
	
	this.fetchActiveStudentStatusList = function(){
		commonService.getActiveStudentStatus(this.renderActiveStudentStatusList, this.errorFuntion);
	}
	
	this.renderActiveStudentStatusList = function(response, status){
		$scope.studentStatusList = response;
	}
	
	this.changeStudentStatus = function(){
		$scope.changeStudentStatusForm.studentId = $("#studentId").html();
		
		if($scope.changeStudentStatusForm.newId == "" || $scope.changeStudentStatusForm.newId == null){
			$("#success_change_admission_status").hide();
			$("#error_change_admission_status_message").html("Opps error. Please Select New Admission Status");
			$("#error_change_admission_status").show();
		}else{
			studentActivityService.changeStudnetStatus($scope.changeStudentStatusForm, this.changeStudentStatusSuccess, this.errorFuntion);
		}
		
		
	}
	
	this.changeStudentStatusSuccess = function(response, status){
		if(response.status == "SUCCESS"){
			$("#error_change_admission_status").hide();
			$("#success_change_admission_status_message").html("Admission Status Changed Successfully");
			$("#success_change_admission_status").show();
		}else{
			$("#success_change_admission_status").hide();
			$("#error_change_admission_status_message").html(response.message);
			$("#error_change_admission_status").show();
		}
	}
	
	this.errorFuntion = function(response){
		
	}
	
	this.init = function(){
		this.fetchActiveBusStops();
		this.fetchAffiliationAuthorities();
		this.fetchAdmissionSchemes();
	}
	
	this.changeBusStop = function(){
		$scope.changeBusStopForm.studentId = $("#studentId").html();
		studentActivityService.changeBusStop($scope.changeBusStopForm, this.changeBusStopSuccess, this.errorFuntion);
	}
	
	this.changeClass = function(){
		$scope.changeClassForm.studentId = $("#studentId").html();
		
		if($scope.changeClassForm.newId == "" || $scope.changeClassForm.newId == null){
			$("#success_change_class").hide();
			$("#error_change_class_message").html("Opps error. Please Select New Admission Class");
			$("#error_change_class").show();
		}else{
			console.log($scope.changeClassForm);
			studentActivityService.changeClass($scope.changeClassForm, this.changeClassSuccess, this.errorFuntion);
		}
	}
	
	this.changeAdmissionScheme = function(){
		$scope.changeAdmissionSchemeForm.studentId = $("#studentId").html();
		studentActivityService.changeAdmissionScheme($scope.changeAdmissionSchemeForm, this.changeAdmissionSchemeSuccess, this.errorFuntion);
		/*if($scope.changeAdmissionSchemeForm.newId == "" || $scope.changeAdmissionSchemeForm.newId == null){
			$("#success_change_admission_scheme").hide();
			$("#error_change_admission_scheme_message").html("Opps error. Please Select New Admission Schemes");
			$("#error_change_admission_scheme").show();
		}else{
			
			studentActivityService.changeAdmissionScheme($scope.changeAdmissionSchemeForm, this.changeAdmissionSchemeSuccess, this.errorFuntion);
		}*/
	}
	
	this.changeBusStopSuccess = function(response, status){
		$scope.busStopChangeErrorMessage = null;
		$scope.changeBusStopForm.isForced = false;
		if(response.status == "SUCCESS"){
			$("#error_change_bus_stop").hide();
			$("#modalForceChangeBusStop").modal('hide');
			$("#success_change_busstop_message").html("Bus Stop Changed Successfully");
			$("#success_change_bus_stop").show();
		}else{
			
			$("#success_change_bus_stop").hide();
			
			if(response.showAlert == true){
				$("#modalForceChangeBusStop").modal('show');
				$scope.busStopChangeErrorMessage = response.message; 
			}else{
				$("#error_change_busstop_message").html(response.message);
				$("#error_change_bus_stop").show();
			}
			
		}
	}
	
	this.changeClassSuccess = function(response, status){
		$scope.classChangeErrorMessage = null;
		$scope.changeClassForm.isForced = false;
		if(response.status == "SUCCESS"){
			$("#error_change_class").hide();
			$("#modalForceChangeClass").modal('hide');
			$("#success_change_class_message").html("Admission Class Changed Successfully");
			$("#success_change_class").show();
		}else{
			
			$("#success_change_class").hide();
			if(response.showAlert == true){
				$("#modalForceChangeClass").modal('show');
				$scope.classChangeErrorMessage = response.message;
			}else{
				$("#error_change_class_message").html(response.message);
				$("#error_change_class").show();
			}
			
		}
	}
	
	this.openForceBusStopChangePopup = function(){
		$("#modalForceChangeBusStop").modal('show');
	}
	
	this.forceBusStopChange = function(){
		$scope.changeBusStopForm.isForced = true;
		this.changeBusStop();
	}
	
	this.closeForceBusStopChangePopup = function(){
		$("#modalForceChangeBusStop").modal('hide');
		$scope.busStopChangeErrorMessage = null;
		$scope.changeBusStopForm.isForced = false;
	}
	
	this.openForceClassChangePopup = function(){
		$("#modalForceChangeClass").modal('show');
	}
	
	this.forceClassChange = function(){
		$scope.changeClassForm.isForced = true;
		this.changeClass();
	}
	
	this.closeForceClassChangePopup = function(){
		$("#modalForceChangeClass").modal('hide');
		$scope.classChangeErrorMessage = null;
		$scope.changeClassForm.isForced = false;
	}
	
	
	
	this.changeAdmissionSchemeSuccess = function(response, status){
		if(response.status == "SUCCESS"){
			$("#error_change_admission_scheme").hide();
			$("#success_change_admission_scheme_message").html("Admission Scheme Changed Successfully");
			$("#success_change_admission_scheme").show();
		}else{
			$("#success_change_admission_scheme").hide();
			$("#error_change_admission_scheme_message").html(response.message);
			$("#error_change_admission_scheme").show();
		}
	}
   
});






