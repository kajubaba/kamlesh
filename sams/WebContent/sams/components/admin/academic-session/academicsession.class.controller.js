
samsApp.controller('academicsession.class.controller', function(academicSessionClassService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.courseIds = [];
	$scope.notAddedCourseForm = {academicSessionId: null,courseIds : null}
	$scope.courseName = null;
	$scope.statusCanBeChange = true;
	$scope.typeArray = [
	               { "id" : 1,"name" : "Year"},
	               { "id" : 2,"name" : "Semester"}
	               ];

	$scope.updateCourseForm = {
			courseYearSettingId: null,
			active: null,
			courseYearTypeId: null,
			intake: null
	};
	
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
	          { name:'Class', field: 'name', enableColumnResizing:true,
	        	 cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admin/academicsession/setup/classfee/{{row.entity.academicYearId}}/{{row.entity.id}}">{{row.entity.name}}</a></div>'},
	          { name:'Type', field: 'type',  enableColumnResizing:true},
	          { name:'Active', field: 'isActive',  enableColumnResizing:true, visible:false},
	          { name:'Intake', field: 'intake',  enableColumnResizing:true, visible:false},
	          { name:'New Fee', field: 'newAdmissionFee',  enableColumnResizing:true},
	          { name:'New Fee Inst?', field: 'newAdmissionFeeInstallmentConfigured',  enableColumnResizing:true},
	          { name:'Regular Fee', field: 'regularAdmissionFee',  enableColumnResizing:true},
	          { name:'Regular Fee Inst?', field: 'regularAdmissionFeeInstallmentConfigured',  enableColumnResizing:true},
	          { name:'', field: 'regularAdmissionFee',enableSorting: false,
	        	  cellTemplate: '<div class="ui-grid-button"><button class="btn btn-info btn-sm" ng-click="grid.appScope.openUpdateCoursePopup(row.entity.id)"><Span class="glyphicon glyphicon-pencil" /> Edit</button></div>'  
	          }
	          
	          
	          
	        ]
	};
	$scope.gridOptions.appScopeProvider = this;
	$scope.notAddedCourseGridOptions = {
			rowHeight: $rootScope.UI_GRID_ROW_HEIGHT,
			enableSorting:  $rootScope.UI_GRID_ENABLE_SORTING,
			enablePaginationControls: false,
			enableGridMenu:  false,
			exporterMenuPdf: false,
			paginationPageSizes: $rootScope.UI_GRID_PAGINATION_PAGE_SIZES,
		    paginationPageSize: $rootScope.UI_GRID_PAGE_SIZE,
		    enableHorizontalScrollbar : $rootScope.UI_GRID_ENABLE_HORIZONTAL_SCROLL_BAR, 
		    enableVerticalScrollbar : false,
		    flatEntityAccess: $rootScope.UI_GRID_FLAT_ENTITY_ACCESS,
		    
			
	        columnDefs: [
	          { name:'Course', field: 'displayName', enableColumnResizing:false},
	          { name:'Duration (Yrs.)', field: 'duration',  enableColumnResizing:false},
	          { name:'Affiliated With', field: 'affiliationAuthority',  enableColumnResizing:false}
	        ]
	};
	
	$scope.notAddedCourseGridOptions.multiSelect = true;
	$scope.notAddedCourseGridOptions.appScopeProvider = this;
	
	$scope.notAddedCourseGridOptions.onRegisterApi = function( gridApi ) {
        
		$scope.gridApi = gridApi;
        
		gridApi.selection.on.rowSelectionChanged($scope,function(row){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnImportCourses").removeClass("disabled");
    			
        	}else{
        		$("#btnImportCourses").addClass("disabled");
        	}

        	$scope.courseIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.courseIds.push(row.id);
    	        });
    		}
          });
        
        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnImportCourses").removeClass("disabled");
        	}else{
        		$("#btnImportCourses").addClass("disabled");
        	}
        	$scope.courseIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.courseIds.push(row.id);
    	        });
    		}
          });
     };
	
	this.init = function(){
		this.getAcademicSessionClassDetails();
	}
	
	
	this.getAcademicSessionClassDetails= function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		academicSessionClassService.getAcademicSessionClassesDetails($scope.selectedAcademicSession, this.getAcademicSessionClassDetailsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAcademicSessionClassDetailsSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	this.openPopup = function(){
		academicSessionClassService.getAcademicSessionNotAddedCourses($scope.selectedAcademicSession, this.openPopupSuccess, serverErrorHandlerService.handleError)
		$("#btnImportCourses").addClass("disabled");
		$("#modalImportCourse").modal('show');
	}
	
	this.openPopupSuccess = function(response, status){
		$scope.notAddedCourseGridOptions.data = response;
		console.log(response);
	}
	
	this.importCourses = function(){
		$scope.notAddedCourseForm.courseIds= $scope.courseIds;
		$scope.notAddedCourseForm.academicSessionId =$scope.selectedAcademicSession;
		academicSessionClassService.addCourseInAcademicSession($scope.notAddedCourseForm, this.importCoursesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.importCoursesSuccess = function(response, status){
		if("OK" == response.status){
			$("#modalImportCourse").modal('hide');
			$route.reload();
		}
		
	}
	
	this.closePopup = function(){
		$("#modalImportCourse").modal('hide');
	}
	
	this.openUpdateCoursePopup = function(id){
		$scope.statusCanBeChange = true;
		academicSessionClassService.getAcademicSessionClassDetails(id, this.openUpdateCoursePopupSuccess, serverErrorHandlerService.handleError);
		$("#modalUpdateCourse").modal('show');
	}
	
	this.openUpdateCoursePopupSuccess = function(response, status){
		
		$scope.courseName = response.name;
		$scope.updateCourseForm.courseYearSettingId= response.id;
		$scope.updateCourseForm.active= response.isActive;
		$scope.updateCourseForm.courseYearTypeId= response.typeId;
		$scope.updateCourseForm.intake= response.intake;
				
	}
	
	this.updateCourseyearDetails = function(){
		academicSessionClassService.updateCourseYearDetails($scope.updateCourseForm, this.updateCourseyearDetailsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.updateCourseyearDetailsSuccess = function(response, status){
		if("OK" == response.status){
			$("#modalUpdateCourse").modal('hide');
			$route.reload();
		}else if("ERROR" == response.status){
			$scope.statusCanBeChange = false;
		}
		
	}
	
	this.closeCoursePopup = function(){
		$("#modalUpdateCourse").modal('hide');
	}
	
});






