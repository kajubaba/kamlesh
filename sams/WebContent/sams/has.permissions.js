angular.module('samsApp').directive('hasPermission', function(authService) {  
  return {
    link: function(scope, element, attrs) {
      if(!_.isString(attrs.hasPermission)) {
        throw 'hasPermission value must be a string'
      }
      var value = attrs.hasPermission.trim();
    
        
      var hasPermission = authService.hasPermission(value);
      if(!hasPermission){
    	  element.remove();
      }
    	  
    }
  };
});

angular.module('samsApp').directive('hasAnyPermission', function(authService) {  
	  return {
	    link: function(scope, element, attrs) {
	      if(!_.isString(attrs.hasAnyPermission)) {
	        throw 'hasPermission value must be a string'
	      }
	      var value = attrs.hasAnyPermission.trim();
	     
	      var permissionArr = value.split("|");
	      
	      var permissionFound = false;
	      
	      $.each(permissionArr, function( index, value ) {
	    	  var hasPermission = authService.hasPermission(value.trim());  
	    	  if(hasPermission){
	    		  permissionFound = true;
	    	  }
	      });
	      
	      if(!permissionFound){
	    	  element.remove();
	      }
	      
	     
	    }
	  };
});