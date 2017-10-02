<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="popup_header">${courseYearSetting.courseYear.course.displayName} ${courseYearSetting.courseYear.name} Year Properties</div>
<div class="working_area_spacer">
	<form id="courseYearSettingForm">
	  <table width="420" cellspacing="15" cellpadding="0">
		    <tr>
	    	    <td class="label">Status :</td>
	            <td class="data_field_width">
	               	<select name="active">
	               		<option value="true" <c:if test="${courseYearSetting.active==true}"> selected="selected" </c:if>>Active</option>
	               		<option value="false" <c:if test="${courseYearSetting.active==false}"> selected="selected" </c:if>>In-Active</option>
	               	</select>
	            </td>
	        </tr>
	        <tr>
	          	<td class="label"> Year/Sem </td>
	            <td class="data_field_width">
	                  <select name="courseYearTypeId">
	               		 <c:forEach var="type" items="${types}">
	               		 	<option value="${type.id}" <c:if test="${courseYearSetting.courseYearType.id==type.id}"> selected="selected" </c:if>>${type.name}</option>
	               		 </c:forEach>
	               	</select>
	            </td>
	        </tr>
	        <tr>
	          	<td class="label"> Intake</td>
	            <td class="data_field_width">
	                  <input type="hidden" name="courseYearSettingId" value="${courseYearSetting.id}"/>
	                  <input type="text" name="intake" value="${courseYearSetting.intake}"/>
	            </td>
	        </tr>
	    </table>
	    
	</form>	 
	<div style="text-align: center;margin-top: 30px">
		<input type="button" value="Close" class="button" onclick="closeFeePopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
		<input type="button" value="Save" onclick="saveCourseYearSettingProp()" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	</div>
</div>	
