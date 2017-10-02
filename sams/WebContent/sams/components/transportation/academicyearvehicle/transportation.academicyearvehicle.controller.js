samsApp.controller('transportation.academicyearvehicle.controller', function($scope, $filter, $route, $rootScope, academicYearVehicleService){
	
	$scope.vehiclesToBeAssigned = null;
	$scope.selectedVehicles = null;
	
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
	          { name:'Vehicle Name', field: 'vehicleName', enableColumnResizing:true},
	          { name:'Vehicle Type', field: 'vehicleType',  enableColumnResizing:true},
	          { name:'Vehicle No', field: 'vehicleId',  enableColumnResizing:true},
	          { name:'Manufacturer', field: 'manufacturer',  enableColumnResizing:true},
	          { name:'Seat Capacity', field: 'vehicleSeatCapacity',  enableColumnResizing:true},
	          { name:'Students', 
	        	  cellTemplate: '<div style="text-align:center"><a href="#/transportation/bus/{{row.entity.id}}/students/arrival"><i class="fa fa-users" aria-hidden="true"></i></a></div>'
		          }
	         
	          
	        ]
	};
	
	this.getAcademicYearVehicles = function(){
		academicYearVehicleService.getVehiclesInAcademicYear(this.renderAcademicYearVehicles, this.errorFunction);
	}
	
	this.renderAcademicYearVehicles = function(response, status){
		$scope.gridOptions.data = response;
		console.log(response);
		
	}
	
	this.getVehicleToBeAssignedInAcademicYear = function(){
		academicYearVehicleService.getVehicleToBeAssignedInAcademicYear(this.renderVehiclesToBeAssigned, this.errorFunction);
	}
	
	this.renderVehiclesToBeAssigned = function(response, status){
		$scope.vehiclesToBeAssigned = response;
	}
	
	this.selectedVehicle = function () {
		$scope.selectedVehicles  = $filter('filter')($scope.vehiclesToBeAssigned, {checked: true});
	   
	}
	
	this.import = function(){
		
		if($scope.selectedVehicles == null){
			$("#modalImportVehicle").modal('hide');
		}else{
			var ids = [];
			
			angular.forEach($scope.selectedVehicles, function(vehicle){
				ids.push(vehicle.id);
	        })
			academicYearVehicleService.importVehiclesInAcademicYear(ids, this.importSuccess, this.errorFunction);
		}
		
	}
	
	this.importSuccess = function(response, status){
		$("#modalImportVehicle").modal('hide');
		$route.reload();
	}
	
	this.openImportVehicleModal = function(){
		$("#modalImportVehicle").modal('show');
		this.getVehicleToBeAssignedInAcademicYear();
	}
	
	this.closeImportVehicleModal = function(){
		$("#modalImportVehicle").modal('hide');
	}
	
	this.errorFunction = function(){
		
	}
});