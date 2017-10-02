samsApp.controller('student.fee.adjustlatefee.controller', function($scope, $routeParams,$route, adjustLateFeeService, blockUI, uibButtonConfig){
	
	$scope.studentId = null;
	$scope.studentClassHistoryId = null;
	$scope.lateFeeInstallments = null;
	$scope.studentLateFeeAdjustmentForm = {"installmentLateFees" : ""};
	
	//uibButtonConfig.activeClass = 'btn-info';
	
	$scope.lateFeeSum = {
		"lateFeeTotal" : 0,
		"discountTotal" : 0,
		"payableLateFeeTotal" : 0
	};
	
	
	this.getStudentLateFeeDetails = function(){
		$scope.studentId = $routeParams['studentId'];
		$scope.studentClassHistoryId = $routeParams['classHistoryId'];
		adjustLateFeeService.getStudentLateFeeDetails($scope.studentId, $scope.studentClassHistoryId, this.renderLateFeeInstallments, this.errorFunction);
	}
	
	this.renderLateFeeInstallments = function(response, status){
		$scope.lateFeeInstallments = response;
		
		$scope.lateFeeSum.discountTotal =0;
		$scope.lateFeeSum.payableLateFeeTotal =0;
		
		angular.forEach($scope.lateFeeInstallments, function(lateFeeInstallment){
			$scope.lateFeeSum.lateFeeTotal = $scope.lateFeeSum.lateFeeTotal + lateFeeInstallment.lateFee;
			
			if(lateFeeInstallment.discountGiven != null){
				$scope.lateFeeSum.discountTotal = $scope.lateFeeSum.discountTotal + parseInt(lateFeeInstallment.discountGiven);
			}else{
				$scope.lateFeeSum.discountTotal = $scope.lateFeeSum.discountTotal + parseInt(0);
			}
			
			
			$scope.lateFeeSum.payableLateFeeTotal = $scope.lateFeeSum.payableLateFeeTotal + lateFeeInstallment.payableLateFee;
			
        });
	}
	
	this.recalculatePayableLateFee = function(textBox){
		angular.forEach($scope.lateFeeInstallments, function(lateFeeInstallment){
			if(lateFeeInstallment.discountGiven != null){
				lateFeeInstallment.payableLateFee = lateFeeInstallment.lateFee -  parseInt(lateFeeInstallment.discountGiven);
			}else{
				lateFeeInstallment.payableLateFee = lateFeeInstallment.lateFee -  parseInt(0);
			}
			
        })
        this.validateDiscountGiven();
        this.sumLateFeeInstallments();
	}
	
	this.sumLateFeeInstallments = function(){
		
		
		$scope.lateFeeSum.discountTotal =0;
		$scope.lateFeeSum.payableLateFeeTotal =0;
		
		
		angular.forEach($scope.lateFeeInstallments, function(lateFeeInstallment){
			
			if(lateFeeInstallment.discountGiven != null){
				$scope.lateFeeSum.discountTotal = $scope.lateFeeSum.discountTotal + parseInt(lateFeeInstallment.discountGiven);
			}else{
				$scope.lateFeeSum.discountTotal = $scope.lateFeeSum.discountTotal + parseInt(0);
			}
			
			
			$scope.lateFeeSum.payableLateFeeTotal = $scope.lateFeeSum.payableLateFeeTotal + lateFeeInstallment.payableLateFee;
        });
	}
	
	this.saveLateFeeAdjustments = function(){
		console.log($scope.lateFeeInstallments);
		$scope.studentLateFeeAdjustmentForm.installmentLateFees = "";
		$scope.studentLateFeeAdjustmentForm.installmentLateFees = $scope.lateFeeInstallments;
		adjustLateFeeService.saveStudentLateFeeAdjustments($scope.studentLateFeeAdjustmentForm, this.saveLateFeeAdjustmentsSucess, this.errorFunction);
	}
	
	this.saveLateFeeAdjustmentsSucess = function(response, status){
		console.log(response);
	}
	 
	this.validateDiscountGiven = function(){
		
		var invalidDiscount = false;
		
		angular.forEach($scope.lateFeeInstallments, function(lateFeeInstallment){
			
			if(!invalidDiscount){
				if(lateFeeInstallment.discountGiven != null){
					if(lateFeeInstallment.discountGiven > lateFeeInstallment.lateFee){
						invalidDiscount = true;
					}
				}
			}
			
			
        });
		
		if(invalidDiscount){
			$("#btnSaveLateFeeAdjustments").addClass("disabled");
		}else{
			$("#btnSaveLateFeeAdjustments").removeClass("disabled");
		}
		
	}
	
	this.errorFunction = function(){
		
	}
	
	this.onOffLateFeeCalculationSuccess = function(response, status){
		console.log(response);
	}
	
	this.onOffLateFeeCalculation = function(dueInstallmentId){
		console.log(dueInstallmentId);
		angular.forEach($scope.lateFeeInstallments, function(lateFeeInstallment){
			if(dueInstallmentId == lateFeeInstallment.daysOverDueId){
				adjustLateFeeService.onOffLateFeeCalculation(dueInstallmentId, lateFeeInstallment.disableLateFeeCalculation, this.onOffLateFeeCalculationSuccess, this.errorFunction);
			}
        });
	}
	
	
});






	