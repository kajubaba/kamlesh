samsApp.controller('enquiry.upcoming.follow.controller', function(upcomingFollowupService, serverErrorHandlerService, $rootScope, $scope, blockUI, $routeParams, $route, $location){
	
	$scope.followups = null;
	$scope.searchCrt = {
			ay : null,
			from : null,
			to : null
			
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
	          { 
	        	  name:'Student Name', 
	        	  field: 'studentName',
	        	  
	        	  enableFiltering: true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/enquiry/{{row.entity.enquiryId}}/followup/{{row.entity.followupId}}">{{row.entity.studentName}}</a></div>'
	          },
	          
	          { name:'Class', field: 'studentClass',  enableColumnResizing:true},
	          { name:'Student Contact No#', field: 'studentContactNo', enableColumnResizing:true },
	          { name:'Father Contact No#', field: 'fatherContactNo', enableColumnResizing:true },
	          { name:'Mother Contact No#', field: 'motherContactNo', enableColumnResizing:true },
	          { name:'Action', field: 'nextFollowupAction', enableColumnResizing:true },
	          { name:'Follow On', field: 'nextFollowupOn', enableColumnResizing:true }
	          
	                  
	        ]
	};
	
	this.init = function(){
		if("today" == $routeParams['day']){
			upcomingFollowupService.getTodaysFollowups(this.todaysFollowupsSuccess, serverErrorHandlerService.handleError);
		}else if("tomorrow" == $routeParams['day']){
			upcomingFollowupService.getTomorrowsFollowups(this.todaysFollowupsSuccess, serverErrorHandlerService.handleError);
		}
		
	}
	
	this.todaysFollowupsSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
});






