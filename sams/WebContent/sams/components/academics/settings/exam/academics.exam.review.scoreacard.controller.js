

samsApp.controller('academics.exam.review.scorecard.controller', function(scoreCardService, evaluationTypeService, examPatternService, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.scoreCard = null;
	$scope.selectedExamPatternId= null;
	$scope.examPattern = null;
	
	this.init = function(){
		$scope.selectedExamPatternId= $routeParams['examPatternId'];
		this.getExamPattern($scope.selectedExamPatternId);
		scoreCardService.getBlankScoreCard($routeParams['examPatternId'], this.displayBlankScoreCard, this.errorFuntion);
	}
	
	this.displayBlankScoreCard = function(response, status){
		$scope.scoreCard = response;
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






