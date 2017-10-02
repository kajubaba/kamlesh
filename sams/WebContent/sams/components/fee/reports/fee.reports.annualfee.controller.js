samsApp.controller('fee.reports.annualfee.controller', function($scope, $routeParams, annualFeeReportService, commonService, blockUI, $location, uiGridConstants){
	
	$scope.selectedAcademicYear = null;
	$scope.studentStatus = {statusId:5};
	$scope.activeStudentStatusList = null;
	$scope.annualFeeSum ={
			"studentSum" : 0,
			"projectedFee" : 0,
			"discount" : 0,
			"payable" : 0,
			"paid" : 0,
			"unPaid" : 0,
	}
	
	$scope.gridOptions = {
			rowHeight: 37,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: false,
			paginationPageSizes: [100, 200, 500, 1000],
		    paginationPageSize: 100,
		    enableHorizontalScrollbar : 0, 
		    enableVerticalScrollbar : 1,
		    flatEntityAccess: true,
		    enableSelectAll: true,
		    showColumnFooter: true,
			
	        columnDefs: [
	          { 
	        	  name:'Class', 
	        	  field: 'className',
	        	  enableColumnResizing:true
	        	  
	          },
	          { name:'Students#', field: 'studentCount',enableColumnResizing:true ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
	          { name:'Projected Fee', field: 'projectedFee', enableColumnResizing:true ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
	          { name:'Discount', field: 'discount', enableColumnResizing:true ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
	          
	          { name:'Payable', field: 'actualPayable', enableColumnResizing:true ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
	          { name:'Paid', field: 'paidFee', enableColumnResizing:true ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
	          { name:'Unpaid', field: 'unpaidFee', enableColumnResizing:true ,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true }
	                  
	        ]
	};
	
	$scope.gridOptions.appScopeProvider = this;
	
	this.getAnnualFeeReport = function(){
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		$scope.studentStatus.statusId = $routeParams['studentStatusId'];
		annualFeeReportService.getAnnualFeeReport($scope.selectedAcademicYear, $scope.studentStatus.statusId, this.renderAnnualFee, this.errorFunction);
		this.getActiveStudentStatusList();
	}
	
	this.getAcademicYearAnnualFeeReport = function(academicYear){
		//$scope.selectedAcademicYear = academicYear;
		$location.path("fee/reports/annualfee/"+academicYear+"/5");
		//annualFeeReportService.getAnnualFeeReport($scope.selectedAcademicYear, this.renderAnnualFee, this.errorFunction);
	}	
	
	this.filterDueFeeReportByStatus = function(studentStatus){
		$location.path("fee/reports/annualfee/"+$scope.selectedAcademicYear+"/"+studentStatus);
	}
	
	this.renderAnnualFee = function(response, status){
		$scope.gridOptions.data = response;
		/*angular.forEach(response, function(classFee){
			$scope.annualFeeSum.studentSum = $scope.annualFeeSum.studentSum + classFee.studentCount;
			$scope.annualFeeSum.projectedFee = $scope.annualFeeSum.projectedFee + classFee.projectedFee;
			$scope.annualFeeSum.discount = $scope.annualFeeSum.discount + classFee.discount;
			$scope.annualFeeSum.payable = $scope.annualFeeSum.payable + classFee.actualPayable;
			$scope.annualFeeSum.paid = $scope.annualFeeSum.paid + classFee.paidFee;
			$scope.annualFeeSum.unPaid = $scope.annualFeeSum.unPaid + classFee.unpaidFee;
			
        })
        
		var datatable = $('#dt-annual-fee-report').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();*/
	    
	}
	
	this.renderClasswisePaidFee = function(response, status){
		
		angular.forEach(response, function(item){
			$scope.sumClasswise = $scope.sumClasswise + item.paidAmount;
			console.log(item.paidAmount);
        })
        
		var datatable = $('#dt-all-fee-transaction').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	    
	}
	
	this.errorFunction = function(){
		
	}
	
	this.getAcademicYearHeadwiseFee = function(academicYear){
		$scope.selectedAcademicYear = academicYear;
		$scope.sumHeadwise = 0;
		headwisePaidFeeService.getHeadwisePaidFee(academicYear, this.renderHeadwisePaidFee, this.errorFunction);
	}	
	
	this.getAcademicYearClasswiseFee = function(academicYear){
		$scope.selectedAcademicYear = academicYear;
		$scope.sumClasswise = 0;
		headwisePaidFeeService.getClasswisePaidFee(academicYear, this.renderHeadwisePaidFee, this.errorFunction);
	}
	
	this.getActiveStudentStatusList = function(){
		commonService.getActiveStudentStatusList(this.renderActiveStudentStatusList, this.errorFunction);
	}
	
	this.renderActiveStudentStatusList = function(response, status){
		$scope.activeStudentStatusList = response;
	}
	
});






