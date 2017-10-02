
samsApp.controller('student.controller', function(studentService, studentConversationService, commonService, $scope, $location, $routeParams, blockUI, newAdmissionService, ajaxService){
	
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
	
/*	$scope.conversationForm ={
			id : "",
			studentId : "",
			conversation : "",
			conversationType : "",
			conversationWith : "",
			conversationAgenda : "",
			conversationUser : "",
			conversationDate : ""
	}*/
	
	$scope.studentConversations = null;
	
	$scope.parentsDetails = null;
	$scope.gaurdianDetails = null;
	$scope.studentPersonalDetails = null;
	$scope.admissionCategories = null;
	$scope.states = null;
	$scope.bankDetails = null;
	$scope.banks = null;
	$scope.studentDocuments = null;
	$scope.studentDocumetForm = {};
	
	$scope.studentPersonalDetailsBeforeChanges = null;
	$scope.studentParentsDetailsBeforeChanges = null;
	$scope.studentGardianDetailsBeforeChanges = null;
	$scope.bankDetailsBeforeChanges = null;
	
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
	
    this.editBankDetails = function(){
    	this.getActiveBanks();
    	$scope.bankDetailsBeforeChanges = angular.copy($scope.bankDetails);
    	$("#bankDetailsEdit").show();
		$("#bankDetailView").hide();
	}
    
    
    
    this.cancelUpdateBankDetails = function(){
    	$scope.bankDetails = angular.copy($scope.bankDetailsBeforeChanges);
    	$("#bankDetailsEdit").hide();
		$("#bankDetailView").show();
	}
   
    this.getBankDetails = function(){
    	studentService.getStudentBankDetails($scope.studentDetails.id, this.displayBankDetails, this.errorFuntion);
    }
    
    this.getStudentDocuments = function(){
    	studentService.getStudentDocuments($scope.studentDetails.id, this.displayDocuments, this.errorFuntion);
    }
    
    this.displayBankDetails = function(response, status){
    	$scope.bankDetails = response;
    }
    
    this.displayDocuments = function(response, status){
    	$scope.studentDocuments = response;
    }
    
    this.updateStudentBankDetails = function(){
    	console.log($scope.bankDetails);
    	studentService.updateStudentBankDetails($scope.bankDetails, this.updateStudentBankDetailsSuccess, this.errorFuntion);
    	if($scope.bankDetails.bankId == null){
			$scope.bankDetails.bankName = "";
		}else{
			$scope.bankDetails.bankName = $( "#studentBankName option:selected" ).text();
		}
    	$("#bankDetailsEdit").hide();
		$("#bankDetailView").show();
    }
    
    this.updateStudentBankDetailsSuccess = function(response, status){
    	$scope.bankDetails.bankDetailId = response.generatedId;
    }
    
    this.getActiveBanks = function(){
    	commonService.getActiveBanks(this.displayActiveBanks, this.errorFuntion);
    }
    
    this.displayActiveBanks = function(response, status){
    	$scope.banks = response;
    }
    
    this.openPopupToUploadDocument = function(documentId, uploadeDocumentId, documentCategory, documentName, comments){
    	
    	$scope.studentDocumetForm = {};
    	$scope.studentDocumetForm.documentCategory = documentCategory;
    	$scope.studentDocumetForm.documentName = documentName;
    	$scope.studentDocumetForm.comments = comments;
    	$scope.studentDocumetForm.documentFile = null;
    	$("#studentBrowseDocument").val("");
    	
    	if("null"!=documentId && null!=documentId){
    		$scope.studentDocumetForm.documentCategoryId =documentId
    	}else{
    		$scope.studentDocumetForm.documentCategoryId ="";
    	}
    	
    	if("null"!=uploadeDocumentId && null!=uploadeDocumentId){
    		$scope.studentDocumetForm.documentId =uploadeDocumentId;
    	}else{
    		$scope.studentDocumetForm.documentId = "";
    	}
    	
    	
    	$scope.studentDocumetForm.studentId = $scope.studentDetails.id;
    	//this.validateDocumentUploadForm();
    	$("#modalUploadStudDoc").modal('show');
    }
    
    this.openPopupToUploadNewDocument = function(){
    	$scope.studentDocumetForm = {};
    	$scope.studentDocumetForm.documentCategoryId ="";
    	$scope.studentDocumetForm.documentId = "";
    	$("#studentBrowseDocument").val("");
    	$scope.studentDocumetForm.studentId = $scope.studentDetails.id;
    	$("#modalUploadStudDoc").modal('show');
    }
    
    this.closePopupToUploadDocument = function(){
    	$("#modalUploadStudDoc").modal('hide');
    }
    
    
    this.uploadstudentDocument = function(){
    	if(!this.validateDocumentUploadForm()){
    		
    	}else{
    		studentService.uploadStudentDocument($scope.studentDocumetForm, this.uploadStudentDocumentSuccess, this.errorFuntion);
    	}
    	
    }
    
    this.uploadStudentDocumentSuccess = function(response, status){
    	$scope.studentDocuments = response;
    	$("#modalUploadStudDoc").modal('hide');
    }
    
    this.validateDocumentUploadForm = function(){
    	
    	if($scope.studentDocumetForm.documentName == null || $scope.studentDocumetForm.documentFile == null){
    		
    		$("#error_upload_document").show();
    		return false;
    	}else{
    		$("#error_upload_document").hide();
    		return true;
    	}
    }
    
   
    
       
    this.loadConversationPage = function(){
    	$location.path('/admissions/student/'+$scope.studentDetails.id+'/conversation');
    }
    
    
});






