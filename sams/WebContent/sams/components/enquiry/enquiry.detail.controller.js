samsApp.controller('enquiry.detail.controller', function(newAdmissionService, enquiryService, enquiryStatusService, enquiryActivityService, serverErrorHandlerService, $scope, blockUI, $routeParams, $route, $location){
	$scope.enquiryStatusList = null;
	$scope.formNoExist = null;
	$scope.enquiryActivityForm ={
			enquiryId : null,
			newChangedId : null,
			comments : null
	}
	$scope.enquiryIssueForm ={
			enquiryId : null,
			formNo : null
	}
	$scope.enquiryDetails = {
			
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
			preClassPercentage:"",
			statusId:null,
			id:null
			
			
	};
	
	this.init= function(){
		this.getEnquiry();
		this.getEnquiryStatusList();
	}
	
	
	this.getEnquiry = function(){
		enquiryService.getEnquiryDetail($routeParams["enquiryId"], this.getEnquirySuccess, serverErrorHandlerService.handleError);

	}
	
	this.getEnquirySuccess = function(response, status){
		$scope.enquiryDetails = response;
	}
	
	this.getEnquiryStatusList = function(){
		enquiryStatusService.getStatusList(this.getEnquiryStatusListSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getEnquiryStatusListSuccess = function(response, status){
		$scope.enquiryStatusList = response;
	}
	
	this.changeStatus = function(){
		$scope.enquiryActivityForm.enquiryId = $scope.enquiryDetails.id;
		$scope.enquiryActivityForm.newChangedId = $scope.enquiryDetails.statusId;
		$scope.enquiryActivityForm.comments = null;
		console.log($scope.enquiryActivityForm);
		enquiryActivityService.changeStatus($scope.enquiryActivityForm, this.changeStatusSuccess, serverErrorHandlerService.handleError);		
		
	}
	this.changeStatusSuccess = function(){
		
	}
	
	this.openModalForFormIssue = function(){
		$scope.formNoExist = null;
		$scope.enquiryIssueForm.enquiryId = $scope.enquiryDetails.id;
		$scope.enquiryIssueForm.formNo = null;
		$("#modalIssueForm").modal('show');
	}
	
	this.issueForm = function(){
		enquiryActivityService.issueForm($scope.enquiryIssueForm, this.issueFormSuccess, serverErrorHandlerService.handleError);
	}
	
	this.issueFormSuccess = function(response, status){
		window.open(_appContextPath+"/ws/enquiry/form-receipt/"+$scope.enquiryIssueForm.enquiryId);
		$("#modalIssueForm").modal('hide');
		$route.reload();
	}
	
	this.printReceipt = function(){
		window.open(_appContextPath+"/ws/enquiry/form-receipt/"+$scope.enquiryDetails.id);
	}
	
	this.isFormNoExist = function(){
		enquiryActivityService.isFormNoExist($scope.enquiryIssueForm.formNo, this.isFormNoExistSuccess, serverErrorHandlerService.handleError);
	}
	
	this.isFormNoExistSuccess = function(response, status){
		if("ERROR"== response.status){
			$scope.formNoExist = true;
		}else if("OK"== response.status){
			$scope.formNoExist = false;
		}
	}
	
});






