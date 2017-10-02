samsApp.controller('transportation.student.pickupdroppoint.controller', function($scope, $routeParams, transStudentPickupDropPointService, transStudentService,busStopService){
	
	$scope.students = {};
	$scope.pickupPoints = {};
	$scope.dropPoints = {};
	
	
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
	
	$scope.selectedBusStopId = null;
	
	this.getStudentsWithPickDropPoint = function(){
		
		transStudentPickupDropPointService.getStudentsWithPickDropPoint($routeParams['busStopId'], this.renderStudentsWithPickDropPoint, this.errorFunction);
		transStudentService.getBusStopWiseAdmissionCount(5, this.renderBusStopsInDropDown, this.errorFunction);
		busStopService.getBusStopPickupDropPointsByBusStop($routeParams['busStopId'], this.renderBusStopPickupDropPoints, this.errorFunction);
	}
	
	this.renderStudentsWithPickDropPoint = function(response, status){
		$scope.students = response;
	}
	
	
	this.renderBusStopsInDropDown = function(response, status){
		if(response.length > 0){
			var ul = $('#busStopList')
			$.each(response, function(i)
			{
			    
				if($routeParams['busStopId'] == response[i].busStopId){
					$("#selectedBusStop").html(response[i].busStop +" (" +response[i].admissions +")");
				}
				
				var li = $('<li/>')
			        .appendTo(ul);
			    
			    var a = $('<a/>')
			        .attr("href", "#transportation/students/pickupdroppoint/"+ response[i].busStopId)
			    	.text(response[i].busStop +" (" +response[i].admissions +")")
			        .appendTo(li);
			});
		}
	}
	
	this.renderBusStopPickupDropPoints = function(response, status){
		$scope.selectedBusStopId = response.busStopId;
		$scope.pickupPoints = response.pickupPoints;
		$scope.dropPoints = response.dropPoints;
		
	}
	
	this.updateStudentPickupPoint = function(studentId, academicYearBusStopId, pickupPointId){
		$scope.updatePickupPointInfo.studentId = studentId;
		$scope.updatePickupPointInfo.academicYearBusStopId = academicYearBusStopId;
		$scope.updatePickupPointInfo.pointId = pickupPointId;
		console.log($scope.updatePickupPointInfo);
		transStudentPickupDropPointService.updateStudentPickupPoint($scope.updatePickupPointInfo, this.updateStudentPickupDropPointSuccess, this.errorFunction);
	}
	
	this.updateStudentDropPoint = function(studentId, busStopId, dropPointId){
		$scope.updateDropPointInfo.studentId=studentId;
		$scope.updateDropPointInfo.academicYearBusStopId =busStopId;
		$scope.updateDropPointInfo.pointId =dropPointId;
		console.log($scope.updateDropPointInfo);
		transStudentPickupDropPointService.updateStudentDropPoint($scope.updateDropPointInfo, this.updateStudentPickupDropPointSuccess, this.errorFunction);
		
	}
	
	this.updateStudentPickupDropPointSuccess = function(response, status){
		
	}
	
	this.errorFunction = function(){
		
	}
});