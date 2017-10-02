<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>
<div id="content_area">
        <div id="left_navigation" class="color_theme_bg color_theme_border">
	          <div class="color_theme_border" onclick="getAcademicYearListView()">Academic year</div>
	          <!-- <div class="color_theme_border" onclick="getAffiliationAuthorityListView()">Affiliation Authority</div> -->
	         <!--  <div class="color_theme_border" onclick="getFeeHeadListView()">Fee Head</div> -->
	         <%-- 
	          <div class="color_theme_border" onclick="getUserListView()">Users</div>
	          <div class="color_theme_border" onclick="getRoleListView()">Security</div>
	         --%>      
	          <!-- <div class="color_theme_border" onclick="getCourseListView()">Course</div> -->
	          <!-- <div class="color_theme_border" onclick="getBusStopListView()">Bus Stop(s)</div> -->
	          <div class="color_theme_border" onclick="listLateFeeRules()">Late Fee Rule</div>
	          <!-- <div class="color_theme_border" onclick="listLeavePlans()">Leave Calendar</div> -->
	          <%-- 
	          <div class="color_theme_border" onclick="getHostelListView()">Hostel</div>
	          --%>
	          <!--  <div class="color_theme_border"><a href="institute/settings">Institute Settings</a></div> -->
	          
        	  
        </div>
        <div id="working_area" class="color_theme_border">
 			<div id="adminContainer">
 				<jsp:include page="academic_year_list.jsp"/>
 			</div>
 		</div>
</div>
<jsp:include page="sams_footer.jsp"/>
<div id="popupBackground" class="popupBack" onclick="closeFeePopup()"></div>
<div id="popupFee" class="popup">
  	<div id="feePopUpLabel" class="popup_header"></div>
  	<div id="feeContainer"></div>
</div>
<div id="popupRemainingCourses" class="popup">
	<div class="popup_header">Add course(s) in academeic year </div>
	<div id="remainingCoursesContainer"></div>
</div>
<div id="popupRemainingBusStops" class="popup">
	<div class="popup_header">Add bus stop(s) in academeic year </div>
	<div id="popupRemainingBusStopsContainer"></div>
</div>
<div id="popupRemainingHostels" class="popup">
	<div class="popup_header">Add hostel(s) in academeic year </div>
	<div id="popupRemainingHostelContainer"></div>
</div>
<div id="popupFeeInstallment" class="popup">
	<div id="instlPopupLabel" class="popup_header"></div>
	<div id="instlContainer"></div>
</div>
<div id="popupBusFeeInstallment" class="popup">
	<div id="instlPopupLabel" class="popup_header">Bus Fee Installment</div>
	<div id="busFeeInstlContainer"></div>
</div>
<div id="popupHostelFeeInstallment" class="popup">
	<div id="instlPopupLabel" class="popup_header">Hostel Fee Installment</div>
	<div id="hostelFeeInstlContainer"></div>
</div>
<div id="popupCourseYearProperties" class="popup"></div>

<script type="text/javascript">

<!--

$(function() {
	$("#popupFee").draggable( { handle:"div.popup_header" } );
	$("#popupRemainingCourses").draggable( { handle:"div.popup_header" } );
	$("#popupRemainingBusStops").draggable( { handle:"div.popup_header" } );
	$("#popupRemainingHostels").draggable( { handle:"div.popup_header" } );
	$("#popupFeeInstallment").draggable( { handle:"div.popup_header" } );
	$("#popupBusFeeInstallment").draggable( { handle:"div.popup_header" } );
	$("#popupHostelFeeInstallment").draggable( { handle:"div.popup_header" } );
	$("#popupCourseYearProperties").draggable( { handle:"div.popup_header" } );
});

var ACTION_ADD="add";
var ACTION_UPDATE="update";


