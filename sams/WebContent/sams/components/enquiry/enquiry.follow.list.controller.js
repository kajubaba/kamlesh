samsApp.controller('enquiry.followup.list.controller', function(enquiryFollowupService, serverErrorHandlerService, $rootScope, $scope, blockUI, $routeParams, $route, $location){
	
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
	        	  name:'Followup On', 
	        	  field: 'createdOn',
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/enquiry/{{row.entity.enquiryId}}/followup/{{row.entity.id}}">{{row.entity.createdOn}}</a></div>'
	          },
	          
	          { name:'Communication Type', field: 'commType',  enableColumnResizing:true},
	          { name:'Communication With', field: 'commWith', enableColumnResizing:true },
	          { name:'Conclusion', field: 'commConclusion', enableColumnResizing:true },
	          { name:'Summary', field: 'commSummary', enableColumnResizing:true },
	          { name:'Next Action', field: 'nextAction', enableColumnResizing:true },
	          { name:'Next Followup On', field: 'nextFollowupDate', enableColumnResizing:true }
	          
	                  
	        ]
	};
	
	
	this.init = function(){
		enquiryFollowupService.getEnquiryFollowups($routeParams["enquiryId"], this.renderFollowups, serverErrorHandlerService.handleError);
	}
	
	this.renderFollowups = function(response, status){
		console.log(response);
		$scope.gridOptions.data = response;
	}
	
	
	
});






