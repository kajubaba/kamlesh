
samsApp.controller('enquiry.status.controller', function(enquiryStatusService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, blockUI){
	
	
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
	        	  name:'Status Name', 
	        	  field: 'name',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admin/enquirystatus/{{row.entity.id}}">{{row.entity.name}}</a></div>'
	          },
	          
	          { name:'Active', field: 'active',  enableColumnResizing:true},
	          
	          
	        ]
	};
	
	this.init = function(){
		this.getAllStatusList();
	}
	
	this.getAllStatusList = function(){
		enquiryStatusService.getAllStatusList(this.getAllStatusListSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAllStatusListSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
    
});






