
samsApp.controller('tran.manage.driver.controller', function(vehicleDriverService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.driverForm  = {
			 id : null,
			 name: null,
			 address : null,
			 primaryContact : null,
			 secondaryContact : null,
			 licenseNo : null,
			 role : null
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	
	
    this.getDriver = function(){
		
    	if($routeParams['driverId'] != "new"){
    		vehicleDriverService.getDriver($routeParams['driverId'], this.getDriverSuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    	}
	}
	
	this.getDriverSuccess = function(response, status){
		
		$scope.driverForm.id = response.id;
		$scope.driverForm.name = response.name;
		$scope.driverForm.address = response.address;
		$scope.driverForm.primaryContact = response.primaryContact;
		$scope.driverForm.secondaryContact = response.secondaryContact;
		$scope.driverForm.licenseNo = response.licenseNo;
		$scope.driverForm.role = response.role;
				 
	}
	
	this.saveDriver = function(){
		if($scope.driverForm.id == null){
			vehicleDriverService.addDriver($scope.driverForm, this.saveDriverSuccess, serverErrorHandlerService.handleError);
		}else{
			vehicleDriverService.updateDriver($scope.driverForm, this.saveDriverSuccess, serverErrorHandlerService.handleError);
		}
		
	}
	
	this.saveDriverSuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.driverForm.id = response.generatedId;
				$scope.isActionNew = false;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}
	}
	
    
    
});






