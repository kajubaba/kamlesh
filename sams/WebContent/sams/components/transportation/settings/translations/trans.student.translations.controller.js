
samsApp.controller('trans.translation.controller', function(translationService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, blockUI){
	
	$scope.searchParam ={
			classId : "",
			studentStatusId: ""
	}
	
	$scope.translationForm = {
			studentTranslations : null
	}
	
	$scope.busStopTranslationForm = {
			busStopTranslations : null
	}
	
	
	this.init = function(){
		this.getTranslations();
	}
	
	this.getStudentTranslations = function(){
		translationService.getStudentTranslations($scope.searchParam.classId, $scope.searchParam.studentStatusId, this.getStudentTranslationsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getStudentTranslationsSuccess = function(response, status){
		$scope.translationForm.studentTranslations = response;
	}
	
	this.updateStudentTranslations = function(){
		translationService.updateStduentTranslations($scope.translationForm, this.updateStudentTranslationsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.updateStudentTranslationsSuccess = function(response, status){
		console.log("Saved...");
	}
	
	this.getBusStopTranslations = function(){
		translationService.getBusStopTranslations(this.getBusStopTranslationsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getBusStopTranslationsSuccess = function(response, status){
		$scope.busStopTranslationForm.busStopTranslations = response;
	}
	
	this.updateBusStopTranslations = function(){
		translationService.updateBusStopTranslations($scope.busStopTranslationForm, this.updateBusStopTranslationsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.updateBusStopTranslationsSuccess = function(response, status){
		console.log("Saved...");
	}
	
    
});






