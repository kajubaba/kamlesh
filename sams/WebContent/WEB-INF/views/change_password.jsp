<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="working_area_spacer">
<form id="cpForm">
  <table width="450" cellspacing="15" cellpadding="0">
	                    <tr>
                          <td class="label"><span class="mandatory_mark">*</span> New Password</td>
                          <td class="data_field_width">
                          	<input type="password" id="newPassword" name="password" size="25" value="" maxlength="128"/> &nbsp;&nbsp;<span style="font-size: 9px">(min 8 char)</span>
                          </td>
                        </tr>
                      </table>
</form>	 
	 <div style="text-align: center;margin-top: 30px">
	  		<input type="button" value="Close" class="button" onclick="closeChangePasswordPopup()" style="width: 70px;height: 27px;font-weight: bold;margin-right: 10px"/> 
	  		<input type="button" value="Save" onclick="changePassword()" class="button" style="width: 70px;height: 27px;font-weight: bold;"/>
	  </div>
	 
</div>	
