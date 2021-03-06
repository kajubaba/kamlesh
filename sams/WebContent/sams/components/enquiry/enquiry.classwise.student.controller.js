

samsApp.controller('sams.enquiry.classwise.student.controller', function($scope, $rootScope, $route, $location, $routeParams, enquiryListService,serverErrorHandlerService, blockUI){
	$scope.selectedAcademicYear = null;
	$scope.selectedClassId = null;
	$scope.classwiseEnquiries = null;
	
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
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/enquiry/{{row.entity.id}}">{{row.entity.studentName}}</a></div>'
	          },
	          
	          { name:'Father Name', field: 'fatherName',  enableColumnResizing:true},
	          { name:'Gender', field: 'gender', enableColumnResizing:true },
	          { name:'Class', field: 'className', enableColumnResizing:true },
	          { name:'Enquiry Date', field: 'enquiryDate', enableColumnResizing:true },
	          { name:'Form No', field: 'formNo', enableColumnResizing:true },
	          { name:'Form Issued On', field: 'formIssuedOn', enableColumnResizing:true },
	          { name:'Receipt No', field: 'formReceiptNo', enableColumnResizing:true },
	          { name:'Form Fee', field: 'formFee', enableColumnResizing:true },
	          { name:'Address', field: 'city',  enableColumnResizing:true,visible:false,
	        	  cellTemplate: '<span class="ui-grid-cell-contents">{{row.entity.line1}}, {{row.entity.line2}}, {{row.entity.city}}</span>'  
	          },
	          
	          { name:'Student Contact#', field: 'studentContactNo', enableColumnResizing:true, visible:false },
	          { name:'Father Contact#', field: 'fatherContactNo', enableColumnResizing:true , visible:false },
	          { name:'Mother Contact#', field: 'motherContactNo', enableColumnResizing:true , visible:false },
	          
	          { name:'Status', field: 'status', enableColumnResizing:true, visible:false },
	          
	          { name:'Pre Class', field: 'prevClass', enableColumnResizing:true, visible:false, visible:false   },
	          { name:'Pre Institiue', field: 'prevInstituteName', enableColumnResizing:true, visible:false , visible:false  },
	          { name:'Pre Institiue City', field: 'prevClassCity', enableColumnResizing:true, visible:false , visible:false  },
	          { name:'Pre Board', field: 'prevClassBoard', enableColumnResizing:true, visible:false , visible:false  },
	          { name:'Pre Class Result Status', field: 'prevClassStatus', enableColumnResizing:true, visible:false , visible:false  },
	          { name:'Pre Class %', field: 'preClassPercentage', enableColumnResizing:true, visible:false , visible:false  }
	          
	                  
	        ]
	};
	
	this.init = function(){
		$scope.selectedAcademicYear = $routeParams["academicYearId"];
		$scope.selectedClassId = $routeParams["classId"];
		console.log("aa "+$scope.selectedClassId);
		this.getEnquiriesByClass();
		this.getClasswiseEnquiries();
	}
	
	this.getEnquiriesByClass = function(){
		
		enquiryListService.getEnquiriesByClass($scope.selectedClassId, this.getEnquiriesByClassSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getEnquiriesByClassSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}

	this.changeAcademicSession = function(academicYearId){
		window.location ="#/enquiry/list/classwise/"+academicYearId
	}
	
	this.changeClass = function(selectedClassId){
		window.location ="#/enquiry/list/"+$scope.selectedAcademicYear+"/class/"+selectedClassId;
	}
	
	this.getClasswiseEnquiries = function(){
		
		enquiryListService.getClasswiseEnquiries($scope.selectedAcademicYear, this.getClasswiseEnquiriesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getClasswiseEnquiriesSuccess = function(response, status){
		$scope.classwiseEnquiries = response;
	}
	
	
	
	
});






