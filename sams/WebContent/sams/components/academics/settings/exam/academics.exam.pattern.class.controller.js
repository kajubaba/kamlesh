

samsApp.controller('academics.exam.pattern.class.controller', function(examPatternClassService, examPatternService, evaluationTypeService, $scope, blockUI, $routeParams, $route, $location, $filter){
	
	$scope.notAddedClasses= null;
	$scope.addedClasses= null;
	$scope.selectedClasses= null;
	$scope.selectedExamPatternId= null;
	$scope.examPattern = null;
	
	this.init = function(){
		$scope.selectedExamPatternId= $routeParams['examPatternId'];
		this.getExamPattern($scope.selectedExamPatternId);
		this.getAddedClasses();
	}
	
	this.getAddedClasses = function(){
		examPatternClassService.getAddedClasses($scope.selectedExamPatternId, this.renderAddedClasses, this.errorFuntion);
	}
	
	this.deleteClass = function(classId){
		examPatternClassService.deleteClass(classId, this.refreshPage, this.errorFuntion);
	}
	
	this.refreshPage = function(){
		$route.reload();
	}
	
	this.closePopupAndRefreshPage = function(){
		$("#modalImportClass").modal('hide');
		$route.reload();
	}
	
	this.renderAddedClasses = function(response, status){
		$scope.addedClasses= response;
	}
	
	this.closeImportModal = function(){
		$("#modalImportClass").modal('hide');
	}
	
	this.getNotAddedClasses = function(){
		$("#modalImportClass").modal('show');
		examPatternClassService.getNotAddedClasses($scope.selectedExamPatternId, this.renderExamPatterns, this.errorFuntion);
	}
	
	this.selectClass = function () {
		$scope.selectedClasses  = $filter('filter')($scope.notAddedClasses, {checked: true});
	}
	
	this.importClasses = function () {
		console.log($scope.selectedClasses);
		
		var ids = [];
		
		angular.forEach($scope.selectedClasses, function(clazz){
			ids.push(clazz.classId);
        })
		
		examPatternClassService.importClasses(ids, $scope.selectedExamPatternId, this.closePopupAndRefreshPage, this.errorFuntion);
	}
	
	this.renderExamPatterns = function(response, status){
		$scope.notAddedClasses= response;
		
	}
	
	this.getExamPattern = function(examPatternId){
		examPatternService.getExamPattern(examPatternId, this.displayExamPatternName, this.errorFuntion);
	}
	
	this.displayExamPatternName = function(response, status){
		$scope.examPattern = response;
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}

});






