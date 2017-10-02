/*
 * @ Author Narendra Patidar
 * @ Date Jan-10-2016
 * 
 * This is Angular JS based controller. It can be declared into HTML pages with 'ng-controller' directive.
 * 
 * It is used to display/filter students who adopted bus facility in academic year.
 * It is communicating services, which in back end communicates with web server via web services; web services are sending data back in JSON format. 
 *
 * Responsibilities:
 * 
 * 1. For selected student status, displays list of students.
 * 
 */

samsApp.controller('admission.bus.facility.student.view.controller', function(busAdoptedService, $routeParams, $scope, $rootScope, blockUI){
	
	
	// Model variable tied with controller's scope. it can be used on HTML page.
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
	          { name:'Bus Stop', field: 'busStop', enableColumnResizing:true },
	          { name:'Status', field: 'studentStatus', visible:false, enableColumnResizing:true },
	          { name:'Enrollment', field: 'enrollmentNo', visible:false, enableColumnResizing:true },
	          { name:'Mother Name', field: 'motherName', visible:false, enableColumnResizing:true },
	          { name:'Category', field: 'category', visible:false, enableColumnResizing:true },
	          { name:'Caste', field: 'caste', visible:false, enableColumnResizing:true },
	          { name:'Sub Caste', field: 'subCaste', visible:false, enableColumnResizing:true },
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
	          { name:'Confirmation Date', field: 'admissionConfirmationDate', visible:false, enableColumnResizing:true },
	          { name:'Bank', field: 'bankName', visible:false, enableColumnResizing:true },
	          { name:'Bank A/C', field: 'bankAcNo', visible:false, enableColumnResizing:true },
	          { name:'Bank IFSC', field: 'bankIfsc', visible:false, enableColumnResizing:true },
	          { name:'Branch', field: 'bankBranch', visible:false, enableColumnResizing:true }
	          
	        ]
	};
	
	/**
	 * Displays list of students who adopted bus facility in academic year.
	 * Takes student status (confirmed, cancelled, new admission etc.) from end user and send it to server to get students who belongs to selected status.
	 */
	this.listStudents = function(){
		
		blockUI.start();
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
		
		// Invoke service to get the students from server
		busAdoptedService.getAdmissions($routeParams['studentStatusId'], $scope.selectedAcademicYear, this.listStudentsSuccess, this.errorFuntion);
	}
	
	this.filterStudents = function(academicYearId){
		window.location ="#admissions/busfacility/students/status/"+$scope.selectedStudentStatus+"/"+academicYearId;
	}
	
	
	/**
	 * It is success function of 'listStudents()' function. 
	 * It is expecting list of students count in 'response' (in JSON object) and bind the same in JQuery data table.
	 * 
	 * Expected format of JSON : Array of JSON objects 
	 * 
	 * [
	 * 		{"id":988,"studentId":"SPITM20130101","name":"AADARSH GOSWAMI","guardianName":"MR ASHOK GOSWAMI","gender":"male","currentClass":"B.E. Mechanical , 5 Sem.","busStopId":30,"busStop":"Karhi"},
	 *  	{"id":751,"studentId":"SPITM20120267","name":"Aadil Sheikh","guardianName":"Mr Gulamshabir Sheikh","gender":"male","currentClass":"B.E. Mechanical , 7 Sem.","busStopId":91,"busStop":"Hostal Boys"},
	 *  	{"id":953,"studentId":"SPITM20130094","name":"AAKANSHA MANDLOI","guardianName":"MR SANDEEP MANDLOI","gender":"female","currentClass":"B.E. Computer Sc. , 5 Sem.","busStopId":29,"busStop":"Kasrawad"},
	 *  	{"id":935,"studentId":"SPITM20130100","name":"AAKASH  GANGLE","guardianName":"MR.DILIP GANGLE","gender":"male","currentClass":"B.E. Mechanical , 5 Sem.","busStopId":95,"busStop":"Karondiya"},
	 * ]
	 * 
	 */
	this.listStudentsSuccess = function(response, status){
		/*var datatable = $('#dt-bus-facility-students').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();*/
		$scope.gridOptions.data = response;
	    blockUI.stop();
	}
	
	
	/**
	 * Error function; to handle error occurs during server communication.
	 */
	this.errorFuntion = function(response){
		
	}
	
	
});






