
samsApp.controller('academicsession.admissionscheme.controller', function(academicSessionAdmissionSchemeService, academicSessionService, uiGridConstants, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.admissionScheme = null;
	$scope.admissionSchemes = null;
	
	$scope.gridOptions = {
			rowHeight: $rootScope.UI_GRID_ROW_HEIGHT,
			enableSorting:  $rootScope.UI_GRID_ENABLE_SORTING,
			enableGridMenu:  $rootScope.UI_GRID_ENABLE_GRID_MENU,
			gridMenuShowHideColumns : false,
			exporterMenuPdf: $rootScope.UI_GRID_EXPORTER_MENU_PDF,
			paginationPageSizes: $rootScope.UI_GRID_PAGINATION_PAGE_SIZES,
		    paginationPageSize: $rootScope.UI_GRID_PAGE_SIZE,
		    enableHorizontalScrollbar : $rootScope.UI_GRID_ENABLE_HORIZONTAL_SCROLL_BAR, 
		    enableVerticalScrollbar : $rootScope.UI_GRID_ENABLE_VERTICAL_SCROLL_BAR,
		    flatEntityAccess: $rootScope.UI_GRID_FLAT_ENTITY_ACCESS,
		    
			
	        columnDefs: [
	          {name:'Admission Scheme', field: 'admissionSchemeName', enableColumnResizing:true},
	          {	  name:'Assigned By', field: 'assignedBy', enableColumnResizing:true},
	          {name:'Assigned On', field: 'assignedOn', enableColumnResizing:true},
	          { name:'Action', field: 'busFee',  enableColumnResizing:true,
        	  cellTemplate: '<div style="text-align:center"><a href="javascript:void(0)" ng-click="grid.appScope.removeAdmissionScheme(row.entity.id)"><span class="glyphicon glyphicon-trash"></span></a> <button class="btn btn-primary btn-sm" ng-click="grid.appScope.showSchemeDetails(row.entity.id)"><span class="glyphicon glyphicon-save"></span> Save</button></div>'
	          }
	        ],
	        onRegisterApi: function(gridApi) {
	            $scope.gridApi = gridApi;
	          }
	};
	
	
	
	$scope.gridOptions.appScopeProvider = this;
	
	
	$scope.gridOptions.columnDefs[3].visible = false;
	
	
	
	this.init = function(){
		this.getAcademicSessionAdmissionSchemes();
		academicSessionService.getAcademicSession($routeParams['academicSessionId'], this.getAcademicSessionSuccess, serverErrorHandlerService.handleError);
	}
	
	
	this.getAcademicSessionAdmissionSchemes= function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		academicSessionAdmissionSchemeService.getAcademicSessionAdmissionSchemes($scope.selectedAcademicSession, this.getAcademicSessionAdmissionSchemesSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getAcademicSessionAdmissionSchemesSuccess = function(response, status){
		//$scope.gridOptions.data = response;
		$scope.admissionSchemes = response;
		
	}
	
	this.removeAdmissionScheme = function(admissionSchemeId){
		academicSessionAdmissionSchemeService.removeAdmissionScheme(admissionSchemeId, this.removeAdmissionSchemeSuccess, serverErrorHandlerService.handleError);
	}
	
	this.removeAdmissionSchemeSuccess = function(response, status){
		if("ERROR" == response.status){
			$("#modalCanNotDeleteAdmissionScheme").modal('show');
		}else if("OK" == response.status){
			$route.reload();
		}
		
	}
	
	this.showSchemeDetails = function(admissionSchemeId){
		academicSessionAdmissionSchemeService.getAdmissionScheme(admissionSchemeId, this.showSchemeDetailsSuccess, serverErrorHandlerService.handleError);
		$("#modalScehmeDetails").modal('show');
	}
	
	this.showSchemeDetailsSuccess = function(response, status){
		$scope.admissionScheme = response;
	}
	
	this.saveShemeDetails = function(){
		academicSessionAdmissionSchemeService.saveSchemeDetails($scope.admissionScheme, this.saveShemeDetailsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveShemeDetailsSuccess = function(response, status){
		if(response.status = "OK"){
			$("#modalScehmeDetails").modal('hide');
			$route.reload();
		}
		
	}
	
	this.getAcademicSessionSuccess = function(response, status){
		
		if(response.status == $rootScope.ACADEMIC_SESSION_STATUS_PUBLISHED){
			$scope.gridOptions.columnDefs[3].visible = false;
		}else{
			$scope.gridOptions.columnDefs[3].visible = true;
		}
		
		 $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		
	}
	
	
});






