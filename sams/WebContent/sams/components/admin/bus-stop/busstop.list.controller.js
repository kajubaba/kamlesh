
samsApp.controller('busstop.list.controller', function(busstopService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, blockUI){
	
	
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
	        	  name:'Bus Stop Name', 
	        	  field: 'name',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admin/busstop/{{row.entity.id}}">{{row.entity.name}}</a></div>'
	          },
	          { name:'Name in Hindi', field: 'nameInOtherLang',enableColumnResizing:true, visible:false},
	          { name:'Distance (Km.)', field: 'distance',enableColumnResizing:true },
	          { name:'Active', field: 'active',  enableColumnResizing:true},
	          { name:'Created By', field: 'createdBy',  enableColumnResizing:true, visible:false},
	          { name:'Created On', field: 'createdOn',  enableColumnResizing:true, visible:false},
	          { name:'Modified By', field: 'modifiedBy',  enableColumnResizing:true},
	          { name:'Modified On', field: 'modifiedOn',  enableColumnResizing:true}
	          
	        ]
	};
	
	this.init = function(){
		this.getBusStops();
	}
	
	this.getBusStops = function(){
		busstopService.getBusStops(this.getBusStopsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getBusStopsSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
    
});






