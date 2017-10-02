

samsApp.controller('sams.admission.dashboard.controller', function($scope, admissionDashboardService, blockUI){
	
	var block_cnfadmn = blockUI.instances.get('block-cnfadmn');
	var block_newadmn = blockUI.instances.get('block-newadmn');
	var block_diffadmns = blockUI.instances.get('block-diffadmns');
	$scope.amChartOptions = {
			data: [{
	            year: 2005,
	            income: 23.5,
	            expenses: 18.1
	        }, {
	            year: 2006,
	            income: 26.2,
	            expenses: 22.8
	        }, {
	            year: 2007,
	            income: 30.1,
	            expenses: 23.9
	        }, {
	            year: 2008,
	            income: 29.5,
	            expenses: 25.1
	        }, {
	            year: 2009,
	            income: 24.6,
	            expenses: 25
	        }],
	        type: "serial",
	        
	        categoryField: "year",
	        rotate: true,
	        pathToImages: 'https://cdnjs.cloudflare.com/ajax/libs/amcharts/3.13.0/images/',
	        legend: {
	            enabled: true
	        },
	        chartScrollbar: {
	            enabled: true,
	        },
	        categoryAxis: {
	            gridPosition: "start",
	            parseDates: false
	        },
	        valueAxes: [{
	            position: "top",
	            title: "Million USD"
	        }],
	        graphs: [{
	            type: "column",
	            title: "Income",
	            valueField: "income",
	            fillAlphas: 1,
	        }]
	}
	$scope.busNotAdoptedCount = "NA";
	
	this.getConfirmAdmissionsCountSuccess = function(response, status){
		$("#confirmAdmissionCount1").html(response.admissions);
		block_cnfadmn.stop();
	}
	
	this.getNewAdmissionsCountSuccess = function(response, status){
		$("#newAdmissionCount").html(response.admissions);
		block_newadmn.stop();
	}
	
	this.getCancelAdmissionsCountSuccess = function(response, status){
		$("#cancelAdmissionCount").html(response.admissions);
		block_diffadmns.stop();
	}
	
	
	this.getNewRegistrationsCountSuccess = function(response, status){
		$("#newRegistrationsCount").html(response.admissions);
		block_diffadmns.stop();
	}
	
	this.getRenewRegistrationsCountSuccess = function(response, status){
		$("#renewRegistrationsCount").html(response.admissions);
		block_diffadmns.stop();
	}
	
	this.getDegreeAwardedAdmissionCountSuccess = function(response, status){
		$("#degreeAwardedAdmissionCount").html(response.admissions);
		block_diffadmns.stop();
	}
	
	this.getgetBusAdoptedAdmissionCountSuccess = function(response, status){
		$("#busAdoptedAdmissionCount").html(response.admissions);
		block_diffadmns.stop();
	}
	
	this.getBusNotAdoptedAdmissionCountSuccess = function(response, status){
		$scope.busNotAdoptedCount = response.admissions;
		block_diffadmns.stop();
	}

	
	this.renderPendingForRenewalAdmissionCount = function(response, status){
		$("#pendingForRenewalAdmissionCount").html(response.admissions);
		block_diffadmns.stop();
	}
	
	this.errorFuntion = function(response){
		
	}
	
	
	this.getPendingForRenewalAdmissionCount = function(){
		block_diffadmns.start();
		admissionDashboardService.getPendingForRenewalAdmissionCount(this.renderPendingForRenewalAdmissionCount, this.errorFuntion);
	}
	
	this.getBusAdoptedAdmissionCount = function(){
		block_diffadmns.start();
		admissionDashboardService.getBusAdoptedAdmissionCount(this.getgetBusAdoptedAdmissionCountSuccess, this.errorFuntion);
	}
	
	this.getBusNotAdoptedAdmissionCount = function(){
		block_diffadmns.start();
		admissionDashboardService.getBusNotAdoptedAdmissionCount(this.getBusNotAdoptedAdmissionCountSuccess, this.errorFuntion);
	}
	
	this.getConfirmAdmissionsCount = function(){
		block_cnfadmn.start();
		admissionDashboardService.getConfirmAdmissions(this.getConfirmAdmissionsCountSuccess, this.errorFuntion);
	}
	
	this.getnewAdmissionsCount = function(){
		block_newadmn.start();
		admissionDashboardService.getNewAdmissions(this.getNewAdmissionsCountSuccess, this.errorFuntion);
	}
	
	this.getCancelAdmissionsCount = function(){
		block_diffadmns.start();
		admissionDashboardService.getCancelAdmissions(this.getCancelAdmissionsCountSuccess, this.errorFuntion);
	}
	
	this.getNewRegistrationsCount = function(){
		block_diffadmns.start();
		admissionDashboardService.getNewRegistrations(this.getNewRegistrationsCountSuccess, this.errorFuntion);
	}
	
	this.getRenewRegistrationsCount = function(){
		block_diffadmns.start();
		admissionDashboardService.getRenewRegistrations(this.getRenewRegistrationsCountSuccess, this.errorFuntion);
	}
	
	this.getDegreeAwardedAdmissionCount = function(){
		block_diffadmns.start();
		admissionDashboardService.getDegreeAwardedAdmissionCount(this.getDegreeAwardedAdmissionCountSuccess, this.errorFuntion);
	}
	
	this.getUnderSchemeAdmissions = function(){
		admissionDashboardService.getUnderSchemeAdmissions(this.getUnderSchemeAdmissionsSuccess, this.errorFuntion);
	}
	
	this.getUnderSchemeAdmissionsSuccess = function(response, status){
		$("#schemeAdmissionCount").html(response.admissions);
	}
	this.getChartData = function(){
		admissionDashboardService.getChartData(5, this.getChartDataSuccess, this.errorFuntion);
	}
	
	this.getChartDataSuccess = function(response, status){
		$scope.amChartOptions.data = response;
		console.log($scope.amChartOptions.data);
	}
	//this.getChartData();
	this.getConfirmAdmissionsCount();
	this.getnewAdmissionsCount();
	this.getCancelAdmissionsCount();
	this.getNewRegistrationsCount();
	this.getRenewRegistrationsCount();
	this.getDegreeAwardedAdmissionCount();
	this.getBusAdoptedAdmissionCount();
	this.getPendingForRenewalAdmissionCount();
	this.getUnderSchemeAdmissions();
	this.getBusNotAdoptedAdmissionCount();
});






