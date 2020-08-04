<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Online Banking Login</title>
		<style>
			div
			{ 	margin:0 auto;
				text-align:center;
				top: 20px;
				left: 100px;
				width:300px;
				height:150px;
				margin-top:20px;
				border: 2px solid green;
				border-radius: 20px;
			}
		</style>
	</head>
	<body>
		<center> 
			<h2 style="color:green">ONLINE BANKING APPLICATION</h2>
			<hr color="green" width=25%>
			<br/><br/><br/><br/><br/>
			
		</center>
		<div>
			<!-- Login Form -->
			<form name="LoginForm" action="LoginBackEnd" method="Post">
				<table align="center" style="padding:27px 15px" cellpadding="2px" cellspacing="5px">
					<tr>
						<td>Username</td>
						<td><input type="text" name="uname"></td>
					</tr>
					
					<tr>
						<td>Password</td>
						<td><input type="password" name="pwd"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="Login" onClick="fev();"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
	<script>
		/*To validate the login form fields at client side whether the fields are filled or not*/
		function fev()
		{
			var name=document.LoginForm.uname.value;
			var pwd=document.LoginForm.pwd.value;
			if(name=="" || pwd=="")
				alert("Fields should not be empty");
			return false;
		}
	</script>
</html>