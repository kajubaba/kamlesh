<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Manage Academic Year Bus Fee</span></div>
<div class="working_area_spacer">

<div style="width:100%; text-align: right;margin-bottom: 15px;">
  <span style="float: left;">
  	<input type="button" class="button" value="Export" onclick="exportBusFeeinExcel(${academicYearId})" />
  </span>
  
  <a href="javascript:void(0)" onclick="busInstallmentPopupView(${academicYearId})">Installment Setting</a>
  <a href="javascript:void(0)" onclick="busStopPopupView(${academicYearId})">Add bus stop(s)</a>
</div>
   <c:choose>
      	<c:when test="${null!=busFeeList && fn:length(busFeeList)>0 }">
      		<form id="busFeeForm">
	      		<table id="bus_fee_settings_table" class="grid grid_color_theme_border">
			          <thead>
				          <tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Name</th>
				            <th class="grid_item">Distance (km)</th>
				            <th></th>
				            <th class="grid_item">Bus Fee</th>
				          </tr>
			          </thead>
			          <tbody>
				           <c:forEach var="busFee" items="${busFeeList}">
					         <tr class="grid_main_row">
					          	<td class="grid_item">${busFee.busStop.name}</td>
					            <td class="grid_item">${busFee.busStop.distance}</td>
					            <td>${busFee.rs}</td>
					            <td class="grid_item"> 
					            	<input type="hidden" name="busFeeIdArr" value="${busFee.id}" />
					            	<input type="text" value="${busFee.rs}" name="busFeeArr" class="numericBusFee"/>
					             </td>
					          </tr>  
		          			</c:forEach>
	          		</tbody>
			     </table> 	
			     </form>
		     <div style="width: 100%; text-align: center;margin-top: 15px"> <input type="button" value="&nbsp;&nbsp;Cancel&nbsp;&nbsp;" class="button" onclick="getAcademicYearListView()"/> <input type="button" value="&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;" class="button" onclick="updateAcademicYearBusFee()"/></div>
      	</c:when>
      	<c:otherwise>
      	
      		<div style="width:100%; padding-top: 100px;text-align: center;">
      				 No settings are found for selected academic year<br/><br/>
      				 Click <a href="javascript:void(0)" onclick="busStopPopupView(${academicYearId})">here</a> to add bus stop(s)
      		</div>
      	
      	</c:otherwise>
   </c:choose>
</div>
      