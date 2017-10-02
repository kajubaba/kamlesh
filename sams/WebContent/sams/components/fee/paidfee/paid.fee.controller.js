samsApp.controller('sams.fee.transaction.controller', function($scope, $rootScope, $routeParams, $location, $interval, $q, paidFeeService, uiGridConstants){
	
	$scope.feeTransactions = null;
	$scope.fromDate = "";
	$scope.toDate = "";
	$scope.selectedAcademicYear = null;
	$scope.selectedHead = null;
	$scope.selectedHeadId = null;
	$scope.headWisePaidFees = 0;
	$scope.headWisePaidStduents = null;
	$scope.sumHeadwise = 0;
	$scope.sumClasswise = 0;
	$scope.feeHeadName = null;
	$scope.transactionDetail = null;
	
	
	$scope.gridOptions = {
			
		    
		    
		    rowHeight: $rootScope.UI_GRID_ROW_HEIGHT,
			enableSorting:  $rootScope.UI_GRID_ENABLE_SORTING,
			enableGridMenu:  $rootScope.UI_GRID_ENABLE_GRID_MENU,
			exporterMenuPdf: $rootScope.UI_GRID_EXPORTER_MENU_PDF,
			paginationPageSizes: $rootScope.UI_GRID_PAGINATION_PAGE_SIZES,
		    paginationPageSize: $rootScope.UI_GRID_PAGE_SIZE,
		    enableHorizontalScrollbar : $rootScope.UI_GRID_ENABLE_HORIZONTAL_SCROLL_BAR, 
		    enableVerticalScrollbar : $rootScope.UI_GRID_ENABLE_VERTICAL_SCROLL_BAR,
		    flatEntityAccess: $rootScope.UI_GRID_FLAT_ENTITY_ACCESS,
		    showColumnFooter: true,
			
	        columnDefs: [
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentId',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/fee/studentfee/{{row.entity.studentDBId}}/null">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'studentName',enableColumnResizing:true },
	          { name:'Father Name', field: 'fatherName',enableColumnResizing:true },
	          { name:'Payment Class', field: 'paymentClass',enableColumnResizing:true },
	          { name:'Academic Year', field: 'paymentAcademicYear', visible: false ,enableColumnResizing:true },
	          { name:'Installment', field: 'feeInstallment',enableColumnResizing:true },
	          { name:'Receipt No', field: 'receiptNo',enableColumnResizing:true},
	          { name:'Transaction No', field: 'transactionNo', visible: false ,enableColumnResizing:true},
	          { 
	        	  name:'Paid Fee', 
	        	  field: 'paidFee',
	        	  enableColumnResizing:true,
	        	  aggregationType: uiGridConstants.aggregationTypes.sum, 
	        	  aggregationHideLabel: true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="javascript:void(0)" ng-click="grid.appScope.getFeeTransactionDetail(row.entity.transactionId)">{{row.entity.paidFee}}</a></div>'
	          },
	          { name:'Payment Date', field: 'paymentDate',enableColumnResizing:true},
	          { name:'Collected On', field: 'transactionDateTime', visible: false,enableColumnResizing:true},
	          { name:'Collected By', field: 'collectedBy', visible: false,enableColumnResizing:true},
	          { name:'Comments', field: 'paymentComments', visible: false,enableColumnResizing:true},
	          { name:'Payment Mode', field: 'paymentMode', visible: false,enableColumnResizing:true},
	          { name:'Deposited By', field: 'depositedBy', visible: false,enableColumnResizing:true}
	          
	        ],
	        
	        onRegisterApi: function(gridApi) {
	            $scope.gridApi = gridApi;
	        }
	};
	$scope.gridOptions.appScopeProvider = this;
	
	
	$scope.gridHeadWiseOptions = {
			rowHeight: 37,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: false,
			paginationPageSizes: [100, 200, 500, 1000],
		    paginationPageSize: 100,
		    enableHorizontalScrollbar : 0, 
		    enableVerticalScrollbar : 1,
		    flatEntityAccess: true,
		    showColumnFooter: true,
			
	        columnDefs: [
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentAssignedId',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/fee/studentfee/{{row.entity.studentId}}/null">{{row.entity.studentAssignedId}}</a></div>'
	        	  
	          },
	          { name:'Student Name', field: 'studentName',enableColumnResizing:true },
	          { name:'Current Class', field: 'className',enableColumnResizing:true },
	          { name:'Paid Fee (Rs.)', field: 'amountPaid',enableColumnResizing:true,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true }
	          
	          
	        ]
	};
	$scope.gridHeadWiseOptions.appScopeProvider = this;
	
	this.renderTransactions = function(type){
		
		if(type == "todays"){
			this.getTodaysTransactions();
		}else {
			this.getAllFeeTransactions();
		}
	}
	
	this.getTodaysTransactions = function(){
		
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		paidFeeService.getTodaysFeeTransactions(this.renderTodaysFeeTransactions, this.errorFunction);
	}
	
	this.getAllFeeTransactions = function(){
		
		
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		if($routeParams['fromDate'] == -1){
			$scope.fromDate = "";
		}else{
			$scope.fromDate = $routeParams['fromDate'];
		}
		
		if($routeParams['toDate'] == -1){
			$scope.toDate = "";
		}else{
			$scope.toDate = $routeParams['toDate'];
		}
		
		
		if($scope.selectedAcademicYear == -1){
			paidFeeService.getFeeTransactions(null, $scope.fromDate, $scope.toDate, this.renderAllFeeTransactions, this.errorFunction);
		}else{
			paidFeeService.getFeeTransactions($scope.selectedAcademicYear, $scope.fromDate, $scope.toDate, this.renderAllFeeTransactions, this.errorFunction);
		}
		
		
	}
	
	this.getAcademicYearTransactions = function(academicYearId){
		
		
		var redirectURL = "fee/paid/transactions/"+academicYearId;
		
		if($scope.fromDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.fromDate;
		}
		
		if($scope.toDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.toDate;
		}
		
		$location.path(redirectURL);
		
	
	}
	
	this.filterTransactions = function(){
		
		var redirectURL = "fee/paid/transactions/"+$scope.selectedAcademicYear;
		
		if($("#fromDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#fromDate").val();
		}
		
		if($("#toDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#toDate").val();
		}
		
		$location.path(redirectURL);
		
		
	}
	
	this.renderAllFeeTransactions = function(response, status){
		$scope.feeTransactions = response;
		$scope.gridOptions.data = response;
	}
	
	this.renderTodaysFeeTransactions = function(response, status){
		
		$scope.fromDate = response.startDate;
		$scope.toDate = response.endDate;	
		
		
		$scope.gridOptions.data = response.feeTransactions;
	}
	
	this.getHeadwisePaidFee = function(){
		
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		if($routeParams['fromDate'] == -1){
			$scope.fromDate = "";
		}else{
			$scope.fromDate = $routeParams['fromDate'];
		}
		
		if($routeParams['toDate'] == -1){
			$scope.toDate = "";
		}else{
			$scope.toDate = $routeParams['toDate'];
		}
		
		if($scope.selectedAcademicYear == -1){
			paidFeeService.getPaidFeeHeadwise(null, $scope.fromDate, $scope.toDate, this.renderHeadwisePaidFee, this.errorFunction);
		}else{
			paidFeeService.getPaidFeeHeadwise($scope.selectedAcademicYear, $scope.fromDate, $scope.toDate, this.renderHeadwisePaidFee, this.errorFunction);
		}
		
	}
	
	this.getAcademicYearHeadwiseFee = function(academicYear){
		
		var redirectURL = "fee/paid/headwise/"+academicYear;
		
		if($scope.fromDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.fromDate;
		}
		
		if($scope.toDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.toDate;
		}
		
		$location.path(redirectURL);
	}
	
	
	
	
	this.filterHeadWiseFee = function(){
		
		var redirectURL = "fee/paid/headwise/"+$scope.selectedAcademicYear;
		
		if($("#fromDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#fromDate").val();
		}
		
		if($("#toDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#toDate").val();
		}
		
		$location.path(redirectURL);
	}
	
	this.renderHeadwisePaidFee = function(response, status){
		$scope.headWisePaidFees = response;
		angular.forEach(response, function(item){
			$scope.sumHeadwise = $scope.sumHeadwise + item.paidFee;
        })
        
	    
	}
	
	this.getClasswisePaidFee = function(){
		
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		if($routeParams['fromDate'] == -1){
			$scope.fromDate = "";
		}else{
			$scope.fromDate = $routeParams['fromDate'];
		}
		
		if($routeParams['toDate'] == -1){
			$scope.toDate = "";
		}else{
			$scope.toDate = $routeParams['toDate'];
		}
		
		if($scope.selectedAcademicYear == -1){
			paidFeeService.getPaidFeeClasswise(null, $scope.fromDate, $scope.toDate, this.renderClasswisePaidFee, this.errorFunction);
		}else{
			paidFeeService.getPaidFeeClasswise($scope.selectedAcademicYear, $scope.fromDate, $scope.toDate, this.renderClasswisePaidFee, this.errorFunction);
		}
		
	}
	
	this.getStudnetsPaidInHead = function(){
		
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		$scope.selectedHead = $routeParams['feeHeadId'];
		
		if($routeParams['fromDate'] == -1){
			$scope.fromDate = "";
		}else{
			$scope.fromDate = $routeParams['fromDate'];
		}
		
		if($routeParams['toDate'] == -1){
			$scope.toDate = "";
		}else{
			$scope.toDate = $routeParams['toDate'];
		}
		
		if($scope.selectedAcademicYear == -1){
			paidFeeService.getPaidFeeHeadwiseDetails(null, $scope.selectedHead, $scope.fromDate, $scope.toDate, this.renderHeadWisePaidStduents, this.errorFunction);
		}else{
			paidFeeService.getPaidFeeHeadwiseDetails($scope.selectedAcademicYear, $scope.selectedHead, $scope.fromDate, $scope.toDate, this.renderHeadWisePaidStduents, this.errorFunction);
		}
		
	}
	
	this.renderHeadWisePaidStduents = function(response, status){
		$scope.gridHeadWiseOptions.data = response.studentPaidFees;
		$scope.feeHeadName = response.headName;
	}
	
	this.filterStudnetsPaidInHeadByAcademicYear = function(academicYear){
		
		var redirectURL = "fee/paid/headwisewise/details/"+academicYear+"/"+$scope.selectedHead;
		
		if($scope.fromDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.fromDate;
		}
		
		if($scope.toDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.toDate;
		}
		
		$location.path(redirectURL);
	}
	
	this.filterStudnetsPaidInHeadByDate = function(){
		
		var redirectURL = "fee/paid/headwisewise/details/"+$scope.selectedAcademicYear+"/"+$scope.selectedHead;
		
		
		if($("#fromDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#fromDate").val();
		}
		
		if($("#toDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#toDate").val();
		}
		
		$location.path(redirectURL);
	}
	
	this.viewStudnetsPaidInHead = function(feeHeadId){
		
		var redirectURL = "fee/paid/headwisewise/details/"+$scope.selectedAcademicYear+"/"+feeHeadId;
		if($scope.fromDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.fromDate;
		}
		
		if($scope.toDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.toDate;
		}
		
		$location.path(redirectURL);
	}
	
	this.getAcademicYearClasswiseFee = function(academicYear){
		
		var redirectURL = "fee/paid/classwise/"+academicYear;
		
		if($scope.fromDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.fromDate;
		}
		
		if($scope.toDate == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$scope.toDate;
		}
		
		$location.path(redirectURL);
		
	}
	
	this.filterClassWiseFee = function(){
		
		var redirectURL = "fee/paid/classwise/"+$scope.selectedAcademicYear;
		
		if($("#fromDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#fromDate").val();
		}
		
		if($("#toDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#toDate").val();
		}
		
		$location.path(redirectURL);
		
		
	}
	
	this.renderClasswisePaidFee = function(response, status){
		
		angular.forEach(response, function(item){
			$scope.sumClasswise = $scope.sumClasswise + item.paidAmount;
        })
        
		var datatable = $('#dt-all-fee-transaction').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	    
	}
	
		
	
	
	
	
	
	this.errorFunction = function(){
		
	}
	
	
	
	
	
	this.getFeeTransactionDetail = function(feeTransactionId){
		$("#modalTransactionDetail").modal('show');
		paidFeeService.getPaidFeeDetail(feeTransactionId, this.displayTransactionDetail, this.errorFunction);
	}
	
	this.displayTransactionDetail = function(response, status){
		$scope.transactionDetail = response;
	}
	
	this.printReceipt = function(){
		window.open(_appContextPath+"/ws/feereceipt/print/"+$scope.transactionDetail.transactionId);
	}

	this.paidFeeInHeadDateView = function(){
		
		var redirectURL = "fee/paid/datewise/"+$scope.selectedAcademicYear;
		
		if($("#fromDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#fromDate").val();
		}
		
		if($("#toDate").val() == ""){
			redirectURL = redirectURL+"/-1"; 
		}else{
			redirectURL = redirectURL+"/"+$("#toDate").val();
		}
		
		$location.path(redirectURL);
	}
});






