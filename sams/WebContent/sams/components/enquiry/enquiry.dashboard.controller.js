samsApp.controller('sams.enquiry.dashboard.controller', function($scope, $rootScope, $route, $location, $routeParams, enquiryListService,  upcomingFollowupService,academicsessionService,serverErrorHandlerService, blockUI){
	
	
	$scope.enquiryCount = {
		totalCount : 0,
		hotCount : 0,
		inProgressCount : 0,
		completedCount : 0,
		todaysFollowupCount : 0,
		tomorrowsFollowupCount : 0,
		issuedFormCount:0
	};
	
	
	
	$scope.statusHotId = null;
	$scope.statusProgressId = null;
	$scope.statusConvertednAdmissionId = null;
	
	/*$scope.enquiryAcademicSession = null;*/
	$scope.recentEnquiries = null;
	
	
	this.init = function(){
		this.getTotalCount();
		this.getHotCount();
		this.getInProgressCount();
		this.getCompletedCount();
		this.getRecentEnquiries();
		/*this.getCurrentEnquirySession();*/
		this.getTodaysFollowupCount();
		this.getTomorrowsFollowupCount();
		this.getIssuedFormCount();
	}
	
	this.getIssuedFormCount = function(){
		enquiryListService.getIssuedFormCount(this.getIssuedFormCountSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getIssuedFormCountSuccess = function(response, status){
		$scope.enquiryCount.issuedFormCount = response.count;
	}
	
	this.getTotalCount = function(){
		enquiryListService.getTotalCount(this.getTotalCountSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getTotalCountSuccess = function(response, status){
		$scope.enquiryCount.totalCount = response.count;
	}
	
	this.getHotCount = function(){
		enquiryListService.getHotCount(this.getHotCountSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getHotCountSuccess = function(response, status){
		$scope.enquiryCount.hotCount = response.count;
		$scope.statusHotId = response.id;
	}
	
	this.getInProgressCount = function(){
		enquiryListService.getInProgressCount(this.getInProgressCountSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getInProgressCountSuccess = function(response, status){
		$scope.enquiryCount.inProgressCount = response.count;
		$scope.statusProgressId = response.id;
	}
	
	this.getCompletedCount = function(){
		enquiryListService.getCompletedCount(this.getCompletedCountSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getCompletedCountSuccess = function(response, status){
		$scope.enquiryCount.completedCount = response.count;
		$scope.statusConvertednAdmissionId = response.id;
	}
	
	this.getRecentEnquiries = function(){
		enquiryListService.getRecentEnquiries(this.getRecentEnquiriesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getRecentEnquiriesSuccess = function(response, status){
		$scope.recentEnquiries = response;
	}
	
	/*this.getCurrentEnquirySession = function(){
		academicsessionService.getCurrentAdmissionAcademicYear(this.getCurrentEnquirySessionSuccess, this.errorFunction);
	}
	
	this.getCurrentEnquirySessionSuccess = function(response, status){
		$scope.enquiryAcademicSession = response.academicYearId; 
	}*/

	this.getTodaysFollowupCount = function(){
		upcomingFollowupService.getTodaysFollowupsCount(this.getTodaysFollowupCountSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getTodaysFollowupCountSuccess = function(response, status){
		$scope.enquiryCount.todaysFollowupCount = response.count;
	}
	
	this.getTomorrowsFollowupCount = function(){
		upcomingFollowupService.getTomorrowsFollowupsCount(this.getTomorrowsFollowupCountSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getTomorrowsFollowupCountSuccess = function(response, status){
		$scope.enquiryCount.tomorrowsFollowupCount = response.count;
	}
	
});






