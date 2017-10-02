  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="sams_header_new.jsp"/>
 
	  <!-- Row -1 -->
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <h3 class="page-header">
            <div class="row">
              <div class="col-sm-6 col-md-8">
              		<i class="fa fa-dashboard"></i> 
              		<select id="ayId" class="dropdown-in-page-header" onchange="switchAcademicYear()">
	                  	<c:forEach var="academicYear" items="${academicYears}">
	                  		<option value="${academicYear.id}" <c:if test="${currentAcademicYear.name == academicYear.name}">selected="selected"</c:if>>${academicYear.name}</option>
	                   	</c:forEach>
	                </select>
              		Enquiry <small>Dashboard</small> 
              		
	                  
	                  
              </div>
              <div class="col-sm-6 col-md-4">
              		<div class="dropdown pull-right">
							  <button type="button" class="btn btn-default dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true">
								<span class="glyphicon glyphicon-tasks"></span>
							  </button>
							   <ul class="dropdown-menu dropdwon-left" aria-labelledby="dropdownMenu1">
									<security:authorize access="hasRole('ROLE_ENQUIRY_ADD_EDIT')">
										<li><a href="<c:url value="/enquiry/new" />">New Enquery</a></li>
									</security:authorize>	
									<li> <a href="<c:url value="/enquiry/list/all?academicYearId=${academicYearId}" />">All Enqueries</a></li>
							   </ul>
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
            <div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i> City wise  (${totalEnq})
			
			</div>
            <div class="panel-body">
              <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="100%" height="300" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/enquiry/dashboard/enquiries/citywise?academicYearId=${academicYearId}" />&chartWidth=1500&chartHeight=300" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/enquiry/dashboard/enquiries/citywise?academicYearId=${academicYearId}" />&chartWidth=1500&chartHeight=300" quality="high" width="100%" height="300" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
      				</object>

            </div>
          </div>
        </div>
      </div>  
      <!--// Row -2 -->
      <!-- Row -3 -->
      	<div class="row">
        	<div class="col-md-6">
		        	<div class="panel panel-default">
		            <div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i> Enquiry Status (${totalEnq})
					
					</div>
		            <div class="panel-body">
		             <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="100%" height="250" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />/charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/enquiry/dashboard/enquiries/statuswise?academicYearId=${academicYearId}" />&chartWidth=600&chartHeight=250" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />/charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/enquiry/dashboard/enquiries/statuswise?academicYearId=${academicYearId}" />&chartWidth=600&chartHeight=250" quality="high" width="100%" height="250" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	      			</object>
		
		            </div>
		          </div>
        	</div>
        	<div class="col-md-6">
        		<div class="panel panel-default">
		            <div class="panel-heading"> <i class="fa fa-bar-chart-o fa-fw"></i> Class wise (${totalEnq})
					
					</div>
		            <div class="panel-body">
		             <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="100%" height="250" id="Column3D" >
				         <param name="movie" value="<c:url value="/resources/" />/charts/FCF_Column3D.swf" />
				         <param name="FlashVars" value="&dataURL=<c:url value="/enquiry/dashboard/enquiries/classwise?academicYearId=${academicYearId}" />&chartWidth=600&chartHeight=250" />
				         <param name="quality" value="high" />
				         <embed src="<c:url value="/resources/" />/charts/FCF_Column3D.swf" flashVars="&dataURL=<c:url value="/enquiry/dashboard/enquiries/classwise?academicYearId=${academicYearId}" />&chartWidth=600&chartHeight=250" quality="high" width="100%" height="250" name="Column3D" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	      			</object>
		
		            </div>
		          </div>
        	</div>
        </div>	
      <!--// Row -3 -->
      
<jsp:include page="sams_footer_new.jsp"/>
<script type="text/javascript">
	function switchAcademicYear(){
		var url='<c:url value="/enquiry/dashboard" />';
		url=url+"?academicYearId="+$("#ayId").val();
		window.location = url;
	}
</script>