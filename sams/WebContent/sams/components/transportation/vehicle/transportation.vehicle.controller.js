samsApp.controller('transportation.vehicle.controller', function($scope, $route, $rootScope, $timeout, $routeParams, vehicleService, serverErrorHandlerService){
	
	$scope.vehicleFormData = {
			id : null,
			vehicleId : null,
			vehicleType : null,
			vehicleName : null,
			manufacturingYear : null,
			manufacturer : null,
			vehicleColor : null,
			vehicleSeatCapacity : null,
			
			
			chassisNo : null,
			engineNo : null,
			
			registrationDate : null,
			transferDate : null,
			
			pucDueDate : null,
			fitnessDueDate : null,
			insuranceDueDate : null,
			roadTaxDueDate : null,
			vehicleSeatCapacity : null
		};
	
	$scope.vehicles = null;
	$scope.isActionNew= true;
	
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
	        	  name:'Vehicle Name/Number', 
	        	  field: 'vehicleName',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/transportation/vehicle/{{row.entity.id}}">{{row.entity.vehicleName}}</a></div>'
	          },
	          
	          { name:'Type', field: 'vehicleType',  enableColumnResizing:true},
	          { name:'Registration No.', field: 'vehicleId',  enableColumnResizing:true},
	          { name:'Make', field: 'manufacturer',  enableColumnResizing:true},
	          { name:'Passed With Seats', field: 'vehicleSeatCapacity',  enableColumnResizing:true},
	          
	          { name:'Chassis No', field: 'chassisNo',  enableColumnResizing:true, visible:false},
	          { name:'Engine No', field: 'engineNo',  enableColumnResizing:true, visible:false},
	          { name:'Registration Date', field: 'registrationDate',  enableColumnResizing:true, visible:false},
	          { name:'Transfer Date', field: 'transferDate',  enableColumnResizing:true, visible:false},
	          { name:'PUC Due Date', field: 'pucDueDate',  enableColumnResizing:true, visible:false},
	          { name:'Fitness Due Date', field: 'fitnessDueDate',  enableColumnResizing:true, visible:false},
	          { name:'Road Tax Due Date', field: 'insuranceDueDate',  enableColumnResizing:true, visible:false},
	          { name:'Permit Due Date', field: 'roadTaxDueDate',  enableColumnResizing:true, visible:false},
	          { name:'Insurance Due Date', field: 'permitDueDate',  enableColumnResizing:true, visible:false}
	          
	          
	          
	        ]
	};
	
	
	this.getVehicle = function(){
    	if($routeParams['vehicleId'] != "new"){
    		vehicleService.getVehicleDetail($routeParams['vehicleId'], this.displayVehicleDetail, serverErrorHandlerService.handleError);
    		$scope.isActionNew= false;
    	}else{
    		$scope.isActionNew= true;
    	}
	}
	
	this.getAllVehicles = function(){
		vehicleService.getAllVehicles(this.renderAllVehicles, this.errorFunction);
	}
	
	this.saveVehicle = function(){
		if($scope.isActionNew == true){
			vehicleService.addvehicle($scope.vehicleFormData, this.addVehicleSuccess, serverErrorHandlerService.handleError);
		}else if($scope.isActionNew == false){
			vehicleService.updateVehicle($scope.vehicleFormData, this.addVehicleSuccess, serverErrorHandlerService.handleError);
		}
	}
	
	this.addVehicleSuccess = function(response, status){
		if("OK" == response.status){
			$scope.vehicleFormData.id = response.generatedId;
			$scope.isActionNew= false;
			$scope.showSuccessMessage= true;
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);
		}
		
	}
	
	this.updateVehicle = function(){
		vehicleService.updateVehicle($scope.vehicleFormData, this.addVehicleSuccess, serverErrorHandlerService.handleError);
	}
	
	
	this.displayVehicleDetail = function(response, status){
		$scope.vehicleFormData = response;
	}
	
	this.renderAllVehicles = function(response, status){
		$scope.gridOptions.data = response;
	}
	
});