
samsApp.controller('academicsession.class.installment.controller', function(academicSessionClassService, installmentService, lateFeeRuleService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.classDetails = null;
	$scope.installments = null;
	$scope.admissionTypeId = null;
	$scope.courseYearSettingId = null;
	$scope.installmentTotal = {};
	$scope.selectedInstallment = "";
	$scope.lateFeeRules = null;
	$scope.classes = null;
	
	
	$scope.$on('ngRepeatFinished', function(){        
	    
		$("input[id*='date']").datepicker({
	    	dateFormat:'d-M-yy',
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true
	    });        
	});
	
	
	this.init = function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		$scope.admissionTypeId = $routeParams['admissionTypeId'];
		$scope.courseYearSettingId = $routeParams['courseYearSettingId'];
		this.getClassInstallments();
		this.getLateFeeRules();
		this.getAcademicSessionClasses();
	}
	
	
	this.changeClassInstallments= function(){
		academicSessionClassService.getClassInstallments($scope.courseYearSettingId, $scope.admissionTypeId , $scope.selectedInstallment, this.getClassInstallmentsSuccess, serverErrorHandlerService.handleError);
		installmentService.getInstallments(this.getInstallmentsSuccess, serverErrorHandlerService.handleError);
		
	}
	
	
	this.getClassInstallments= function(){
		
		academicSessionClassService.getClassInstallments($scope.courseYearSettingId, $scope.admissionTypeId , $scope.selectedInstallment, this.getClassInstallmentsSuccess, serverErrorHandlerService.handleError);
		installmentService.getInstallments(this.getInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getInstallmentsSuccess = function(response, status){
		$scope.installments = response;
	}
	
	this.getClassInstallmentsSuccess = function(response, status){
		$scope.classDetails = response;
		$scope.selectedInstallment = response.installments;
		//initialize installment array
		angular.forEach($scope.classDetails.headwiseInstallments[0].installmentDetailVOs, function(installmentDetailVO){
			$scope.installmentTotal[installmentDetailVO.installmentId] = 0;
        })
        
        
        angular.forEach($scope.classDetails.headwiseInstallments, function(installment){
			var feeHeadInstallmentSum = 0;	 
			angular.forEach(installment.installmentDetailVOs, function(installmentDetailVO){
				if(installmentDetailVO.amount != null){
					feeHeadInstallmentSum += parseInt(installmentDetailVO.amount);
				}
				
			});
			installment.feeHeadInstallmentSum = feeHeadInstallmentSum;
		});
        
        	angular.forEach($scope.classDetails.headwiseInstallments, function(installment){
				angular.forEach(installment.installmentDetailVOs, function(installmentDetailVO){
					if(installmentDetailVO.amount != null){
						$scope.installmentTotal[installmentDetailVO.installmentId] = $scope.installmentTotal[installmentDetailVO.installmentId] + parseInt(installmentDetailVO.amount);
					}else{
						$scope.installmentTotal[installmentDetailVO.installmentId] = $scope.installmentTotal[installmentDetailVO.installmentId] + parseInt(0);
					}
					
		        })
				
	     })
		
	}
	
	this.feeHeadSum = function(){
		var sum = 0;
		if($scope.classDetails != null){
			angular.forEach($scope.classDetails.headwiseInstallments, function(installment){
				
				 sum += parseInt(installment.amount);
			
		 });
		}
		
		return sum;
	}
	
	this.feeHeadDistributionSum = function(){
	
		angular.forEach($scope.classDetails.headwiseInstallments, function(installment){
			var feeHeadInstallmentSum = 0;	 
			angular.forEach(installment.installmentDetailVOs, function(installmentDetailVO){
				
				console.log(installmentDetailVO.amount);
				
				if(installmentDetailVO.amount != null){
					feeHeadInstallmentSum += parseInt(installmentDetailVO.amount);
				}
				
			});
			installment.feeHeadInstallmentSum = feeHeadInstallmentSum;
			
		});
		this.sumInstallments();
	}
	
	this.sumInstallments = function(){
		
		angular.forEach($scope.installmentTotal, function(value, key) {
			$scope.installmentTotal[key]=0;
		});
		
		angular.forEach($scope.classDetails.headwiseInstallments, function(installment){
				angular.forEach(installment.installmentDetailVOs, function(installmentDetailVO){
					if(installmentDetailVO.amount != null){
						$scope.installmentTotal[installmentDetailVO.installmentId] = $scope.installmentTotal[installmentDetailVO.installmentId] + parseInt(installmentDetailVO.amount);
					}else{
						$scope.installmentTotal[installmentDetailVO.installmentId] = $scope.installmentTotal[installmentDetailVO.installmentId] + parseInt(0);
					}
					
		        })
				
	     })
	}
	
	this.finalSum = function(){
		var sum = 0;
		angular.forEach($scope.installmentTotal, function(value, key) {
			sum = sum+value;
		});
		return sum;
	}
	
	this.isFormInvalid = function(){
		
		var invalid = false ;
		if($scope.classDetails != null){
			angular.forEach($scope.classDetails.headwiseInstallments, function(installment){
				
				if(installment.amount != installment.feeHeadInstallmentSum){
					invalid = true;
				}
				
			});
		}
		
		return invalid;
	}
	
	this.getLateFeeRules = function(){
		lateFeeRuleService.getLateFeeRules(this.getLateFeeRulesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getLateFeeRulesSuccess = function(response, status){
		$scope.lateFeeRules = response;
	}
	
	this.saveFeeInstallments = function(){
		academicSessionClassService.saveFeeInstallments($scope.classDetails, this.saveFeeInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.updateInstallmentDueDateAndLateFeeRule = function(){
		academicSessionClassService.updateInstallmentDueDateAndLateFeeRule($scope.classDetails, this.saveFeeInstallmentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveFeeInstallmentsSuccess = function(response, status){
		if("ERROR" == response.status){
			$("#modalCanNotUpdateInstallment").modal('show');
		}else if("OK" == response.status){
			$route.reload();
		}
		
	}
	
	this.getAcademicSessionClasses = function(){
		academicSessionClassService.getAcademicSessionClassesDetails($scope.selectedAcademicSession, this.getAcademicSessionClassesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAcademicSessionClassesSuccess = function(response, status){
		$scope.classes = response;
	}
	
	this.changeClass = function(){
		$location.path("/admin/academicsession/setup/installments/"+$scope.selectedAcademicSession+"/"+$scope.classDetails.courseYearSettingId+"/1");
	}
	
});