function validateFeeHeadForm(){
	var name=jQuery.trim($("#name").val());
	//var displayName=jQuery.trim($("#displayName").val());
	
	if(name!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}

function getFeeHeadListView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/feehead/list'/>",
		success: getFeeHeadListViewSuccess
	});
}

function getFeeHeadListViewSuccess(data){
	$("#adminContainer").html(data);
}

function getNewFeeHeadView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/feehead/new'/>",
		success: getFeeHeadListViewSuccess
	});
}

function getNewFeeHeadViewSuccess(data){
	$("#adminContainer").html(data);
}

function saveFeeHead(){
	
	if(validateFeeHeadForm()){
		var feeHeadData = $("#feeHeadForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : "<c:url value='/admin/feehead/add' />",
			data : feeHeadData,
			success : saveFeeHeadSuccess
		});	
	}
	
		
}

function saveFeeHeadSuccess(response){
	
	if("OK"==response.status){
		//alert("Saved Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Name already exists. Please choose different name");
	}
	
}

function loadFeeHeadToUpdate(feeHeadId){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/feehead/get/"+feeHeadId+"'/>",
		success: loadFeeHeadToUpdateSuccess
	});
}

function loadFeeHeadToUpdateSuccess(data){
	$("#adminContainer").html(data);
}

function updateFeeHead(){
	
	if(validateFeeHeadForm()){
		var feeHeadData = $("#feeHeadForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : "<c:url value='/admin/feehead/update' />",
			data : feeHeadData,
			success : updateFeeHeadSuccess
		});	
	
	}
	
}

function updateFeeHeadSuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Name already exists. Please choose different name");
	}
}
 

/* Affiliation Authority  : START */
  
function validateAffiliationAuthorityForm(){
	var name=jQuery.trim($("#name").val());
	var displayName=jQuery.trim($("#displayName").val());
	
	if(name!="" && displayName!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}  
  
function getAffiliationAuthorityListView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/aa/list'/>",
		success: getAffiliationAuthorityListViewSuccess
	});
}

function getAffiliationAuthorityListViewSuccess(data){
	$("#adminContainer").html(data);
}

function getNewAffiliationAuthorityView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/aa/new'/>",
		success: getNewAffiliationAuthorityViewSuccess
	});
}

function getNewAffiliationAuthorityViewSuccess(data){
	$("#adminContainer").html(data);
}
 
function loadAffiliationAuthoriyToUpdate(aaId){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/aa/get/"+aaId+"'/>",
		success: loadFeeHeadToUpdateSuccess
	});
}

function loadAffiliationAuthoriyToUpdateSuccess(data){
	$("#adminContainer").html(data);
} 

function addAffiliationAuthority(){
	
	if(validateAffiliationAuthorityForm()){
		var feeHeadData = $("#aaForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : "<c:url value='/admin/aa/add' />",
			data : feeHeadData,
			success : addAffiliationAuthoritySuccess
		});	
	}
	
		
}

function addAffiliationAuthoritySuccess(response){
	
	if("OK"==response.status){
		//alert("Saved Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Name already exists. Please choose different name");
	}
	
}

function updateAffiliationAuthority(){
	
	if(validateAffiliationAuthorityForm()){
		var feeHeadData = $("#aaForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : "<c:url value='/admin/aa/update' />",
			data : feeHeadData,
			success : updateAffiliationAuthoritySuccess
		});	
	
	}
	
}

function updateAffiliationAuthoritySuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Name already exists. Please choose different name");
	}
}

/* Affiliation Authority  : END */ 
 
/* Academic Year  : START */
  
function validateAcademicYearForm(){
	var name=jQuery.trim($("#name").val());
	var fromDate=jQuery.trim($("#fromDate").val());
	var toDate=jQuery.trim($("#toDate").val());
	if(name!="" && fromDate!="" && toDate!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}    
  
function getAcademicYearListView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/ay/list'/>",
		success: getAcademicYearListSuccess
	});
}

