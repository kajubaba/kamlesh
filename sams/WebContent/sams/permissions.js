samsApp.service('authService', function($rootScope, localStorageService) {
	var permissionList;
	
	this.fetchPermissionsFromServer = function() {
		  $.get(_appContextPath+'/api/permissions', function(data) {
			$rootScope.permissionList = data;
			localStorageService.set("permissions", data);
			$rootScope.$broadcast('permissionsChanged');
		  });
	  }
	
	
	this.getPermissions = function() {
	      return $rootScope.permissionList;
	      
	 }
	
	this.setPermissions = function(permissions) {
		$rootScope.permissionList = permissions;
	      $rootScope.$broadcast('permissionsChanged');
	}
	
	
	this.hasPermission = function (permission) {
	      permission = permission.trim();
	      
	      var found = false;
	     
	      angular.forEach($rootScope.permissionList, function(userPermission) {
	    	  if(userPermission == permission){
	    		found=true;  
	    	  }
		  });
	      return found;
	  }
	  
	
	
	  /*return {
		  fetchPermissionsFromServer: function() {
			  $.get(_appContextPath+'/api/permissions', function(data) {
				  permissionList = data;
					$rootScope.$broadcast('permissionsChanged');
				  });
		  }, 
		  getPermissions: function() {
		      return permissionList;
		      
		   },
		  setPermissions: function(permissions) {
		      permissionList = permissions;
		      $rootScope.$broadcast('permissionsChanged');
	    },
	    hasPermission: function (permission) {
	      permission = permission.trim();
	      
	      var found = false;
	      console.log("permissionList: "+permissionList);
	      angular.forEach(permissionList, function(userPermission) {
	    	  if(userPermission == permission){
	    		found=true;  
	    	  }
		  });
	      return found;
	    }
	  };*/
})

/*

angular.module('samsApp').factory('authService', function ($rootScope) {
  var permissionList;
  return {
	  fetchPermissionsFromServer: function() {
		  $.get(_appContextPath+'/api/permissions', function(data) {
			  permissionList = data;
				$rootScope.$broadcast('permissionsChanged');
			  });
	  }, 
	  getPermissions: function() {
	      return permissionList;
	      
	   },
	  setPermissions: function(permissions) {
	      permissionList = permissions;
	      $rootScope.$broadcast('permissionsChanged');
    },
    hasPermission: function (permission) {
      permission = permission.trim();
      
      var found = false;
      console.log("permissionList: "+permissionList);
      angular.forEach(permissionList, function(userPermission) {
    	  if(userPermission == permission){
    		found=true;  
    	  }
	  });
      return found;
    }
  };*/


