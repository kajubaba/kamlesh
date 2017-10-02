<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<jsp:include page="sams_header.jsp" />
<div id="content_area">
	<div id="wider_working_area" class="color_theme_border">
		<div class="page_title">
			<span class="page_title_text">Academic Year class Sequence</span>
		</div>
		<div id="admission_container" class="working_area_spacer">
			<table id="class_seq_table" class="grid grid_color_theme_border">
				<thead>
					<tr class="grid_heading grid_heading_theme">
						<th class="grid_item">Class Name</th>
						<th class="grid_item">Order</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="clazz" items="${classes}">
						<tr class="grid_main_row">
							<td class="grid_item"></td>
							<td class="grid_item"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<jsp:include page="sams_footer.jsp" />
<script type="text/javascript">
<!--
	
//-->
</script>
