samsApp.controller('student.fee.controller', function($scope, $routeParams,$route, $compile, studentFeeServiceService, blockUI, $location){
	
	var block_fee_collection_section = blockUI.instances.get('block-fee-collection-section');
	
	/*variable to store installments of an academic year*/
	$scope.classFee = null;
	$scope.feeSummary = null;
	$scope.installmentDetail = null;
	$scope.studentId = null;
	$scope.classHistoryId = null;
	$scope.transaction_no = null;
	$scope.receipt_no = null;
	$scope.transaction_id = null;
	$scope.paidFees = null;
	$scope.feeReceiptHeaders = null;
	$scope.paymentHistoryPaidTotal = 0;
	
	$scope.installmentTotal = {
			totalFee : 0,
			paidTotal : 0,
			unPaidTotal : 0,
			payingTotal : 0
	}
	
	$scope.deleteFeeTransactionForm ={
			id : null,
			receiptNo : null,
			amount : null
			
	}
	
	this.fetchStudentFee = function(){
		$scope.studentId = $routeParams['studentId'];
		$scope.classHistoryId = $routeParams['classHistoryId'];
		studentFeeServiceService.getStudentInstallments($routeParams['studentId'], $routeParams['classHistoryId'], this.renderStudentFee, this.errorFunction);
	}
	
	this.fetchInstallmentFeeDetailsForPayment = function(installmentIndex){
		window.location ="#/fee/pay/student/"+$routeParams['studentId']+"/"+$scope.classFee.installmentFees[installmentIndex].academicYearFeeInstallmentId+"/"+$scope.classFee.installmentFees[installmentIndex].custFeeInstallmentId+"/"+$scope.classFee.installmentFees[installmentIndex].installmentId+"/"+$scope.classFee.classHistoryId;
	}
	
	this.getInstallmentForPayment = function(){
		$scope.studentId =$routeParams['studentId'];
		$scope.classHistoryId = $routeParams['classHistoryId'];
		studentFeeServiceService.getInstallmentForPayment($routeParams['studentId'],$routeParams['installmentId'],$routeParams['academicYearFeeIInstallmentId'],$routeParams['customiseFeeInstallmentId'], $scope.classHistoryId, this.renderInstallmentDetailForPayment, this.errorFunction);
		studentFeeServiceService.getReceiptHeaders(this.renderFeeReceiptheaders, this.errorFunction);
	}
	
	this.renderFeeReceiptheaders = function(response, status){
		$scope.feeReceiptHeaders = response;
		if(response.length > 1){
			$("#receiptHeaderRow").show();
		}
	}
	
	this.renderStudentFee = function(response, status){
		$scope.classFee = response;
		
		
	}
	
	this.renderInstallmentDetailForPayment = function(response, status){
		$scope.installmentDetail = response;
		angular.forEach($scope.installmentDetail.installmentHeads, function(item){
			 $scope.installmentTotal.totalFee = $scope.installmentTotal.totalFee + item.amount;   
			 $scope.installmentTotal.paidTotal = $scope.installmentTotal.paidTotal + item.paid;
			 item.payable = item.amount - item.paid;
			 $scope.installmentTotal.payingTotal += item.payable;
         })
         
       $scope.installmentTotal.unPaidTotal = $scope.installmentTotal.totalFee - $scope.installmentTotal.paidTotal;
	
		$scope.installmentDetail.paymentMode = "Cash"; 	
		
	  if($scope.installmentTotal.payingTotal == 0){
		  $("#btn-collect-fee").addClass('disabled');
	  }	
		
		
		
	}
	
	this.changePayingTotal = function(){
		
		
		var sum = 0;
		
		$( ".payableFee" ).each( function( index, element ){
		    
			if($(element).val()!=""){
				sum+=parseInt($(element).val());
			}
			
		});
		
		$scope.installmentTotal.payingTotal = sum;
		
		this.validateFeeBeforeCollection();
		
		
		
	}
	
	this.errorFunction = function(response){
		
	}
	
	this.payFee = function(){
		$("#modalConfirmPayment").modal('hide');
		block_fee_collection_section.start();
		studentFeeServiceService.collectFee($scope.installmentDetail, this.payFeeSuccess, this.errorFunction)
	}
	
	this.payFeeSuccess = function(response, status){
		block_fee_collection_section.stop();
		console.log(response);
		$scope.transaction_no = response.transactionId;
		$scope.receipt_no = response.recieptNo;
		$scope.transaction_id = response.dbTransactionId;
		$("#modalPostPayment").modal('show');
		
		
	}
	
	this.printReceipt = function(){
		$("#modalPostPayment").modal('hide');
		window.open(_appContextPath+"/ws/feereceipt/print/"+$scope.transaction_id);
		$location.path("/fee/studentfee/"+$scope.studentId+"/null");
	}
	
	this.paymentHistory = function(){
		$scope.studentId =$routeParams['studentId'];
		studentFeeServiceService.getStudentPaymentHistory($routeParams['studentId'], this.renderPaymentHistory, this.errorFunction)
	}
	
	this.renderPaymentHistory = function(response, status){
		$scope.paidFees = response;
		var total = 0;
		angular.forEach($scope.paidFees, function(paidFee){
			total = total + parseInt(paidFee.paidAmount);   
        });
		$scope.paymentHistoryPaidTotal = total;
	}
	
	this.feeSummary = function(){
		$scope.studentId =$routeParams['studentId'];
		$scope.classHistoryId = $routeParams['classHistoryId'];
		studentFeeServiceService.getFeeSummary($routeParams['studentId'], this.renderFeeHistory, this.errorFunction)
	}
	
	this.renderFeeHistory = function(response, status){
		$scope.feeSummary = response;
		
	}
	
	this.checkForPaymentConfirmation = function(){
		
		if(this.validateBankDetaial()){
			$("#collectionAmount").html($scope.installmentTotal.payingTotal);
			$("#modalConfirmPayment").modal('show');
		}
	}
	
	this.closeConfirmationBox = function(){
		$("#modalConfirmPayment").modal('hide');
	}
	
	this.closePostPaymentBox = function(){
		$("#modalPostPayment").modal('hide');
		$location.path("/fee/studentfee/"+$scope.studentId+"/null");
	}
	
	this.validateFeeBeforeCollection = function(){
		
		var hasError = false;
		
		angular.forEach($scope.installmentDetail.installmentHeads, function(installmentHead){
			
			if(hasError == false){
				if(installmentHead.unpaid < installmentHead.payable){
					hasError = true;
				}
			}
			
        })
        
        if(hasError || $scope.installmentTotal.payingTotal == 0){
        	$("#btn-collect-fee").addClass('disabled');
        }else{
        	$("#btn-collect-fee").removeClass('disabled');
        }
	}
	
	this.paymentModeChange = function(){
		
		if($scope.installmentDetail.paymentMode != "Cash"){
			$("#bank-details").show();
		}else{
			$("#bank-details").hide();
			$("#errorMsg").hide();
			$scope.installmentDetail.chequeDDNo=null;
			$scope.installmentDetail.chequeDDDate=null;
			$scope.installmentDetail.chequeDDBankName=null;
			$scope.installmentDetail.bankBranchName=null;
			
		}
	}
	
	this.validateBankDetaial = function(){
		if($scope.installmentDetail.paymentMode != "Cash"){
			if($scope.installmentDetail.chequeDDNo == "" || 
			   $scope.installmentDetail.chequeDDDate == "" || 
			   $scope.installmentDetail.chequeDDBankName == "" || 
			   $scope.installmentDetail.bankBranchName == "" ||
			   $scope.installmentDetail.chequeDDNo == null || 
			   $scope.installmentDetail.chequeDDDate == null || 
			   $scope.installmentDetail.chequeDDBankName == null || 
			   $scope.installmentDetail.bankBranchName == null){
				$("#errorMsg").show();
				return false;
			}else{
				$("#errorMsg").hide();
			}
		}
		return true;
	}
	
	
	this.deleteFeeTransaction = function(){
		studentFeeServiceService.deleteStudentFeeTransaction($scope.deleteFeeTransactionForm.id, this.deleteFeeTransactionSuccess, this.errorFunction);
	}
	
	this.deleteFeeTransactionSuccess = function(response, status){
		$("#modalDeleteTransaction").modal('hide');
		$route.reload();
	}
	
	this.showDeleteTransactionModal = function(id, receiptNo, amount){
		$scope.deleteFeeTransactionForm.id = id;
		$scope.deleteFeeTransactionForm.receiptNo = receiptNo;
		$scope.deleteFeeTransactionForm.amount = amount;
		$("#modalDeleteTransaction").modal('show');
	}
	
	this.closeDeleteTransactionModal = function(){
		$scope.deleteFeeTransactionForm.id = null;
		$scope.deleteFeeTransactionForm.receiptNo = null;
		$scope.deleteFeeTransactionForm.amount = null;
		$("#modalDeleteTransaction").modal('hide');
	}
});






