samsApp.controller('enquiry.controller', function(newAdmissionService, enquiryService, enquiryListService, serverErrorHandlerService, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.classes = null;
	$scope.authorities = null;
	$scope.states = null;
	$scope.enquiryId = null;
	$scope.action = "add";
	$scope.enqSearchResults = null;
	$scope.cities = null;
	$scope.cityIn = "drop-down";
	
	$scope.formData = {
			
			affiliationAuthorityId     : "",
			academicYearClassId : "",
			studentName : "",
			studentContactNo : "",
			dobStr : "",
			gender : "",
			
			fatherName : "",
			fatherContactNo : "",
			fatherOccupation : "",
			
			motherName : "",
			motherContactNo : "",
			motherOccupation : "",
			
			
			line1 : "",
			line2 : "",
			city : "",
			stateId : "",
			zipCode : "",
			
			prevClass:"",
			prevInstituteName:"",
			prevClassBoard:"",
			prevClassCity:"",
			prevClassStatus:"",
			preClassPercentage:""
			
			
			
	};
	
	this.initializeScreen = function(){
		this.fetchAffiliationAuthorities();
		this.fetchStates();
		this.getEnquiry();
		this.getCities();
	}
	
	this.submitForm = function(){
		$scope.formData.dobStr = $("#admissionDOB").val();
		console.log($scope.formData);
		
		if($scope.action=="add"){
			enquiryService.addEnquiry($scope.formData, this.submitFormSuccess, this.errorFuntion)
		}else{
			enquiryService.updateEnquiry($scope.formData, this.submitFormSuccess, this.errorFuntion)
		}
		
	}
	
	this.submitFormSuccess = function(response, status){
		$scope.enquiryId = response.generatedId;
		$("#modalEnquirySaveSuccess").modal('show');
	}
	
	this.newEnquiry = function(){
		$("#modalEnquirySaveSuccess").modal('hide');
		$location.path("enquiry/new");
	}
	
	this.stayOnEnquiry = function(){
		$("#modalEnquirySaveSuccess").modal('hide');
		$location.path("enquiry/"+$scope.enquiryId);
		
	}
	
	this.fetchAffiliationAuthorities = function(){
		newAdmissionService.getAffiliationAuthorities(this.renderAffiliationAuthorities, serverErrorHandlerService.handleError);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.authorities = response;
	}
	
	this.fetchActiveClasses = function(){
		
		if($scope.formData.affiliationAuthorityId != null){
			newAdmissionService.getEnquiryActiveClasses($scope.formData.affiliationAuthorityId, this.renderActiveClasses, serverErrorHandlerService.handleError);
		}else{
			$scope.classes = null;
		}
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
	}
	
	
	this.fetchStates = function(){
		newAdmissionService.getStates(this.renderStates, serverErrorHandlerService.handleError);
	}
	
	this.renderStates = function(response, status){
		$scope.states = response;
	}
	
	
	this.errorFuntion = function(response){
		
	}
	
	this.openDatePicker = function(){
		$("#admissionDOB").datepicker("show");
	}
	
	this.getEnquiry = function(){
		if($routeParams["enquiryId"] != undefined){
			$scope.action = "update";
			enquiryService.getEnquiry($routeParams["enquiryId"], this.getEnquirySuccess, serverErrorHandlerService.handleError);
		}
	}
	
	this.getEnquirySuccess = function(response, status){
		$("#btnAction").html("Save Changes");
		$scope.formData = response.enquiry;
		$scope.classes = response.classes;
	}
	
	this.getEnquiryByMobileNo = function(){
		enquiryService.getEnquiryByMobileNo($scope.mobileNo, this.getEnquiryByMobileNoSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getEnquiryByMobileNoSuccess = function(response, status){
		$scope.formData = response.enquiry;
		if($scope.formData.id != null){
			$scope.action = "update";
			$("#btnAction").html("Save Changes");
		}else{
			$scope.action = "add";
			$("#btnAction").html("Register");
		}
		$scope.classes = response.classes;
		console.log($scope.formData);
	}
	
	this.searchEnquiriesByStudentContactNo = function(){
		$scope.enqSearchResults = null;
		if($scope.formData.studentContactNo != null){
			enquiryListService.getEnquiriesByContactNo($scope.formData.studentContactNo, "Student", this.searchEnquiriesByContactNoSuccess, serverErrorHandlerService.handleError);
		}
	}
	
	this.searchEnquiriesByFatherContactNo = function(){
		$scope.enqSearchResults = null;
		if($scope.formData.fatherContactNo != null){
			enquiryListService.getEnquiriesByContactNo($scope.formData.fatherContactNo, "Father", this.searchEnquiriesByContactNoSuccess, serverErrorHandlerService.handleError);
		}
	}
	
	this.searchEnquiriesByMotherContactNo = function(){
		$scope.enqSearchResults = null;
		if($scope.formData.motherContactNo != null){
			enquiryListService.getEnquiriesByContactNo($scope.formData.motherContactNo, "Mother", this.searchEnquiriesByContactNoSuccess, serverErrorHandlerService.handleError);
		}
	}
	
	
	
	this.searchEnquiriesByContactNoSuccess = function(response, status){
		if(response.length > 0){
			$scope.enqSearchResults = response;
			$("#modalEnqSearchResults").modal('show');
		}
	}
	
	this.getCities = function(){
		enquiryListService.getCities(this.getCitiesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getCitiesSuccess = function(response, status){
		$scope.cities = response;
	}
	
	this.typeCityInTextBox = function(){
		$scope.cityIn = "text-box";
	}
	
	this.searchCityInDropDown = function(){
		$scope.cityIn = "drop-down";
	}
});






