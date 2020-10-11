<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer</title>


<link href="css/jquery-ui.min.css" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>

</head>

<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		
	 <h2 class="page_header">Customer Edit Profile</h2>
		
	</div>
	<div class = "center">
		
		
	  <form id="customer-form" action="update_profile" method="post">
		

		
		<table class = "form">

			<tr>
				<td align = "right">Email : </td>
				<td align = "left"><label   id="email">${loggedCustomer.email } ( Cannot be changed )
					 </label></td>
				
			</tr>
			<tr>
				<td align ="right">Full Name :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="fullName" name="fullName" value = "${loggedCustomer.fullName }"/></td>
			</tr>
		
			<tr>
				<td align = "right">Phone Number :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="phoneNumber" name="phoneNumber" value = "${loggedCustomer.phoneNumper }"
					 /></td>
			</tr>
			<tr>
				<td align = "right">Address :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="address" name="address" value = "${loggedCustomer.address }" /></td>
			</tr>
			<tr>
				<td align = "right">City :</td>
				<td align = "left"><input class="form-input" type="text" size="45" id="city"
					name="city" value = "${loggedCustomer.city }" /></td>
			</tr>
			<tr>
				<td align = "right">Zip Code :</td>
				<td align = "left"><input class="form-input" type="text" size="45" id="zipCode"
					name="zipCode" value = "${loggedCustomer.zipCode }" /></td>
			</tr>
            <tr>
				<td align = "right">Country :</td>
				<td align ="left"><input class="form-input" type="text" size="45" id="country"
					name="country" value = "${loggedCustomer.country }" /></td>
			</tr>
			<tr>
			 <td colspan = "2"><i>(leave password field blank if you don`t want to change password)</i></td>
			</tr>
				<tr>
				<td align ="right">Password :</td>
				<td align = "left"><input class="form-input" type="password" size="45"
					id="password" name="password"  /></td>
			</tr>
			<tr>
				<td align = "right">Confirm Password :</td>
				<td align = "left"><input class="form-input" type="password" size="45"
					id="confPassword" name = "confPassword" />
					 </td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
					<button class="form-btn" type="submit">Save</button>
					<button class="form-btn" onClick="javascript:history.go(-1)">
						Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>

<script>

$(function() { 
	
	 $("#customer-form").validate({
		 rules: { 
			
			 fullName : "required" , 
			
			 confPassword : { 
				
				 equalTo : "#password"
			 },
			 phoneNumber : "required" , 
			 address : "required" , 
			 city : "required" , 
			 zipCode : "required" , 
			 country : "required"
			 
		 } , 
		 messages: { 
			
			 fullName :"Please enter full name." , 
			
			 confPassword : {
			
				 equalTo : "Wrong password"
			 } , 
			 phoneNumber : "Please enter phone number." , 
			 address : "Please enter address." , 
			 city : "Please enter city." , 
			 zipCode : "Please enter zip code." , 
			 country : "Please enter country." 
		 }
	 }) ; 
}) ; 

  
  
</script>
</html>