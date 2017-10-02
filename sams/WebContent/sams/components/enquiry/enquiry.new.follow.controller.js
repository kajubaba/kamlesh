samsApp.controller('enquiry.new.follow.controller', function(enquiryFollowupService, serverErrorHandlerService, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.commTypes = null;
	$scope.commWithList = null;
	$scope.commConclusions = null;
	$scope.suggestions = null;
	$scope.nextActions = null;
	$scope.hrs = [{id:1, name:1},{id:2, name:2},{id:3, name:3},{id:4, name:4},{id:5, name:5},{id:6, name:6},{id:7, name:7},{id:8, name:8},{id:9, name:9},{id:10, name:10},{id:11, name:11}, {id:12, name:12}];
	$scope.mins = [{id:0, name:00},{id:5, name:05},{id:10, name:10},{id:15, name:15},{id:20, name:20},{id:25, name:25},{id:30, name:30},{id:35, name:35},{id:40, name:40},{id:45, name:45},{id:50, name:50}, {id:55, name:55}];
	
	$scope.formData = {
			commTypeId : null,
			commWithId : null,
			commConclusionId : null,
			suggestionId : null,
			nextActionId : null,
			commSummary : null,
			nextFollowupDate : null,
			nextFollowupHr : 12,
			nextFollowupMin : 0,
			amOrPM : "PM",
			enquiryId : null
	}
	
	this.newFollowupInit = function(){
		$scope.formData.enquiryId = $routeParams["enquiryId"]; 
		if($routeParams["followupId"] != undefined){
			this.getFollowupToEdit();
		}
		this.getCommunicationTypes();
		this.getCommunicationWithList();
		this.getCommunicationConclusions();
		this.getSuggestions();
		this.getNextActions();
	}
	
	this.getCommunicationTypes = function(){
		enquiryFollowupService.getCommunicationTypes(this.getCommunicationTypesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getCommunicationTypesSuccess = function(response, status){
		$scope.commTypes = response;
	}
	
	this.getCommunicationWithList = function(){
		enquiryFollowupService.getCommunicationWithList(this.getCommunicationWithListSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getCommunicationWithListSuccess = function(response, status){
		$scope.commWithList = response;
	}
	
	this.getCommunicationConclusions = function(){
		enquiryFollowupService.getCommunicationConclusions(this.getCommunicationConclusionsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getCommunicationConclusionsSuccess = function(response, status){
		$scope.commConclusions = response;
	}
	
	this.getSuggestions = function(){
		enquiryFollowupService.getSuggestions(this.getSuggestionsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getSuggestionsSuccess = function(response, status){
		$scope.suggestions = response;
	}
	
	this.getNextActions = function(){
		enquiryFollowupService.getNextActions(this.getNextActionsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getNextActionsSuccess = function(response, status){
		$scope.nextActions  = response;
	}
	
	this.saveEnquiryFollowup = function(){
		enquiryFollowupService.saveEnquiryFollowup($scope.formData,this.saveEnquiryFollowupSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveEnquiryFollowupSuccess = function(response, status){
		$scope.formData.id = response.generatedId;
	}
	
	this.getFollowupToEdit = function(){
		enquiryFollowupService.getEnquiryFollowup($routeParams["followupId"], this.getFollowupToEditSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getFollowupToEditSuccess = function(response, status){
		$scope.formData = response;
	}
	
	
	
});






