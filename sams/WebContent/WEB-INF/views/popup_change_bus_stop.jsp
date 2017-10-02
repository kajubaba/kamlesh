<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="popup_header">Change Bus Stop</div>
<div class="working_area_spacer">
	<form id="changeBusStopForm">
	  <div id="changeBusStopMsg" style="color:#FF0000;">${msg}</div>
	  <table width="420" cellspacing="15" cellpadding="0">
		    <tr>
	    	    <td class="label">Current Bus Stop : </td>
	            <td class="data_field_width">
	               	${currentBusStop.name}
	            </td>
	        </tr>
	        <tr>
	          	<td class="label"><span class="mandatory_mark">*</span> New Bus Stop : </td>
	            <td class="data_field_width">
	                  <select id="newBusStopId" onchange="verifyBusStop()">
	                  	<option></option>
	                  	<c:forEach var="busStop" items="${busStops}">
	                  		<option value="${busStop.id}">${busStop.name}</option>
	                   	</c:forEach>
	                  </select>
	            </td>
	        </tr>
	    </table>
	</form>	 
	<div style="text-align: center;margin-top: 30px">
		<input type="button" value="Close" class="button" onclick="closeChangeBusStopPopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
		<input type="button" id="saveBusStop" value="Save" onclick="changeBusStopPopup(${studentId})" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	</div>
</div>	
