<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title"><span class="page_title_text">Admissions >> Bus Stop Wise </span></div>
		<div class="working_area_spacer">
			<div class="search_param">
				<form action="<c:url value='/admission/report/busstopwise' />" method="get">
					<table width="300px">
						<tr>
							<td class="bold">Academic Year: </td>
							<td> 
								<select name="academicYear">
										<c:forEach var="academicYear" items="${academicYears}">
											<option value="${academicYear.id}" <c:if test="${academicYearId==academicYear.id}">selected="selected" </c:if>>${academicYear.name}</option>
										</c:forEach>
								</select> 
							 </td>
							
							<td> <input type="submit" class="button" value="Search"> </td>
							
						</tr>	
					</table>
				</form>
			</div>
			
			<div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(admissions)!=0 }">
			         		Displaying 1-${fn:length(admissions)} of ${fn:length(admissions)}
			         	</c:when>
			         	<c:otherwise>Displaying 0-0 of 0</c:otherwise>
			         </c:choose> 
			     </span>
			</div>	
    		
          	 <table id="bus_stop_tbl" class="grid grid_color_theme_border">
		          <thead>
		          		
		          		<tr class="grid_heading grid_heading_theme">
				            <th class="grid_item">Bus Stop</th>
				            <th class="grid_item">Distance (Km.)</th>
				            <th class="grid_item"></th>
				            <th class="grid_item">No Of Admissions</th>
				            
		         		 </tr>
		          </thead>
		          <tbody>
		          
		           <c:forEach var="admission" items="${admissions}">
				           <tr class="grid_main_row">
				           	<td class="grid_item">
				        		${admission.busStop.name}
				            </td>
				            <td class="grid_item">
				        		${admission.busStop.distance}
				            </td>
				            <td class="grid_item">
				        		${admission.admissionCount}
				            </td>
				            <td class="grid_item">
				        		<c:set var="admissionCount" value="${admissionCount+admission.admissionCount}"></c:set>
				        		<a href=" <c:url value='/admission/report/busstopwise/admns?ay=${academicYearId}&bs=${admission.busStop.id}' />">${admission.admissionCount}</a>
				            </td>
		           		</tr>
		           		</c:forEach>
		           <tfoot>
		           	<tr class="grid_heading grid_heading_theme">
			            <td class="grid_item sortable">Total</td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable"></td>
			            <td class="grid_item sortable">${admissionCount}</td>
			            
		          </tr>
		          </tfoot>
		          </tbody>
		     </table>
		
		     <div class="grid_info">
	             <span style=" float: right;">
			         <c:choose>
			         	<c:when test="${ fn:length(admissions)!=0 }">
			         		Displaying 1-${fn:length(admissions)} of ${fn:length(admissions)}
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

$(document).ready(function() {
    $('#bus_stop_tbl').dataTable({
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bInfo": false,
        "bAutoWidth": false,
        
        "aoColumns": [
                      null,
                      null,
                      {"bVisible": false},
                      {"iDataSort": 2}
                      
                     ]
    } );
} );

</script>
        	 
       