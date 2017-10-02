

samsApp.controller('sams.admission.studentlist.controller', function(studentListService, $routeParams, $scope, blockUI){
	
	$scope.selectedStudentStatus = null;
	$scope.selectedAcademicYear = null;
	$scope.selectedClassId = null;
	
	
	$scope.gridOptions = {
			rowHeight: 37,
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
	        	  name:'Student ID', 
	        	  field: 'studentId',
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admissions/studentdetails/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'name',enableColumnResizing:true },
	          { name:'Father Name', field: 'guardianName',  enableColumnResizing:true },
	          { name:'Gender', field: 'gender', enableColumnResizing:true },
	          { name:'Class', field: 'currentClass', enableColumnResizing:true },
	          { name:'Section', field: 'classSection', enableColumnResizing:true },
	          { name:'Bus Stop', field: 'busStop', enableColumnResizing:true },
	          { name:'Status', field: 'studentStatus', visible:false, enableColumnResizing:true },
	          { name:'Enrollment', field: 'enrollmentNo', visible:false, enableColumnResizing:true },
	          { name:'Mother Name', field: 'motherName', visible:false, enableColumnResizing:true },
	          { name:'Category', field: 'category', visible:false, enableColumnResizing:true },
	          { name:'Caste', field: 'caste', visible:false, enableColumnResizing:true },
	          { name:'Sub Caste', field: 'subCaste', visible:false, enableColumnResizing:true },
	          { name:'Address', field: 'fullAddress', visible:false, enableColumnResizing:true },
	          { name:'City', field: 'city', visible:false, enableColumnResizing:true },
	          { name:'Student Contact#', field: 'studentContactNo', visible:false, enableColumnResizing:true },
	          { name:'Father Contact#', field: 'fatherContactNo', visible:false, enableColumnResizing:true },
	          { name:'Mother Contact#', field: 'motherContactNo', visible:false, enableColumnResizing:true },
	          { name:'Religion', field: 'relegion', visible:false, enableColumnResizing:true },
	          { name:'Date Of Birth', field: 'dob', visible:false, enableColumnResizing:true },
	          { name:'Birth Place', field: 'birthPlace', visible:false, enableColumnResizing:true },
	          { name:'Family ID', field: 'familyId', visible:false, enableColumnResizing:true },
	          { name:'Samagra ID', field: 'samagraId', visible:false, enableColumnResizing:true },
	          { name:'Aadhar No', field: 'aadharNo', visible:false, enableColumnResizing:true },
	          { name:'Blood Group', field: 'bloodGroup', visible:false, enableColumnResizing:true },
	          { name:'Admission Form No#', field: 'admissionFormNo', visible:false, enableColumnResizing:true },
	          { name:'Registration Date', field: 'admissionRegistrationDate', visible:false, enableColumnResizing:true },
	          { name:'Confirmation Date', field: 'admissionConfirmationDate', visible:false, enableColumnResizing:true },
	          
	          { name:'Bank', field: 'bankName', visible:false, enableColumnResizing:true },
	          { name:'Bank A/C', field: 'bankAcNo', visible:false, enableColumnResizing:true },
	          { name:'Bank IFSC', field: 'bankIfsc', visible:false, enableColumnResizing:true },
	          { name:'Branch', field: 'bankBranch', visible:false, enableColumnResizing:true }
	                  
	        ]
	};
	
	$scope.gridOptionsUS = {
			rowHeight: 37,
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
		   	        	  name:'Student ID', 
		   	        	  field: 'studentId',
		   	        	  enableColumnResizing:true,
		   	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admissions/studentdetails/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'
		   	          },
		   	          { name:'Student Name', field: 'name',enableColumnResizing:true },
		   	          { name:'Father Name', field: 'guardianName',  enableColumnResizing:true },
		   	          { name:'Gender', field: 'gender', enableColumnResizing:true },
		   	          { name:'Class', field: 'currentClass', enableColumnResizing:true },
		   	          { name:'Section', field: 'classSection', enableColumnResizing:true },
		   	          { name:'Admission Scheme', field: 'admissionScheme', enableColumnResizing:true },
		   	          { name:'Bus Stop', field: 'busStop', enableColumnResizing:true },
		   	          { name:'Status', field: 'studentStatus', visible:false, enableColumnResizing:true },
		   	          { name:'Enrollment', field: 'enrollmentNo', visible:false, enableColumnResizing:true },
		   	          { name:'Mother Name', field: 'motherName', visible:false, enableColumnResizing:true },
		   	          { name:'Category', field: 'category', visible:false, enableColumnResizing:true },
		   	          { name:'Caste', field: 'caste', visible:false, enableColumnResizing:true },
		   	          { name:'Sub Caste', field: 'subCaste', visible:false, enableColumnResizing:true },
		   	          { name:'Address', field: 'fullAddress', visible:false, enableColumnResizing:true },
		   	          { name:'City', field: 'city', visible:false, enableColumnResizing:true },
		   	          { name:'Student Contact#', field: 'studentContactNo', visible:false, enableColumnResizing:true },
		   	          { name:'Father Contact#', field: 'fatherContactNo', visible:false, enableColumnResizing:true },
		   	          { name:'Mother Contact#', field: 'motherContactNo', visible:false, enableColumnResizing:true },
		   	          { name:'Religion', field: 'relegion', visible:false, enableColumnResizing:true },
		   	          { name:'Date Of Birth', field: 'dob', visible:false, enableColumnResizing:true },
		   	          { name:'Birth Place', field: 'birthPlace', visible:false, enableColumnResizing:true },
		   	          { name:'Family ID', field: 'familyId', visible:false, enableColumnResizing:true },
		   	          { name:'Samagra ID', field: 'samagraId', visible:false, enableColumnResizing:true },
		   	          { name:'Aadhar No', field: 'aadharNo', visible:false, enableColumnResizing:true },
		   	          { name:'Blood Group', field: 'bloodGroup', visible:false, enableColumnResizing:true },
		   	          { name:'Admission Form No#', field: 'admissionFormNo', visible:false, enableColumnResizing:true },
		   	          { name:'Registration Date', field: 'admissionRegistrationDate', visible:false, enableColumnResizing:true },
		   	          { name:'Confirmation Date', field: 'admissionConfirmationDate', visible:false, enableColumnResizing:true },
		   	          
		   	          { name:'Bank', field: 'bankName', visible:false, enableColumnResizing:true },
		   	          { name:'Bank A/C', field: 'bankAcNo', visible:false, enableColumnResizing:true },
		   	          { name:'Bank IFSC', field: 'bankIfsc', visible:false, enableColumnResizing:true },
		   	          { name:'Branch', field: 'bankBranch', visible:false, enableColumnResizing:true }

	                  
	        ]
	};
	
	this.getStudentListSuccess = function(response, status){
		
		/*var datatable = $('#datatable-student-list').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	    */
		$scope.gridOptions.data = response;
		blockUI.stop();
	}
	

	this.getClasswiseAdmissionsSuccess = function(response, status){
	
		var datatable = $('#datatable-classwise-admissions').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	    blockUI.stop();
	}

	
	
	this.errorFuntion = function(response){
		
	}
	
	
	this.getStudents = function(academicYearId){
		// set selected student status in scope. 
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		studentListService.getStudents($routeParams['studentStatusId'], academicYearId, this.getStudentListSuccess, this.errorFuntion);
	}
	
	this.getStudentsOfClass = function(){
		blockUI.start();
		// set selected student status in scope. 
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		$scope.selectedClassId = null;
		
		if(5 == $scope.selectedStudentStatus){
			$("#studentStatusType").html("Confirm Admissions");
		}else if("new" == $scope.selectedStudentStatus){
			$("#studentStatusType").html("New Admissions");
		}else if(4 == $scope.selectedStudentStatus){
			$("#studentStatusType").html("New Registrations");
		}else if(6 == $scope.selectedStudentStatus){
			$("#studentStatusType").html("Renewed Admissions");
		}else if(1 == $scope.selectedStudentStatus){
			$("#studentStatusType").html("Cancelled Admissions");
		}else if(3 == $scope.selectedStudentStatus){
			$("#studentStatusType").html("Degree Awarded");
		}
		
		studentListService.getClasswiseAdmissions($routeParams['studentStatusId'], $scope.selectedAcademicYear, this.populateClassDropDown, this.errorFuntion);
		studentListService.getStudentsOfClass($routeParams['classId'], $routeParams['studentStatusId'], this.getStudentsOfClassSuccess, this.errorFuntion);
	}
	
	this.populateClassDropDown = function(response){
		
		if(response.length > 0){
			var ul = $('#classList')
			$.each(response, function(i)
			{
			    
				if($routeParams['classId'] == response[i].academicYearClassId){
					$("#selectedClass").html(response[i].name +" (" +response[i].admissionCount +")");
				}
				
				var li = $('<li/>')
			        .appendTo(ul);
			    
			    var a = $('<a/>')
			        .attr("href", "#/admissions/classwise/"+$scope.selectedStudentStatus+"/"+ response[i].academicYearClassId+"/"+$scope.selectedAcademicYear)
			    	.text(response[i].name +" (" +response[i].admissionCount +")")
			        .appendTo(li);
			});
		}
	}
	
	this.getStudentsOfClassSuccess = function(response, status){
		
		/*var datatable = $('#datatable-classwise-students').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();*/
		$scope.gridOptions.data = response;
	    blockUI.stop();
	}
	
	this.getClasswiseAdmissions = function(academicYearId){
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		studentListService.getClasswiseAdmissions($routeParams['studentStatusId'], academicYearId, this.getClasswiseAdmissionsSuccess, this.errorFuntion);
	}
	
	this.getNewStudents = function(){
		// set selected student status in scope. 
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		studentListService.getNewStudents(this.getStudentListSuccess, this.errorFuntion);
	}
	
	this.getActiveClasses = function(){
		blockUI.start();
		studentListService.getActiveClasses(this.getActiveClassesSuccess, this.errorFuntion);
	}
	
	this.getActiveClassesSuccess = function(response, status){
		
		if(response.length > 0){
			var ul = $('#classList')
			$.each(response, function(i)
			{
			    
				if($routeParams['classId'] == response[i].classId){
					$("#selectedClass").html(response[i].className +" (" +response[i].affiliatedTo +")");
				}
				
				var li = $('<li/>')
			        .appendTo(ul);
			    
			    var a = $('<a/>')
			        .attr("href", "#/admissions/classwise/"+$scope.selectedStudentStatus+"/"+ response[i].classId)
			    	.text(response[i].className +" (" +response[i].affiliatedTo +")")
			        .appendTo(li);
			});
		}
		blockUI.stop();
		
	}
	
	this.loadStudents = function(){

		blockUI.start();
		
		var admissionType = $routeParams['studentStatusId'];
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		//selectedAdmissionType = admissionType;
		
		if(5 == admissionType){
			$("#admissionType").html("Confirm Admissions");
		}else if("new" == admissionType){
			$("#admissionType").html("New Admissions");
			this.getNewStudents();
		}else if(4 == admissionType){
			$("#admissionType").html("New Registrations");
		}else if(6 == admissionType){
			$("#admissionType").html("Renewed Admissions");
		}else if(1 == admissionType){
			$("#admissionType").html("Cancelled Admissions");
		}else if(3 == admissionType){
			$("#admissionType").html("Degree Awarded");
		}
		
		this.getStudents($scope.selectedAcademicYear);
		
	}
	
	this.filterClasswiseAdmissionCount = function(academicYearId){
		window.location ="#admissions/classview/"+$scope.selectedStudentStatus+"/"+academicYearId;
	}
	
	this.filterAdmissions = function(academicYearId){
		window.location ="#admissions/studentview/"+$scope.selectedStudentStatus+"/"+academicYearId;
	}
	
	
	
	this.loadClasswiseAdmissions = function(){

		blockUI.start();
		var admissionType = $routeParams['studentStatusId'];
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		
		if(5 == admissionType){
			$("#admissionType").html("Confirm Admissions");
		}else if("new" == admissionType){
			$("#admissionType").html("New Admissions");
			this.getNewStudents();
		}else if(4 == admissionType){
			$("#admissionType").html("New Registrations");
		}else if(6 == admissionType){
			$("#admissionType").html("Renewed Admissions");
		}else if(1 == admissionType){
			$("#admissionType").html("Cancelled Admissions");
		}else if(3 == admissionType){
			$("#admissionType").html("Degree Awarded");
		}
		
		this.getClasswiseAdmissions($scope.selectedAcademicYear);
		
	}
	
	this.getUnderSchemeStudents = function(){
		$scope.selectedStudentStatus = $routeParams['studentStatusId'];
		var admissionType = $routeParams['studentStatusId'];
		$scope.selectedAcademicYear = $routeParams['academicYearId'];
		
		//selectedAdmissionType = admissionType;
		
		if(5 == admissionType){
			$("#admissionType").html("Confirm Admissions");
		}else if("new" == admissionType){
			$("#admissionType").html("New Admissions");
			this.getNewStudents();
		}else if(4 == admissionType){
			$("#admissionType").html("New Registrations");
		}else if(6 == admissionType){
			$("#admissionType").html("Renewed Admissions");
		}else if(1 == admissionType){
			$("#admissionType").html("Cancelled Admissions");
		}else if(3 == admissionType){
			$("#admissionType").html("Degree Awarded");
		}
		studentListService.getUnderSchemeAdmissions($routeParams['studentStatusId'], $scope.selectedAcademicYear, this.displayUnderSchemeStudents, this.errorFuntion);
	}
	
	this.filterUnderSchemeAdmisions = function(academicYearId){
		window.location ="#admission/underscheme/"+$scope.selectedStudentStatus+"/"+academicYearId;
	}
	
	this.displayUnderSchemeStudents = function(response, status){
		/*var datatable = $('#datatable-us-student-list').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);*/
		$scope.gridOptionsUS.data = response; 
	   // datatable.draw();
	}
	
});






