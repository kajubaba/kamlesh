
samsApp.controller('academicsession.assign.busstop.controller', function(academicSessionBusStopService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.academicSessionBusStopForm ={
			academicSessionId : null,
			busStopIds : []
	}
	
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
	          { name:'Bus Stop Name', field: 'name', enableColumnResizing:true
	        	   
	          },
	          { name:'Distance (Km.)', field: 'distance',  enableColumnResizing:true},
	          { name:'Active?', field: 'active',  enableColumnResizing:false}
	        ]
	};
	
	$scope.gridOptions.onRegisterApi = function( gridApi ) {
        
		$scope.gridApi = gridApi;
        
		gridApi.selection.on.rowSelectionChanged($scope,function(row){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnAssignBusStops").removeClass("disabled");
    			
        	}else{
        		$("#btnAssignBusStops").addClass("disabled");
        	}

        	$scope.academicSessionBusStopForm.busStopIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.academicSessionBusStopForm.busStopIds.push(row.id);
    	        });
    		}
          });
        
        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnAssignBusStops").removeClass("disabled");
        	}else{
        		$("#btnAssignBusStops").addClass("disabled");
        	}
        	$scope.academicSessionBusStopForm.busStopIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.academicSessionBusStopForm.busStopIds.push(row.id);
    	        });
    		}
          });
     };
	
	
	this.getUnAssignedBusStops= function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		$scope.academicSessionBusStopForm.academicSessionId = $scope.selectedAcademicSession;
		academicSessionBusStopService.getUnAssignedBusStops($scope.selectedAcademicSession, this.getUnAssignedBusStopsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getUnAssignedBusStopsSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	this.assignBusStops = function(){
		academicSessionBusStopService.assignBusStops($scope.academicSessionBusStopForm, this.assignBusStopsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.assignBusStopsSuccess = function(response, status){
		$route.reload();
	}
	
	
});






