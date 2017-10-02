<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Student Fee Transactions</span></div>
		<div class="working_area_spacer">
			Name : ${student.firstName} ${student.lastName}
				
			<div style="float: right;">
				<a href=' <c:url value="/fee/admissionrenewal/${student.studentId }"  /> '>Back</a>
			</div>
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(feeTransactions)!=0 }">
			         		Displaying 1-${fn:length(feeTransactions)} of ${fn:length(feeTransactions)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table class="grid grid_color_theme_border">
		          <tr class="grid_heading grid_heading_theme">
		            <td class="grid_item sortable">Transaction ID</td>
		            <td class="grid_item sortable">Reciept#</td>
		            <td class="grid_item sortable">Class</td>
		             <td class="grid_item sortable">Academic Year</td>
		             <td class="grid_item sortable">Inst#</td>
		            <td class="grid_item sortable">Rs.</td>
		            <td class="grid_item sortable">Date/Time</td>
		            <td class="grid_item sortable">User</td>
		            <td class="grid_item sortable">Comments</td>
		            
		          </tr>
		          
		           <c:forEach var="feeTransaction" items="${feeTransactions}" varStatus="rowCounter">
			         	<c:choose>
			          		<c:when test="${rowCounter.count % 2 != 0}">
			          			<tr class="grid_main_row">
			          		</c:when>
			          		<c:otherwise>
			    	      		<tr class="grid_alt_row">
				          	</c:otherwise>
			          	</c:choose>
			          
				           	<td class="grid_item">
				           	<a href="<c:url value="/fee/transaction/tr/${feeTransaction.id}" />" >${feeTransaction.transactionId}</a>
				            </td>
				            <td class="grid_item">${feeTransaction.recieptNo}</td>
				            
				            <td class="grid_item">
				        		${feeTransaction.studentClass.courseYear.course.displayName}, ${feeTransaction.studentClass.name} Sem
				            </td>
				             <td class="grid_item">
				        		${feeTransaction.academicYear.name}
				            </td>
				            <td class="grid_item">
				        		<c:choose>
				        			<c:when test="${feeTransaction.academicYearFeeInstallment==null }">
				        				${feeTransaction.customizeInstallment.installment.name}
				        			</c:when>
				        			<c:otherwise>
				        				${feeTransaction.academicYearFeeInstallment.installment.name}
				        			</c:otherwise>
				        		</c:choose>
				            </td>
				             <td class="grid_item">
				        		<c:set var="grandSum" value="${grandSum+feeTransaction.feeSum}"></c:set>
				        		${feeTransaction.feeSum}
				            </td>
				            <td class="grid_item">
				        		<fmt:formatDate type="both" timeStyle="short" value="${feeTransaction.transactionTime}" />
				            </td>
				            <td class="grid_item">
				        		${feeTransaction.user.firstName} ${feeTransaction.user.lastName}
				            </td>
				            <td class="grid_item">
				        		${feeTransaction.comments}
				            </td>
		           		</tr>
		           		</c:forEach>
		           	<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			             <td class="grid_item sortable"></td>
			            <td class="grid_item sortable">${grandSum}</td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
		          </tr>
		     </table>
		
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(feeTransactions)!=0 }">
			         		Displaying 1-${fn:length(feeTransactions)} of ${fn:length(feeTransactions)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>					
			
		
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>

<script type="text/javascript">

var course_url="<c:url value='/course' />";
var admission_list_base_url="<c:url value='/admission/list' />";
var admission_base_url="<c:url value='/admission' />";





</script>
        	 
       