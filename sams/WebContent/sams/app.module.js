'use strict'

var samsApp = angular.module("samsApp", ['ngRoute', 'blockUI','datatables', 'dndLists', 'ui.bootstrap', 'LocalStorageModule', 'ui.grid', 'ui.grid.exporter', 'ui.grid.selection','ui.grid.pagination', 'ui.grid.moveColumns','ui.grid.resizeColumns', 'amChartsDirective']);

samsApp.config(function (localStorageServiceProvider) {
	  localStorageServiceProvider
	    .setPrefix('samsApp');
	});

/*var permissionList;


samsApp.run(function(authService) {  
	
	console.log("2 :"+permissionList);
	authService.setPermissions(permissionList);
});

angular.element(document).ready(function() {  
	  $.get('/sams-web/api/permissions', function(data) {
		permissionList = data;
	    
		console.log("1 :"+permissionList);
		
		//angular.bootstrap(document, ['samsApp']);
	  });
});*/



samsApp.directive('fileModel', ['$parse', 'ajaxService', function ($parse, ajaxService) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                    console.log(element[0].files[0]);
                    scope.$digest;
                });
            });
        }
    };
}]);


samsApp.directive('ngOnFinishRender', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit(attr.broadcastEventName ? attr.broadcastEventName : 'ngRepeatFinished');
                });
            }
        }
    };
});

samsApp.directive('transactionDetail', function () {
	return {
        restrict: 'E',
        scope: false,
        templateUrl: 'sams/components/fee/paidfee/transaction_detail_popup.html'
      }
});

samsApp.directive('noticePopup', function () {
	return {
        restrict: 'E',
        scope: false,
        templateUrl: 'sams/components/fee/reports/due_fee_notice_popup.html'
      }
});

samsApp.directive('newEnquiry', function () {
	return {
        restrict: 'E',
        scope: false,
        templateUrl: 'sams/components/enquiry/icon_new_enquiry.html'
      }
});

samsApp.directive('newAdmission', function () {
	return {
        restrict: 'E',
        scope: false,
        templateUrl: 'sams/components/admission/icon_new_admission.html'
      }
});


samsApp.directive('allowInteger', function () {
    return {
      require: 'ngModel',
      restrict: 'A',
      link: function (scope, element, attr, ctrl) {
        function inputValue(val) {
          if (val) {
            var digits = val.replace(/[^0-9]/g, '');

            if (digits !== val) {
              ctrl.$setViewValue(digits);
              ctrl.$render();
            }
            return parseInt(digits,10);
          }
          return undefined;
        }            
        ctrl.$parsers.push(inputValue);
      }
    };
});

samsApp.directive("maxValue", [function() {
    return {
        restrict: "A",
        link: function(scope, elem, attrs) {
            var limit = parseInt(attrs.maxValue);
            angular.element(elem).on("keypress", function(e) {
                //if (this.value.length == limit) e.preventDefault();
            	console.log("Limit to "+limit);
            	console.log("Value "+this.value);
            	/*if (parseInt(elem.value) >  limit){
            		//this.value = 0;
            		elem.value="0";
            	}*/
            });
        }
    }
}]);

samsApp.controller('mainAppCtrl', function($scope, $location, authService, $rootScope, localStorageService) {  

  this.init = function(){
	  
	  $rootScope.SUCCESS_MSG_DURATION = 2000;
	  
	  $rootScope.UI_GRID_PAGE_SIZE = 100;
	  $rootScope.UI_GRID_ENABLE_VERTICAL_SCROLL_BAR = 1;
	  $rootScope.UI_GRID_ENABLE_HORIZONTAL_SCROLL_BAR = 0;
	  $rootScope.UI_GRID_PAGINATION_PAGE_SIZES = [50, 100, 500, 1000];
	  $rootScope.UI_GRID_ROW_HEIGHT = 37;
	  $rootScope.UI_GRID_ENABLE_SORTING = true;
	  $rootScope.UI_GRID_ENABLE_GRID_MENU = true;
	  $rootScope.UI_GRID_EXPORTER_MENU_PDF = true;
	  $rootScope.UI_GRID_FLAT_ENTITY_ACCESS = true;
	  $rootScope.UI_GRID_ENABLE_COLUMN_MENU = false;
	  
	  $rootScope.ACADEMIC_SESSION_STATUS_PUBLISHED = "Published";
	  
	  
	  if(angular.isUndefined($rootScope.permissionList) && localStorageService.get("permissions") != null){
		  $rootScope.permissionList = localStorageService.get("permissions");
	  }else{
		  authService.fetchPermissionsFromServer();
	  }
  }

  this.logout = function(){
	 
	  localStorageService.set("permissions", null);
	
  }
  
  $scope.$on('$routeChangeStart', function(scope, next, current) {
	
	var permission = next.$$route.permission;
   
    if(_.isString(permission)){
    	 var permissionArr = permission.trim().split("|");
    	 var permissionFound = false;
    	 $.each(permissionArr, function( index, value ) {
    	  	  var hasPermission = authService.hasPermission(value.trim());  
    	  	  if(hasPermission){
    	  		  permissionFound = true;
    	  	  }
    	    });
    	    
    	    if(!permissionFound) {
    	    	console.log("Do not have permissions...");
    	    	$location.path('/unauthorized');
    	    }
    }
    
   
    
    
  });
  
  
});