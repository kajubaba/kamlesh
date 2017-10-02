<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="page_title"><span class="page_title_text">Manage Academic Year Hostel Fee</span></div>
<div class="working_area_spacer">

<div style="width:100%; text-align: right;margin-bottom: 15px;">
  <a href="javascript:void(0)" onclick="hostelInstallmentPopupView(${academicYearId})">Installment Setting</a>
  <a href="javascript:void(0)" onclick="hostelPopupView(${academicYearId})">Add Hostel(s)</a>
</div>
   <c:choose>
      	<c:when test="${null!=hostelFeeList && fn:length(hostelFeeList)>0 }">
      		<form id="hostelFeeForm">
	      		<table id="hostel_fee_settings_table" class="grid grid_color_theme_border">
			          <thead>
				          <tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Name</th>
				            <th></th>
				            <th class="grid_item">Hostel Fee</th>
				          </tr>
			          </thead>
			          <tbody>
				           <c:forEach var="hostelFee" items="${hostelFeeList}">
					         <tr class="grid_main_row">
					          	<td class="grid_item">${hostelFee.hostel.name}</td>
					            <td>${hostelFee.rs}</td>
					            <td class="grid_item"> 
					            	<input type="hidden" name="hostelFeeIdArr" value="${hostelFee.id}">
					            	<input type="text" value="${hostelFee.rs}" name="hostelFeeArr" class="numericHostelFee"/>  </td>
					          </tr>  
		          			</c:forEach>
	          		</tbody>
			     </table> 	
			     </form>
		     <div style="width: 100%; text-align: center;margin-top: 15px"> <input type="button" value="&nbsp;&nbsp;Cancel&nbsp;&nbsp;" class="button" onclick="getAcademicYearListView()"/> <input type="button" value="&nbsp;&nbsp;&nbsp;Save&nbsp;&nbsp;&nbsp;" class="button" onclick="updateAcademicYearHostelFee()"/></div>
      	</c:when>
      	<c:otherwise>
      	
      		<div style="width:100%; padding-top: 100px;text-align: center;">
      				 No settings are found for selected academic year<br/><br/>
      				 Click <a href="javascript:void(0)" onclick="hostelPopupView(${academicYearId})">here</a> to add hostel(s)
      		</div>
      	
      	</c:otherwise>
   </c:choose>
</div>
      