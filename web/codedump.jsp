<div id="form-container">
	<center>
		<s:form method="post" action="loginprocess" border="0" id="form1">
			<div class="form-group">
				<s:textfield type="text" placeholder="Username" class="form-control" id="username" name="username" size='50'/><s:fielderror fieldName="userLogin"/>
			</div>
			<div class="form-group">
				<s:password type="password" placeholder="Password" class="form-control" id="password" name="userpass" size='50'/><s:fielderror fieldName="userPw"/>
			</div>
			
			<div>
				<s:submit class="btn btn-primary" type="submit" value="Login"/>
			</div>
		</s:form>
	</center>
</div>