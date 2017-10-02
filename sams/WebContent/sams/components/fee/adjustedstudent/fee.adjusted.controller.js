samsApp.controller('sams.fee.adjusted.controller', function($scope, $routeParams, $location, feeAdjustedStudentService, blockUI){
	
	$scope.gridOptions = {
			rowHeight: 37,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: false,
			paginationPageSizes: [50, 75, 100, 500, 2000],
		    paginationPageSize: 50,
		    enableHorizontalScrollbar : 0, 
		    enableVerticalScrollbar : 1,
		    flatEntityAccess: true,
			
	        columnDefs: [
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentId',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/fee/student/customize/{{row.entity.id}}/class/{{row.entity.feeAdjustedClassId}}">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'studentName',enableColumnResizing:true },
	          { name:'Class', field: 'feeAdjustedClass',enableColumnResizing:true },
	          { name:'Admission Scheme', field: 'schemeName' ,enableColumnResizing:true },
	          { name:'Discount (Rs.)', field: 'discountGiven' ,enableColumnResizing:true },
	          { name:'Installments#', field: 'noOfInstallments',enableColumnResizing:true }
	          
	        ]
	};
	$scope.gridOptions.appScopeProvider = this;
	
	this.getFeeAdjustedStudents = function(){
		
		var selectedAcademicYear = null;
		
		if($routeParams['academicYearId'] !="null"){
			selectedAcademicYear = $routeParams['academicYearId'];
		}
		
		
		feeAdjustedStudentService.getFeeAdjustedStudents(selectedAcademicYear, this.renderFeeAdjustedStudents, this.errorFunction);
	}
	
	this.getAcademicYearAdjustments = function(academicYearId){
		var redirectURL = "fee/adjusted/students/"+academicYearId;
		$location.path(redirectURL);
	}
	
	this.renderFeeAdjustedStudents = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	this.errorFunction = function(){
		
	}
	
});






