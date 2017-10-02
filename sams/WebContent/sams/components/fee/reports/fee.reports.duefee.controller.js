samsApp.controller('fee.reports.duefee.controller', function($scope, $routeParams, dueFeeReportService, commonService, blockUI, $location, uiGridConstants){
	
	$scope.selectedAcademicYear = null;
	$scope.dueDate = null;
	$scope.studentStatus = {statusId:5};
	$scope.dueAdmissionStatus = "";
	$scope.dueClass = "";
	$scope.dueClassDate = "";
	$scope.dueAcademicYear = "";
	$scope.showOnlyDue = {value:false};
	$scope.studentIds = [];
	$scope.dueDates = null;
	$scope.unPaidFeeSum = null;
	$scope.activeStudentStatusList = null;
	
	$scope.dueFeeSum ={
			"totalFee" : 0,
			"paid" : 0,
			"unPaid" : 0,
			"paidBeforeDueDate" : 0
	}
	
	$scope.studentDueFeeSum ={
			"totalFee" : 0,
			"paid" : 0,
			"unPaid" : 0,
			"paidBeforeDueDate" : 0
	}
	
	$scope.gridOptions = {
			rowHeight: 37,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: false,
			paginationPageSizes: [50, 75, 2000],
		    paginationPageSize: 50,
		    enableHorizontalScrollbar : 0, 
		    enableVerticalScrollbar : 1,
		    flatEntityAccess: true,
		    enableSelectAll: true,
		    showColumnFooter: true,
			
	        columnDefs: [
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentId',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/fee/studentfee/{{row.entity.studentDbId}}/{{row.entity.classHistoryId}}">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'studentFullName',enableColumnResizing:true },
	          { name:'Class', field: 'studentClass', enableColumnResizing:true },
	          { name:'Father Name', field: 'fatherName', visible:false, enableColumnResizing:true },
	          { name:'Payable Fee', field: 'totalFee',enableColumnResizing:true,enableColumnResizing:true,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true },
	          { name:'Paid Fee', field: 'paidFee', enableColumnResizing:true ,enableColumnResizing:true,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true},
	          { name:'Unpaid Fee', field: 'unPaidFee',enableColumnResizing:true ,enableColumnResizing:true,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true},
	          { name:'Paid Before Due Date', field: 'paidFeeInAdvance',enableColumnResizing:true ,enableColumnResizing:true,aggregationType: uiGridConstants.aggregationTypes.sum, aggregationHideLabel: true},
	          { name:'Student Contact #1', field: 'studentContactNo1', visible:false, enableColumnResizing:true },
	          { name:'Student Contact #2', field: 'studentContactNo2', visible:false, enableColumnResizing:true },
	          { name:'Father Contact #1', field: 'fatherContactNo1', visible:false, enableColumnResizing:true },
	          { name:'Father Contact #2', field: 'fatherContactNo2', visible:false, enableColumnResizing:true },
	          { name:'Mother Contact #1', field: 'fatherContactNo1', visible:false, enableColumnResizing:true },
	          { name:'Mother Contact #2', field: 'fatherContactNo2', visible:false, enableColumnResizing:true },
	          { name:'Student Name (Hindi)', field: 'studentNameInOtherLanguage', visible:false, enableColumnResizing:true },
	          { name:'Father Name (Hindi)', field: 'fatherNameInOtherLanguage', visible:false, enableColumnResizing:true },
	          { name:'Bus Stop', field: 'busStopName', visible:false, enableColumnResizing:true },
	          { name:'Bus Stop (Hindi)', field: 'busStopInOtherLanguage', visible:false, enableColumnResizing:true },
	          { name:'Arrival Bus', field: 'arrivalBusName', visible:false, enableColumnResizing:true },
	          { name:'Departure Bus', field: 'departureBusName', visible:false, enableColumnResizing:true }
	          
	          
	                  
	        ]
	};
	$scope.gridOptions.multiSelect = true;
	$scope.gridOptions.appScopeProvider = this;
	
	
	$scope.gridOptions.onRegisterApi = function( gridApi ) {
        
		$scope.gridApi = gridApi;
        
		gridApi.selection.on.rowSelectionChanged($scope,function(row){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnGenerateDueFeeNotice").removeClass("disabled");
    			
        	}else{
        		$("#btnGenerateDueFeeNotice").addClass("disabled");
        	}

        	$scope.studentIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.studentIds.push(row.studentDbId);
    	        });
    		}
          });
        
        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnGenerateDueFeeNotice").removeClass("disabled");
        	}else{
        		$("#btnGenerateDueFeeNotice").addClass("disabled");
        	}
        	$scope.studentIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.studentIds.push(row.studentDbId);
    	        });
    		}
          });
     };
	
	// used for current academic year
	this.getDueFeeReport = function(){
		$scope.studentStatus.statusId = $routeParams['admissionStatus'];
		
		if(angular.isUndefined($routeParams['academicYearId']) || $routeParams['academicYearId']=="null"){
			$scope.selectedAcademicYear = null;
		}else{
			$scope.selectedAcademicYear = $routeParams['academicYearId'];
		}
		
		if(angular.isUndefined($routeParams['dueDate']) || $routeParams['dueDate']=="null"){
			$scope.dueDate = null;
		}else{
			$scope.dueDate = $routeParams['dueDate'];
		}
		dueFeeReportService.getAllDueDates($scope.selectedAcademicYear, this.renderDueDates, this.errorFunction);
		dueFeeReportService.getDueFeeReport($scope.selectedAcademicYear, $scope.dueDate,$scope.studentStatus.statusId, this.renderDueFee, this.errorFunction);
		this.getActiveStudentStatusList();
		
		
	}
	
	this.getDueFeeReportForDashboard = function(){
		$scope.studentStatus.statusId = 5;
		$scope.selectedAcademicYear = null;
		$scope.dueDate = null;
		dueFeeReportService.getDueFeeReport($scope.selectedAcademicYear, $scope.dueDate,$scope.studentStatus.statusId, this.renderDueFee, this.errorFunction);
	}
	
	this.filterDueFeeReportByDueDate = function(dueDate){
		$location.path("fee/reports/duefee/"+$scope.selectedAcademicYear+"/"+dueDate+"/"+$scope.studentStatus.statusId);
	}
	
	this.filterDueFeeReportByStatus = function(studentStatus){
		$location.path("fee/reports/duefee/"+$scope.selectedAcademicYear+"/"+$scope.dueDate+"/"+studentStatus);
	}
	
	this.filterAllDueStudentsByStatus = function(studentStatus){
		$location.path("fee/duestudents/"+$scope.selectedAcademicYear+"/"+$scope.dueDate+"/"+studentStatus);
	}
	
	
	
	// Used on selection of academic year
	this.getAcademicYearDueFeeReport = function(academicYear){
		$location.path("fee/reports/duefee/"+academicYear+"/null/5");
	}
	
	this.getDueStudentsOfClass = function(){
		$scope.studentStatus.statusId = $routeParams['admissionStatus'];
		if(angular.isUndefined($routeParams['academicYearId']) || $routeParams['academicYearId']=="null"){
			$scope.selectedAcademicYear = null;
		}else{
			$scope.selectedAcademicYear = $routeParams['academicYearId'];
		}
		$scope.dueDate = $routeParams['dueDate'];
		dueFeeReportService.getDueStudentsOfClass($scope.selectedAcademicYear, $routeParams['courseYearId'], $scope.dueDate , $scope.studentStatus.statusId, $scope.showOnlyDue.value, this.renderDueStudents, this.errorFunction);
	}
	
	this.getAllDueStudents = function(){
		//$scope.selectedAcademicYear = $routeParams['academicYearId'];
		$scope.studentStatus.statusId = $routeParams['admissionStatus'];
		//$scope.dueDate = $routeParams['dueDate'];
		console.log("121313...");
		if(angular.isUndefined($routeParams['academicYearId']) || $routeParams['academicYearId']=="null"){
			$scope.selectedAcademicYear = null;
		}else{
			$scope.selectedAcademicYear = $routeParams['academicYearId'];
		}
		
		if(angular.isUndefined($routeParams['dueDate']) || $routeParams['dueDate']=="null"){
			$scope.dueDate = null;
		}else{
			$scope.dueDate = $routeParams['dueDate'];
		}
		
		dueFeeReportService.getAllDueDates($scope.selectedAcademicYear, this.renderDueDates, this.errorFunction);
		dueFeeReportService.getAllDueStudents($scope.selectedAcademicYear, $scope.dueDate , $scope.studentStatus.statusId, $scope.showOnlyDue.value, this.renderAllDueStudents, this.errorFunction);
		this.getActiveStudentStatusList();
	}
	
	this.filterDueStudentsByAcademicYear = function(academicYear){
		//$location.path("/fee/reports/duefee/allstudents/"+academicYear+"/"+$scope.dueDate);
		$location.path("fee/duestudents/"+academicYear+"/null/5");
	}
	
	this.filterDueStudentsByDueDate = function(dueDate){
		//$scope.dueDate = $("#dueDate").val();
		$scope.dueDate = dueDate;
		$location.path("/fee/duestudents/"+$scope.selectedAcademicYear+"/"+$scope.dueDate+"/"+$scope.studentStatus.statusId );
	}
	
	this.renderDueFee = function(response, status){
		$scope.dueDate = response.dueDate;
		$scope.selectedAcademicYear = response.academicYearId;
		
		
		angular.forEach(response.classDueFees, function(classDueFee){
			$scope.dueFeeSum.totalFee = $scope.dueFeeSum.totalFee + classDueFee.projectedFee;
			$scope.dueFeeSum.paid = $scope.dueFeeSum.paid + classDueFee.paidFee;
			$scope.dueFeeSum.unPaid = $scope.dueFeeSum.unPaid + classDueFee.unpaidFee;
			$scope.dueFeeSum.paidBeforeDueDate = $scope.dueFeeSum.paidBeforeDueDate + classDueFee.paidAdvance;
			
        })
		
		
		
		var datatable = $('#dt-due-fee-report').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response.classDueFees);
	    datatable.draw();
	}
	
	this.renderDueStudents = function(response, status){
		
		
		
		$scope.dueClass = response.className;
		$scope.dueClassDate = response.dueDate;
		$scope.dueAcademicYear = response.academicYear;
		$scope.unPaidFeeSum = response.unpaidFeeSum;
		$scope.dueAdmissionStatus = response.admissionStatus;
		
		var datatable = $('#dt-due-students').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response.students);
	    datatable.draw();
		
		$scope.gridOptions.data = response.students;
		
		
	}
	
	this.renderAllDueStudents = function(response, status){
		$scope.unPaidFeeSum = response.unpaidFeeSum;
		$scope.gridOptions.data = response.students;
		
		var datatable = $('#dt-due-students').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response.students);
	    datatable.draw();
		
		$scope.dueDate = response.dueDate;
	}

	
	
	this.renderDueDates = function(response, status){
		$scope.dueDates = response;
	}
	
	this.getActiveStudentStatusList = function(){
		commonService.getActiveStudentStatusList(this.renderActiveStudentStatusList, this.errorFunction);
	}
	
	this.renderActiveStudentStatusList = function(response, status){
		$scope.activeStudentStatusList = response;
	}
	
	this.errorFunction = function(){
		
	}
	
	this.filterDueStudentsOfClass = function(){
		dueFeeReportService.getDueStudentsOfClass($scope.selectedAcademicYear, $routeParams['courseYearId'], $scope.dueDate , $scope.studentStatus.statusId, $scope.showOnlyDue.value, this.renderDueStudents, this.errorFunction);
	}
	this.filterDueStudents = function(){
		dueFeeReportService.getAllDueStudents($scope.selectedAcademicYear, $scope.dueDate , $scope.studentStatus.statusId, $scope.showOnlyDue.value, this.renderAllDueStudents, this.errorFunction);
	}
	
	this.switchToDataTableView = function(){
		$("#list-ui-grid").hide();
		$("#linkDriverView").hide();
		$("#list-data-table").show();
		$("#linkClassicView").show();
		
	}
	this.switchToClassicView = function(){
		$("#list-data-table").hide();
		$("#linkClassicView").hide();
		$("#list-ui-grid").show();
		$("#linkDriverView").show();
		
	}
	
});






