<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


		<table class = "form" align = "center">

			<tr>
				<td align = "right" >Email : </td>
				<td align = "left"><input class="form-input" type="text" size="45" id="email"
					name="email" value="${customer.email}" /></td>
			</tr>
			<tr>
				<td align = "right">First Name :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="firstname" name="firstname" value="${customer.firstname}" /></td>
			</tr>
			<tr>
				<td align = "right">Last Name :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="lastname" name="lastname" value="${customer.lastname}" /></td>
			</tr>
			<tr>
				<td align = "right">Password :</td>
				<td align = "left"><input class="form-input" type="password" size="45"
					id="password" name="password"  /></td>
			</tr>
			<tr>
				<td align = "right">Confirm Password :</td>
				<td align = "left"><input class="form-input" type="password" size="45"
					name="confPassword" 
					 /></td>
			</tr>
			<tr>
				<td align = "right">Phone Number :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="phoneNumber" name="phoneNumber"
					value="${customer.phoneNumber }" /></td>
			</tr>
			<tr>
				<td align = "right">Address Line 1 :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="address1" name="address1" value="${customer.addressLine1 }" /></td>
			</tr>
			<tr>
				<td align = "right">Address Line 2 :</td>
				<td align = "left"><input class="form-input" type="text" size="45"
					id="address2" name="address2" value="${customer.addressLine2 }" /></td>
			</tr>
			<tr>
				<td align = "right">City :</td>
				<td align = "left"><input class="form-input" type="text" size="45" id="city"
					name="city" value="${customer.city }" /></td>
			</tr>
			<tr>
				<td align = "right">State :</td>
				<td align = "left"><input class="form-input" type="text" size="45" id="state"
					name="state" value="${customer.state }" /></td>
			</tr>
			<tr>
				<td align = "right">Zip Code :</td>
				<td align = "left"><input class="form-input" type="text" size="45" id="zipCode"
					name="zipCode" value="${customer.zipCode }" /></td>
			</tr>
            <tr>
				<td align = "right">Country :</td>
				<td align = "left">
				<select name = "country" id = "country" >
				  <c:forEach var = "country" items = "${countries }">
				    
				   <option value = "${country.value }" ${fn:toLowerCase(customer.country) eq fn:toLowerCase(country.value )   ? "selected = 'selected'" : "" }>${country.key }</option>
				  </c:forEach>
				</select>
				
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
		

  