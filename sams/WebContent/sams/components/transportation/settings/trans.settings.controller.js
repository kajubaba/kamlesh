

samsApp.controller('sams.trans.settings.controller', function(commonService, classSectionService, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.AYID = null;
	
	this.init = function(){
		$scope.AYID = $routeParams['academicYearId'];
		console.log("ID:"+$routeParams['academicYearId']);
	}
	
	this.changeAcademicYear = function(academicYearId){
		$location.path('academics/settings/'+academicYearId);
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}

});






