<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="sams_header_new.jsp"/>



	  <!-- Row -1 -->
      <div class="row">
        <div class="col-md-12">
          <h3 class="page-header">
            <div class="row">
              <div class="col-md-8">
              		<i class="fa fa-dashboard"></i> 
              		${currentAcademicYear.name}  <small>Enquiries</small> 
              </div>
              <div class="col-md-4">
              	<div class="row">
              		<div class="col-md-10">
              		
              			<div class="form-group input-group ">
							  <input id="txtBoxsearchStud" type="text" class="form-control" placeholder="Clic Here for Advance Search Option" onclick="openAdvanceSearchPopup()">
							  <span class="input-group-btn" >
								<button class="btn btn-default" type="button" onclick="openAdvanceSearchPopup()"><i class="fa fa-search"></i> </button>
							  </span> 
						 </div>
              		</div>
              		<div class="col-md-2">
              			<security:authorize access="hasRole('ROLE_ENQUIRY_ADD_EDIT')">	
              		
              		<div class="dropdown pull-right">
							  <button type="button" class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true">
								<span class="glyphicon glyphicon-tasks"></span>
							  </button>
							   <ul class="dropdown-menu dropdwon-left" aria-labelledby="dropdownMenu1">
									<li><a href="<c:url value="/enquiry/new" />">New Enquery</a></li>
							   </ul>
					</div>
				</security:authorize>
              		</div>
              	</div>
              </div>
     
         
            
			</div>
          </h3>
        </div>
      </div>
      <!-- // Row -1 --> 
	
	
	 <!-- Row -2 -->
      <div class="row">
        <div class="col-sm-12 col-md-12">
        	
        	<div class="panel panel-default">
            <div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i> 
                
               <div class="pull-right">
                
                <div class="btn-group">
                  <button type="button" class="btn btn-default btn-xs dropdown-toggle btn-panel-default" data-toggle="dropdown">
                   [Select Action]<span class="caret"></span> </button>
                  <ul class="dropdown-menu pull-right" role="menu">
                    
                     <c:if test="${isEnqAcademicYear==true}">  
                    
                    <security:authorize access="hasRole('ROLE_ENQUIRY_ASSIGN_OWNER')">
                    	<li><a href="javascript:void(0)" onclick="viewChangeEnqOwnerPopup()">Change owner</a></li>
                    </security:authorize>	
                    <security:authorize access="hasRole('ROLE_ENQUIRY_ASSIGN_ASSIGNEE')">
                    	<li><a href="javascript:void(0)" onclick="viewChangeEnqAssigneePopup()">Change Assignee</a></li>
                    </security:authorize>	
                     <security:authorize access="hasRole('ROLE_ENQUIRY_CHANGE_STATUS')">
                    	<li><a href="javascript:void(0)" onclick="viewChangeEnqStatusPopup()">Change status</a></li>
                    </security:authorize>	
                     <security:authorize access="hasRole('ROLE_ENQUIRY_FOLLOW')">
                    	<li><a href="javascript:void(0)" onclick="viewEnqfollowupPopup()">Follow</a></li>
                    </security:authorize>	
                    </c:if>
                    <security:authorize access="hasRole('ROLE_ENQUIRY_EXPORT_EXCEL')">
                    	<li><a href="javascript:void(0)" onclick="enqExportToexcel(${academicYearId})">Export</a></li>
                    </security:authorize>	
                  </ul>
                </div>
              </div>
			
			</div>
            <div class="panel-body">
              <%-- 
               <div id="quickEnqSearch">
				          <form id="enquirySearchForm" name="enquirySearchForm">
				          	
				          	<table>
				          		<tr>
				          			<td>
				          				<input id="searchStr" name="searchStr" type="text" value="${searchStr}" onkeypress="return activate(event, this.value)" size="40" class="form-control"/>
				          			</td>
				          			<td>
				          				<select name="searchCriteria" class="form-control">
						          			<option value="name" <c:if test="${'name' == searchCriteria}">selected</c:if>  >Student Name</option>
						          			<option value="city"   <c:if test="${'city' == searchCriteria}">selected</c:if> >City</option>
						          			<option value="teh"  <c:if test="${'teh' == searchCriteria}">selected</c:if> >Tehsil</option>
						          			<option value="dist" <c:if test="${'dist' == searchCriteria}">selected</c:if> >District</option>
						          			<option value="registered" <c:if test="${'registered' == searchCriteria}">selected</c:if> >Registered</option>
						          			<option value="non-registered" <c:if test="${'non-registered' == searchCriteria}">selected</c:if> >Non-Registered</option>
				          				</select>
				          			</td>
				          			<td>
				          				<input type="button" value="Search" class="button" onclick="searchEnquiries(${academicYearId});" />
				          			</td>
				          		</tr>
				          	</table>
				          </form>
				</div>
			--%>	
              <div id="enquiry_list_container">
              	<jsp:include page="enquiry_listing_new.jsp"/>
              	
              </div>

            </div>
          </div>
        	
        	
        </div>
      </div>  
	
	
	<jsp:include page="enquiry_advance_search_new.jsp"/>
	


	  
		    
<jsp:include page="sams_footer_new.jsp"/>   
<!-- 
<div id="popupBackground" class="popupBack" onclick="closeEnqActivityPopup()"></div>
 -->
 
<script type="text/javascript">


var enqActivityUrl="<c:url value='/enquiry/activity'/>";
var enqListUrl="<c:url value='/enquiry/list'/>";
var searchType="quick";
var enqExportUrl="<c:url value='/enquiryExport'/>";
var course_url="<c:url value='/course' />";

$(document).ready(function() {
	bindEnqListWithDataTable();
	$( "#activityFromDateStr" ).datepicker({
		
		changeMonth: true,
		changeYear: true
	});
    
    $( "#activityToDateStr" ).datepicker({
		
		changeMonth: true,
		changeYear: true
	});
})

function openAdvanceSearchPopup(){
	$("#enq_advance_search_popup").modal('show');
}

function bindEnqListWithDataTable(){
	
	$('#enq_table').dataTable({
	        
		 responsive: true,
         "lengthMenu": [[25, 50, 100, -1], [25, 50, 100, "All"]],
         "order": [[ 1, "asc" ]],
         "aoColumns": [
                       { "bSortable": false },
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       null,
                       { "bSortable": false }
                       ]
	    } );
}

</script>