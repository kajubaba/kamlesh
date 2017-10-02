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

samsApp.controller('admission.newadmission.controller', function(newAdmissionService, busStopService, serverErrorHandlerService, $scope, blockUI, $routeParams, $route, $location){
	$scope.genderList = [{id:"male", name:"Male"},{id:"female", name:"Female"}];
	$scope.generatedStudentId = null;
	$scope.formNo = null;
	$scope.states = null;
	$scope.busStops = null;
	$scope.categories = null;
	$scope.admissionSchemes = null;
	$scope.authorities = null;
	$scope.classes = null;
	$scope.busStopDetail = null;
	$scope.formData = {
			formNo : "",
			affiliationAuthorityId     : "",
			academicYearClassId : "",
			
			studentName : "",
			dobStr : "",
			studentCategoryId : "",
			gender : "",
			studentContactNo : "",
			emailId : "",
			
			fatherName : "",
			fatherContact1 : "",
			fatherOccupation : "",
			
			motherName : "",
			motherContact1 : "",
			motherOccupation : "",
			
			busStopId : "",
			pickupPointId : "",
			dropPointId : "",
			
			line1 : "",
			line2 : "",
			city : "",
			stateId : "",
			zipCode : "",
			
			
	};
	
	$scope.editForm = null;
	
	var block_select_class = blockUI.instances.get('block-select-class');
	
	var oriFormData = angular.copy($scope.formData);
	
	this.resetForm = function(){
		//$scope.formData = angular.copy(oriFormData);
		$("#modalRegistrationSuccess").modal('hide');
		$route.reload();
	}
	
	this.submitForm = function(){
		newAdmissionService.registerNewAdmission($scope.formData, this.submitFormSuccess, this.errorFuntion)
	}
	
	
	this.submitFormSuccess = function(response, status){
		$("#plcHorderStudentName").html($scope.formData.studentName);
		$("#plcHorderStudentId").html(response.studentId);
		$scope.generatedStudentId = response.id;
		$("#modalRegistrationSuccess").modal('show');

		blockUI.stop();
	}
	
	this.initializeScreen = function(){
		
		this.fetchAffiliationAuthorities();
		this.fetchCategories();
		this.fetchStates();
		this.fetchActiveBusStops();
		this.fetchAdmissionSchemes();
		
	}
	
	this.fetchAffiliationAuthorities = function(){
		blockUI.start();
		newAdmissionService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.fetchAdmissionSchemes = function(){
		newAdmissionService.getActiveAdmissionSchemes(this.renderAdmissionSchemes, this.errorFuntion);
		blockUI.start();
	}
	
	this.renderAdmissionSchemes = function(response, status){
		$scope.admissionSchemes = response;
		blockUI.stop();
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.authorities = response;
		blockUI.stop();
	}
	
	this.fetchActiveClasses = function(){
		
		if($scope.formData.affiliationAuthorityId != null){
			block_select_class.start();
			newAdmissionService.getActiveClasses($scope.formData.affiliationAuthorityId, this.renderActiveClasses, this.errorFuntion);
		}else{
			$scope.classes = null;
		}
		
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
		console.log($scope.classes);
		block_select_class.stop();
	}
	
	
	
	this.fetchCategories = function(){
		blockUI.start();
		newAdmissionService.getCategories(this.renderCategories, this.errorFuntion);
	}
	
	this.renderCategories = function(response, status){
		$scope.categories = response;
		blockUI.stop();
	}
	
	this.fetchStates = function(){
		blockUI.start();
		newAdmissionService.getStates(this.renderStates, this.errorFuntion);
	}
	
	this.renderStates = function(response, status){
		$scope.states = response;
		blockUI.stop();
	}
	
	this.fetchActiveBusStops = function(){
		blockUI.start();
		newAdmissionService.getActiveBusStopps(this.renderBusStops, this.errorFuntion);
	}
	
	this.renderBusStops = function(response, status){
		$scope.busStops = response;
		blockUI.stop();
	}
	
	this.errorFuntion = function(response){
		
	}
	
	
	this.hideAlertBox = function(){
		
		$("#alert-box").hide();
	}
	
	this.openDatePicker = function(){
		$("#admissionDOB").datepicker("show");
	}
	
	this.getAdmissionByFormNo = function(){
		if($scope.authorities.length == 1){
			newAdmissionService.getActiveClasses($scope.authorities[0].id, this.renderActiveClasses, this.errorFuntion);
		}
		newAdmissionService.getAdmissionByFormNo($scope.formNo, this.getAdmissionByFormNoSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAdmissionByFormNoSuccess = function(response, status){
		
		if(response.isAlreadyAdmissionTaken == true){
			$("#modalEnqMessage").modal('show');
		}else{
			$scope.formData = response.admission;
		}
		
		
	}
	
	this.payFee = function(){
		$("#modalRegistrationSuccess").modal('hide');
		$location.path("fee/studentfee/"+$scope.generatedStudentId+"/null");
	}
	
	this.stayHere = function(){
		$("#modalRegistrationSuccess").modal('hide');
		$location.path("admissions/studentdetails/"+$scope.generatedStudentId);
	}
	
	this.getPickupDropPoints = function(){
		$scope.busStopDetail = null;
		busStopService.getBusStopPickupDropPointsByBusStop($scope.formData.busStopId, this.getPickupDropPointsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getPickupDropPointsSuccess = function(response, status){
		$scope.busStopDetail = response;
		
	}
});






