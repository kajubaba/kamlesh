<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Late Fee Rules</span></div>
<div class="working_area_spacer">
       
        <div class="add_new">
          <input type="button" value="New Rule" class="button" onclick="newLateFeeRule()" />
        </div>
       
        <div class="grid_above_gap"></div>
        <div class="grid_info">
             <span style=" float: right;">
		         <c:choose>
		         	<c:when test="${ fn:length(rules)!=0 }">
		         		Displaying 1-${fn:length(rules)} of ${fn:length(rules)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
		</div>
        
        <br/>
	    
	    <table id="role_listing_table" class="grid grid_color_theme_border">
          <thead>
	          <tr class="grid_heading grid_heading_theme">
	            <th class="grid_item">Rule</th>
	            <th class="grid_item">Description</th>
	            <th class="grid_item">Status</th>
	            <th class="grid_item">Created By</th>
	          </tr>
	      </thead>
         <tbody>
	         <c:forEach var="rule" items="${rules}">
	         	<tr class="grid_main_row">
		          	<td class="grid_item"><a href="javascript:void(0)" onclick="getLateFeeRule(${rule.id})">${rule.name} </a> </td>
		            <td class="grid_item">${rule.rule} </td>
		            <td class="grid_item">${rule.active}</td>
		            <td class="grid_item">${rule.createdBy.firstName}${rule.createdBy.lastName}</td>
	          </tr>
	         </c:forEach>
         </tbody>
        </table>
        <br/>
        <div class="grid_info">
        	 <span style=" float: right;">
		        <c:choose>
		         	<c:when test="${ fn:length(rules)!=0 }">
		         		Displaying 1-${fn:length(rules)} of ${fn:length(rules)}
		         	</c:when>
		         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
		         </c:choose> 
		     </span>
	    </div>
</div>
      