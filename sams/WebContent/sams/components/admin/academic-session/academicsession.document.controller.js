
samsApp.controller('academicsession.document.controller', function(academicSessionDocumentService, academicSessionService, uiGridConstants, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.selectedAcademicSession = null;
	$scope.admissionScheme = null;
	$scope.admissionSchemes = null;
	$scope.documents = null;
	$scope.isDuplicateName = false;
	$scope.admissionTypeList = [{id:1, name:"New"},{id:2, name:"Regular"}];
	$scope.document ={
		id : null,	
		name : null,
		admissionTypeId : "1",
		academicSessionId : null
	};
	
	
	this.init = function(){
		$scope.selectedAcademicSession = $routeParams['academicSessionId'];
		this.getDocuments();
	}
	
	this.getDocuments= function(){
		academicSessionDocumentService.getDocuments($scope.selectedAcademicSession, this.getDocumentsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getDocumentsSuccess= function(response, status){
		$scope.documents = response;
	}

	this.newDocument = function(){
		$scope.isDuplicateName = false;
		$("#modalDocument").modal('show');
	}

	this.getDocument = function(documentId){
		$scope.isDuplicateName = false;
		academicSessionDocumentService.getDocument(documentId, this.getDocumentSuccess, serverErrorHandlerService.handleError);
	}
	
	this.getDocumentSuccess = function(response, status){
		$scope.document = response;
		$("#modalDocument").modal('show');
	}
	
	this.saveDocument = function(){
		$scope.document.academicSessionId = $scope.selectedAcademicSession;
		console.log($scope.document);
		if($scope.document.id == null){
			academicSessionDocumentService.addDocument($scope.document, this.saveDocumentSuccess, serverErrorHandlerService.handleError);
		}else{
			academicSessionDocumentService.updateDocument($scope.document, this.saveDocumentSuccess, serverErrorHandlerService.handleError);
		}
	}
	
	this.saveDocumentSuccess = function(response, status){
		
		if(response.status == "DUPLICATE"){
			$scope.isDuplicateName = true;
		}else{
			$("#modalDocument").modal('hide');
			$route.reload();
		}
		
	}
	
	this.getAcademicSessionAdmissionSchemesSuccess = function(response, status){
		$scope.admissionSchemes = response;
		
	}
	
	this.removeAdmissionScheme = function(admissionSchemeId){
		academicSessionAdmissionSchemeService.removeAdmissionScheme(admissionSchemeId, this.removeAdmissionSchemeSuccess, serverErrorHandlerService.handleError);
	}
	
	this.removeAdmissionSchemeSuccess = function(response, status){
		if("ERROR" == response.status){
			$("#modalCanNotDeleteAdmissionScheme").modal('show');
		}else if("OK" == response.status){
			$route.reload();
		}
		
	}
	
	this.showSchemeDetails = function(admissionSchemeId){
		academicSessionAdmissionSchemeService.getAdmissionScheme(admissionSchemeId, this.showSchemeDetailsSuccess, serverErrorHandlerService.handleError);
		$("#modalScehmeDetails").modal('show');
	}
	
	this.showSchemeDetailsSuccess = function(response, status){
		$scope.admissionScheme = response;
	}
	
	this.saveShemeDetails = function(){
		academicSessionAdmissionSchemeService.saveSchemeDetails($scope.admissionScheme, this.saveShemeDetailsSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveShemeDetailsSuccess = function(response, status){
		if(response.status = "OK"){
			$("#modalScehmeDetails").modal('hide');
			$route.reload();
		}
		
	}
	
	this.getAcademicSessionSuccess = function(response, status){
		
		if(response.status == $rootScope.ACADEMIC_SESSION_STATUS_PUBLISHED){
			$scope.gridOptions.columnDefs[3].visible = false;
		}else{
			$scope.gridOptions.columnDefs[3].visible = true;
		}
		
		 $scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		
	}
	
	
});






