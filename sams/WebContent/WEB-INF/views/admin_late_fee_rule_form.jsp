<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="page_title"><span class="page_title_text">Add/Update Late Fee Rule</span></div>
              <div class="working_area_spacer">
               
               
                  <div class="form_container">
                    <form id="lateFeeRuleForm">
                    	<input type="hidden" id="lateFeeRuleId" name="id" value="${lateFeeRule.id}">
                      <div>Name : <input type="text" name="name" value="${lateFeeRule.name}"> </div>
                      <table id="ruleTable" width="100%" cellspacing="15" cellpadding="0">
                        
                       
                       <c:choose>
                       		<c:when test="${rules==null}">
                       			<tr>
		                          <td >From </td>
		                          <td><input type="text" id="from0" name="lateFeeRules[0].from" size="5" class="numeric" value="${rule.from }"/></td>
		                          <td >To</td>
		                          <td><input type="text" id="to0" name="lateFeeRules[0].to" size="5" class="numeric" value="${rule.to }"/></td>
		                          <td>Days</td>
		                          <td ><input type="text" id="fineAmount0" name="lateFeeRules[0].fineAmount" size="5" class="numeric" value="${rule.fineAmount}"/></td>
		                          <td>
		                          	<select id="fineRule0" name="lateFeeRules[0].fineRule">
		                          		<option value="rs"  <c:if test="${'rs'==rule.fineRule}">selected="selected"</c:if>>Rs</option>
		                          		<%-- 
		                          		<option value="%_inst" <c:if test="${'%_inst'==rule.fineRule}">selected="selected"</c:if>>% Of Installment Total</option>
		                          		<option value="%_inst_due" <c:if test="${'%_inst_due'==rule.fineRule}">selected="selected"</c:if>>% Of Installment Payable</option>
		                          		--%>
		                          	</select>
		                          </td>
		                          <td>
		                          	<select id="finePeriod0" name="lateFeeRules[0].finePeriod">
		                          		<option value="per_day" <c:if test="${'per_day'==rule.finePeriod}">selected="selected"</c:if>>Per Day</option>
		                          		<option value="period" <c:if test="${'period'==rule.finePeriod}">selected="selected"</c:if>>For Entire Period</option>
		                          	</select>
		                          </td>
		                          <td>
		                          	<input type="button" value="&nbsp;&nbsp;add&nbsp;&nbsp;" class="button" onclick="AddMoreRule()"/>
		                          </td>
                        	</tr>
                       		</c:when>
                       		<c:otherwise>
                       			 <c:forEach var="rule" items="${rules}" varStatus="rowCounter">
                        	<tr>
		                          <td >From </td>
		                          <td><input type="text" id="from${rowCounter.count-1}" name="lateFeeRules[${rowCounter.count-1}].from" size="5" class="numeric" value="${rule.from }"/></td>
		                          <td >To</td>
		                          <td><input type="text" id="to${rowCounter.count-1}" name="lateFeeRules[${rowCounter.count-1}].to" size="5" class="numeric" value="${rule.to }"/></td>
		                          <td>Days</td>
		                          <td ><input type="text" id="fineAmount${rowCounter.count-1}" name="lateFeeRules[${rowCounter.count-1}].fineAmount" size="5" class="numeric" value="${rule.fineAmount}"/></td>
		                          <td>
		                          	<select id="fineRule${rowCounter.count-1}" name="lateFeeRules[${rowCounter.count-1}].fineRule">
		                          		<option value="rs"  <c:if test="${'rs'==rule.fineRule}">selected="selected"</c:if>>Rs</option>
		                          		<option value="%_inst" <c:if test="${'%_inst'==rule.fineRule}">selected="selected"</c:if>>% Of Installment Total</option>
		                          		<option value="%_inst_due" <c:if test="${'%_inst_due'==rule.fineRule}">selected="selected"</c:if>>% Of Installment Payable</option>
		                          	</select>
		                          </td>
		                          <td>
		                          	<select id="finePeriod${rowCounter.count-1}" name="lateFeeRules[${rowCounter.count-1}].finePeriod">
		                          		<option value="per_day" <c:if test="${'per_day'==rule.finePeriod}">selected="selected"</c:if>>Per Day</option>
		                          		<option value="period" <c:if test="${'period'==rule.finePeriod}">selected="selected"</c:if>>For Entire Period</option>
		                          	</select>
		                          </td>
		                          <td>
		                          		<input type="button" value="&nbsp;&nbsp;add&nbsp;&nbsp;" class="button" onclick="AddMoreRule()"/>
		                          </td>
                        	</tr>
                        </c:forEach>
                        
                       		
                       		</c:otherwise>
                       </c:choose>
                       
                       
                        
                      </table>
                      <div style="width: 100%;text-align: center;">
                      		<input type="button" value="&nbsp;&nbsp;Cancel&nbsp;&nbsp;" class="button" onclick="listLateFeeRules()"/>
                      		<input type="button" value="&nbsp;&nbsp;Save Rule&nbsp;&nbsp;" class="button" onclick="saveRule()"/>
                      </div>
                    </form>
                  </div>
               
              </div>
