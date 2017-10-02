
samsApp.controller('student.personal.information.controller', function(studentService, $scope, $routeParams, blockUI, newAdmissionService, ajaxService){
	
	$scope.studentDetails = {
		
		id : "",
		studentName : "",
		studentId : "",
		currentClass : "",
		currentAcademicYear : "",
		status : "",
		admissionScheme : "",
		busStop : "",
		category : "",
		gender : "",
		email : "",
		mobileNo : "",
		fatherName : "",
		fatherMobileNo : "",
		enrollmentNo : "",
		dob : "",
		city : "",
		photo : "",
			
	}
	
	$scope.parentsDetails = null;
	$scope.gaurdianDetails = null;
	$scope.studentPersonalDetails = null;
	$scope.admissionCategories = null;
	$scope.states = null;
	
	$scope.studentPersonalDetailsBeforeChanges = null;
	$scope.studentParentsDetailsBeforeChanges = null;
	$scope.studentGardianDetailsBeforeChanges = null;
	
	var block_stud_personal_detail_section =blockUI.instances.get('block-stud-personal-detail-section');
	
	this.fetchStudentDetails = function(){
		this.fetchCategories();
		this.fetchStates();
		blockUI.start();
		studentService.getStudentDetails($routeParams['studentId'], this.renderStudentDetails, this.errorFuntion);
	}
	
	this.renderStudentDetails = function(response, status){
		
		$scope.studentDetails = angular.copy(response);
		$scope.parentsDetails = response.parentsDetails;
		$scope.gaurdianDetails = response.gaurdianDetails;
		$scope.studentPersonalDetails = response.studentPersonalInformation;
		if($scope.studentDetails.photo !=null){
			$("#studentImage").attr('src',$scope.studentDetails.photo+"?ver="+Math.random());
		}
		blockUI.stop();
		
	}
	
	this.editStudentPersonalInformation = function(){
		$scope.studentPersonalDetailsBeforeChanges = angular.copy($scope.studentPersonalDetails);
		$("#personalInfoEdit").show();
		$("#personalInfoView").hide();
	}
	
	this.updateStudentPersonalInformation = function(){
		
		$scope.studentPersonalDetails.dob = $("#requiredStudentDOB").val();
		
		var stateId = $("#studentAddState" ).val();
		if(stateId==""){
			$scope.studentPersonalDetails.address.state = "";
		}else{
			$scope.studentPersonalDetails.address.state = $( "#studentAddState option:selected" ).text();
		}
		
		var studentCategory = $("#studentCategory" ).val();
		if(studentCategory == ""){
			$scope.studentPersonalDetails.category = "";
		}else{
			$scope.studentPersonalDetails.category = $( "#studentCategory option:selected" ).text();
		}
		
		if(this.validateStudentPersonalInformationForm()){
			$("#alert-error").show();
			return;
		}
		$("#alert-error").hide();
		block_stud_personal_detail_section.start();
		studentService.updateStudentPersonalInformation($scope.studentPersonalDetails, this.updateStudentPersonalInformationSuccess, this.errorFuntion);
	}
	
	this.updateStudentPersonalInformationSuccess = function(response, status){
		
		if(response.status == "SUCCESS"){
			$scope.studentDetails.studentName = $scope.studentPersonalDetails.name;
			$("#personalInfoEdit").hide();
			$("#personalInfoView").show();
		}else if(response.status == "FAIL"){
			alert("Error Occurs");
			
		}
		block_stud_personal_detail_section.stop();
		
	}
	
	this.cancelStuentPersonalInfoChanges = function(){
		$scope.studentPersonalDetails = angular.copy($scope.studentPersonalDetailsBeforeChanges);
		$("#personalInfoEdit").hide();
		$("#personalInfoView").show();
	}

	this.editStudentParentsInformation = function(){
		$scope.studentParentsDetailsBeforeChanges = angular.copy($scope.parentsDetails);
		$("#parentsInformationEdit").show();
		$("#parentsInformationView").hide();
		
	}
	
	this.updateStudentParentsInformation = function(){
		var stateId = $("#parentsAddState" ).val();
		
		if(stateId==""){
			$scope.parentsDetails.address.state = "";
		}else{
			$scope.parentsDetails.address.state = $( "#parentsAddState option:selected" ).text();
		}
		block_stud_personal_detail_section.start();
		studentService.updateStudentParentsInformation($scope.parentsDetails, this.updateStudentParentsInformationSuccess, this.errorFuntion);
	}
	
	this.updateStudentParentsInformationSuccess = function(response, status){
		
		if(response.status == "SUCCESS"){
			$scope.studentDetails.fatherName = $scope.parentsDetails.fatherName;
			$("#parentsInformationEdit").hide();
			$("#parentsInformationView").show();
		}else if(response.status == "FAIL"){
			alert("Error Occurs");
			
		}
		block_stud_personal_detail_section.stop();
		
		
	}
	
	this.cancelStuentParentsInfoChanges = function(){
		$scope.parentsDetails = angular.copy($scope.studentParentsDetailsBeforeChanges);
		$("#parentsInformationEdit").hide();
		$("#parentsInformationView").show();
	}
	
	this.editStudentGardianInformation = function(){
		$scope.studentGardianDetailsBeforeChanges = angular.copy($scope.gaurdianDetails);
		$("#gaurdianInfoEdit").show();
		$("#gaurdianInfoView").hide();
		
	}
	
	this.updateStudentGaurdianInformation = function(){
		
		var stateId = $("#gaurdianAddState" ).val();
		
		if(stateId==""){
			$scope.gaurdianDetails.address.state = "";
		}else{
			$scope.gaurdianDetails.address.state = $( "#gaurdianAddState option:selected" ).text();
		}
		block_stud_personal_detail_section.start();
		studentService.saveOrUpdateGaurdianDetails($scope.gaurdianDetails, this.updateStudentGaurdianSuccess, this.errorFuntion);
	}
	
	this.updateStudentGaurdianSuccess = function(response, status){
		
		if(response.status == "SUCCESS"){
			$scope.gaurdianDetails.id = response.generatedId;
			$("#gaurdianInfoEdit").hide();
			$("#gaurdianInfoView").show();
		}else if(response.status == "FAIL"){
			alert("Error Occurs");
			
		}
		block_stud_personal_detail_section.stop();
	}
	
	this.cancelStuentGaurdianInfoChanges = function(){
		$scope.gaurdianDetails = angular.copy($scope.studentGardianDetailsBeforeChanges);
		$("#gaurdianInfoEdit").hide();
		$("#gaurdianInfoView").show();
	}
	
	
	
	this.fetchCategories = function(){
		newAdmissionService.getCategories(this.renderCategories, this.errorFuntion);
	}
	
	this.renderCategories = function(response, status){
		$scope.admissionCategories = response;
	}
	this.fetchStates = function(){
		newAdmissionService.getStates(this.renderStates, this.errorFuntion);
	}
	
	this.renderStates = function(response, status){
		$scope.states = response;
		
	}
	
	this.errorFuntion = function(response){
		block_stud_personal_detail_section.stop();
	}
	
	this.validateStudentPersonalInformationForm = function(){

		
		var hasError = false;
		
		if(!this.validateStudentNameRequired()){
			hasError = true;
		}
		
		if(!this.validateGenderRequired()){
			hasError = true;
		}
		
		if(!this.validateDOB()){
			hasError = true;
		}
		
		return hasError;
		
	}
	
	this.validateStudentNameRequired = function(){
		if($scope.studentPersonalDetails.name == ""){
			
			
			return false;
		}else{
			
			return true;
		}
	}
	
	this.validateGenderRequired = function(){
		
		if($scope.studentPersonalDetails.gender == ""){
			
			return false;
		}else{
			
			return true;
		}
	}
	
	this.validateDOB = function(){
		
		if($scope.studentPersonalDetails.dob == ""){
			
			return false;
		}else{
			
			return true;
		}
	}
	
	this.uploadFile = function(){
        var file = $scope.myFile;
        console.dir($scope.myFile);
        if(file != undefined){
        	ajaxService.uploadFileToUrl($scope.studentDetails.id, $scope.studentDetails.studentId, file);
        }
        
    }
	
   
});






