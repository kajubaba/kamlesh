
samsApp.controller('academicsession.assign.admissionscheme.controller', function(academicSessionAdmissionSchemeService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.academicSessionAdmissionSchemeForm ={
			academicSessionId : null,
			admissionSchemeIds : []
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
	          { name:'Admission Scheme', field: 'schemeName', enableColumnResizing:true},
	          { name:'Modified By', field: 'modifiedBy', enableColumnResizing:true},
	          { name:'Modified On', field: 'modifiedOn', enableColumnResizing:true},
	          { name:'Created By', field: 'createdBy', enableColumnResizing:true,visible:false},
	          { name:'Created On', field: 'createdOn', enableColumnResizing:true,visible:false}
	        ]
	};
	
	$scope.gridOptions.onRegisterApi = function( gridApi ) {
        
		$scope.gridApi = gridApi;
        
		gridApi.selection.on.rowSelectionChanged($scope,function(row){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnAssignAdmissionScheme").removeClass("disabled");
    			
        	}else{
        		$("#btnAssignAdmissionScheme").addClass("disabled");
        	}

        	$scope.academicSessionAdmissionSchemeForm.admissionSchemeIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.academicSessionAdmissionSchemeForm.admissionSchemeIds.push(row.schemeId);
    	        });
    		}
          });
        
        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnAssignAdmissionScheme").removeClass("disabled");
        	}else{
        		$("#btnAssignAdmissionScheme").addClass("disabled");
        	}
        	$scope.academicSessionAdmissionSchemeForm.admissionSchemeIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.academicSessionAdmissionSchemeForm.admissionSchemeIds.push(row.schemeId);
    	        });
    		}
          });
     };
	
	
	this.getUnAssignedAdmissionSchemes= function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		$scope.academicSessionAdmissionSchemeForm.academicSessionId = $scope.selectedAcademicSession;
		academicSessionAdmissionSchemeService.getUnAssignedAdmissionSchemes($scope.selectedAcademicSession, this.getUnAssignedAdmissionSchemesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getUnAssignedAdmissionSchemesSuccess = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	this.assignAdmissionSchemes = function(){
		academicSessionAdmissionSchemeService.assignAdmissionSchemes($scope.academicSessionAdmissionSchemeForm, this.assignAdmissionSchemesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.assignAdmissionSchemesSuccess = function(response, status){
		$route.reload();
	}
	
	
});






