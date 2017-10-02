samsApp.controller('transportation.busstop.controller', function($scope, $rootScope, $routeParams, $route, busStopService){
	
	$scope.pickupPoints = {};
	$scope.dropPoints = {};
	$scope.busStopName = "";
	
	$scope.newPointFormData = {
			name : "",
			ladmark     : "",
			busStopId : "",
			type : "Pickup",
			createReversePoint : ""
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
	          { 
	        	  name:'Bus Stop', 
	        	  field: 'name'
	          },
	          
	          { name:'Distance(Km.)', field: 'distance',  enableColumnResizing:true},
	          { name:'Pickup Points', field: 'pickupPointCount',  enableColumnResizing:true},
	          { name:'Drop Points', field: 'dropPointCount',  enableColumnResizing:true},
	          { name:'', field: 'regularAdmissionFee',enableSorting: false,
	        	  cellTemplate: '<div class="ui-grid-button"><a class="btn btn-info btn-sm" href="#/transportation/busstop/pickupdroppoints/{{row.entity.id}}"><Span class="glyphicon glyphicon-pencil" /> Manage</a></div>'  
	          }
	          
	        ]
	};
	
	this.getAllBusStops = function(){
		busStopService.getAllBusStops(this.renderAllBusStops, this.errorFunction);
	}
	
	this.renderAllBusStops = function(response, status){
		$scope.gridOptions.data = response;
		var datatable = $('#dt-busstop-list').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	}
	
	this.getBusStopPickupDropPoints = function(){
		busStopService.getBusStopPickupDropPoints($routeParams['busStopId'], this.renderBusStopPickupDropPoints, this.errorFunction);
	}
	
	this.renderBusStopPickupDropPoints = function(response, status){
		$scope.newPointFormData.busStopId = response.busStopId;
		$scope.busStopName = response.busStopName;
		$scope.pickupPoints = response.pickupPoints;
		$scope.dropPoints = response.dropPoints;
		
		if($scope.pickupPoints.length == 0){
			$("#noPickupPoint").show();
		}else{
			$("#noPickupPoint").hide();
		}
		
		if($scope.dropPoints.length == 0){
			$("#noDropPoint").show();
		}else{
			$("#noDropPoint").hide();
		}
		
	}
	
	this.openAddPickupDropPointModal = function(){
		$("#modalAddPickupPoint").modal('show');
	}
	
	this.closeAddPickupDropPointModal = function(){
		$("#modalAddPickupPoint").modal('hide');
	}
	
	
	
	this.createNewPoint = function(){
		console.log($scope.newPointFormData);
		busStopService.addNewBusStopPoint($scope.newPointFormData, this.createNewPointsuccess, this.errorFunction);
	}
	
	this.createNewPointsuccess = function(response, status){
		if(response.status == "SUCCESS"){
			$("#modalAddPickupPoint").modal('hide');
			//alert("Hi...");
			//busStopService.getBusStopPickupDropPoints($scope.newPointFormData.busStopId, this.renderBusStopPickupDropPoints, this.errorFunction);
			$route.reload();
		}
		
	}
	
	this.errorFunction = function(){
		
	}
});