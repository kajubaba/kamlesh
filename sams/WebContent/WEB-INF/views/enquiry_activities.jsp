<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="sams_header_new.jsp"/>



<!-- Row -1 -->
      <div class="row">
        <div class="col-sm-12 col-md-12">
          <h3 class="page-header">
            <div class="row">
              <div class="col-md-12">
              		<i class="fa fa-dashboard"></i> 
              		
              		Admission enquiry  :  <small>${enquiry.studentFirstName} ${enquiry.studentLastName}</small> 
              		
	                   
	                  
              </div>
              
         
            
			</div>
          </h3>
        </div>
      </div>
      <!-- // Row -1 --> 
	
	  <div class="row">
	  		<div class="col-sm-12 col-md-12">
	  			<div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-clock-o fa-fw"></i> Activities on Admission enquiry :
                        </div>
                        <div class="panel-body">
                        	<ul class="timeline">
                        		<c:forEach var="enqActivity" items="${enqActivities}" varStatus="rowCounter">
                        			<c:choose>
						          		<c:when test="${rowCounter.count % 2 != 0}">
						          			<li>
						          				<div class="timeline-badge"><i class="fa fa-clock-o"></i></div>
						          		</c:when>
						          		<c:otherwise>
						    	      		 <li class="timeline-inverted">
						    	      		 <div class="timeline-badge warning"><i class="fa fa-clock-o"></i></div>
							          	</c:otherwise>
          							</c:choose>
          							 <div class="timeline-panel">
          							 	<div class="timeline-heading">
                                            <h4 class="timeline-title">
                                            
                                            	<c:if test="${enqActivity.activityType == 'change_owner'}">
						          			Change owner from "${enqActivity.ownerFrom.firstName} ${enqActivity.ownerFrom.lastName}"
						          			To "${enqActivity.ownerTo.firstName} ${enqActivity.ownerTo.lastName}" 
						          			
					          		</c:if>
					          		<c:if test="${enqActivity.activityType == 'change_assignee'}">
						          			Change assignee from "${enqActivity.assigneeFrom.firstName} ${enqActivity.assigneeFrom.lastName}"
						          			To "${enqActivity.assigneeTo.firstName} ${enqActivity.assigneeTo.lastName}" 
						          			
					          		</c:if>
					          		<c:if test="${enqActivity.activityType == 'change_status'}">
						          			
						          			Change status from "${enqActivity.statusFrom.name}" To "${enqActivity.statusTo.name}" 
						          			
						          			
					          		</c:if>
					          		<c:if test="${enqActivity.activityType == 'followup'}">
						          			${enqActivity.followupActivity}
						          			
					          		</c:if>
                                            
                                            </h4>
                                            <p>
                                            	<small class="text-muted"> 
	                                            	<i class="fa fa-clock-o"></i> On  
	                                            	<fmt:formatDate type="both" timeStyle="short" value="${enqActivity.createdDateTime}" />
	                                            	&nbsp;&nbsp; By ${enqActivity.createdBy.firstName} ${enqActivity.createdBy.lastName}
                                            	</small>
                                            </p>
                                        </div>
                                        <div class="timeline-body">
                                            <p>${enqActivity.comments}</p>
                                        </div>
          							 </div>
          							
          							
          							
          							
          							
          							</li>
                        		</c:forEach>
                        		
                        	</ul>
                        </div>
                 </div>       
	  			
	  		</div>
	  </div>	

 
<jsp:include page="sams_footer_new.jsp"/>