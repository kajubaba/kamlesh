<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="modal-dialog" role="document">
<div class="modal-content">
	    <div class="modal-header">
        		<h4 class="modal-title"> <span class="glyphicon glyphicon-plus-sign"></span> 
        			Change enquiry owner
        		</h4>
      	</div>
      	<div class="modal-body">
      		<div class="form-group">
	            <label><span class="mandatory_mark">*</span> Owner :</label>
	            <select id="owners" class="form-control" >
		  					<c:forEach var="user" items="${users}">
								  		<option value="${user.id}">${user.firstName} ${user.lastName}</option>
							 </c:forEach>
		  		</select>	
	          </div>
	          <div class="form-group">
	            <label>Comments :</label>
	            <textarea class="form-control"  rows="5" cols="47" maxlength="1024" id="changeOwnerComments"></textarea>
	          </div>
      	</div>
      	<div class="modal-footer">
	  		<button type="button" class="btn btn-default"  onclick="closeEnqActivityPopup()"><span class="glyphicon glyphicon-plus-sign" ></span> Cancel</button>
	  		<button type="button" class="btn btn-primary"  onclick="changeEnqOwner()"><span class="glyphicon glyphicon-plus-sign" ></span> Change Owner</button>
      	</div>
</div>  	
</div>  	
  