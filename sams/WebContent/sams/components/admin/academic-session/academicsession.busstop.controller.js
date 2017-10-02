
samsApp.controller('academicsession.busstop.controller', function(academicSessionBusStopService, academicSessionService, serverErrorHandlerService, uiGridConstants, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.academicSessionBusFees = null;
	$scope.academicSesionBusStopFeeForm = {
		academicSessionBusFees : null
	};
	
	$scope.showSuccessMessage= false; 
	
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
	          { name:'Bus Stop Name', field: 'busStopName', enableColumnResizing:true
	        	   
	          },
	          { name:'Distance (Km.)', field: 'distance',  enableColumnResizing:true},
	          { name:'Bus Fee (Rs.)', field: 'busFee',  enableColumnResizing:true},
	          { name:'Action', field: 'busFee',  enableColumnResizing:true,
	        	  cellTemplate: '<div style="text-align:center"><a href="javascript:void(0)" ng-click="grid.appScope.removeBusStop(row.entity.id)"><span class="glyphicon glyphicon-trash"></span></a></div>'
		          }
	        ],
	        onRegisterApi: function(gridApi) {
	            $scope.gridApi = gridApi;
	          }
	};
	
	$scope.gridOptions.appScopeProvider = this;
	$scope.gridOptions.columnDefs[3].visible = false;
	
	this.init = function(){
		academicSessionService.getAcademicSession($routeParams['academicSessionId'], this.getAcademicSessionSuccess, serverErrorHandlerService.handleError);
		this.getAcademicSessionBusStops();
		
	}
	
	
	this.getAcademicSessionBusStops= function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		academicSessionBusStopService.getAcademicSessionBusStops($scope.selectedAcademicSession, this.getAcademicSessionBusStopsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAcademicSessionBusStopsSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	this.initForEdit = function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		academicSessionBusStopService.getAcademicSessionBusStops($scope.selectedAcademicSession, this.initForEditSuccess, serverErrorHandlerService.handleError);
	}

	this.initForEditSuccess = function(response, status){
		$scope.academicSessionBusFees = response;
	}
	
	this.saveBusStopFees = function(){
		$scope.academicSesionBusStopFeeForm.academicSessionBusFees = $scope.academicSessionBusFees;
		academicSessionBusStopService.saveAcademicSessionBusStopFee($scope.academicSesionBusStopFeeForm, this.saveBusStopFeesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveBusStopFeesSuccess = function(response, status){
		console.log(response);
		if("OK" == response.status){
			$scope.showSuccessMessage= true;
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}
	}
	
	this.removeBusStop = function(busStopId){
		academicSessionBusStopService.removeBusStop(busStopId, this.removeBusStopSuccess, serverErrorHandlerService.handleError);
	}
	
	this.removeBusStopSuccess = function(response, status){
		if("ERROR" == response.status){
			$("#modalCanNotDeleteBusStop").modal('show');
		}else if("OK" == response.status){
			$route.reload();
		}
		
	}
	
	this.getAcademicSessionSuccess = function(response, status){
		
		console.log(response.status +"=="+ $rootScope.ACADEMIC_SESSION_STATUS_PUBLISHED);
		
		if(response.status == $rootScope.ACADEMIC_SESSION_STATUS_PUBLISHED){
			$scope.gridOptions.columnDefs[3].visible = false;
		}else{
			$scope.gridOptions.columnDefs[3].visible = true;
		}
		
		 $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		
	}
});






