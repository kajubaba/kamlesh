
samsApp.controller('student.conversation.controller', function(studentService, studentConversationService, commonService, $scope, $route, $location, $routeParams, blockUI, newAdmissionService, ajaxService){
	
	$scope.conversationForm ={
			id : "",
			studentId : "",
			conversation : "",
			conversationType : "",
			conversationWith : "",
			conversationAgenda : "",
			conversationUser : "",
			isSelf : 1
	}
	
	$scope.blankConversationForm =  angular.copy($scope.conversationForm);
	
	$scope.timelineinverted="timeline-inverted";
	$scope.studentConversations = null;
	$scope.conversationTypes = null;
	
    this.showAddConversationForm = function(){
    	this.resetForm();
    	$("#div-list-conversations").hide();
    	$("#div-add-conversation").show();
    	$("#btn-add-conversation").hide();
    	
    }
    
    this.resetForm = function(){
    	//$scope.conversationForm = $scope.blankConversationForm;
    	$scope.conversationForm ={
    			id : "",
    			studentId : "",
    			conversation : "",
    			conversationType : "",
    			conversationWith : "",
    			conversationAgenda : "",
    			conversationUser : "",
    			isSelf : 1
    	}
    	console.log($scope.conversationForm);
    }
    
    this.cancelChanges = function(){
    	$("#div-add-conversation").hide();
    	$("#div-list-conversations").show();
    	$("#btn-add-conversation").show();
    }
    
    
    this.getConversations = function(){
    	studentConversationService.getConversations($routeParams['studentId'], this.renderConversations, this.errorFuntion);
    	this.getConversationTypes();
    }
    
    this.renderConversations = function(response, status){
    	$scope.studentConversations = response;
    }
    
    this.saveConversation = function(){
    	$scope.conversationForm.studentId = $scope.studentDetails.id;
    	if($scope.conversationForm.isSelf == "1"){
    		$scope.conversationForm.isSelf = true;
    	}else{
    		$scope.conversationForm.isSelf = false;
    	}
    	studentConversationService.saveConversation($scope.conversationForm, this.refreshPage, this.errorFuntion);
    }
    
    this.getConversationTypes = function(){
    	studentConversationService.getConversationTypes(this.renderConversationTypes, this.errorFuntion);
    }
    
    this.renderConversationTypes = function(response, status){
    	$scope.conversationTypes = response;
    }
    
    this.loadStudentDetails = function(){
    	$location.path('/admissions/studentdetails/'+$scope.studentDetails.id);
    }
    
    this.refreshPage = function(){
    	$route.reload();
    }
    
    this.editConversation = function(id, conversationType, conversationAgenda, conversationWith, conversation, conversationUser){
    	this.resetForm();
    	$scope.conversationForm.id = id;
    	$scope.conversationForm.conversation = conversation;
    	$scope.conversationForm.conversationType = conversationType;
    	$scope.conversationForm.conversationAgenda = conversationAgenda;
    	$scope.conversationForm.conversationWith = conversationWith;
    	$scope.conversationForm.conversationUser = conversationUser;
    	$scope.conversationForm.isSelf = "0";
    	$("#div-list-conversations").hide();
    	$("#div-add-conversation").show();
    	$("#btn-add-conversation").hide();
    }
    
    this.errorFuntion = function(response){
		
	}
    
});






