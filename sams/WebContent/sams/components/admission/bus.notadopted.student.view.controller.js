samsApp.controller('bus.notadopted.student.view.controller', function(busNotAdoptedService, serverErrorHandlerService, $routeParams,$rootScope, $scope, blockUI){
	
	$scope.selectedStudentStatus = null;
	$scope.selectedAcademicYear = null;
	
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
			
			
	        columnDefs: [
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentId',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admissions/studentdetails/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'name',enableColumnResizing:true },
	          { name:'Father Name', field: 'guardianName',  enableColumnResizing:true },
	          { name:'Gender', field: 'gender', enableColumnResizing:true },
	          { name:'Class', field: 'currentClass', enableColumnResizing:true },
	          { name:'Section', field: 'classSection', enableColumnResizing:true },
	         
	          { name:'Status', field: 'studentStatus', visible:false, enableColumnResizing:true },
	          { name:'Enrollment', field: 'enrollmentNo', visible:false, enableColumnResizing:true },
	          { name:'Mother Name', field: 'motherName', visible:false, enableColumnResizing:true },
	          { name:'Category', field: 'category', visible:false, enableColumnResizing:true },
	          { name:'Caste', field: 'caste', visible:false, enableColumnResizing:true },
	          { name:'Address', field: 'fullAddress', visible:false, enableColumnResizing:true },
	          { name:'City', field: 'city', visible:false, enableColumnResizing:true },
	          { name:'Student Contact#', field: 'studentContactNo', visible:false, enableColumnResizing:true },
	          { name:'Father Contact#', field: 'fatherContactNo', visible:false, enableColumnResizing:true },
	          { name:'Mother Contact#', field: 'motherContactNo', visible:false, enableColumnResizing:true },
	          { name:'Religion', field: 'relegion', visible:false, enableColumnResizing:true },
	          { name:'Date Of Birth', field: 'dob', visible:false, enableColumnResizing:true },
	          { name:'Birth Place', field: 'birthPlace', visible:false, enableColumnResizing:true },
	          { name:'Family ID', field: 'familyId', visible:false, enableColumnResizing:true },
	          { name:'Samagra ID', field: 'samagraId', visible:false, enableColumnResizing:true },
	          { name:'Aadhar No', field: 'aadharNo', visible:false, enableColumnResizing:true },
	          { name:'Blood Group', field: 'bloodGroup', visible:false, enableColumnResizing:true },
	          { name:'Admission Form No#', field: 'admissionFormNo', visible:false, enableColumnResizing:true },
	          { name:'Registration Date', field: 'admissionRegistrationDate', visible:false, enableColumnResizing:true },
	          { name:'Confirmation Date', field: 'admissionConfirmationDate', visible:false, enableColumnResizing:true }
	          
	        ]
	};
	
	/**
	 * Displays list of students who adopted bus facility in academic year.
	 * Takes student status (confirmed, cancelled, new admission etc.) from end user and send it to server to get students who belongs to selected status.
	 */
	this.listStudents = function(){
		
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		var studentStatus = $routeParams['studentStatusId'];
		
		
		if(5 == studentStatus){
			$("#studentStatusType").html("Confirm Admissions");
		}else if("new" == studentStatus){
			$("#studentStatusType").html("New Admissions");
		}else if(4 == studentStatus){
			$("#studentStatusType").html("New Registrations");
		}else if(6 == studentStatus){
			$("#studentStatusType").html("Renewed Admissions");
		}else if(1 == studentStatus){
			$("#studentStatusType").html("Cancelled Admissions");
		}else if(3 == studentStatus){
			$("#studentStatusType").html("Degree Awarded");
		}
		console.log($scope.selectedAcademicYear);
		busNotAdoptedService.getStudents($routeParams['studentStatusId'], $scope.selectedAcademicYear, this.listStudentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.filterStudents = function(academicYearId){
		window.location ="#admissions/nobus/students/"+$scope.selectedStudentStatus+"/"+academicYearId;
	}
	
	this.listStudentsSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	
	
	
	
});






