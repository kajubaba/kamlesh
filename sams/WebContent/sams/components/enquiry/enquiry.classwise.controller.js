

samsApp.controller('sams.enquiry.classwise.list.controller', function($scope, $rootScope, $route, $location, $routeParams, enquiryListService,serverErrorHandlerService, blockUI){
	$scope.selectedAcademicYear = null;
	
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
	        	  name:'Class', 
	        	  field: 'className',
	        	  enableColumnResizing:true
	        	
	          },
	          
	          { 
	        	  name:'Enquiries', field: 'enquiryCount',  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/enquiry/list/{{row.entity.academicSessionId}}/class/{{row.entity.classId}}">{{row.entity.enquiryCount}}</a></div>'
	          }
	          
	                  
	        ]
	};
	
	this.getClasswiseEnquiries = function(){
		$scope.selectedAcademicYear = $routeParams["academicYearId"];
		enquiryListService.getClasswiseEnquiries($scope.selectedAcademicYear, this.getClasswiseEnquiriesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getClasswiseEnquiriesSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}

	this.changeAcademicSession = function(academicYearId){
		window.location ="#/enquiry/list/classwise/"+academicYearId
	}
	
	
	
	
	
	
});