function getAcademicYearListSuccess(data){
	$("#adminContainer").html(data);
}


function getNewAcademicYearView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/ay/new'/>",
		success: getNewAcademicYearViewSuccess
	});
}

function getNewAcademicYearViewSuccess(data){
	$("#adminContainer").html(data);
	bindFromDatePicker();
	bindToDatePicker();
}

var academicYearStartYear="";
var academicYearEndYear="";

function bindFromDatePicker(){
	
	$( "#fromDate" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true,
		onSelect: function(dateText, inst) {
			academicYearStartYear=(dateText.split("-"))[2]; // selected year
             $("#name").val(academicYearStartYear+"-"+academicYearEndYear);
         }
	});
	
}

function bindToDatePicker(){
	
	$( "#toDate" ).datepicker({
		showOn: "button",
		buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true,
		onSelect: function(dateText, inst) {
            academicYearEndYear = (dateText.split("-"))[2]; // selected year
            $("#name").val(academicYearStartYear+"-"+academicYearEndYear);
         }
	});
	
}

function addAcademicYear(){
	
	if(validateAcademicYearForm()){
		var academicYearData = $("#academicYearForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : "<c:url value='/admin/ay/add' />",
			data : academicYearData,
			success : addAcademicYearSuccess
		});	
	}
	
		
}

function addAcademicYearSuccess(response){
	
	if("OK"==response.status){
		//alert("Saved Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Name already exists. Please choose different name");
	}
	
}

function loadAcademicYearToUpdate(ayId){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/ay/get/"+ayId+"'/>",
		success: loadAcademicYearToUpdateSuccess
	});
}

function loadAcademicYearToUpdateSuccess(data){
	$("#adminContainer").html(data);
	bindFromDatePicker();
	bindToDatePicker();
}

function updateAcademicYear(){
	
	if(validateAcademicYearForm()){
		var feeHeadData = $("#academicYearForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : "<c:url value='/admin/ay/update' />",
			data : feeHeadData,
			success : updateAcademicYearSuccess
		});	
	
	}
	
}

function updateAcademicYearSuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Name already exists. Please choose different name");
	}
}


/* Academic Year  : END */


/* Users  : START */

var userAction=null;

function getUserListView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/user/defaultList'/>",
		success: getUserListViewSuccess
	});
}

function getUserListViewSuccess(data){
	$("#adminContainer").html(data);
	bindUserListingWithDataTable();
}

function bindUserListingWithDataTable(){
	
	$('#user_listing_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      null,
	                      { "bSortable": false },
	                      null,
	                      null,
	                      {"bVisible": false},
	                      {"iDataSort": 5}
	                     ]
	    } );
}

function getNewUserView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/user/new'/>",
		success: getNewUserViewSuccess
	});
}

function getNewUserViewSuccess(data){
	$("#adminContainer").html(data);
	userAction=ACTION_ADD;
}

function saveUser(){
	
	if(validateUserForm()){
		if(ACTION_ADD==userAction){
			addUser();
		}else if(ACTION_UPDATE==userAction){
			updateUser();
		}	
	}
	
}

function validateUserForm(){
	var firstName=jQuery.trim($("#firstName").val());
	
	var lastName=jQuery.trim($("#lastName").val());
	var userName=jQuery.trim($("#userName").val());
	var password=jQuery.trim($("#password").val());
	
	if(firstName!="" && lastName!="" && userName!="" && password!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}

function addUser(){
	
	var userData = $("#userForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : "<c:url value='/admin/user/add' />",
		data : userData,
		success : addUserSuccess
	});	
}

function addUserSuccess(response){
	
	if("OK"==response.status){
		userAction=ACTION_UPDATE;
		$("#userSave").val("Update");
		$("#userId").val(response.id);
		//alert("Saved Successfully..");
	}else if("DUPLICATE"==response.status){
		alert("User name already exists. Please choose different name");
	}
	
}

