

samsApp.controller('academics.manage.exam.controller', function(manageCoScholasticService, evaluationTypeService, examPatternService, $scope, blockUI, $routeParams, $route, $location){
	
	
	$scope.selectedExamPatternId= null;
	$scope.csa= null;
	
	$scope.activityName = null;
	$scope.isAdd = true;
	$scope.isUpdate = false;
	$scope.modalTile = null;
	$scope.examPattern = null;
	$scope.gradeIndicatorListForm ={
			skillGradeIndicatorForms:null
			
	}
	
	
	$scope.activityForm ={
			id: null,
			name: null,
			displayName: null,
			displayOrder : null,
			parentActivityId: null,
			evaluationTypeId:null,
			isSkillBasedAssessment : null
	}
	
	$scope.skillForm ={
			id: null,
			name: null,
			displayName: null,
			displayOrder : null,
			activityId: null,
			doNotDisplayOnScorecard : null
			
	}
	
	$scope.skillIndicatorForm ={
			skillId: null,
			id: null,
			name: null,
			displayName: null,
			displayOrder : null,
	}
	
	
	
	
	this.getCSA = function(){
		$scope.selectedExamPatternId= $routeParams['examPatternId'];
		this.getExamPattern($scope.selectedExamPatternId);
		manageCoScholasticService.getCSA($scope.selectedExamPatternId, this.displayCSA, this.errorFuntion);
	}
	
	this.displayCSA = function(response, status){
		$scope.csa= response;
	}
	
	/* Skill Indicator - START  */
	
	this.openAddIndicatorModal = function(skillId, skillName){
		$scope.modalTile = "Add New Indicator in "+skillName+" Skill";
		$scope.skillIndicatorForm.skillId = skillId;
		$("#modalAddUpdateIndicator").modal('show');
	}
	
	this.openUpdateIndicatorModal = function(indicatorId, name, displayName, displayOrder){
		$scope.modalTile = "Update Skill Indicator";
		$scope.skillIndicatorForm.id =indicatorId;
		$scope.skillIndicatorForm.name =name;
		$scope.skillIndicatorForm.displayName =displayName;
		$scope.skillIndicatorForm.displayOrder =displayOrder;
		
		
		$("#modalAddUpdateIndicator").modal('show');
	}
	
	this.saveSkillIndicator = function(){
		manageCoScholasticService.saveSkillIndicator($scope.skillIndicatorForm, this.saveSkillIndicatorSuccess, this.errorFuntion);
	}
	
	this.saveSkillIndicatorSuccess = function(response, status){
		$("#modalAddUpdateIndicator").modal('hide');
		$route.reload();
	}
	
	this.deleteSkillIndicator = function(indicatorId){
		manageCoScholasticService.deleteSkillIndicator(indicatorId, this.deleteSkillIndicatorSuccess, this.errorFuntion );
	}
	
	this.deleteSkillIndicatorSuccess = function(response, status){
		
	}
	
	/* Skill Indicator - END  */
	
	this.deleteActivitySkill = function(skillId){
		manageCoScholasticService.deleteActivitySkill(skillId, this.deleteActivitySkillSuccess, this.errorFuntion );
	}
	
	this.deleteActivitySkillSuccess = function(response, status){
		
	}
	
	this.closeAddUpdateIndicatorModal = function(){
		this.resetIndicatorForm();
		$("#modalAddUpdateIndicator").modal('hide');
	}
	
	this.resetActivityForm = function(){
		$scope.activityForm.id =null;
		$scope.activityForm.name =null;
		$scope.activityForm.displayName =null;
		$scope.activityForm.parentActivityId =null;
		$scope.activityForm.evaluationTypeId =null;
				
	}
	
	this.openActivityModalToAddNewActivity = function(){
		$scope.modalTile = "Add New Co-Scholastic Area";
		$scope.activityForm.evaluationTypeId = $scope.csa.evaluationTypeId;
		$("#modalAddUpdateActivity").modal('show');
	}
	
	this.openAddActivityModal = function(activityId){
		$scope.activityForm.parentActivityId = activityId;
		$scope.activityForm.evaluationTypeId = $scope.csa.evaluationTypeId;
		$("#modalAddUpdateActivity").modal('show');
	}
	
	this.openUpdateActivityModal = function(activityId, name, displayName, displayOrder, isSkillBasedAssessment){
		$scope.modalTile = "Update Co-Scholastic Area";
		$scope.activityForm.id = activityId;
		$scope.activityForm.name = name;
		$scope.activityForm.displayName = displayName;
		$scope.activityForm.displayOrder = displayOrder;
		$scope.activityForm.isSkillBasedAssessment = isSkillBasedAssessment;
		console.log($scope.activityForm.isSkillBasedAssessment);
		
		$("#modalAddUpdateActivity").modal('show');
	}
	
	this.closeAddUpdateActivityModal = function(){
		this.resetActivityForm();
		$("#modalAddUpdateActivity").modal('hide');
	}
	
	this.saveActivity = function(){
		if($scope.activityForm.isSkillBasedAssessment == 1){
			$scope.activityForm.isSkillBasedAssessment = true;
		}else{
			$scope.activityForm.isSkillBasedAssessment = false;
		}
		manageCoScholasticService.saveActivity($scope.activityForm, this.saveActivitySuccess, this.errorFuntion);
	}
	
	this.saveActivitySuccess = function(response, status){
		$("#modalAddUpdateActivity").modal('hide');
		$route.reload();
	}
	
	this.deleteActivity = function(activityId){
		manageCoScholasticService.deleteActivity(activityId, this.deleteActivitySkillSuccess, this.errorFuntion );
	}
	
	this.deleteActivitySuccess = function(response, status){
		
	}
	
	
	this.openAddActivitySkillModal = function(activityId, activityName){
		$scope.isAdd = true;
		$scope.isUpdate = false;
		$scope.activityName = activityName;
		$scope.skillForm.activityId = activityId;
		$("#modalAddUpdateSkill").modal('show');
	}
	
	this.openUpdateActivitySkillModal = function(skillId, name, displayName, displayOrder, doNotDisplayOnScorecard){
		$scope.isAdd = false;
		$scope.isUpdate = true;
		$scope.skillForm.id = skillId;
		$scope.skillForm.name = name;
		$scope.skillForm.displayName = displayName;
		$scope.skillForm.displayOrder = displayOrder;
		
		$scope.skillForm.doNotDisplayOnScorecard = doNotDisplayOnScorecard;
		
		
		$("#modalAddUpdateSkill").modal('show');
	}
	
	this.closeAddUpdateSkillModal = function(){
		this.resetSkillForm();
		$("#modalAddUpdateSkill").modal('hide');
	}
	
	this.resetSkillForm = function(){
		$scope.skillForm.id =null;
		$scope.skillForm.name =null;
		$scope.skillForm.displayName =null;
		$scope.skillForm.activityId =null;
	}
	
	this.saveSkill = function(){
		manageCoScholasticService.saveSkill($scope.skillForm, this.saveSkillSuccess, this.errorFuntion);
	}
	
	this.saveSkillSuccess = function(response, status){
		$("#modalAddUpdateSkill").modal('hide');
		$route.reload();
	}
	
	this.resetIndicatorForm = function(){
		$scope.skillIndicatorForm.id = null;
		$scope.skillIndicatorForm.name = null;
		$scope.skillIndicatorForm.displayName = null;
		$scope.skillIndicatorForm.skillId = null;
		
	}
	
	this.errorFuntion = function(response){
		console.log(response);
	}

	this.doNothing = function(e){
	
		if (e) {
		      e.preventDefault();
		      e.stopPropagation();
		}
	}
	
	

	
	this.getExamPattern = function(examPatternId){
		examPatternService.getExamPattern(examPatternId, this.displayExamPatternName, this.errorFuntion);
	}
	
	this.displayExamPatternName = function(response, status){
		
		$scope.examPattern = response;
	}
	
	this.saveGradeToIndicatoryMapping = function(){
		
		/*angular.forEach($scope.csa.activities, function(activity){
			angular.forEach(activity.skills, function(skill){
				if(angular.equals(skill.id, skillId)){
					$scope.gradeToIndicatorMap = skill.skillGradeIndicatorVOs;
					$scope.gradeIndicatorListForm.skillGradeIndicatorForms =skill.skillGradeIndicatorVOs;
				}
	        })
        })*/
		
        manageCoScholasticService.saveGradeToIndicatorMapping($scope.gradeIndicatorListForm, this.saveGradeToIndicatoryMappingSuccess, this.errorFuntion);
        
	}
	
	this.openPopupToAddUpdateOverallIndicator = function(indicatorId, indicatorName, evaluationTypeId){
		
		manageCoScholasticService.getOverallIndicator(evaluationTypeId, indicatorId, this.renderOverallIndicators, this.errorFuntion);
	}
	
	this.renderOverallIndicators = function(response, status){
		$("#modalAddUpdateOverallIndicator").modal('show');
		$scope.gradeIndicatorListForm.skillGradeIndicatorForms =response;
		
	}
	
	this.closeOverallIndicatorPopup = function(){
		$("#modalAddUpdateOverallIndicator").modal('hide');
	}
	
	this.saveGradeToIndicatoryMappingSuccess = function(response, status){
		$("#modalAddUpdateOverallIndicator").modal('hide');
		
	}
});






