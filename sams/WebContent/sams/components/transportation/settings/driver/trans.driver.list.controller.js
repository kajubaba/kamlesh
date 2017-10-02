
samsApp.controller('trans.driver.list.controller', function(vehicleDriverService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, blockUI){
	
	
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
	        	  name:'Name', 
	        	  field: 'name',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/transportation/driver/{{row.entity.id}}">{{row.entity.name}}</a></div>'
	          },
	          
	          { name:'Role', field: 'role',  enableColumnResizing:true},
	          { name:'Primary Contact', field: 'primaryContact',  enableColumnResizing:true},
	          { name:'Alternate Contact', field: 'secondaryContact',  enableColumnResizing:true},
	          { name:'license No', field: 'licenseNo',  enableColumnResizing:true},
	          { name:'Modified By', field: 'lastUpdatedBy',  enableColumnResizing:true, visible:false},
	          { name:'Modified On', field: 'lastUpdatedDateTime',  enableColumnResizing:true, visible:false}
	        ]
	};
	
	this.init = function(){
		this.getDriverList();
	}
	
	this.getDriverList = function(){
		vehicleDriverService.getdriverList(this.getDriverListSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getDriverListSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
    
});