function updateUser(){
	
	var userData = $("#userForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : "<c:url value='/admin/user/update' />",
		data : userData,
		success : updateUserSuccess
	});	
}

function updateUserSuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("User name already exists. Please choose different name");
	}
}

function loadUserToUpdate(userId){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/user/get/"+userId+"'/>",
		success: loadUserToUpdateSuccess
	});
}

function loadUserToUpdateSuccess(data){
	$("#adminContainer").html(data);
	userAction=ACTION_UPDATE;
}

function getUserListOfRole(roleId){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/user/role/"+roleId+"'/>",
		success: getUserListOfRoleSuccess
	});
}

function getUserListOfRoleSuccess(data){
	$("#adminContainer").html(data);
}

/* Users  : END */


/* Role  : START */

var roleAction=null;

function getRoleListView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/role/defaultList'/>",
		success: getRoleListViewSuccess
	});
}

function getRoleListViewSuccess(data){
	$("#adminContainer").html(data);
	
	bindRoleListingWithDataTable();
}

function bindRoleListingWithDataTable(){
	
	$('#role_listing_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      null,
	                      { "bSortable": false },
	                      {"bVisible": false},
	                      null,
	                      {"bVisible": false},
	                      {"iDataSort": 5}
	                      
	                     ]
	    } );
}

function getNewRoleView(){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/role/new'/>",
		success: getNewRoleViewSuccess
	});
}

function getNewRoleViewSuccess(data){
	$("#adminContainer").html(data);
	roleAction=ACTION_ADD;
}

function saveRole(){
	
	if(validateRoleForm()){
		if(ACTION_ADD==roleAction){
			addRole();
		}else if(ACTION_UPDATE==roleAction){
			updateRole();
		}	
	}
	
}

function addRole(){
	
	var roleData = $("#roleForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : "<c:url value='/admin/role/add' />",
		data : roleData,
		success : addRoleSuccess
	});	
}

function addRoleSuccess(response){
	
	if("OK"==response.status){
		roleAction=ACTION_UPDATE;
		$("#roleSave").val("Update");
		$("#roleId").val(response.id);
		alert("Saved Successfully..");
	}else if("DUPLICATE"==response.status){
		alert("Group name already exists. Please choose different name");
	}
	
}

function loadRoleToUpdate(roleId){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/role/get/"+roleId+"'/>",
		success: loadRoleToUpdateSuccess
	});
}

function loadRoleToUpdateSuccess(data){
	$("#adminContainer").html(data);
	roleAction=ACTION_UPDATE;
}

function updateRole(){
	
	var roleData = $("#roleForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : "<c:url value='/admin/role/update' />",
		data : roleData,
		success : updateRoleSuccess
	});	
}

function updateRoleSuccess(response){
	if("OK"==response.status){
		//alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Group name already exists. Please choose different name");
	}
}

function validateRoleForm(){
	var name=jQuery.trim($("#name").val());
	if(name!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}

var admin_main_url="<c:url value='/admin'/>";
var admin_ay_settings_url="<c:url value='/admin/academicyear/setting'/>";
var admin_bus_stop_url="<c:url value='/admin/busstop'/>";
var admin_fee_setting_url="<c:url value='/admin/academicyear/fee'/>";
var admin_bus_fee_setting_url="<c:url value='/admin/academicyear/bussfee'/>";
var admin_fee_installment_url="<c:url value='/admin/manageInstallments'/>";
var admin_hostel_url="<c:url value='/admin/hostel'/>";
var admin_hostel_fee_setting_url="<c:url value='/admin/academicyear/hostelfee'/>";
var admin_late_fee_rule_url="<c:url value='/admin/latefeerule'/>";
var admin_misc_activity_url="<c:url value='/admin/miscactivity'/>";
var admin_leave_plan_url="<c:url value='/admin/leavePlan'/>";
var selected_fee_link="";

/* Role  : END */

//-->


</script>