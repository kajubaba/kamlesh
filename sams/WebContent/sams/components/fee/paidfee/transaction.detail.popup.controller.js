samsApp.controller('sams.fee.transaction.detail.popup.controller', function($scope, paidFeeService){
	
	$scope.transactionDetail = null;
	
	this.getFeeTransactionDetail = function(feeTransactionId){
		
		console.log("1234");
		
		$("#modalTransactionDetail").modal('show');
		paidFeeService.getPaidFeeDetail(feeTransactionId, this.displayTransactionDetail, this.errorFunction);
	}
	
	this.displayTransactionDetail = function(response, status){
		$scope.transactionDetail = response;
	}

	this.printReceipt = function(){
		window.open(_appContextPath+"/ws/feereceipt/print/"+$scope.transactionDetail.transactionId);
	}
	
	this.errorFunction = function(){
		
	}
	
});






