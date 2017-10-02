samsApp.controller('transportation.route.plan.controller', function($scope, $route, $filter, $routeParams, $location, routeService, routePlanService, busStopService, transStudentPickupDropPointService, academicYearVehicleService){
	
	$scope.routeInfo = null;
	$scope.flipRouteInfo = {
			id : null,
			name : null,
			from : null,
			to : null,
			type: null,
			plannedStudents: null,
			forceCopy: "true"
	};
	
	
	$scope.routeBusStops = null;
	$scope.busStopsToBeAssigned = null;
	$scope.selectedBusStops = null;
	$scope.studentsToBeAssigned = null;
	$scope.selectedStduentsForRouteAssignment = null;
	$scope.selectedRouteBusStopsId = null;
	$scope.selectedRouteBusStopsIndex = null;
	$scope.busStopStudents = null;
	$scope.routeVehicle = null;
	$scope.academicSessionVehicles = null;
	
	$scope.status = {
	        isOpen: new Array(5)
	};
	
	$scope.pickDropPoints = null;
	
	$scope.updatePickupPointInfo ={
			academicYearBusStopId : "",
			studentId : "",
			pointId : ""
	};
	
	$scope.updateDropPointInfo ={
			academicYearBusStopId : "",
			studentId : "",
			pointId : ""
	};
	
	for (var i = 0; i < $scope.status.isOpen.length; i++) {
        $scope.status.isOpen[i] = (i === 0);
    }
	
	this.viewRouteDetail = function(){
		routeService.getRouteDetail($routeParams['routeId'], this.displayRouteDetail, this.errorFunction);
		routePlanService.getRouteBusStops($routeParams['routeId'], this.renderRouteBusStops, this.errorFunction);
		routePlanService.getVehicleOnRoute($routeParams['routeId'], this.displayRouteVehicleDetail, this.errorFunction)
		this.getAcademicSessionVehicles();
	}
	
	this.openImportBusStopModal = function(){
		$("#modalImportBusStops").modal('show');
		routePlanService.getBusStopsToBeAssignedToRoute($scope.routeInfo.id, this.displayUnAssignedBusStops, this.errorFunction);
	}
	
	this.closeImportBusStopModal = function(){
		$("#modalImportBusStops").modal('hide');
	}
	
	this.openNotAssignedStudnetsModal = function(busRouteId,index, e){
		
		$scope.selectedStduentsForRouteAssignment = null;
		
		if (e) {
		      e.preventDefault();
		      e.stopPropagation();
		}
		$scope.selectedRouteBusStopsIndex = index;
		$scope.selectedRouteBusStopsId = busRouteId;
		$("#modalNotAssignedStudents").modal('show');
		routePlanService.getStudentsforRouteAssignment(busRouteId, this.displayStudentsForRouteAssignment, this.errorFunction);
		
	}
	
	this.displayStudentsForRouteAssignment = function(response, status){
		$scope.studentsToBeAssigned = response;
	}
	
	this.closeNotAssignedStudnetsModal = function(){
		$("#modalNotAssignedStudents").modal('hide');
	}
	
	this.selectedBusStop = function () {
		$scope.selectedBusStops  = $filter('filter')($scope.busStopsToBeAssigned, {checked: true});
	}
	
	this.selectStudentForRouteAssignment = function () {
		$scope.selectedStduentsForRouteAssignment  = $filter('filter')($scope.studentsToBeAssigned, {checked: true});
	}
	
	this.assignStudentToRoute = function(){
		
		if($scope.selectedStduentsForRouteAssignment == null){
			$("#modalNotAssignedStudents").modal('hide');
		}else{
			var ids = [];
			
			angular.forEach($scope.selectedStduentsForRouteAssignment, function(student){
				ids.push(student.studentDBId);
	        })
			routePlanService.assignStudentToRoute($scope.selectedRouteBusStopsId, ids, this.assignStudentToRouteSuccess, this.errorFunction);
		}
		
	}
	
	this.assignStudentToRouteSuccess = function(response, status){
		$scope.busStopStudents = response.busStopStudents;
		$scope.routeBusStops[$scope.selectedRouteBusStopsIndex].assignedStudentCount = response.busStopInfo.assignedStudentCount;
		$scope.routeBusStops[$scope.selectedRouteBusStopsIndex].unAssignedStudenCount = response.busStopInfo.unAssignedStudenCount;
		console.log(response.busStopStudents);
		$("#modalNotAssignedStudents").modal('hide');
		
	}
	
	this.import = function(){
		
		if($scope.selectedBusStops == null){
			$("#modalImportBusStops").modal('hide');
		}else{
			var ids = [];
			
			angular.forEach($scope.selectedBusStops, function(busStop){
				ids.push(busStop.id);
	        })
			routePlanService.importBusStopsInRoute($scope.routeInfo.id, ids, this.importSuccess, this.errorFunction);
		}
		
	}
	
	this.importSuccess = function(response, status){
		$("#modalImportBusStops").modal('hide');
		$route.reload();
	}
	
	this.displayRouteDetail = function(response, status){
		$scope.routeInfo = response;
	}
	
	this.renderRouteBusStops = function(response, status){
		$scope.routeBusStops = response;
	}
	
	this.displayUnAssignedBusStops = function(response, status){
		$scope.busStopsToBeAssigned = response;
	}
	
	this.fetchRouteBusStopStudents = function(routeBusStopId, busStopId){
		routePlanService.getStudentsOfRouteBusStop(routeBusStopId, this.displayBusStopStudents, this.errorFunction);
		busStopService.getBusStopPickupDropPointsByBusStop(busStopId, this.renderPickupDropPoints, this.errorFunction);
	}
	
	this.displayBusStopStudents = function(response, status){
		$scope.busStopStudents = response;
		console.log(response);
	}
	
	this.removeRouteStudent = function(routeStudentId, routeBusStopId, index){
		$scope.selectedRouteBusStopsIndex = index;
		routePlanService.removeRouteStudent(routeStudentId, routeBusStopId, this.removeRouteStudentSuccess, this.errorFunction);
	}
	
	this.removeRouteStudentSuccess = function(response, status){
		$scope.busStopStudents = response.busStopStudents;
		$scope.routeBusStops[$scope.selectedRouteBusStopsIndex].assignedStudentCount = response.busStopInfo.assignedStudentCount;
		$scope.routeBusStops[$scope.selectedRouteBusStopsIndex].unAssignedStudenCount = response.busStopInfo.unAssignedStudenCount;
	}
	
	this.removeRouteBusStop = function(routeBusStopId, e){
		if (e) {
		      e.preventDefault();
		      e.stopPropagation();
		}
		routePlanService.removeRouteBusStop(routeBusStopId, this.removeRouteBusStopSuccess, this.errorFunction);
	}
	
	this.removeRouteBusStopSuccess = function(response, status){
		$scope.routeBusStops = response;
	}
	
	this.displayRouteVehicleDetail = function(response, status){
		$scope.routeVehicle = response;
	}
	
	this.renderPickupDropPoints = function(response, status){
		$scope.pickDropPoints = response;
	}
	
	this.updateStudentPickupPoint = function(studentId, academicYearBusStopId, pickupPointId){
		$scope.updatePickupPointInfo.studentId = studentId;
		$scope.updatePickupPointInfo.academicYearBusStopId = academicYearBusStopId;
		$scope.updatePickupPointInfo.pointId = pickupPointId;
		console.log($scope.updatePickupPointInfo);
		transStudentPickupDropPointService.updateStudentPickupPoint($scope.updatePickupPointInfo, this.updateStudentPickupDropPointSuccess, this.errorFunction);
	}
	
	this.updateStudentDropPoint = function(studentId, academicYearBusStopId, dropPointId){
		$scope.updateDropPointInfo.studentId=studentId;
		$scope.updateDropPointInfo.academicYearBusStopId =academicYearBusStopId;
		$scope.updateDropPointInfo.pointId =dropPointId;
		console.log($scope.updateDropPointInfo);
		transStudentPickupDropPointService.updateStudentDropPoint($scope.updateDropPointInfo, this.updateStudentPickupDropPointSuccess, this.errorFunction);
		
	}
	
	this.updateStudentPickupDropPointSuccess = function(response, status){
			
	}
	
	this.createFlipRoute = function(){
		console.log($scope.flipRouteInfo);
		routeService.createFlipRoute($scope.flipRouteInfo, this.createFlipRouteSuccess, this.errorFunction);
	}
	
	this.createFlipRouteSuccess = function(response, status){
		$("#modalCreateFlipRoute").modal('hide');
		$location.path('/transportation/route/plan/'+response.generatedId);
	}
	
	/* Flip route methods - start */
	
	this.openModalToCreateFlipRoute = function(){
		$scope.flipRouteInfo.id = $scope.routeInfo.id;
		$scope.flipRouteInfo.name = "Copy "+$scope.routeInfo.to+" To "+$scope.routeInfo.from;
		$scope.flipRouteInfo.from = $scope.routeInfo.to;
		$scope.flipRouteInfo.to = $scope.routeInfo.from;
		if($scope.routeInfo.type == "Arrival"){
			$scope.flipRouteInfo.type = "Departure";
		}else{
			$scope.flipRouteInfo.type = "Arrival";
		}
		
		$scope.flipRouteInfo.plannedStudents = $scope.routeInfo.plannedStudents;
		$("#modalCreateFlipRoute").modal('show');
	}
	
	this.closeModalToCreateFlipRoute = function(){
		$("#modalCreateFlipRoute").modal('hide');
	}
	
	
	
	/* Flip route methods - end */
	
	this.getAcademicSessionVehicles = function(){
		academicYearVehicleService.getVehiclesInAcademicYear(this.getAcademicSessionVehiclesSuccess, this.errorFunction);
	}
	
	this.getAcademicSessionVehiclesSuccess = function(response, status){
		$scope.academicSessionVehicles = response;
	}
	
	this.updateRouteVehicle = function(){
		routePlanService.updateRouteVehicle($scope.routeInfo.id, $scope.routeVehicle.vehicleId, this.updateRouteVehicleSuccess, this.errorFunction);
	}
	
	this.updateRouteVehicleSuccess = function(response, status){
		
	}
	
	this.errorFunction = function(){
		
	}
});