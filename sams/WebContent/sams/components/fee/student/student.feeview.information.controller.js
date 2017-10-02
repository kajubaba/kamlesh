
samsApp.controller('student.fee.brief.information.controller', function(studentService, studentFeeServiceService, $scope, $routeParams, blockUI){
	
	
	$scope.studBriefInfo = {};
	$scope.totalDue = 0;
	
	this.fetchStudentBriefInfo = function(){
		this.feeSummary();
		
		if(angular.isUndefined($routeParams['classHistoryId'])){
			studentService.getStudentFeeBriefInfo($routeParams['studentId'], null, this.renderStudentBriefInfo, this.errorFuntion);
		}else{
			
			if($routeParams['classHistoryId'] == "null"){
				studentService.getStudentFeeBriefInfo($routeParams['studentId'], null, this.renderStudentBriefInfo, this.errorFuntion);
			}else{
				studentService.getStudentFeeBriefInfo($routeParams['studentId'], $routeParams['classHistoryId'], this.renderStudentBriefInfo, this.errorFuntion);
			}
			
			
		}
		
		
	}
	
	
	
	this.renderStudentBriefInfo = function(response, status){
		$scope.studBriefInfo = response;
		if($scope.studBriefInfo.photo != null){
			$("#studentImage").attr('src',$scope.studBriefInfo.photo+"?ver="+Math.random());
		}
	}
	
	this.errorFuntion = function(response){
		block_stud_personal_detail_section.stop();
	}
	
	this.feeSummary = function(){
		$scope.studentId =$routeParams['studentId'];
		$scope.classHistoryId = $routeParams['classHistoryId'];
		studentFeeServiceService.totalDueFee($routeParams['studentId'], this.renderFeeHistory, this.errorFunction)
	}
	
	this.renderFeeHistory = function(response, status){
		
		angular.forEach(response, function(classFee){
			$scope.totalDue = $scope.totalDue + (classFee.totalPayable - classFee.paidTotal);   
        });
		
	}
	
   
});






