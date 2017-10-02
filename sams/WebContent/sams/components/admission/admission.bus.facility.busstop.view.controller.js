/*
 * @ Author Narendra Patidar
 * @ Date Jan-10-2016
 * 
 * This is Angular JS based controller. It can be declared into HTML pages with 'ng-controller' directive.
 * 
 * 
 * It is used to display/filter bus stop students who adopted bus facility in academic year.
 * It is communicating services, which in back end communicates with web server via web services; web services are sending data back in JSON format. 
 *
 * Responsibilities:
 * 
 * 1. For selected student status, displays bus stop wise student count
 *  
 */

samsApp.controller('admission.bus.facility.busstop.view.controller', function(busAdoptedService, studentListService, $routeParams, $scope, blockUI){
	
	// Model variable tied with controller's scope. it can be used on HTML page.
	$scope.selectedStudentStatus = null;
	$scope.selectedAcademicYear = null;
	
	$scope.gridOptions = {
			rowHeight: 37,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: false,
			paginationPageSizes: [50, 75, 100],
		    paginationPageSize: 50,
		    enableHorizontalScrollbar : 0, 
		    enableVerticalScrollbar : 0,
		    flatEntityAccess: true,
		    enableSelectAll: true,
			
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
	
	this.filterStudentCount = function(academicYearId){
		window.location ="#/admissions/busfacility/busstopwise/admissioncount/status/"+$scope.selectedStudentStatus+"/"+academicYearId;
	}
	
	/**
	 * 
	 * Displays and filters bus stop wise students count who adopted bus facility.
	 * Takes student status (confirmed, cancelled, new admission etc.) from end user to and send it to server to get bus stop wise student count who adopted bus facility.
	 *  
	 */
	this.fetchAdmissionCount = function(){

		blockUI.start();
		// update model selected student status into scope variable
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		// get selected student status from route service.
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
		
		// Invoke service and passing selected student status 
		busAdoptedService.getBusStopWiseAdmissionCount($routeParams['studentStatusId'], $scope.selectedAcademicYear, this.renderAdmissionCount, this.errorFuntion);
		
		
	}
	
	/**
	 * It is success function of 'fetchAdmissionCount()' function. 
	 * It is expecting list of bus stop wise student count in 'response' (in JSON object) and bind the same in JQuery data table.
	 * 
	 * Expected format of JSON : 
	 * 
	 * [
	 * 		{"busStopId":98,"busStop":"Arihant Nagar Kasrawad","admissions":2},
	 * 		{"busStopId":97,"busStop":"Bagwaniya","admissions":2},{"busStopId":6,"busStop":"Bakaner","admissions":5},
	 * 		{"busStopId":7,"busStop":"Balsmud","admissions":10},{"busStopId":64,"busStop":"Bamandi","admissions":7}
	 * ]
	 * 
	 */
	this.renderAdmissionCount = function(response, status){
		var datatable = $('#dt-bus-facility-busstopwise-admission-count').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	    blockUI.stop();
	}
	
	/**
	 * 
	 * Displays list of students (who adopted bus facility) for selected bus stop and students status. student status can be confirmed, cancelled, new admission etc.
	 * Takes student status (confirmed, cancelled, new admission etc.) and bus stop for which end users wants to see student list.
	 *  
	 */
	this.fetchAdmissionsByBusStop = function(){

		
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		
		//Print selected student status on page
		this.printStudentStatus();
		
		// Invoke service to get list of bus stops
		busAdoptedService.getBusStopWiseAdmissionCount($routeParams['studentStatusId'], $scope.selectedAcademicYear, this.renderBusStopsInDropDown, this.errorFuntion);
		
		// Invoke service to get list of students for selected student status and class
		busAdoptedService.getStudentByBusStop($routeParams['studentStatusId'], $routeParams['busStopId'], $scope.selectedAcademicYear, this.renderAdmissionsOfBusStop, this.errorFuntion);
		
	}
	
	/**
	 * It is success function of 'fetchAdmissionsByClass()' function. 
	 * It is expecting list students in 'response' (in JSON object) and bind the same in JQuery data table.
	 * 
	 * Expected format of JSON : 
	 * 
	 * [
	 * 		{"id":1487,"studentId":"SPITM20150053","name":"DISHAKSHI SHARMA","guardianName":"RAJESH SHARMA","gender":"female","currentClass":"B.E. EC , 1 Sem.","busStopId":8,"busStop":"Barwaha"},
	 * 		{"id":1531,"studentId":"SPITM20150057","name":"SANDEEP DHOPE","guardianName":"SABALSINGH","gender":"male","currentClass":"B.E. EC , 1 Sem.","busStopId":21,"busStop":"Sanawad"}
	 * ]
	 * 
	 */
	this.renderAdmissionsOfBusStop = function(response, status){
		/*var datatable = $('#dt-busstopwise-student-list').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);*/
		$scope.gridOptions.data = response;
	    datatable.draw();
	}
	
	/**
	 *  
	 * It is expecting list of bus stop wise student count in 'response' (in JSON object).
	 * 
	 * Expected format of JSON : 
	 * 
	 * [
	 * 		{"busStopId":98,"busStop":"Arihant Nagar Kasrawad","admissions":2},
	 * 		{"busStopId":97,"busStop":"Bagwaniya","admissions":2},{"busStopId":6,"busStop":"Bakaner","admissions":5},
	 * 		{"busStopId":7,"busStop":"Balsmud","admissions":10},{"busStopId":64,"busStop":"Bamandi","admissions":7}
	 * ]
	 * 
	 */
	this.renderBusStopsInDropDown = function(response, status){
		if(response.length > 0){
			var ul = $('#busStopList')
			$.each(response, function(i)
			{
			    
				if($routeParams['busStopId'] == response[i].busStopId){
					$("#selectedBusStop").html(response[i].busStop +" (" +response[i].admissions +")");
				}
				
				var li = $('<li/>')
			        .appendTo(ul);
			    
			    var a = $('<a/>')
			        .attr("href", "#/admissions/busfacility/busstopwise/admissions/"+$scope.selectedStudentStatus+"/"+ response[i].busStopId+"/"+$scope.selectedAcademicYear)
			    	.text(response[i].busStop +" (" +response[i].admissions +")")
			        .appendTo(li);
			});
		}
	}
	
	/**
	 * Prints student status (confirmed, new etc.) selected by end user.
	 * It looks URL to get selected student status.
	 */
	this.printStudentStatus = function(){
		
		var studStatus = $routeParams['studentStatusId'];
	
		
		if(5 == studStatus){
			$("#studStatus").html("Confirm Admissions");
		}else if("new" == studStatus){
			$("#studStatus").html("New Admissions");
		}else if(4 == studStatus){
			$("#studStatus").html("New Registrations");
		}else if(6 == studStatus){
			$("#studStatus").html("Renewed Admissions");
		}else if(1 == studStatus){
			$("#studStatus").html("Cancelled Admissions");
		}else if(3 == studStatus){
			$("#studStatus").html("Degree Awarded Admissions");
		}
		
		
	}
	
	this.errorFuntion = function(response){
		
	}
	
});






