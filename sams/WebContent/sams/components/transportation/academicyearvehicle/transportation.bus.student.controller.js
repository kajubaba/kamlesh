samsApp.controller('transportation.bus.student.controller', function($scope, $routeParams, $route, $rootScope, busStudentService,academicYearVehicleService, serverErrorHandlerService){

	$scope.academicSessionBusId = null;
	$scope.vehicle = null;
	$scope.vehicleRouteStudentInfo = null;
	$scope.showDropDown = false;
	$scope.arrivalStudents = null;
	$scope.arrivalGridOptions = {
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
					  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/transportation/student/{{row.entity.studentDBId}}">{{row.entity.studentId}}</a></div>'
				},
	         
	          { name:'Student Name', field: 'studentName', enableColumnResizing:true},
	          { name:'Student Name (Hindi)', field: 'nameInOtherLanguage', enableColumnResizing:true, visible:false},
	          { name:'Father Name', field: 'fatherName',  enableColumnResizing:true},
	          { name:'Father Name (Hindi)', field: 'fatherNameInOtherLanguage',  enableColumnResizing:true, visible:false},
	          { name:'Gender', field: 'studentGender',  enableColumnResizing:true},
	          { name:'Class', field: 'studentClass',  enableColumnResizing:true},
	          { name:'Bus Stop', field: 'busStop',  enableColumnResizing:true},
	          { name:'Bus Stop (Hindi)', field: 'busStopNameInOtherLanguage',  enableColumnResizing:true, visible:false},
	          { name:'Pickup Point', field: 'pickupPoint',  enableColumnResizing:true, visible:false},
	          { name:'Student Contact #', field: 'studentContact',  enableColumnResizing:true, visible:false},
	          { name:'Father Contact #', field: 'fatherContact',  enableColumnResizing:true, visible:false},
	          { name:'Mother Contact #', field: 'motherContact',  enableColumnResizing:true, visible:false}
	          
	        ]
	};
	
	$scope.departureGridOptions = {
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
	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/transportation/student/{{row.entity.studentDBId}}">{{row.entity.studentId}}</a></div>'
},
				{ name:'Student Name', field: 'studentName', enableColumnResizing:true},
				{ name:'Student Name (Hindi)', field: 'nameInOtherLanguage', enableColumnResizing:true, visible:false},
				{ name:'Father Name', field: 'fatherName',  enableColumnResizing:true},
				{ name:'Father Name (Hindi)', field: 'fatherNameInOtherLanguage',  enableColumnResizing:true, visible:false},
	          { name:'Gender', field: 'studentGender',  enableColumnResizing:true},
	          { name:'Class', field: 'studentClass',  enableColumnResizing:true},
	          { name:'Bus Stop', field: 'busStop',  enableColumnResizing:true},
	          { name:'Bus Stop (Hindi)', field: 'busStopNameInOtherLanguage',  enableColumnResizing:true, visible:false},
	          { name:'Drop Point', field: 'dropPoint',  enableColumnResizing:true, visible:false},
	          { name:'Student Contact #', field: 'studentContact',  enableColumnResizing:true, visible:false},
	          { name:'Father Contact #', field: 'fatherContact',  enableColumnResizing:true, visible:false},
	          { name:'Mother Contact #', field: 'motherContact',  enableColumnResizing:true, visible:false}
	          
	        ]
	};
	
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
	this.getArrivalStudents = function(){
		$scope.academicSessionBusId = $routeParams['academicSessionBusId'];
		this.getAcademicSessionVehicle($scope.academicSessionBusId);
		busStudentService.getArrivalStudents($scope.academicSessionBusId, this.getArrivalStudentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getArrivalStudentsSuccess = function(response, status){
		$scope.vehicleRouteStudentInfo = response;
		$scope.arrivalGridOptions.data = response.routeStudents;
		$scope.arrivalStudents = response.routeStudents;
		
		
		var datatable = $('#dt-arrival-students').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response.routeStudents);
	    datatable.draw();
		
		if($scope.vehicleRouteStudentInfo != null && $scope.vehicleRouteStudentInfo.routes != null && $scope.vehicleRouteStudentInfo.routes.length > 1){
			$scope.showDropDown = true;
		}
	}
	
	this.getDepartureStudents = function(){
		$scope.academicSessionBusId = $routeParams['academicSessionBusId'];
		this.getAcademicSessionVehicle($scope.academicSessionBusId);
		busStudentService.getDepartureStudents($scope.academicSessionBusId, this.getDepartureStudentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getDepartureStudentsSuccess = function(response, status){
		$scope.vehicleRouteStudentInfo = response;
		$scope.departureGridOptions.data = response.routeStudents;
		if($scope.vehicleRouteStudentInfo != null && $scope.vehicleRouteStudentInfo.routes != null && $scope.vehicleRouteStudentInfo.routes.length > 1){
			$scope.showDropDown = true;
		}
	}
	
	this.getAcademicSessionVehicle = function(academicSessionVehicleId){
		academicYearVehicleService.getAcademicSessionVehicle(academicSessionVehicleId, this.getAcademicSessionVehicleSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAcademicSessionVehicleSuccess = function(response, status){
		$scope.vehicle = response;
	}
	
	this.getArrivalRouteStudents = function(){
		busStudentService.getRouteStudents($scope.vehicleRouteStudentInfo.routeId, this.getArrivalStudentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getDepartureRouteStudents = function(){
		busStudentService.getRouteStudents($scope.vehicleRouteStudentInfo.routeId, this.getDepartureStudentsSuccess, serverErrorHandlerService.handleError);
	}
});