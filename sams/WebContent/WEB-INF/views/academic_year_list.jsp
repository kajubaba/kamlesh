<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Academic Year</span></div>
<div class="working_area_spacer">
       
        <!-- <div class="add_new">
          <input type="button" value="New Academic Year" class="button" onclick="getNewAcademicYearView()" />
        </div> -->
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(academicYears)!=0 }">
		         		Displaying 1-${fn:length(academicYears)} of ${fn:length(academicYears)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table class="grid grid_color_theme_border">
          <tr class="grid_heading grid_heading_theme">
           
            <td class="grid_item sortable">Academic Year</td>
            <td class="grid_item sortable">Status</td>
            <td class="grid_item sortable">Created By</td>
            <td class="grid_item sortable">Created Date</td>
            <td class="grid_item sortable">Modified By</td>
            <%-- 
            <td class="grid_item sortable"></td>
            --%>
           <!--  <td class="grid_item sortable"></td>
            <td class="grid_item sortable"></td> -->
            <td class="grid_item sortable"></td>
            
          </tr>
         
         <c:forEach var="academicYear" items="${academicYears}" varStatus="rowCounter">
         	<c:choose>
          		<c:when test="${rowCounter.count % 2 != 0}">
          			<tr class="grid_main_row">
          		</c:when>
          		<c:otherwise>
    	      		<tr class="grid_alt_row">
	          	</c:otherwise>
          	</c:choose>
            <td class="grid_item">${academicYear.name}</td>
            <td class="grid_item">${academicYear.status}</td>
            <td class="grid_item">${academicYear.createdBy.firstName} ${academicYear.createdBy.lastName}</td>
            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${academicYear.createdDate}" /></td>
            <td class="grid_item">${academicYear.modifiedBy.firstName} ${academicYear.modifiedBy.lastName}</td>
            
            <%-- 
            
            <td class="grid_item">
            	<c:if test="${academicYear.status=='active'}">
            		<a href="javascript:void(0)" onclick="hostelFeeSettingView(${academicYear.id})">Hostel Fee Settings</a>
            	</c:if>
            </td>
            --%>
            <%-- <td class="grid_item">
            	<a href="javascript:void(0)" onclick="listMiscActivities(${academicYear.id})">Misc Act</a>
            </td>
            <td class="grid_item">
            	<a href="javascript:void(0)" onclick="busFeeSettingView(${academicYear.id})">Bus Fee</a>
            </td> --%>
            <td class="grid_item">
					<a href="javascript:void(0)" onclick="aySettingDefaultPage(${academicYear.id})">Academic Fee</a> 		
             </td>
            			
          </tr>
         </c:forEach>
        </table>
       
        <br/>
       
        <div class="grid_info">
        	<span style=" float: right;">
        		<c:choose>
		         	<c:when test="${ fn:length(academicYears)!=0 }">
		         		Displaying 1-${fn:length(academicYears)} of ${fn:length(academicYears)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
	         </span>
	    </div>
</div>
      