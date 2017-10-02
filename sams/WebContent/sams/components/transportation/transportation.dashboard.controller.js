

samsApp.controller('transportation.dashboard.controller', function($scope, academicYearVehicleService, routeService, busStopService, transStudentService){
	
	$scope.academicYearBusCount = "NA";
	$scope.routeCount = "NA";
	$scope.busStopCount = "NA";
	$scope.busAdoptedStudentCount = "NA";
	$scope.seatCapacity = "NA";
	
	this.initialize = function(){
		this.getAcademicYearBusCount();
		this.getRouteCount();
		this.getBusStopCount();
		this.getBusFacilityAdoptedStudentCount();
		this.getCurrentAcademicSessionVehicleSeatCapacity();
	}
	
	this.getBusFacilityAdoptedStudentCount = function(){
		transStudentService.getBusAdoptedAdmissionCount(this.renderBusFacilityAdoptedStudentCount, this.errorFunction);
	}
	
	this.renderBusFacilityAdoptedStudentCount = function(response, status){
		$scope.busAdoptedStudentCount = response.admissions;
	}
	
	this.getAcademicYearBusCount = function(){
		academicYearVehicleService.getVehicleCountInAcademicYear(this.renderAcademicYearBusCount, this.errorFunction);
	}
	
	this.renderAcademicYearBusCount = function(response, status){
		$scope.academicYearBusCount = response.count;
	}
	
	this.getRouteCount = function(){
		routeService.getRouteCount(this.renderRouteCount, this.errorFunction);
	}
	
	this.renderRouteCount = function(response, status){
		$scope.routeCount = response.count;
	}
	
	this.getBusStopCount = function(){
		busStopService.getBusStopCount(this.renderBusStopCount, this.errorFunction);
	}
	
	this.renderBusStopCount = function(response, status){
		$scope.busStopCount = response.count;
	}
	
	this.getCurrentAcademicSessionVehicleSeatCapacity = function(){
		academicYearVehicleService.getVehicleSeatCapacity(this.renderSeatCapacity, this.errorFunction);
	}
	

	this.renderSeatCapacity = function(response, status){
		$scope.seatCapacity = response.count;
	}
	
	this.errorFunction = function(){
		
	}
});






