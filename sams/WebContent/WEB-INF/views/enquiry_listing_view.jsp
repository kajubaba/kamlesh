<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="sams_header.jsp"/>
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="working_area_spacer">
				<div  id="enquiry_container">
				
				 <security:authorize access="hasRole('ROLE_ENQUIRY_ADD_EDIT')">
		    	     <div>
		    	      <span class="form_heading"> Academic Year ${currentAcademicYear.name}</span>
			          <c:if test="${isEnqAcademicYear==true}">
			          		<a href='<c:url value="/enquiry/new" />' style="float: right;">New Enquiry</a> 
			          </c:if>
			        </div>
			     </security:authorize>
				
				<br/>
		       
		       	<jsp:include page="enquiry_advance_search.jsp"/>
				
				
			</div>
			<div id="enqListContainer" >
			        <div class="grid_above_gap"></div>
			        <div id="quickEnqSearch" class="grid_info">
				        <form id="enquirySearchForm" name="enquirySearchForm">
				          		<input id="searchStr" name="searchStr" type="text" value="${searchStr}" onkeypress="return activate(event, this.value)" size="40"/> 
				          		<select name="searchCriteria">
				          			<option value="name" <c:if test="${'name' == searchCriteria}">selected</c:if>  >Student Name</option>
				          			<option value="city"   <c:if test="${'city' == searchCriteria}">selected</c:if> >City</option>
				          			<option value="teh"  <c:if test="${'teh' == searchCriteria}">selected</c:if> >Tehsil</option>
				          			<option value="dist" <c:if test="${'dist' == searchCriteria}">selected</c:if> >District</option>
				          			<option value="registered" <c:if test="${'registered' == searchCriteria}">selected</c:if> >Registered</option>
				          			<option value="non-registered" <c:if test="${'non-registered' == searchCriteria}">selected</c:if> >Non-Registered</option>
				          		</select>
				          		&nbsp;&nbsp;&nbsp; <input type="button" value="Search" class="button" onclick="searchEnquiries(${academicYearId});" />
				          </form>
					  </div>
			       <br/>
				   
				 <c:if test="${isEnqAcademicYear==true}">   
        			    <security:authorize access="hasRole('ROLE_ENQUIRY_ASSIGN_OWNER')">
					    	<div style="width: 100px;float: left;"  class="action-button" onclick="viewChangeEnqOwnerPopup()">Change owner</div>
				        </security:authorize>
				         <security:authorize access="hasRole('ROLE_ENQUIRY_ASSIGN_ASSIGNEE')">
				        	<div style="width: 120px;float: left;"  class="action-button" onclick="viewChangeEnqAssigneePopup()">Change Assignee</div>
				        </security:authorize>	
				         <security:authorize access="hasRole('ROLE_ENQUIRY_CHANGE_STATUS')">
				        	<div style="width: 100px;float: left;"  class="action-button" onclick="viewChangeEnqStatusPopup()">Change status</div>
				        </security:authorize>
				         <security:authorize access="hasRole('ROLE_ENQUIRY_FOLLOW')">
				        	<div style="width: 60px;float: left;"  class="action-button" onclick="viewEnqfollowupPopup()">Follow</div>
				        </security:authorize>
			       </c:if>
			        <security:authorize access="hasRole('ROLE_ENQUIRY_EXPORT_EXCEL')">
				    	<div style="width: 60px;float: left;"  class="action-button" onclick="enqExportToexcel(${academicYearId})">Export</div>      		
				    </security:authorize>
			        <div id="enquiry_list_container">
			       		<jsp:include page="enquiry_listing.jsp"/>
			       	</div>	
			      </div>
			   </div>
			 </div>
		</div>	  
		    
<jsp:include page="sams_footer.jsp"/>   
<div id="popupBackground" class="popupBack" onclick="closeEnqActivityPopup()"></div>
<div id="enq_activity_popup" class="popup" style="height: 336px">
</div>  
<script type="text/javascript">
<!--
var enqActivityUrl="<c:url value='/enquiry/activity'/>";
var enqListUrl="<c:url value='/enquiry/list'/>";
var searchType="quick";
var enqExportUrl="<c:url value='/enquiryExport'/>";
var course_url="<c:url value='/course' />";




$(document).ready(function() {
	/*
	    $(document).bind('contextmenu', function(e) {
	          return false;
	    });
	*/
	bindEnqListWithDataTable();
	    $( "#activityFromDateStr" ).datepicker({
			showOn: "button",
			buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
			dateFormat:'d-M-yy',
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true
		});
	    
	    $( "#activityToDateStr" ).datepicker({
			showOn: "button",
			buttonImage: "<c:url value='/resources/'/>images/calendar.gif",
			dateFormat:'d-M-yy',
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true
		});
	}); 

function bindEnqListWithDataTable(){
	
	$('#enq_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	       

	    } );
	
}



//-->


</script>