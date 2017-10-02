
samsApp.controller('student.brief.information.controller', function(studentService, $scope, $routeParams, blockUI, newAdmissionService, ajaxService){
	
	
	$scope.studBriefInfo = {};
	
	this.fetchStudentBriefInfo = function(){
		studentService.getStudentBriedInfo($routeParams['studentId'], this.renderStudentBriefInfo, this.errorFuntion);
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
	
	
	
   
});






