
/* 
 *   Server Resource : com.narendra.sams.web.restws.fee.PaidFeeRestController 
 *   Resource URI : /ws/fee/paid
 *   
 */

samsApp.service('paidFeeService', function(ajaxService) {

	var serverResourceURL = _appContextPath+"/ws/fee/paid";
	
	
	this.getRecentFeeTransactions = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = serverResourceURL+"/recentTransactions";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getTodaysPaidFee = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = serverResourceURL+"/today";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getPaidFeeAsOnToday = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = serverResourceURL+"/asontoday";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getFeeTransactions = function(academicYearId, fromDate, toDate, successCallBackFunction, errorCallBackFunction){
		
		var resourceURL = "";
		
		if(academicYearId != null){
			resourceURL = serverResourceURL+"/allTransactions?academicYearId="+academicYearId+"&fromDate="+fromDate+"&toDate="+toDate;
		}else{
			resourceURL =serverResourceURL+"/allTransactions?fromDate="+fromDate+"&toDate="+toDate;
		}
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getTodaysFeeTransactions = function(successCallBackFunction, errorCallBackFunction){
		console.log("Getting todays transactions from server...");
		return ajaxService.get(serverResourceURL+"/todaysTransactions", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getPaidFeeHeadwise = function(academicYearId, fromDate, toDate, successCallBackFunction, errorCallBackFunction){
		var resourceURL = "";
		if(academicYearId != null){
			resourceURL = serverResourceURL+"/headwise?academicYearId="+academicYearId+"&fromDate="+fromDate+"&toDate="+toDate;
		}else{
			resourceURL = serverResourceURL+"/headwise?fromDate="+fromDate+"&toDate="+toDate;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getPaidFeeHeadwiseDetails = function(academicYearId, feeHeadId, fromDate, toDate, successCallBackFunction, errorCallBackFunction){
		var resourceURL = "";
		if(academicYearId != null){
			resourceURL = serverResourceURL+"/headwise/"+feeHeadId+"/students?academicYearId="+academicYearId+"&fromDate="+fromDate+"&toDate="+toDate;
		}else{
			resourceURL = serverResourceURL+"/headwise/"+feeHeadId+"/students?fromDate="+fromDate+"&toDate="+toDate;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getPaidFeeInHeadDateWise = function(academicYearId, feeHeadId, fromDate, toDate, successCallBackFunction, errorCallBackFunction){
		var resourceURL = "";
		if(academicYearId != null){
			resourceURL = serverResourceURL+"/in-head/datewise/"+feeHeadId+"?academicYearId="+academicYearId+"&fromDate="+fromDate+"&toDate="+toDate;
		}else{
			resourceURL = serverResourceURL+"/in-head/datewise/"+feeHeadId+"?fromDate="+fromDate+"&toDate="+toDate;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getPaidFeeClasswise = function(academicYearId, fromDate, toDate, successCallBackFunction, errorCallBackFunction){
		var resourceURL = "";
		if(academicYearId != null){
			resourceURL = serverResourceURL+"/classwise?academicYearId="+academicYearId+"&fromDate="+fromDate+"&toDate="+toDate;
		}else{
			resourceURL = serverResourceURL+"/classwise?fromDate="+fromDate+"&toDate="+toDate;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getPaidFeeDetail = function(feeTransactionId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = serverResourceURL+"/transaction/"+feeTransactionId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
})










