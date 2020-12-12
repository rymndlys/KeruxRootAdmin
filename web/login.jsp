
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>KERUX</title>

<style>


</style>
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>



	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url(images/docbg.png);">
					<span class="login100-form-title-1">
						Sign In
					</span>
				</div>

				<s:form cssClass="login100-form validate-form" action="loginprocess" theme="simple">
					<div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">Username</span>
						<s:textfield cssClass="input100" type="text" name="username" placeholder="Enter username" maxlength="50"/>
						<span class="focus-input100"><s:fielderror fieldName="username"/></span>
					</div>

					<div class="wrap-input100 validate-input m-b-18" data-validate = "Password is required">
						<span class="label-input100">Password</span>
						<s:password cssClass="input100" type="password" name="userpass" placeholder="Enter password" maxlength="50"/>
						<span class="focus-input100"><s:fielderror fieldName="userpass"/></span>
					</div>

					<div class="flex-sb-m w-full p-b-30">
						<font color="#d0262a">
							<s:if test="hasActionErrors()">
									<s:actionerror/>
							</s:if>
						</font>
					</div>

					<div class="container-login100-form-btn">
						<s:submit cssClass="login100-form-btn" type="submit" value="Login"/>
						
					</div>
					
					
				</s:form>

				<div class="container-login100-form-apkbtn">
                    <a href ="/Sympl/apk/qmfinal.apk"><button class="login100-apk-btn" type="button"/>Download the Queue Manager APK</button></a>
                    <a href ="/Sympl/apk/adminfinal.apk"><button class="login100-apk-btn" type="button"/>Download the Administrator APK</button></a>
                </div>
			</div>
				
		</div>
		
		<%-- <div class="container-login200">
			<div class="wrap-login200">
				<div class="login200-form-title"">
					<span class="login200-form-title-1">
						<div class="container-login100-form-btn">
							<s:submit cssClass="login100-apk-btn" type="submit" value="Download the APK Here"/>
						</div>
						
						<div class="container-login100-form-btn">
							<s:submit cssClass="login100-apk-btn" type="submit" value="Download the APK Here"/>
						</div>
					</span>
				</div>
			</div>
		</div> --%>
		
	</div>
	

			
	

<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>