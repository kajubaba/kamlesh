samsApp.controller('sams.fee.dashboard.controller', function($scope, paidFeeService, feeAdjustedStudentService, academicsessionService){
	
	
	/*var block_recent_tr_section = blockUI.instances.get('block-recent-tr-section');
	var block_all_fee_tr_section = blockUI.instances.get('block-all-fee-tr-section');
	var block_paid_fee_section = blockUI.instances.get('block-paid-fee-section');
	var block_today_paid_fee_section = blockUI.instances.get('block-today-paid-fee-section');*/
	
	
	$scope.feeAdjsutedStudentCount = "";
	$scope.totalPaidFee = "";
	$scope.todayPaidFee = "";
	$scope.recentTransactions = null;
	$scope.admissionAcademicYear = "";
	
	this.initDashboard = function(){
		this.getCurrentAdmissionAcademicYear();
		this.getPaidFee();
		this.getTodaysCollection();
		this.getRecentFeeTransactions();
		this.getFeeAdjustedStudnetCount();
	}
	
	this.getRecentFeeTransactions = function(){
		//block_recent_tr_section.start();
		paidFeeService.getRecentFeeTransactions(this.renderRecentFeeTransactions, this.errorFunction);
	}
	
	this.getTodaysCollection = function(){
		//block_today_paid_fee_section.start();
		paidFeeService.getTodaysPaidFee(this.renderTodayPaidFee, this.errorFunction);
	}
	
	this.getPaidFee = function(){
		//block_paid_fee_section.start();
		paidFeeService.getPaidFeeAsOnToday(this.renderPaidFee, this.errorFunction);
	}
	
	
	
	this.renderPaidFee = function(response, status){
		$scope.totalPaidFee = response.amount;
		//block_paid_fee_section.stop();
	}
	
	this.renderTodayPaidFee = function(response, status){
		
		$scope.todayPaidFee = response.amount;
		//block_today_paid_fee_section.stop();
	}
	
	this.renderRecentFeeTransactions = function(response, status){
		$scope.recentTransactions = response;
		//block_recent_tr_section.stop();
	}
	
	this.getFeeAdjustedStudnetCount = function(){
		feeAdjustedStudentService.getFeeAdjustedStudentCount(this.displayFeeAdjustedStudentCount, this.errorFunction);
	}
	
	this.displayFeeAdjustedStudentCount = function(response, status){
		$scope.feeAdjsutedStudentCount =  response.count;
	}
	
	this.errorFunction = function(){
		
	}
	
	this.getCurrentAdmissionAcademicYear = function(){
		academicsessionService.getCurrentAdmissionAcademicYear(this.displayAdmissionAcademicYear, this.errorFunction);
	}
	
	this.displayAdmissionAcademicYear = function(response, status){
		$scope.admissionAcademicYear = response.academicYearId; 
	}
	
});






