samsApp.controller('academics.student.birthday.controller', function(studentBirthdayService, $scope, blockUI, $routeParams, $route, $location){

	$scope.todaysBirthdays = {};
	
	
	$scope.gridOptions = {
			rowHeight: 65,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: false,
			paginationPageSizes: [50, 75, 100],
		    paginationPageSize: 50,
		    enableHorizontalScrollbar : 0, 
		    enableVerticalScrollbar : 0,
		    flatEntityAccess: true,
		    enableSelectAll: true,
			
	        columnDefs: [
	          
			{ 
				  name:'Image', 
				  field: 'imageURL',
				  enableColumnResizing:true,
				  cellTemplate: '<img class="img-circle img-thumbnail img-responsive profile-img" src="{{row.entity.imageURL}}" style="width: 60px;height: 60px">'
			},
	          
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentId',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admissions/studentdetails/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'studentName',enableColumnResizing:true },
	          { name:'Born On', field: 'bornOn',  enableColumnResizing:true },
	          { name:'Class', field: 'currentClass', enableColumnResizing:true },
	          { name:'Gender', field: 'gender', enableColumnResizing:true },
	          { name:'Father Name', field: 'fatherName',  enableColumnResizing:true, visible:false },
	          { name:'Admission Type', field: 'admissionType',  enableColumnResizing:true , visible:false },
	          { name:'Admission Status', field: 'status',  enableColumnResizing:true , visible:false },
	          { name:'Student ContactNo#1', field: 'studentContactNo1',  enableColumnResizing:true , visible:false },
	          { name:'Student Contact No#2', field: 'studentContactNo2',  enableColumnResizing:true , visible:false },
	          { name:'Father Contact No#1', field: 'fatherContactNo1',  enableColumnResizing:true , visible:false },
	          { name:'Father Contact No#2', field: 'fatherContactNo2',  enableColumnResizing:true , visible:false },
	          { name:'Mother Contact No#1', field: 'motherContactNo1',  enableColumnResizing:true , visible:false },
	          { name:'Mother Contact No#2', field: 'motherContactNo2',  enableColumnResizing:true , visible:false },
	          { name:'Address', field: 'fullAddress',  enableColumnResizing:true , visible:false },
	          { name:'City', field: 'city',  enableColumnResizing:true , visible:false }
	          
	                  
	        ]
	};
	
	
	
	this.todaysBirthday = function(){
		studentBirthdayService.getTodaysBirthdays(this.todaysBirthdaySuccess, this.errorFuntion);
	}

	this.todaysBirthdaySuccess = function(response, status){
		$scope.todaysBirthdays = response;
	}
	
	this.todaysBirthdayWithDetail = function(){
		studentBirthdayService.getTodaysBirthdays(this.todaysBirthdayWithDetailSuccess, this.errorFuntion);
	}
	
	this.todaysBirthdayWithDetailSuccess = function(response, status){
		$scope.gridOptions.data = response; 
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}
	

});






