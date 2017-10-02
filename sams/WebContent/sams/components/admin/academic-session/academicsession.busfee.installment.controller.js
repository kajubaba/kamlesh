
samsApp.controller('academicsession.busfee.installment.controller', function(busFeeInstallmentService, academicSessionBusStopService, installmentService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.busFeeInstallment = null;
	$scope.installments = null;
	
	$scope.installmentArray = [];
	$scope.isSumCorrect = false;
	
	this.init = function(){
		this.getInstallments();
		this.getBusFeeInstallments();
	}
	
	this.changeSetupType = function(){
		if($scope.busFeeInstallment.setupType == "Manual"){
			busFeeInstallmentService.getManualBusFeeInstallments($scope.selectedAcademicSession, "",this.getBusStopsInstallmentsSuccess, serverErrorHandlerService.handleError);
		}else if($scope.busFeeInstallment.setupType == "Percentage"){
			busFeeInstallmentService.changePercentageBusFeeInstallments($scope.selectedAcademicSession, "", this.changePercentageInstallmentsSuccess, serverErrorHandlerService.handleError);
		}
	}
	
	this.getBusFeeInstallments= function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		busFeeInstallmentService.getAcademicSessionBusFeeInstallments($scope.selectedAcademicSession, this.getBusFeeInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getBusFeeInstallmentsSuccess = function(response, status){
		$scope.busFeeInstallment = response;
		if($scope.busFeeInstallment.setupType == "Manual"){
			for (var i=1; i<= $scope.busFeeInstallment.installments; i++) {
				$scope.installmentArray.push(i);
			}
			
			$scope.isSumCorrect = true;
			if($scope.busFeeInstallment != null && $scope.busFeeInstallment.busStopFees != null){
				angular.forEach($scope.busFeeInstallment.busStopFees, function(fee){
					fee.installmentTotal = 0;
					angular.forEach(fee.busFeeDetails, function(installment){
						if(installment.fee != null){
							fee.installmentTotal = parseInt(fee.installmentTotal) + parseInt(installment.fee);
						}else{
							fee.installmentTotal = parseInt(fee.installmentTotal) + parseInt(0);
						}
						
					});
					if(fee.installmentTotal != fee.busFee){
						$scope.isSumCorrect = false;
					}
				});
			}
			
		}
	}
	
	this.getInstallments= function(){
		installmentService.getInstallments(this.getInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getInstallmentsSuccess = function(response, status){
		$scope.installments = response;
	}
	
	this.changePercentageInstallments= function(){
		busFeeInstallmentService.changePercentageBusFeeInstallments($scope.selectedAcademicSession, $scope.busFeeInstallment.installments, this.changePercentageInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.changePercentageInstallmentsSuccess = function(response, status){
		$scope.busFeeInstallment.busFeeInstallmentInPercentage = response;
		if(response != null){
			$scope.busFeeInstallment.installments = response.length;
		}
		
	}
	
	this.savePercentageInstallments= function(){
		busFeeInstallmentService.savePercentageBusFeeInstallments($scope.busFeeInstallment, this.saveInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveInstallmentsSuccess = function(response, status){
		$route.reload();
	}
	
	this.sumFeePercentage = function(){
		var total = 0;
	    if($scope.busFeeInstallment != null && $scope.busFeeInstallment.busFeeInstallmentInPercentage != null){
	    	for(var i = 0; i < $scope.busFeeInstallment.busFeeInstallmentInPercentage.length; i++){
		        var installment = $scope.busFeeInstallment.busFeeInstallmentInPercentage[i];
		        total += (installment.feePercent);
		    }
	    }
		
	    return total;
	}
	
	this.getBusStopsInstallmentsSuccess = function(response, status){
		$scope.installmentArray = [];
		$scope.busFeeInstallment = response;
		
		for (var i=1; i<= $scope.busFeeInstallment.installments; i++) {
			$scope.installmentArray.push(i);
		}
		
		$scope.isSumCorrect = true;
		if($scope.busFeeInstallment != null && $scope.busFeeInstallment.busStopFees != null){
			angular.forEach($scope.busFeeInstallment.busStopFees, function(fee){
				fee.installmentTotal = 0;
				angular.forEach(fee.busFeeDetails, function(installment){
					if(installment.fee != null){
						fee.installmentTotal = parseInt(fee.installmentTotal) + parseInt(installment.fee);
					}else{
						fee.installmentTotal = parseInt(fee.installmentTotal) + parseInt(0);
					}
					
				});
				if(fee.installmentTotal != fee.busFee){
					$scope.isSumCorrect = false;
				}
			});
		}
	}
	
	this.sumInstallments = function(){
		$scope.isSumCorrect = true;
		if($scope.busFeeInstallment != null && $scope.busFeeInstallment.busStopFees != null){
			angular.forEach($scope.busFeeInstallment.busStopFees, function(fee){
				fee.installmentTotal = 0;
				angular.forEach(fee.busFeeDetails, function(installment){
					if(installment.fee != null){
						fee.installmentTotal = parseInt(fee.installmentTotal) + parseInt(installment.fee);
					}else{
						fee.installmentTotal = parseInt(fee.installmentTotal) + parseInt(0);
					}
					
				});
				if(fee.installmentTotal != fee.busFee){
					$scope.isSumCorrect = false;
				}
			});
		}
		
	}
	
	this.changeManualBusFeeInstallments= function(){
		busFeeInstallmentService.getManualBusFeeInstallments($scope.selectedAcademicSession, $scope.busFeeInstallment.installments,this.getBusStopsInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveManualBusFeeInstallments= function(){
		busFeeInstallmentService.saveManualBusFeeInstallments($scope.busFeeInstallment, this.getBusStopsInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
});






