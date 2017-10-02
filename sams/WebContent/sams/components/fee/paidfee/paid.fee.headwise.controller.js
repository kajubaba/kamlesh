samsApp.controller('sams.paid.fee.headiwse.controller', function($scope, $routeParams, $location, $interval, $q, paidFeeService, uiGridConstants){
	
	$scope.fromDate = "";
	$scope.toDate = "";
	$scope.selectedAcademicYear = null;
	$scope.selectedHeadId = null;
	$scope.feeHeadName = null;
	
	$scope.gridDateViewOptions = {
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
	          
	          { name:'Date', field: 'dateStr',enableColumnResizing:true },
	          { name:'Paid Amount (Rs.)', field: 'paidFee',enableColumnResizing:true, aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true }
	        ]
	};
	$scope.gridDateViewOptions.appScopeProvider = this;
	
	$scope.gridStudentViewOptions = {
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
	          { name:'Father Name', field: 'fatherName',enableColumnResizing:true },
	          { name:'Current Class', field: 'className',enableColumnResizing:true },
	          { name:'Paid Fee (Rs.)', field: 'amountPaid',enableColumnResizing:true,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true }
	          
	          
	        ]
	};
	
	$scope.gridStudentViewOptions.appScopeProvider = this;
	
	this.getStudentViewData = function(){
		
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		$scope.selectedHeadId = $routeParams['feeHeadId'];
		this.setDateBlankOnCheck();
		
		if($scope.selectedAcademicYear == -1){
			paidFeeService.getPaidFeeHeadwiseDetails(null, $scope.selectedHeadId, $scope.fromDate, $scope.toDate, this.renderStudentViewData, this.errorFunction);
		}else{
			paidFeeService.getPaidFeeHeadwiseDetails($scope.selectedAcademicYear, $scope.selectedHeadId, $scope.fromDate, $scope.toDate, this.renderStudentViewData, this.errorFunction);
		}
		
	}
	
	this.filterStudentVeiwByAcademicYear = function(academicYear){
		var redirectURL = "fee/paid/headwisewise/details/"+academicYear+"/"+$scope.selectedHeadId;
		redirectURL = this.prepareDateURL(redirectURL);
		$location.path(redirectURL);
	}
	
	this.filterStudnetView = function(){
		var redirectURL = "fee/paid/headwisewise/details/"+$scope.selectedAcademicYear+"/"+$scope.selectedHeadId;
		redirectURL = this.prepareDateURL(redirectURL);
		$location.path(redirectURL);
	}
	
	this.renderStudentViewData = function(response, status){
		$scope.gridStudentViewOptions.data = response.studentPaidFees;
		$scope.feeHeadName = response.headName;
	}
	
	this.getDateViewData = function(){
		
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		$scope.selectedHeadId = $routeParams['feeHeadId'];
		this.setDateBlankOnCheck();
		
		if($scope.selectedAcademicYear == -1){
			paidFeeService.getPaidFeeInHeadDateWise(null, $scope.selectedHeadId, $scope.fromDate, $scope.toDate, this.renderDateViewData, this.errorFunction);
		}else{
			paidFeeService.getPaidFeeInHeadDateWise($scope.selectedAcademicYear, $scope.selectedHeadId, $scope.fromDate, $scope.toDate, this.renderDateViewData, this.errorFunction);
		}
		
	}
	
	this.filterDateView = function(){
		var redirectURL = "fee/paid/in-head/dateview/"+$scope.selectedAcademicYear+"/"+$scope.selectedHeadId;
		redirectURL = this.prepareDateURL(redirectURL);
		console.log(redirectURL);
		$location.path(redirectURL);
	}
	
	this.filterDateVeiwByAcademicYear = function(academicYear){
		var redirectURL = "fee/paid/in-head/dateview/"+academicYear+"/"+$scope.selectedHeadId;
		redirectURL = this.prepareDateURL(redirectURL);
		$location.path(redirectURL);
	}
	
	this.renderDateViewData = function(response, status){
		$scope.feeHeadName = response.headName;
		$scope.gridDateViewOptions.data = response.paidFees;
	}
	
	this.prepareDateURL = function(redirectURL){
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
		
		return redirectURL;
	}
	
	this.setDateBlankOnCheck = function(){
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
	}
	
	/*this.switchToDateView = function(){
		var redirectURL = "fee/paid/in-head/dateview/"+$scope.selectedAcademicYear+"/"+$scope.selectedHeadId;
		redirectURL = this.prepareDateURL(redirectURL);
		$location.path(redirectURL);
	}*/
	
	
	
	
	this.errorFunction = function(){
		
	}
});






