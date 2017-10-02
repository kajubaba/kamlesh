samsApp.controller('duefee.notice.controller', function($scope, $routeParams, dueFeeNoticeService, blockUI, $location){
	
	/*$scope.generateNoticeForm = {
			studentIds: null,
			dueDateStr : null,
			academicYearId : null,
			noticeHeader : "",
			noticeSubHeader : "",
			noticeName : "",
			noticeMessage : "",
			addressedBy : "",
			showNoticeDate : true,
			showLateFeeMessage : true
	};*/
	
	$scope.noticeInfo = null;
	
	this.getNoticeInfo = function(){
		dueFeeNoticeService.getNoticeInfo(this.getNoticeInfosuccess, this.errorFunction);
	}
	
	this.getNoticeInfosuccess = function(response, status){
		$scope.noticeInfo = response;
		$("#noticeHeader").val($scope.noticeInfo.noticeHeader);
		$("#noticeSubHeader").val($scope.noticeInfo.noticeSubHeader);
		$("#noticeName").val($scope.noticeInfo.noticeName);
		$("#noticeMessage").val($scope.noticeInfo.noticeMessage);
		$("#addressedBy").val($scope.noticeInfo.addressedBy);
		$("#noticeGenerationDate").val($scope.noticeInfo.noticeGenerationDate);
	}
	
	this.openGenerateDueFeeNoticePopup = function(){
		$("#studentIds").val($scope.$parent.studentIds);
		$("#dueDateStr").val($scope.$parent.dueDate);
		$("#academicYearId").val($scope.$parent.selectedAcademicYear);
		this.getNoticeInfo();
		$("#modalGenerateDueFeeNotice").modal('show');
		
	}
	
	this.canceGenerateDueFeeNoticePopup = function(){
		$("#modalGenerateDueFeeNotice").modal('hide');
	}
	
	this.generateDueFeeNotice = function(){
		$("#noticeHeader").val($scope.noticeInfo.noticeHeader);
		$("#noticeSubHeader").val($scope.noticeInfo.noticeSubHeader);
		$("#noticeName").val($scope.noticeInfo.noticeName);
		$("#noticeMessage").val($scope.noticeInfo.noticeMessage);
		$("#addressedBy").val($scope.noticeInfo.addressedBy);
		$("#noticeGenerationDate").val($scope.noticeInfo.noticeGenerationDate);
		$("#noticeType").val($scope.noticeInfo.noticeType);
		
		$("#generateDueFeeNoticeForm").submit();
		$("#modalGenerateDueFeeNotice").modal('hide');
	}
	
	this.generateDueFeeNoticeSuccess = function(response, status){
		console.log("Notice Generated...");
	}
	
	this.errorFunction = function(){
		
	}
	
	this.editNoticeName = function(){
		$("#roNoticeName").hide();
		$("#editNoticeName").show();
	}
	
	this.saveNoticeName = function(){
		
		$("#editNoticeName").hide();
		$("#roNoticeName").show();
	}
	
	this.editAddressedBy = function(){
		$("#roAddressedBy").hide();
		$("#editAddressedBy").show();
	}
	
	this.saveAddressedBy = function(){
		$("#editAddressedBy").hide();
		$("#roAddressedBy").show();
	}
	
	this.editNoiceMessage = function(){
		$("#roNoiceMessage").hide();
		$("#editNoiceMessage").show();
	}
	
	this.saveNoiceMessage = function(){
		$("#editNoiceMessage").hide();
		$("#roNoiceMessage").show();
	}
	
	this.editNoticeHeader = function(){
		$("#roNoticeHeader").hide();
		$("#editNoticeHeader").show();
	}
	
	this.saveNoticeHeader = function(){
		$("#editNoticeHeader").hide();
		$("#roNoticeHeader").show();
	}
	
	this.editSubHeader = function(){
		$("#roSubHeader").hide();
		$("#editSubHeader").show();
	}
	
	this.saveSubHeader = function(){
		$("#editSubHeader").hide();
		$("#roSubHeader").show();
	}
	
	this.editNoticeDate = function(){
		$("#roNoticeDate").hide();
		$("#editNoticeDate").show();
	}
	
	this.saveNoticeDate = function(){
		$("#editNoticeDate").hide();
		$("#roNoticeDate").show();
	}
	
	
	
});






