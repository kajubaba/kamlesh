samsApp.controller('transportation.student.controller', function($scope, $rootScope, $routeParams, transStudentService){
	
	
	$scope.selectedBusStopId = null;
	
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
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/transportation/student/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'name',enableColumnResizing:true },
	          { name:'Father Name', field: 'guardianName',  enableColumnResizing:true },
	          { name:'Gender', field: 'gender', enableColumnResizing:true },
	          { name:'Class', field: 'currentClass', enableColumnResizing:true },
	          { name:'Section', field: 'classSection', enableColumnResizing:true },
	          { name:'Bus Stop', field: 'busStop', enableColumnResizing:true },
	          { name:'Pickup Point', field: 'pickupPoint', visible:false,enableColumnResizing:true },
	          { name:'Drop Point', field: 'dropPoint', visible:false,enableColumnResizing:true },
	          { name:'Arrival Bus', field: 'arrivalBus', visible:false,enableColumnResizing:true },
	          { name:'Departure Bus', field: 'departureBus', visible:false,enableColumnResizing:true },
	          
	          
	          { name:'Status', field: 'studentStatus', visible:false, enableColumnResizing:true },
	          /*{ name:'Enrollment', field: 'enrollmentNo', visible:false, enableColumnResizing:true },*/
	          { name:'Mother Name', field: 'motherName', visible:false, enableColumnResizing:true },
	          /*{ name:'Category', field: 'category', visible:false, enableColumnResizing:true },*/
	          /*{ name:'Caste', field: 'caste', visible:false, enableColumnResizing:true },*/
	          { name:'Address', field: 'fullAddress', visible:false, enableColumnResizing:true },
	          { name:'City', field: 'city', visible:false, enableColumnResizing:true },
	          { name:'Student Contact#', field: 'studentContactNo', visible:false, enableColumnResizing:true },
	          { name:'Father Contact#', field: 'fatherContactNo', visible:false, enableColumnResizing:true },
	          { name:'Mother Contact#', field: 'motherContactNo', visible:false, enableColumnResizing:true },
	          /*{ name:'Religion', field: 'relegion', visible:false, enableColumnResizing:true },
	          { name:'Date Of Birth', field: 'dob', visible:false, enableColumnResizing:true },
	          { name:'Birth Place', field: 'birthPlace', visible:false, enableColumnResizing:true },
	          { name:'Family ID', field: 'familyId', visible:false, enableColumnResizing:true },
	          { name:'Samagra ID', field: 'samagraId', visible:false, enableColumnResizing:true },
	          { name:'Aadhar No', field: 'aadharNo', visible:false, enableColumnResizing:true },
	          { name:'Blood Group', field: 'bloodGroup', visible:false, enableColumnResizing:true },
	          { name:'Admission Form No#', field: 'admissionFormNo', visible:false, enableColumnResizing:true },
	          { name:'Registration Date', field: 'admissionRegistrationDate', visible:false, enableColumnResizing:true },
	          { name:'Confirmation Date', field: 'admissionConfirmationDate', visible:false, enableColumnResizing:true }*/
	                  
	        ]
	};
	
	this.getBusFacilityAdoptedStudents = function(){
		transStudentService.getBusFacilityAdoptedStudents(5, this.renderBusFacilityAdoptedStudents, this.errorFunction);
	}
	
	this.getBusFacilityAdoptedClasswiseCount = function(){
		transStudentService.getClassWiseAdmissionCount(5, this.renderBusFacilityAdoptedClasswiseCount, this.errorFunction);
	}
	
	this.getBusFacilityAdoptedBusStopwiseCount = function(){
		transStudentService.getBusStopWiseAdmissionCount(5, this.renderBusFacilityAdoptedBusStopwiseCount, this.errorFunction);
	}
	
	this.renderBusFacilityAdoptedStudents = function(response, status){
		$scope.gridOptions.data = response;
		/*var datatable = $('#dt-student-list').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();*/
	}
	
	this.renderBusFacilityAdoptedClasswiseCount = function(response, status){
		var datatable = $('#dt-bus-facility-classwise-admission-count').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	}
	
	this.renderBusFacilityAdoptedBusStopwiseCount = function(response, status){
		var datatable = $('#dt-bus-facility-busstopwise-admission-count').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	}
	
	this.getStudentByClass = function(){
		transStudentService.getStudentByClass(5, $routeParams['classId'], this.renderStudentByClass, this.errorFunction);
		transStudentService.getClassWiseAdmissionCount(5, this.populateClassDropDown, this.errorFuntion);
	}
	
	this.populateClassDropDown = function(response, status){
		
		if(response.length > 0){
			var ul = $('#classList')
			$.each(response, function(i)
			{
			    
				if($routeParams['classId'] == response[i].academicYearClassId){
					$("#selectedClass").html(response[i].name +" (" +response[i].admissionCount +")");
				}
				
				var li = $('<li/>')
			        .appendTo(ul);
			    
			    var a = $('<a/>')
			        .attr("href", "#/transportation/students/classwise/"+ response[i].academicYearClassId)
			    	.text(response[i].name +" (" +response[i].admissionCount +")")
			        .appendTo(li);
			});
		}
	}
	this.getStudentByBusStop = function(){
		$scope.selectedBusStopId = $routeParams['busStopId'];
		console.log($scope.selectedBusStopId);
		transStudentService.getStudentByBusStop(5, $scope.selectedBusStopId, this.renderStudentByBusStop, this.errorFunction);
		transStudentService.getBusStopWiseAdmissionCount(5, this.renderBusStopsInDropDown, this.errorFunction);
	}
	
	this.renderStudentByBusStop = function(response, status){
		$scope.gridOptions.data = response;
		/*var datatable = $('#dt-busstopwise-student-list').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();*/
	}
	
	this.renderStudentByClass = function(response, status){
		$scope.gridOptions.data = response;
		/*var datatable = $('#dt-classwise-student-list').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();*/
	}
	
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
			        .attr("href", "#/transportation/students/busstopwise/"+ response[i].busStopId)
			    	.text(response[i].busStop +" (" +response[i].admissions +")")
			        .appendTo(li);
			});
		}
	}
	
	this.errorFunction = function(){
		
	}
});