<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<form action="create.spring" method="POST">
	<tr>
	<td>
		<input type="text" name="fname" placeholder="First Name">
	</td>
	<td>
		<%-- <form:errors path="fname" csstyle="color: red"></form:errors>	 --%>
	</td>
	</tr>
	<tr>
	<td>
		<input type="text" name="lname" placeholder="Last Name">
	</td>
	<td>
		<%-- <form:input path="lname" cssStyle="color: red"/> --%>
	</td>
	</tr>
	<tr>
	<td>
		<input type="text" name="mname" placeholder="Middle Name">
	</td>
	<td>
		<%-- <form:errors path="dob" cssStyle="color: red"></form:errors> --%>
	</td>
</tr>
	<tr>
	<td>
		<input type="email" name="email" placeholder="Email Address">
	</td>
	<td>
		<%-- <form:errors path="email" cssStyle="color: red" /> --%>
	</td>
	</tr>
	<tr>
	<td>
		<input type="date" name="dob" placeholder="Date Of Birth">
	</td>
	<td>
		<%-- <form:errors path="dob" cssStyle="color: red"></form:errors> --%>
	</td>
	</tr>
	<tr>
	<td>
		<input type="text" name="phone" placeholder="Phone Numeber">
	</td>
	<td>
		<%-- <form:errors path="dob" cssStyle="color: red"></form:errors> --%>
	</td>
</tr>
<tr>
	<td>
		<input type="text" name="ssn" placeholder="SSN">
	</td>
	<td>
		<%-- <form:errors path="dob" cssStyle="color: red"></form:errors> --%>
	</td>
</tr>
<tr>
	<td>
		<input type="password" name="pswd" placeholder="Password">
	</td>
	<td>
		<%-- <form:errors path="dob" cssStyle="color: red"></form:errors> --%>
	</td>
</tr>
<tr>
	<td>
		<input type="text" name="confirmPswd" placeholder="Confirm Password">
	</td>
	<td>
		<%-- <form:errors path="dob" cssStyle="color: red"></form:errors> --%>
	</td>
</tr>
<tr>
	<td colspan="2"><input type="submit" name="submit" value="Register"></td>
</tr>
</form>
</table>
</body>
</html>