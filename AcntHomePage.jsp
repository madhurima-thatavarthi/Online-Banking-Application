<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Account Home Page</title>
		
		<style>
			marquee
			{
				font-size:40px;
				color:green;
				margin-left:270px;
			}
			#logout
			{
				position:absolute;
				top:50px;
				right:30px;
			}
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
		<script>
			/* It will check the fields of transfer form are filled or not */
			function transVal()
			{
				var acntno=document.LoginForm.acnt.value;
				var amt=document.LoginForm.amount.value;
				if(acntno=="" || amt=="")
					alert("Fields should not be empty");
				return false;
			}
		</script>
	</head>
	<body>
	
		<center> 
			<h2 style="color:green">ONLINE BANKING APPLICATION</h2>
			<hr color="green" width=25%>
			<br/><br/><br/>
		</center>
		
		<form name="LogoutForm" action="LogoutBackend" method="post">
			<button type="submit" id="logout" value="<%= request.getParameter("uname")%>" name="userid">Logout</button>
		</form>
		
		<marquee width="60%" direction="left" height="100px">Hello <b><%= request.getParameter("uname")%></b>!Welcome...!!!</marquee>
		
		<div>
			<form name="LoginForm" action="Transfer" method="Post">
				<table align="center" style="padding:27px 15px" cellpadding="2px" cellspacing="5px">
					<tr>
						<td>Acnt No.</td>
						<td><input type="text" name="acnt"></td>
					</tr>
					<tr>
						<td>Amount</td>
						<td><input type="text" name="amount"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><button type="submit" value="<%= request.getParameter("uname")%>" style="margin:0 auto;" name="userid" onclick="transVal();">Transfer</button></td> 				
					</tr>
				</table>
			</form>
		</div>
		<br/><br/><br/><br/><br/>
		
	</body>
</html>



