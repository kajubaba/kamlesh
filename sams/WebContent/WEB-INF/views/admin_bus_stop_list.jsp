<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Bus Stop</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Bus Stop" class="button" onclick="getNewBusStopView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(busStops)!=0 }">
		         		Displaying 1-${fn:length(busStops)} of ${fn:length(busStops)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="bus_stop_table" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">Name</th>
	            <th class="grid_item">Distance (km)</th>
	            <th class="grid_item">Status</th>
	            <th class="grid_item">Created By</th>
	            <th></th>
	            <th class="grid_item">Created Date</th>
	          </tr>
         </thead>
         <tbody>
	         <c:forEach var="busStop" items="${busStops}">
	         	<tr class="grid_main_row">
	            <td class="grid_item"><a href="javascript:void(0)" onclick="loadBusStopToUpdate(${busStop.id})"> ${busStop.name}</a> </td>
	            <td class="grid_item">${busStop.distance}</td>
	            <td class="grid_item"> 
	            	<c:choose>
	            		<c:when test="${busStop.active=='true'}">Active</c:when>
	            		<c:otherwise>In Active</c:otherwise>
	            	</c:choose>
	            </td>
	            <td class="grid_item">${busStop.createdBy.firstName} ${busStop.createdBy.lastName}</td>
	            <td>${busStop.createdDate.time}</td>
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
		         	<c:when test="${ fn:length(busStops)!=0 }">
		         		Displaying 1-${fn:length(busStops)} of ${fn:length(busStops)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      