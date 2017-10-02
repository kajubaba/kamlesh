
samsApp.controller('busstop.manage.controller', function(busstopService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.busStopForm ={
			id : "",
			name : "",
			nameInOtherLang : "",
			distance : "",
			active : ""
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
    this.getBusStop = function(){
		
    	if($routeParams['busStopId'] != "new"){
    		busstopService.getBusStop($routeParams['busStopId'], this.getBusStopSuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    		$scope.busStopForm.active = true;
    	}
    	
    	
	}
	
	this.getBusStopSuccess = function(response, status){
		$scope.busStopForm.id = response.id;
		$scope.busStopForm.name = response.name;
		$scope.busStopForm.nameInOtherLang = response.nameInOtherLang;
		$scope.busStopForm.distance = response.distance;
		$scope.busStopForm.active = response.active;
	}
	
	this.saveBusStop = function(){
		busstopService.saveBusStop($scope.busStopForm, this.saveBusStopSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveBusStopSuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.busStopForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
});






