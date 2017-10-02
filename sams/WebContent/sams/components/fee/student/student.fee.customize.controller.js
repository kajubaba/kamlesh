samsApp.controller('student.fee.customize.controller', function($scope, $routeParams,$route, $compile, feeCustomizeService, blockUI){
	
	$scope.customizeFee = null;
	$scope.totalFee = 0;
	$scope.totalDiscount = 0;
	$scope.totalPayable = 0;
	$scope.installmentTotoal = {};
	$scope.allInstallmentTotal = 0;
	
	$scope.studentId = null;
	$scope.studentClassHistoryId = null;
	$scope.selectedInstallment = null;
	
	
	$scope.$on('ngRepeatFinished', function(){        
	    
		$("input[id*='date']").datepicker({
	    	dateFormat:'d-M-yy',
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true
	    });        
	});
	
	this.getCustomizeFee = function(){
		$scope.studentId = $routeParams['studentId'];
		$scope.studentClassHistoryId = $routeParams['classHistoryId'];
		feeCustomizeService.getCustomizeFee($scope.studentId, $scope.studentClassHistoryId, null, this.getCustomizeFeeSuccess, this.errorFunction)
	}
	
	this.saveAdjustedFee = function(){
		
		console.log(this.validateForm());
		
		if(this.validateForm() == true){
			$("#alert-error").show();
		}else{
			$("#alert-error").hide();
			feeCustomizeService.saveAdjustedFee($scope.customizeFee, this.saveAdjustedFeeSucess, this.errorFunction)
		}
		
		
	}
	
	this.saveAdjustedFeeSucess = function(response, status){
		
		$route.reload();
	}
	
	this.changeInstallments = function(){
		feeCustomizeService.getCustomizeFee($scope.studentId, $scope.studentClassHistoryId, $scope.selectedInstallment, this.getCustomizeFeeSuccess, this.errorFunction)
	}
	
	this.getCustomizeFeeSuccess = function(response, status){
		
		$scope.totalFee = 0;
		$scope.totalDiscount = 0;
		$scope.totalPayable = 0;
		$scope.customizeFee = response;
		
		angular.forEach($scope.customizeFee.feeHeads, function(item){
			
			$scope.totalFee = $scope.totalFee + item.headFee;
			$scope.totalDiscount = $scope.totalDiscount + item.discount;
			$scope.totalPayable = $scope.totalPayable + item.payable;
			
			angular.forEach(item.installments, function(installment){
				$scope.installmentTotoal[installment.installmentId] = 0;
	        })
			
        })
        
        angular.forEach($scope.customizeFee.feeHeads, function(item){
			angular.forEach(item.installments, function(installment){
				$scope.installmentTotoal[installment.installmentId] = $scope.installmentTotoal[installment.installmentId] + installment.amount;
	        })
			
        })
        
        $scope.allInstallmentTotal = 0;
		
		angular.forEach($scope.installmentTotoal, function(value, key) {
			$scope.allInstallmentTotal += $scope.installmentTotoal[key];
		});
		
	}
	
	this.sumDiscountTotal = function(){
		
		var discountTotal = 0;
		
		angular.forEach($scope.customizeFee.feeHeads, function(feeHead){
			
			if(feeHead.discount != ""){
				discountTotal = parseInt(feeHead.discount) + parseInt(discountTotal);
				feeHead.payable = feeHead.headFee - feeHead.discount;
			}else{
				discountTotal = parseInt(0) + parseInt(discountTotal);
				feeHead.payable = feeHead.headFee - feeHead.discount;
			}
        })
        
        $scope.totalDiscount = discountTotal;
		this.sumPayableTotal();
		this.enableDisable();
		
	}
	
	this.sumPayableTotal = function(){
		$scope.totalPayable = $scope.totalFee - $scope.totalDiscount;
	}
	
	this.sumFeeHeadTotal = function(textBox){
		
		angular.forEach($scope.customizeFee.feeHeads, function(feeHead){
			
			var headFeeSum = 0;
			
			angular.forEach(feeHead.installments, function(installment){
				if(installment.amount != ""){
					headFeeSum = headFeeSum + parseInt(installment.amount);
				}else{
					headFeeSum = headFeeSum + parseInt(0);
				}
				
			});
			feeHead.installmentTotal = headFeeSum;
			
        });
		
        this.enableDisable();
        this.sumInstallmentTotal();
		this.sumAllInstallments();
		
		/*if(textBox.installment.amount != ""){
			if(parseInt(textBox.installment.amount) < textBox.installment.deposited){
				//textBox.addClass("has-error")
			}
		}
		console.log(textBox);*/
	}
	
	this.sumInstallmentTotal = function(){
		
		this.zeroInstallmentTotal();
		angular.forEach($scope.customizeFee.feeHeads, function(item){
				angular.forEach(item.installments, function(installment){
					if(installment.amount != ""){
						$scope.installmentTotoal[installment.installmentId] = $scope.installmentTotoal[installment.installmentId] + parseInt(installment.amount);
					}else{
						$scope.installmentTotoal[installment.installmentId] = $scope.installmentTotoal[installment.installmentId] + parseInt(0);
					}
					
		        })
				
	     })
	}
	
	this.zeroInstallmentTotal = function(){
		angular.forEach($scope.installmentTotoal, function(value, key) {
			$scope.installmentTotoal[key]=0;
		});
	}
	
	this.sumAllInstallments = function(){
		
		$scope.allInstallmentTotal = 0;
		
		angular.forEach($scope.installmentTotoal, function(value, key) {
			$scope.allInstallmentTotal += $scope.installmentTotoal[key];
		});
	}
	
	this.enableDisable = function(){
		
		var wrongAdjustMent = false;
		
		wrongAdjustMent = this.validateCorrectDiscount();
		
		
		if(wrongAdjustMent == false){
			wrongAdjustMent = this.validateCorrectInstallment();
		}
		
		 if(wrongAdjustMent == true){
	        	$("#btnSaveAdjustments").addClass("disabled");
	     }else{
	        	$("#btnSaveAdjustments").removeClass("disabled");
	     }
		
	}
	
	this.validateCorrectDiscount = function(){
		var wrongAdjustMent = false;
		angular.forEach($scope.customizeFee.feeHeads, function(feeHead){
			
			if(wrongAdjustMent == false){
				
				if(feeHead.headFee < feeHead.discount){
					wrongAdjustMent = true;
				}
			}
        })
        return wrongAdjustMent;
	}
	
	this.validateCorrectInstallment = function(){
		var wrongAdjustMent = false;
		angular.forEach($scope.customizeFee.feeHeads, function(feeHead){
			
			if(wrongAdjustMent == false){
				
				if(feeHead.payable != feeHead.installmentTotal){
					wrongAdjustMent = true;
				}
				if(wrongAdjustMent == false){
					angular.forEach(feeHead.installments, function(installment){
						if(installment.amount < installment.deposited){
							wrongAdjustMent = true;
						}
					})
				}
			}
        })
        return wrongAdjustMent;
	}
	
	this.validateForm = function(){
		
		var hasError = false;
		
		angular.forEach($scope.customizeFee.installmentDueDates, function(installmentDueDate){
			
			if(!hasError){
				if(installmentDueDate.latefeeRuleId == null || installmentDueDate.dueDateStr == null){
					
					hasError = true;
				}
			}
			
		})

		return hasError;
	}
	
	this.errorFunction = function(){
		
	}
	
	this.showRestPopup = function(){
		$("#modalResetFeeTransaction").modal('show')
	}
	
	this.closeRestPopup = function(){
		$("#modalResetFeeTransaction").modal('hide')
	}
	
	this.resetFeeAdjustment = function(){
		feeCustomizeService.resetFeeAdjustment($scope.customizeFee.studentId, $scope.customizeFee.academicYearFeeId ,this.resetFeeAdjustmentSuccess,this.errorFunction);
	}
	
	this.resetFeeAdjustmentSuccess = function(response, status){
		$route.reload();
		$("#modalResetFeeTransaction").modal('hide')
	}
});






	