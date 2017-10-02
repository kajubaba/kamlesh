
samsApp.service('manageCoScholasticService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/exam/manage/csa";
	
	this.getCSA = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+examPatternId+"/activities";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveSkillIndicator = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/indicator";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteSkillIndicator = function(indicatorId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/indicator/"+indicatorId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteActivitySkill = function(skillId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/skill/"+skillId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveActivity = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/activity";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteActivity = function(activityId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/activity/"+activityId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveSkill = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/activityskill";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveGradeToIndicatorMapping = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/gradeindicator";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getOverallIndicator = function(evaluationTypeId, indicatorId,successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+evaluationTypeId+"/"+indicatorId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










