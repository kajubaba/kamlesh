<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Hostel</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Hostel" class="button" onclick="getNewHostelView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(hostels)!=0 }">
		         		Displaying 1-${fn:length(hostels)} of ${fn:length(hostels)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="hostel_table" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">Name</th>
	            <th class="grid_item">Status</th>
	            <th class="grid_item">Created By</th>
	            <th></th>
	            <th class="grid_item">Created Date</th>
	          </tr>
         </thead>
         <tbody>
	         <c:forEach var="hostel" items="${hostels}">
	         	<tr class="grid_main_row">
	            <td class="grid_item"><a href="javascript:void(0)" onclick="loadHostelToUpdate(${hostel.id})"> ${hostel.name}</a></td>
	            <td class="grid_item">
	            	<c:choose>
	            		<c:when test="${hostel.active=='true'}">Active</c:when>
	            		<c:otherwise>In Active</c:otherwise>
	            	</c:choose>
	            </td>
	           
	            <td class="grid_item">${hostel.createdBy.firstName} ${hostel.createdBy.lastName}</td>
	            <td>${hostel.createdDate.time}</td>
	            <td class="grid_item">
	            	<fmt:formatDate pattern="dd-MMM-yyyy" value="${hostel.createdDate}" />
	            </td>
	          </tr>
	         </c:forEach>
         </tbody>
        </table>
       
        <br/>
       
        <div class="grid_info">
        	 <span style=" float: right;">
		        <c:choose>
		         	<c:when test="${ fn:length(hostels)!=0 }">
		         		Displaying 1-${fn:length(hostels)} of ${fn:length(hostels)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      