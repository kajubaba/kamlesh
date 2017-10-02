
samsApp.controller('academicsession.select.wizard.controller', function($scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.setupClasses = false;
	$scope.setupFee = false;
	$scope.setupInstallments = false;
	$scope.setupBusFee = false;
	$scope.setupBusFeeInstallments = false;
	$scope.setupAdmissionSchemes = false;
	
	
	this.init = function(){
		
		var url = $route.current.originalPath;
		
		if(url.indexOf("/setup/classfee") !== -1){
			$scope.setupFee = true;
		}else if(url.indexOf("/setup/classes") !== -1){
			$scope.setupClasses = true;
		}else if(url.indexOf("/setup/installments") !== -1){
			$scope.setupInstallments = true;
		}else if(url.indexOf("/setup/busstop") !== -1 || url.indexOf("/edit/busfee") !== -1 || url.indexOf("/assign/busstop") !== -1){
			$scope.setupBusFee = true;
		}else if(url.indexOf("/setup/busfee/installments") !== -1){
			$scope.setupBusFeeInstallments = true;
		}else if(url.indexOf("/setup/admissionschemes") !== -1 || url.indexOf("/assign/admissionscheme") !== -1){
			$scope.setupAdmissionSchemes = true;
		}else if(url.indexOf("/setup/documents") !== -1){
			$scope.setupDocuments = true;
		} 
	}
	
});






