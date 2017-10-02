
samsApp.controller('course.list.controller', function(courseService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, blockUI){
	
	
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
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admin/course/{{row.entity.id}}">{{row.entity.name}}</a></div>'
	          },
	          { name:'Display Name', field: 'displayName',  enableColumnResizing:true},
	          { name:'Affiliated With', field: 'affiliationAuthority',  enableColumnResizing:true},
	          { name:'Duration (Yrs.)', field: 'duration',  enableColumnResizing:true},
	          { name:'Created By', field: 'createdBy',  enableColumnResizing:true, visible:false},
	          { name:'Created On', field: 'createdOn',  enableColumnResizing:true, visible:false},
	          { name:'Modified By', field: 'modifiedBy',  enableColumnResizing:false, visible:true},
	          { name:'Modified On', field: 'modifiedOn',  enableColumnResizing:false, visible:true}
	          
	        ]
	};
	
	this.init = function(){
		this.getCourses();
	}
	
	this.getCourses = function(){
		courseService.getCourses(this.getgetCoursesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getgetCoursesSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
    
});






