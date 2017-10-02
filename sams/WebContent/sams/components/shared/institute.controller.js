
samsApp.controller('sams.institute.controller', function($scope, $location, instituteService){
	
	$scope.institutes = ""; 
	$scope.multipleInstitute = false;
	$scope.selectedInstitute = null;
	
	this.getAllConfiguredInstitutes = function(){
		instituteService.getAllInstitutes(this.displayInstitutes, this.errorFunction);
	}
	
	this.switchInstitute = function(){
		instituteService.switchInstitute($scope.selectedInstitute, this.switchInstituteSuccess, this.errorFunction);
		//console.log($scope.selectedInstitute)
	}
	
	this.switchInstituteSuccess = function(response, status){
		window.location = _appContextPath+"/home";
	}
	
	this.displayInstitutes = function(response, status){
		$scope.institutes = response; 
		
		if($scope.institutes.length > 1){
			$scope.multipleInstitute = true;
			
			angular.forEach($scope.institutes, function(institute){
				if(institute.isWorking==true){
					$scope.selectedInstitute = institute.id;
				}
				
	        });
			
		}else{
			$scope.selectedInstitute = $scope.institutes[0].id;
		}
		
		console.log($scope.multipleInstitute);
	}
	
	this.errorFunction = function(){
		
	}
	
	
});






