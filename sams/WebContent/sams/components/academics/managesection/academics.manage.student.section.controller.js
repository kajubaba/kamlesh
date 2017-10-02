

samsApp.controller('academics.manage.student.section.controller', function(studentListService, classSectionService, commonService, studentSectionService, academicsessionService, $scope, blockUI, $routeParams, $route, $location){
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	$scope.studentstatusList = null;
	$scope.SectionList = null;
	
	
	$scope.changeSectionForm ={
			studentIds : [],
			academicYearClassId : null,
			newSectionId : null,
			selectedSectionId : null
	}
	
	$scope.filterCrt = {
				auId : null,	
				academicYearId : null,
				classId : null,
				sectionId : null,
				statusId : null
			};
	
	
	$scope.gridOptions = {
			rowHeight: 37,
			enableSorting: true,
			enableGridMenu: true,
			exporterMenuPdf: false,
			paginationPageSizes: [50, 75, 100, 500],
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
	        	  /*cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admissions/studentdetails/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'*/
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/academics/student/scorecard/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'
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
	          { name:'Confirmation Date', field: 'admissionConfirmationDate', visible:false, enableColumnResizing:true }
	                  
	        ]
	};
	
	$scope.gridOptions.multiSelect = true;
	$scope.gridOptions.appScopeProvider = this;
	
	$scope.gridOptions.onRegisterApi = function( gridApi ) {
        
		$scope.gridApi = gridApi;
        
		gridApi.selection.on.rowSelectionChanged($scope,function(row){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnChangeSection").removeClass("disabled");
    			
        	}else{
        		$("#btnChangeSection").addClass("disabled");
        	}

        	$scope.changeSectionForm.studentIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.changeSectionForm.studentIds.push(row.id);
    	        });
    		}
          });
        
        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnChangeSection").removeClass("disabled");
        	}else{
        		$("#btnChangeSection").addClass("disabled");
        	}
        	$scope.changeSectionForm.studentIds = [];
    		if(selectedRows.length > 0){
    			angular.forEach(selectedRows, function(row){
    				$scope.changeSectionForm.studentIds.push(row.id);
    	        });
    		}
          });
     };
	
	this.fetchAffiliationAuthorities = function(){
		commonService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
	}
	
	this.fetchActiveClasses = function(){
		if($scope.filterCrt.auId != null){
			commonService.getActiveClassesByAcademicYear($scope.filterCrt.auId, $scope.filterCrt.academicYearId,this.renderActiveClasses, this.errorFuntion);
		}else{
			$scope.classes = null;
			$scope.SectionList = null;
		}
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
	}
	
	
	
	this.getActiveStudentStatusList = function(){
		commonService.getActiveStudentStatusList(this.populateStudentStatusInDropDown, this.errorFunction);
	}
	
	this.populateStudentStatusInDropDown = function(response, status){
		$scope.studentstatusList = response;
	}

	this.filterStudentsByClass = function(){
		if($scope.filterCrt.classId != null){
			classSectionService.getClassSections($scope.filterCrt.classId, this.populateSectionsInDropDown, this.errorFuntion);
			studentSectionService.getStudents($scope.filterCrt, this.renderStduents, this.errorFuntion);
		}else{
			$scope.SectionList = null;
		}
	}
	
	this.populateSectionsInDropDown = function(response, status){
		$scope.SectionList = response;
	}
	
	this.renderStduents = function(response, status){
		$("#btnChangeSection").addClass("disabled");
		$scope.gridOptions.data = response; 
		$("#modalChangeSection").modal('hide');
		
	}
	
	this.errorFuntion = function(response){
		
	}
	
	this.init = function(){
		$scope.filterCrt.academicYearId = $routeParams['academicYearId'];
		this.fetchAffiliationAuthorities();
		
	}
	
	this.openChangeSectionModal = function(){
		$scope.changeSectionForm.newSectionId = null;
		$("#modalChangeSection").modal('show');
	}
	
	this.closeChangeSectionModal = function(){
		$("#modalChangeSection").modal('hide');
	}
	
	this.changeStduentClassSection = function(){
		$scope.changeSectionForm.academicYearClassId= $scope.filterCrt.classId;
		$scope.changeSectionForm.selectedSectionId= $scope.filterCrt.sectionId;
		studentSectionService.changeStduentClassSection($scope.changeSectionForm, this.renderStduents, this.errorFuntion);
	}
	
	this.toggleUpdateSectionButton = function(){
		if($scope.changeSectionForm.newSectionId != null){
			$("#btnUpdateSection").removeClass("disabled");
		}else{
			$("#btnUpdateSection").addClass("disabled");
		}
	}
	
	this.changeAcademicYear = function(academicYearId){
		$location.path("/academics/mgstudsctn/"+academicYearId);
	}
	
	
	
});






