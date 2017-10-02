<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Fee Head</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Fee Head" class="button" onclick="getNewFeeHeadView()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(feeHeads)!=0 }">
		         		Displaying 1-${fn:length(feeHeads)} of ${fn:length(feeHeads)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table class="grid grid_color_theme_border">
          <tr class="grid_heading grid_heading_theme">
           
            <td class="grid_item sortable">Fee Head</td>
            <td class="grid_item sortable">Active</td>
            <td class="grid_item sortable">Created By</td>
            <td class="grid_item sortable">Created Date</td>
            <td class="grid_item sortable">Modified By</td>
            <td class="grid_item sortable">Modified Date</td>
            
          </tr>
         
         <c:forEach var="feeHead" items="${feeHeads}" varStatus="rowCounter">
         	<c:choose>
          		<c:when test="${rowCounter.count % 2 != 0}">
          			<tr class="grid_main_row">
          		</c:when>
          		<c:otherwise>
    	      		<tr class="grid_alt_row">
	          	</c:otherwise>
          	</c:choose>
            <td class="grid_item"><a href="javascript:void(0)" onclick="loadFeeHeadToUpdate(${feeHead.id})"> ${feeHead.name}</a> </td>
            <td class="grid_item">
            	<c:choose>
            		<c:when test="${feeHead.active=='true'}">Active</c:when>
            		<c:otherwise>In Active</c:otherwise>
            	</c:choose>
            </td>
            <td class="grid_item">${feeHead.createdBy.firstName} ${feeHead.createdBy.lastName}</td>
            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${feeHead.createdDate}" /></td>
            <td class="grid_item">${feeHead.modifiedBy.firstName} ${feeHead.modifiedBy.lastName}</td>
            <td class="grid_item"><fmt:formatDate type="both" timeStyle="short" value="${feeHead.modifiedDate}" /></td>
            			
          </tr>
         </c:forEach>
        </table>
       
        <br/>
       
        <div class="grid_info">
        	<span style=" float: right;">
        		<c:choose>
		         	<c:when test="${ fn:length(feeHeads)!=0 }">
		         		Displaying 1-${fn:length(feeHeads)} of ${fn:length(feeHeads)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
	         </span>
	    </div>
</div>
      