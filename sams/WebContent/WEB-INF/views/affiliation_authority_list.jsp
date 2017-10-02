<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Affiliation Authority</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Authority" class="button" onclick="getNewAffiliationAuthorityView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(affiliationAuthorities)!=0 }">
		         		Displaying 1-${fn:length(affiliationAuthorities)} of ${fn:length(affiliationAuthorities)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table class="grid grid_color_theme_border">
          <tr class="grid_heading grid_heading_theme">
           
            <td class="grid_item sortable">Affiliation Authority</td>
            <td class="grid_item sortable">Status</td>
            <td class="grid_item sortable">Created By</td>
            <td class="grid_item sortable">Created Date</td>
            <td class="grid_item sortable">Modified By</td>
            <td class="grid_item sortable">Modified Date</td>
            
          </tr>
         
         <c:forEach var="affiliationAuthority" items="${affiliationAuthorities}" varStatus="rowCounter">
         	<c:choose>
          		<c:when test="${rowCounter.count % 2 != 0}">
          			<tr class="grid_main_row">
          		</c:when>
          		<c:otherwise>
    	      		<tr class="grid_alt_row">
	          	</c:otherwise>
          	</c:choose>
            <td class="grid_item"><a href="javascript:void(0)" onclick="loadAffiliationAuthoriyToUpdate(${affiliationAuthority.id})"> ${affiliationAuthority.displayName}</a> </td>
            <td class="grid_item">
            	<c:choose>
            		<c:when test="${affiliationAuthority.active=='true'}">Active</c:when>
            		<c:otherwise>In Active</c:otherwise>
            	</c:choose>
            </td>
            <td class="grid_item">${affiliationAuthority.createdBy.firstName} ${affiliationAuthority.createdBy.lastName}</td>
            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${affiliationAuthority.createdDate}" /></td>
            <td class="grid_item">${affiliationAuthority.modifiedBy.firstName} ${affiliationAuthority.modifiedBy.lastName}</td>
            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${affiliationAuthority.modifiedDate}" /></td>
            			
          </tr>
         </c:forEach>
        </table>
       
        <br/>
       
        <div class="grid_info">
        	<span style=" float: right;">
        		<c:choose>
		         	<c:when test="${ fn:length(affiliationAuthorities)!=0 }">
		         		Displaying 1-${fn:length(affiliationAuthorities)} of ${fn:length(affiliationAuthorities)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
	         </span>
	    </div>
</div>
      