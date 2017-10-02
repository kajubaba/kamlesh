<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<jsp:include page="sams_header.jsp"/>

<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
	<div class="page_title"><span class="page_title_text">Misc Program Home</span></div>
		<div class="working_area_spacer">
			
			<table cellpadding="10" align="center">
				<tr>
				<td>
					<a href="<c:url value='/miscfee' />">Misc Program - Fee Payment</a>
					
				</td></tr>
				<tr><td><a href="<c:url value='/miscfee/report/mawise/default' />">Paid Fee - Misc Program Wise</a></td></tr>
				<tr><td><a href="<c:url value='/miscfee/report/trview' />">Misc Program - Fee Transactions</a></td></tr>
				<tr><td><a href="<c:url value='/miscfee/report' />">Misc Program - Paid/Due Fee Summary</a></td></tr>
			</table>
			
			
			
    		
          	 	
			
		
		</div>
	</div>
</div>	

<jsp:include page="sams_footer.jsp"/>
<script type="text/javascript">




</script>

        	 
       