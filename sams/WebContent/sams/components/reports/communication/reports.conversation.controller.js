
samsApp.controller('reports.conversation.controller', function($scope, $route, $location, $routeParams, uiGridConstants, conversationService, studentConversationService){
	
	$scope.searchForm ={
			frm : "",
			to : "",
			mode : ""
	}
	
	
	$scope.conversations = null;
	$scope.conversationTypes = null;
	
	$scope.gridOptions = {
			rowHeight: 37,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: true,
			paginationPageSizes: [50, 75, 100],
		    paginationPageSize: 50,
		    enableHorizontalScrollbar : 0, 
		    enableVerticalScrollbar : 0,
		    flatEntityAccess: true,
		    
			
	        columnDefs: [
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentName',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admissions/student/{{row.entity.studentDBId}}/conversation">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'studentName',enableColumnResizing:true },
	          { name:'Student Class', field: 'studentClass',  enableColumnResizing:true, visible:false },
	          { name:'User', field: 'conversationUser', enableColumnResizing:true, visible:false },
	          { name:'On', field: 'conversationDate', enableColumnResizing:true, sort: {
	              direction: uiGridConstants.DESC
	             
	          } },
	          { name:'Mode', field: 'conversationType', enableColumnResizing:true, visible:false },
	          { name:'Topic', field: 'conversationAgenda', enableColumnResizing:true },
	          { name:'Conversation', field: 'conversation', enableColumnResizing:true }
	        ]
	};
	
    this.init = function(){
    	
    	$scope.searchForm.frm = $routeParams['from'];
		$scope.searchForm.to = $routeParams['to'];
		$scope.searchForm.mode = $routeParams['mode'];
		
		if($scope.searchForm.frm == "null"){
			$scope.searchForm.frm = "";
		}
		
		if($scope.searchForm.to == "null"){
			$scope.searchForm.to = "";
		}
		
		if($scope.searchForm.mode == "null"){
			$scope.searchForm.mode = "";
		}
   
		if($scope.searchForm.frm =="" && $scope.searchForm.to == ""){
			this.getInitialSearchParams();
			this.getTodaysConversations();
		}
		
		console.log($scope.searchForm);
		
    	this.getConversationModes();
    	this.getConversations();
    }
    
    this.getConversationModes = function(){
    	studentConversationService.getConversationTypes(this.renderConversationModes, this.errorFuntion);
    }
    
    this.renderConversationModes = function(response, status){
    	$scope.conversationTypes = response;
    }
    
    this.getConversations = function(){
    	conversationService.getConversations($scope.searchForm, this.renderConversations, this.errorFuntion);
    }
    
    this.renderConversations = function(response, status){
    	$scope.gridOptions.data = response;
    }
    
    this.getInitialSearchParams = function(){
    	conversationService.initSearchForm(this.renderInitialSearchParams, this.errorFuntion);
    }
    
    this.renderInitialSearchParams = function(response, status){
    	$scope.searchForm = response;
    }
	
    this.search = function(){
    	if($scope.searchForm.mode == null || $scope.searchForm.mode == ""){
    		$scope.searchForm.mode = "null";
    	}
    	
    	if($scope.searchForm.frm == null || $scope.searchForm.frm == ""){
    		$scope.searchForm.frm = "null";
    	}
    	
    	if($scope.searchForm.to == null || $scope.searchForm.to == ""){
    		$scope.searchForm.to = "null";
    	}
    	$location.path('/reports/conversation/'+$scope.searchForm.frm+'/'+$scope.searchForm.to+'/'+$scope.searchForm.mode);
    }
    
    this.getTodaysConversations = function(){
    	conversationService.getTodaysConversations(this.renderConversations, this.errorFuntion);
    }
    
    this.errorFuntion = function(response){
		
	}
    
});






