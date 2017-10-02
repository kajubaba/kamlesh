<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Leave Calendar</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Leave" class="button" onclick="getNewLeavePlanView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(leavePlans)!=0 }">
		         		Displaying 1-${fn:length(leavePlans)} of ${fn:length(leavePlans)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="bus_stop_table" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">Leave On</th>
	            <th class="grid_item">Occassion</th>
	            <th class="grid_item">Created By</th>
	          </tr>
         </thead>
         <tbody>
	         <c:forEach var="leavePlan" items="${leavePlans}">
	         	<tr class="grid_main_row">
	            	<td class="grid_item">
	            		<a href="javascript:void(0)" onclick="loadBusStopToUpdate(${busStop.id})"> 
	            			<fmt:formatDate pattern="dd-MMM-yyyy" value="${leavePlan.leaveOn}" />
	            		</a> 
	            	</td>
	            <td class="grid_item">${leavePlan.reason}</td>
	            
	            <td class="grid_item">${leavePlan.createdBy.firstName} ${leavePlan.createdBy.lastName}</td>
	            
	            <td class="grid_item">
	            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${busStop.createdDate}" />
	            </td>
	          </tr>
	         </c:forEach>
         </tbody>
        </table>
       
        <br/>
       
        <div class="grid_info">
        	 <span style=" float: right;">
		        <c:choose>
		         	<c:when test="${ fn:length(leavePlans)!=0 }">
		         		Displaying 1-${fn:length(leavePlans)} of ${fn:length(leavePlans)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      