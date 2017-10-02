samsApp.controller('transportation.route.controller', function($scope, $route, $rootScope, $routeParams, $timeout, routeService, serverErrorHandlerService){
	
	$scope.routeFormData = {
		id : "",
		name : "",
		from : "",
		to : "",
		type : "",
		plannedStudents : ""
	};
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= true;
	$scope.routes = null;
	
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
	        	  name:'Route Name', 
	        	  field: 'name',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/transportation/route/{{row.entity.id}}">{{row.entity.name}}</a></div>'
	          },
	          
	          { name:'From', field: 'from',  enableColumnResizing:true},
	          { name:'To', field: 'to',  enableColumnResizing:true},
	          { name:'Route Type', field: 'type',  enableColumnResizing:true},
	          { name:'Planned Students', field: 'plannedStudents',  enableColumnResizing:true, visible:false},
	          { name:'Bus On Route', field: 'busName',  enableColumnResizing:true},
	          { name:'Bus Stops', field: 'busstops',  enableColumnResizing:true},
	          { name:'Students', field: 'studentCount',  enableColumnResizing:true},
	          { name:'', field: 'regularAdmissionFee',enableSorting: false,
	        	  cellTemplate: '<div class="ui-grid-button"><a class="btn btn-info btn-sm" href="#/transportation/route/plan/{{row.entity.id}}"><Span class="glyphicon glyphicon-pencil" /> Manage</a></div>'  
	          }
	          
	        ]
	};
	
	
	this.getAllRoutes = function(){
		routeService.getAllRoutes(this.renderAllRoutes, serverErrorHandlerService.handleError);
	}
	
	this.renderAllRoutes = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	this.getRoute = function(){
    	if($routeParams['routeId'] != "new"){
    		routeService.getRouteDetail($routeParams['routeId'], this.displayRouteDetail, serverErrorHandlerService.handleError);
    		$scope.isActionNew= false;
    	}else{
    		$scope.isActionNew= true;
    	}
	}
	
	this.displayRouteDetail = function(response, status){
		$scope.routeFormData = response;
	}
	
	this.saveRoute = function(){
		if($scope.isActionNew == true){
			routeService.addRoute($scope.routeFormData, this.saveRouteSuccess, serverErrorHandlerService.handleError);
		}else if($scope.isActionNew == false){
			routeService.updateRoute($scope.routeFormData, this.saveRouteSuccess, serverErrorHandlerService.handleError);
		}
	}
	
	this.saveRouteSuccess = function(response, status){
		if("OK" == response.status){
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.routeFormData.id = response.generatedId;
				$scope.isActionNew = false;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}
	}
	
	
	
	
});