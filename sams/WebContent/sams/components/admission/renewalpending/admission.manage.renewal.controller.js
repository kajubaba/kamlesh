
samsApp.controller('admission.manage.renewal.controller', function($scope, $routeParams, $route, admissionRenewalService, commonService, blockUI){
	
	
	$scope.admissionsToBeRenewed = null;
	$scope.affiliationAuthorities = null;
	$scope.classes = null;
	
	$scope.renewForm ={
		affiliationAuthorityId : null,
		academicYearClassId : null,
		copyBusStop : false,
		copyAdmissionScheme : false,
		studentIds : null
	}
	
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
		    enableRowSelection: true,
		    enableSelectAll: true,
			
	        columnDefs: [
	          { 
	        	  name:'Student ID', 
	        	  field: 'studentId',
	        	  width: 200,
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admissions/studentdetails/{{row.entity.id}}">{{row.entity.studentId}}</a></div>'
	          },
	          { name:'Student Name', field: 'name',enableColumnResizing:true },
	          { name:'Father Name', field: 'guardianName',  enableColumnResizing:true },
	          { name:'Gender', field: 'gender', enableColumnResizing:true },
	          { name:'Class', field: 'currentClass', enableColumnResizing:true },
	          { name:'Section', field: 'classSection', enableColumnResizing:true },
	          { name:'Bus Stop', field: 'busStop', enableColumnResizing:true },
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
	          { name:'Confirmation Date', field: 'admissionConfirmationDate', visible:false, enableColumnResizing:true },
	          { 
	        	  name:' ',
	        	  width: 80,
	        	  enableSorting: false ,
	        	  enableColumnResizing:true,
	        	  cellTemplate: '<div class="ui-grid-cell-contents" ><a href="#/admission/renew/{{row.entity.id}}"><i class="fa fa-retweet" title="Renew Admission"></i></a></div>'
	        	  
	          }
	                  
	        ],
	        
	};
	$scope.gridOptions.multiSelect = true;
	$scope.gridOptions.appScopeProvider = this;
	
	$scope.gridOptions.onRegisterApi = function( gridApi ) {
        
		$scope.gridApi = gridApi;
        
		gridApi.selection.on.rowSelectionChanged($scope,function(row){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnOpenAdmnRenewPopup").removeClass("disabled");
        	}else{
        		$("#btnOpenAdmnRenewPopup").addClass("disabled");
        	}

          });
        
        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
        	var selectedRows = $scope.gridApi.selection.getSelectedRows();
        	if(selectedRows.length > 0){
        		$("#btnOpenAdmnRenewPopup").removeClass("disabled");
        	}else{
        		$("#btnOpenAdmnRenewPopup").addClass("disabled");
        	}
          });
     };
    
	this.fetchAdmissionToBeRenewed = function(){
		admissionRenewalService.fetchAdmissionToBeRenewed(this.renderAllAdmissionsToBeRenewed, this.errorFuntion);
	}
	
	this.fetchClassWiseAdmissionCountToBeRenewed = function(){
		admissionRenewalService.fetchClassWiseAdmissionsToBeRenewed(this.renderClassWiseAdmissionsToBeRenewed, this.errorFuntion);
	}
	
	this.fetchClassAdmissionsToBeRenewed = function(){
		admissionRenewalService.fetchClassWiseAdmissionsToBeRenewed(this.renderClasswiseAdmissionCountinDropDown, this.errorFuntion);
		admissionRenewalService.fetchClassAdmissionsToBeRenewed($routeParams['academicYearClassId'], this.renderAdmissionsToBeRenewed, this.errorFuntion);
	}
	
	this.renderAdmissionsToBeRenewed = function(response, status){
		$scope.gridOptions.data = response;
	}
	
	this.renderAllAdmissionsToBeRenewed = function(response, status){
		//$scope.admissionsToBeRenewed   = response;
		$scope.gridOptions.data = response;
	}
	
	this.renderClassWiseAdmissionsToBeRenewed = function(response, status){
		var datatable = $('#datatable-classwise-pending_renewal').dataTable().api();
		datatable.clear();
	    datatable.rows.add(response);
	    datatable.draw();
	    
	}
	
	this.renderClasswiseAdmissionCountinDropDown = function(response, status){
		if(response.length > 0){
			var ul = $('#classList')
			$.each(response, function(i)
			{
			    
				if($routeParams['academicYearClassId'] == response[i].academicYearClassId){
					$("#selectedClass").html(response[i].name +" (" +response[i].admissionCount +")");
				}else{
					var li = $('<li/>')
			        .appendTo(ul);
			    
			    var a = $('<a/>')
			        .attr("href", "#/admissions/toberenewed/class/"+response[i].academicYearClassId)
			    	.text(response[i].name +" (" +response[i].admissionCount +")")
			        .appendTo(li);
				}
			});
		}
	}
	
	this.openAdmissionRenewalPopup = function(){
		this.fetchAffiliationAuthorities();
		$("#modalAdmissionRenew").modal('show');
	}
	
	this.canceRenewSelectedAdmissions = function(){
		$("#modalAdmissionRenew").modal('hide');
	}
	
	this.renewSelectedAdmissions = function(){
		var selectedRows = $scope.gridApi.selection.getSelectedRows();
		if(selectedRows.length > 0){
			var studentIds = [];
			angular.forEach(selectedRows, function(row){
				studentIds.push(row.id);
	        });
			$scope.renewForm.studentIds = studentIds;
		}
		admissionRenewalService.bulkRenew($scope.renewForm, this.bulkAdmissionRenewSuccess, this.errorFuntion);
	}
	
	this.bulkAdmissionRenewSuccess = function(reponse, status){
		$("#modalAdmissionRenew").modal('hide');
		$route.reload();
	}
	
	this.fetchAffiliationAuthorities = function(){
		commonService.getAffiliationAuthorities(this.renderAffiliationAuthorities, this.errorFuntion);
	}
	
	this.renderAffiliationAuthorities = function(response, status){
		$scope.affiliationAuthorities = response;
	}
	
	this.fetchActiveClasses = function(){
		
		
		if($scope.renewForm.affiliationAuthorityId != null){
			commonService.getActiveClasses($scope.renewForm.affiliationAuthorityId, this.renderActiveClasses, this.errorFuntion);
		}else{
			$scope.classes = [];
		}
		this.enableDisblaeRenewalButton();
		
	}
	
	this.renderActiveClasses = function(response, status){
		$scope.classes = response;
		
		
	}
	
	this.onClassSelect = function(){
		this.enableDisblaeRenewalButton();
	}
	
	this.enableDisblaeRenewalButton = function(){
		if($scope.renewForm.academicYearClassId == null){
			$("#button-renew-admission").addClass("disabled");
		}else{
			$("#button-renew-admission").removeClass("disabled");
		}
	}
	
	this.errorFuntion = function(response){
		
	}
	
});






