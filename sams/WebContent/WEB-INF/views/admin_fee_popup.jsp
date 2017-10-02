<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form id="feeSettingForm">
<div class="working_area_spacer">
	 
	 <c:set var="newadmnfeetotal" value="0" />
	 <c:set var="rglradmnfeetotal" value="0" />
	 <table class="grid grid_color_theme_border">
          
          <tr class="grid_heading grid_heading_theme">
	          <td class="grid_item">Fee Head</td>
	          <td class="grid_item">New Addmission</td>
	          <td class="grid_item">Regular Addmission</td>
          </tr>
           		<c:forEach var="academicyearFeeDetailForm" items="${academicyearFeeDetailForms}" varStatus="rowCounter">
           			<c:choose>
	           			<c:when test="${rowCounter.count % 2 != 0}" >
					        <tr class="grid_main_row_plain">
					    </c:when>
					    <c:otherwise>
					    	<tr class="grid_alt_row_plain">
						</c:otherwise>
					    </c:choose>
	           				<input type="hidden" name="academicyearFeeDetailForms['${rowCounter.index}'].feeHeadId" value="${academicyearFeeDetailForm.feeHeadId}" />
	           				<td class="grid_item">${academicyearFeeDetailForm.feeHeadName}</td>
	           				<td class="grid_item">
	           					<input type="hidden" name="academicyearFeeDetailForms['${rowCounter.index}'].newAdmissionFeeForm.id" value="${academicyearFeeDetailForm.newAdmissionFeeForm.id}"/>
	           					<input type="text" maxlength="6" name="academicyearFeeDetailForms['${rowCounter.index}'].newAdmissionFeeForm.amount" value="${academicyearFeeDetailForm.newAdmissionFeeForm.amount}" class="newadmnfee numeric" onkeyup="sumNewAdmissionFee()" />
	           					<c:set var="newadmnfeetotal" value="${newadmnfeetotal + academicyearFeeDetailForm.newAdmissionFeeForm.amount}" />
	           				</td>
	           				<td class="grid_item">
	           					<input type="hidden" name="academicyearFeeDetailForms['${rowCounter.index}'].regularAdmissionFeeForm.id" value="${academicyearFeeDetailForm.regularAdmissionFeeForm.id}"/>
	           					<input type="text" maxlength="6" name="academicyearFeeDetailForms['${rowCounter.index}'].regularAdmissionFeeForm.amount" value="${academicyearFeeDetailForm.regularAdmissionFeeForm.amount}" class="rglradmnfee numeric" onkeyup="sumRegularAdmissionFee()" >
	           					<c:set var="rglradmnfeetotal" value="${rglradmnfeetotal + academicyearFeeDetailForm.regularAdmissionFeeForm.amount}" />
	           				</td>
	           			</tr>	
    				</c:forEach>
			<tr class="grid_heading grid_heading_theme">
	          <td class="grid_item" style="font-weight: bold;">Total</td>
	          <td class="grid_item" id="newadmntotal" style="font-weight: bold;">${newadmnfeetotal}</td>
	          <td class="grid_item" id="rglradmntotal" style="font-weight: bold;">${rglradmnfeetotal}</td>
          </tr>
	 </table>	
	 <div style="text-align: center;margin-top: 30px">
	  		<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
	  		<input type="button" value="Save" onclick="saveFeeChanges()" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	  </div>	
</div>	
<input type="hidden" value="${courseYearSettingId}" name="courseYearSettingId">

</form>