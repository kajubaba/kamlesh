
samsApp.service('ajaxService', function($http, $location, blockUI) {

	//$scope.studentPic = null;
	
	this.get = function(resourceURL, successFunction, errorCallBackFunction){
		 
		$http({ method: 'GET', url: resourceURL })
			
			.success(function (response, status, headers, config){
				successFunction(response, status);
			 })
			
			 .error(function (response, status) {
				 blockUI.stop();
				 console.log("111");
				 if(status == 403 || status == 401) {
					 console.log("222");
					 $location.path('/unauthorized');
			     }
				 errorCallBackFunction(response);
			})
				
	}//end if this.get
	
	this.remove = function(resourceURL, successFunction, errorCallBackFunction){
		 
		$http({ method: 'DELETE', url: resourceURL })
			
			.success(function (response, status, headers, config){
				successFunction(response, status);
			 })
			
			 .error(function (response, status) {
				 blockUI.stop();
				 console.log("111");
				 if(status == 403 || status == 401) {
					 console.log("222");
					 $location.path('/unauthorized');
			     }
				 errorCallBackFunction(response);
			})
				
	}
	
	this.post = function(data, resourceURL, successFunction, errorCallBackFunction){
		 
		$http({ method: 'POST', url: resourceURL, data: data})
			
			.success(function (response, status, headers, config){
				successFunction(response, status);
			 })
			
			 .error(function (response, status) {
				 blockUI.stop();
				 console.log("111");
				 if(status == 403 || status == 401) {
					 console.log("222");
					 $location.path('/unauthorized');
			     }
				 errorCallBackFunction(response);
			})
				
	}//end if this.get
	
	this.put = function(data, resourceURL, successFunction, errorCallBackFunction){
		 
		$http({ method: 'PUT', url: resourceURL, data: data})
			
			.success(function (response, status, headers, config){
				successFunction(response, status);
			 })
			
			 .error(function (response, status) {
				 blockUI.stop();
				 console.log("111");
				 if(status == 403 || status == 401) {
					 console.log("222");
					 $location.path('/unauthorized');
			     }
				 errorCallBackFunction(response);
			})
				
	}
	
	
	this.uploadFileToUrl = function(id, studentId, file){
       var uploadURL = _appContextPath+"/ws/studentpic/upload?id="+id+"&studentID="+studentId;
		
		var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadURL, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(response){
        	$("#studentImage").attr('src',response.imageName+"?ver="+Math.random());
        })
        .error(function(){
        	alert("Error");
        });
    }
	
	
	this.postWithFileInput = function(data, resourceURL, successFunction, errorCallBackFunction){
		
		console.log(data.documentFile);
		var fd = new FormData();
		fd.append('documentCategoryId', data.documentCategoryId);
		fd.append('documentId', data.documentId);
		fd.append('studentId', data.studentId);
		fd.append('documentFile', data.documentFile);
		fd.append('comments', data.comments);
		fd.append('documentName', data.documentName);
		
        $http.post(resourceURL, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(response){
        	successFunction(response, status);
        })
        .error(function(){
        	 blockUI.stop();
			 console.log("111");
			 if(status == 403 || status == 401) {
				 console.log("222");
				 $location.path('/unauthorized');
		     }
			 errorCallBackFunction(response);
        });
	    }
	
	
	
	
})










